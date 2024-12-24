# NeoBank_KMP
# Kotlin Multiplatform Mobile (KMM) Project

This is a Kotlin Multiplatform Mobile (KMM) project that allows you to write code that runs on both Android and iOS platforms. The project is built using Kotlin's shared codebase, enabling code reuse across different platforms while still providing the ability to implement platform-specific functionality when needed.

## Integrated
- Login Screen with API
  - Authorize 
  - Login Username / Password 
  - After OTP validate - Resume for token 
  - Obtain Token 6
- Get user profile with API
- Fetch Wallets API 
- Update User Profile 
 

## Features 
- Shared code for both Android and iOS using Kotlin.
- Supports common functionality across platforms such as data models, business logic, and more.
- Platform-specific implementations for Android and iOS using `expect`/`actual` mechanism.
- Easy integration with existing Android and iOS applications.

## Requirements to run this project
 Make sure you have the following tools installed:

- **Android Studio** (latest stable version) with the Kotlin plugin.
- **Xcode** (for iOS development).
- **JDK 11 or higher** (for building the project).
- **Kotlin 1.5 or higher** (for KMM support).
- **Gradle** (to manage dependencies).

## Setup

### 1. Clone the Repository
First, clone the repository to your local machine:
git clone https://github.com/NilayDev/NeoBank_KMP.git
cd your-kmm-project

### 2. Open the Project in Android Studio
Open Android Studio and select Open an existing project.
Navigate to the folder where you cloned the repository and open it.

### 3. Sync Gradle
Once the project is open, sync the Gradle files by clicking on Sync Now when prompted. This will download the necessary dependencies.

### 4. Build the Project
You can build the project using Android Studio or via the terminal.

### 5. Run the Application
On Android:

Connect your Android device or start an emulator.
Run the project by selecting your target device and clicking Run in Android Studio.

On iOS:
Open the iOS project in Xcode (located in iosApp directory).
Select a target device or simulator and run the project.

