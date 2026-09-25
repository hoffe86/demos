# Android Custom Voice Sample
This sample demonstrates the Azure Speech Embedded SDK for Android, which allows you to add speech recognition and synthesis capabilities to your Android applications. 
The sample includes a simple user interface that allows users to interact with the speech recognition and synthesis features.

## Prerequisites
- Android Studio 4.0 or later
- Android 15 (API level 35) or later
- Android SDK 36 or later
- Kotlin 

**Important:** The model requires an ARM64 based hardware architecture, so the App won't run on x86 or x64 system.

## Package Structure

### Custom Voice App
App is developed for Android Automotive OS

#### com.example.tts Package
This package contains the classes and resources related to text-to-speech (TTS) functionality, which includes the CustomTtsService class for the custom implementation of the TextToSpeechService and a Text2SpeechHandler class to interact with the TTS system.

In addition the package contains the custom embedded voice model as assets, which will be copied over to the app's data directory when the TTS service is started.

#### com.example.app Package
This package contains the main service and the user interface components of the application. The Main Service class is main entry point, which create an instance of the MainSession class, which spins up the MainScreen.

## Running the Sample
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Install 'Automotive (1408p landscape)' emulator image from the Virtual Device Manager.
3. Build and run the 'app' project on an Android device or emulator.


#### TtsClient
Modified TtsClient to integrated the TTS service based on the custom custom embedded voice model, which is integrated via the Azure Speech Embedded SDK. 

Modifications include:
- Adding the TTS package as external jar dependency to intefrated the custom TTS service.
- Added dependeny for the [Azure Speech Embedded SDK](https://learn.microsoft.com/en-us/azure/ai-services/speech-service/quickstarts/setup-platform?tabs=windows%2Cubuntu%2Cdotnetcli%2Candroid%2Cmaven%2Cnodejs%2Cmac%2Cpypi&pivots=programming-language-java). (com.microsoft.cognitiveservices.speech:client-sdk:1.43.0.)
- Register the custom TTS service in the AndroidManifest.xml file for the app package.
``` xml
...
<service
    android:name="com.example.tts.CustomTtsService"
    android:exported="true"
    android:label="Custom TTS Engine"
    android:permission="android.permission.BIND_TEXT_SERVICE">
    <intent-filter>
        <action android:name="android.intent.action.TTS_SERVICE" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>

    <meta-data
        android:name="android.speech.tts"
        android:resource="@xml/tts_engine" />
</service>

...
```

- Modified the custom TextToSpeechService to work in the Queueing mode and added handling for SSML to adjust the speak tag and added voice tag to provided information about the voice which should be used.

This modification was required to ensure the Azure Speech Embedded SDK can process the SSML correctly and use the custom custom embedded voice model for text-to-speech synthesis.

__Original SSML:__
``` xml
<?xml version="1.0"?><speak>Auf der schnellsten Route gibt es momentan Verkehrsverzögerungen von insgesamt <say-as interpret-as="TTS-DURATION"><say-as format="slot" interpret-as="g_requested_destination_traffic_delay_time">fünf Minuten</say-as></say-as>. Du benötigst ungefähr <say-as interpret-as="TTS-DURATION"><say-as format="slot" interpret-as="g_requested_destination_driving_time">30 Minuten</say-as></say-as>, um das Ziel <say-as format="slot" interpret-as="g_final_destination">Berlin</say-as> zu erreichen.</speak>

```
__Modified SSML:__
``` xml
<?xml version="1.0"?> <speak version='1.0' xmlns='http://www.w3.org/2001/10/synthesis' xmlns:mstts='https://www.w3.org/2001/mstts' xml:lang='de-DE'><voice xml:lang='de-DE' xml:gender='female' name='__MODEL_VOICE__'>Auf der schnellsten Route gibt es momentan Verkehrsverzögerungen von insgesamt <say-as interpret-as="TTS-DURATION"><say-as format="slot" interpret-as="g_requested_destination_traffic_delay_time">fünf Minuten</say-as></say-as>. Du benötigst ungefähr <say-as interpret-as="TTS-DURATION"><say-as format="slot" interpret-as="g_requested_destination_driving_time">30 Minuten</say-as></say-as>, um das Ziel <say-as format="slot" interpret-as="g_final_destination">Berlin</say-as> zu erreichen.</voice></speak>
```
