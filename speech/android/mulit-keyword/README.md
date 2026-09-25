# Multi-keyword spotting on Android Automotive OS (Azure Speech Embedded SDK)

A minimal AAOS app (Kotlin, Jetpack Compose) that shows **two wake words armed in parallel**, fully offline:

| Keyword | Effect |
|---|---|
| **"Hey Jarvis"** | Enters the simulated **LISTENING** mode. Saying it again extends the timeout. |
| **"Hold on"** | Stops LISTENING and returns to **IDLE**. It is ignored while IDLE. |

LISTENING also turns itself off after `listeningTimeoutSeconds` (15 s by default).

## How it works

```
MainActivity (Compose) ──bind──► KeywordService (foreground service, type=microphone)
        ▲ StateFlow<UiState>              │
        └──────────────────────────────── KeywordEngine
                                            ├─ FromConfigEngine  (default)  1 recognizer, 2 wake words
                                            └─ MultiTableEngine  (fallback) 2 recognizers, 2 .table files, shared mic
                                          ListeningStateMachine · ProcessMetrics
```

- **`FROM_CONFIG` (default).** `EmbeddedSpeechConfig.fromPath(modelDir)`, then `setKeywordRecognitionModel(name, license)`, then
  `KeywordRecognitionModel.fromConfig(cfg, listOf("Hey Jarvis", "Hold on"))`, then **one** `KeywordRecognizer` on the
  default mic. The app works out which keyword fired from `result.text`.
- **`MULTI_TABLE` (fallback).** Two `KeywordRecognizer`s, each with its own Speech Studio `.table` model, sharing a single
  `AudioConfig.fromDefaultMicrophoneInput()`. The app knows the keyword from which recognizer fired.
- `recognizeOnceAsync` is single-shot, so every recognizer runs a dedicated thread that **re-arms after each result**
  (`kws/RearmingRecognizer.kt`).
- Every SDK object (recognizers, models, `AudioConfig`, `EmbeddedSpeechConfig`) is closed in reverse creation order
  when you stop, when an error occurs, and in `onDestroy`.

Source is in `src/app/src/main/java/com/hoffe86/speechmultikeyword/` in three packages: `kws/` (engines and domain),
`service/` and `ui/`.

## Prerequisites

- **Limited Access to Embedded Speech** (https://aka.ms/csgate-embedded-speech). The embedded speech model files plus the
  keyword model **name and license** come from that onboarding.
- An `arm64-v8a` AAOS target. On an Apple Silicon Mac, use the image
  `system-images;android-35-ext15;android-automotive;arm64-v8a`, with **emulator ≥ 36.6.11** (fixes macOS audio distortion).
  - Run the emulator standalone and enable **Extended controls → Microphone → "Virtual microphone uses host audio input"**.
    It is **off by default**, and while it's off you get silent "no detection".
  - Check real hardware with `adb shell getprop ro.product.cpu.abilist`.
- SDK: `com.microsoft.cognitiveservices.speech:client-sdk-embedded:1.51.2`. Don't add `client-sdk`, which is cloud-only.

## Configure the device (secrets are never committed or bundled)

The app reads `kws-config.json` at runtime from its external files dir. Both it and the models are gitignored.

```jsonc
{
  "engine": "FROM_CONFIG",               // optional: overrides the build flag (FROM_CONFIG | MULTI_TABLE)
  "modelDir": "models",                  // relative to the config file's dir, or absolute
  "keywordModelName": "<from onboarding>",
  "keywordModelLicense": "<from onboarding>",
  "tableFiles": {                        // MULTI_TABLE only, relative to modelDir or absolute
    "heyJarvis": "hey_jarvis.table",
    "holdOn": "hold_on.table"
  },
  "listeningTimeoutSeconds": 15
}
```

```bash
APP=/sdcard/Android/data/com.hoffe86.speechmultikeyword/files
adb shell mkdir -p $APP/models
adb push ./models/. $APP/models/          # embedded model files (and .table files for MULTI_TABLE)
adb push ./kws-config.json $APP/
```

The license is redacted from `KwsConfig.toString()` and is never logged. `allowBackup` is disabled.

## Build and run

```bash
cd speech/src
./gradlew :app:installDebug                          # FROM_CONFIG (default)
./gradlew :app:installDebug -PkwsEngine=MULTI_TABLE  # fallback path
./gradlew :app:testDebugUnitTest                     # state machine, keyword matching, config, latency
adb logcat -s KWS                                    # detections, load time, metrics
```

In the app, tap **Start keyword spotting**. That requests `RECORD_AUDIO` and `POST_NOTIFICATIONS` and starts the
foreground service. Debug builds also show **Simulate "Hey Jarvis" / "Hold on"** buttons, so you can demo the UI without a mic.

## Comparing the two paths

The UI's metrics panel and `logcat -s KWS` show:

| Metric | How it's measured |
|---|---|
| Model load | Wall time to create config, model(s), audio config and recognizer(s) |
| Latency (est.) | `now − reference − (offset + duration)`. It's an estimate because the SDK doesn't document whether `offset` is relative to the current arm or to the first one. |
| Arm → result | Time from `recognizeOnceAsync` to the result |
| Memory | Process PSS (`Debug.getPss`) |
| CPU | Process CPU time delta, shown as a percentage of one core |

Every hit logs the raw `result.text` and `reason`.

## Known unknowns / risks

- **Unverified:** whether `result.text` returns the matched wake word on the `fromConfig` path. If it doesn't, the log
  shows `UNKNOWN`. Switch to `MULTI_TABLE` in that case.
- Max wake-word count, supported locales, and false-accept behaviour for `userDefinedWakeWords` aren't documented. Ask Microsoft.
- "Hold on" is a common everyday phrase in cabin conversation, so expect a higher false-accept rate than for "Hey Jarvis". Single short words like "stop" can't be trained as Custom Keywords, so the demo uses a two-word phrase.
- The emulator is for **functional validation only**. It has no vehicle mic array, AEC or cabin noise, so its accuracy numbers don't carry over to a car.
- The research notes said the SDK ships no x86_64 natives, but the 1.51.2 AAR does include `x86_64`. The app still ships
  `arm64-v8a` only, because AAOS targets arm64.
- Speech Studio Custom Keyword training (the `.table` fallback) reportedly retires on 2027-08-01. This is unconfirmed.
