# Android Custom Voice Sample

These Android Automotive and TTS client samples use the Azure Speech Embedded
SDK to synthesize speech with a locally supplied, licensed model.

## Prerequisites

- Android Studio with the Android SDK required by each project's Gradle configuration.
- ARM64 Android hardware for embedded synthesis; x86/x64 emulators cannot run the model.
- An authorized embedded speech model, its exact voice name, and its model license.

The repository does **not** distribute a model or license. A public checkout can
build without them, but the TTS service reports a setup error and synthesis is
unavailable until both are supplied.

## Local model setup

For each project you want to run, place the complete model files directly in:

- `src\CustomVoice\tts\src\main\assets\model`
- `src\TtsClient\tts\src\main\assets\model`

Preserve all model-internal file names and metadata. Do not introduce an extra
directory level underneath `model`.

Copy that project's `tts\model.properties.example` to `tts\model.properties`.
Set `voice` to the exact SDK voice identifier provided with the model and
`license` to its authorized license string. Values use Java properties syntax;
escape literal backslashes as `\\`. Keep configuration on your own machine.

Both model directories and `model.properties` files are ignored by Git.
Gradle generates the voice/license string resources from the local properties;
do not add licenses to tracked Android resources.

At startup, each TTS service checks that voice and license are non-blank, that
model assets are present and non-empty, and that copying them succeeds. Assets
are copied into the application's `models/model` directory. The SDK validates
the model format and license when it initializes synthesis.

## Running the samples

Open either `src\CustomVoice` or `src\TtsClient` in Android Studio, configure the
local model as above, and build/run the application on compatible ARM64 hardware.
The CustomVoice application targets Android Automotive OS.

The `tts` modules implement Android `TextToSpeechService`; their SDK dependency
and service registrations are already included. Requests support plain text and
SSML. Built-in styled samples use `__MODEL_VOICE__` in their SSML; the service
substitutes the locally configured voice with XML-safe escaping. SSML requests
without a voice tag are wrapped with the configured voice.

Model languages and expressive styles must match the supplied model. The
existing sample language behavior is unchanged.

## Publication precautions

Local APKs and AARs built with these assets contain the private model and
license. **Do not upload or publish those packages, build outputs, or local
configuration.** Ignore rules do not make packaged assets safe to distribute.

Obtain the model owner's permission before sharing any model, license, or
generated package.

SDK reference: [Azure Speech SDK platform setup](https://learn.microsoft.com/en-us/azure/ai-services/speech-service/quickstarts/setup-platform).
