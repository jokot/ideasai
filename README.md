# IdeasAI

## GeminiAI Idea Generator
IdeasAI is an Android application that creates and manages creative ideas using AI-driven functionality. The app follows modern Android development practices with Jetpack Compose, MVVM architecture, and several core Android libraries.

### AI Source
This project utilizes the [GeminiAI](https://developer.android.com/ai/google-ai-client-sdk) for generating fresh and inspiring ideas in various categories.

## Tech Stack
Below are the primary libraries and tools used in this project:

| Library/Tool          | Version & Link                                                        | Included |
|-----------------------|-----------------------------------------------------------------------|----------|
| **Kotlin**            | [2.0.21](https://kotlinlang.org/)                                     | ✔️       |
| **Jetpack Compose**   | [2024.04.01](https://developer.android.com/jetpack/compose)           | ✔️       |
| **Room**              | [2.6.1](https://developer.android.com/jetpack/androidx/releases/room) | ✔️       |
| **Hilt**              | [2.52](https://developer.android.com/jetpack/androidx/releases/hilt)  | ✔️       |
| **GenerativeAi**      | [0.9.0](https://developer.android.com/ai/generativeai)                | ✔️       |

## How to Run the Project
1. **Clone or Download** the repository to your local machine.
2. **Open** the project in [Android Studio](https://developer.android.com/studio).
3. **Add your API key** to `local.properties`:
   ```properties
   apiKey=YOUR_API_KEY
   ```
   Make sure to replace `YOUR_API_KEY` with your actual Gemini AI API key or any relevant AI key.
4. **Sync Gradle** files to download and install the necessary dependencies.
5. **Run** the app on a device or emulator with a minimum SDK of **26**.

This project showcases clean architecture, dependency injection with Hilt, and Jetpack Compose for building modern Android UIs. While it currently focuses on generating ideas using GeminiAI, you can easily extend it to integrate other AI models or add extra features as needed. Enjoy exploring and get inspired!
