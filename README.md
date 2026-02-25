# India Pulse - Offline-First News App

**India Pulse** is a professional Android application demonstrating a robust, offline-first architecture. It leverages **WorkManager** for background synchronization to fetch real-time news from India, ensuring data is available even when the device is offline by using a local Room database as the single source of truth.



## 🚀 Key Features

* **Periodic Background Sync**: Automatically fetches the latest news at system-allowed intervals using `WorkManager`.
* **Offline-First Experience**: Seamlessly view cached news articles using **Room Persistence**.
* **Type-Safe Navigation**: Implements the latest **Jetpack Compose Navigation (2.8.0+)** with Kotlin Serialization for compile-time safety.
* **Modern UI/UX**: Built with **Jetpack Compose** and **Material 3**, featuring:
    * Pull-to-refresh with `PullToRefreshBox`.
    * Real-time sync status indicators linked to WorkManager's `WorkInfo`.
* **Clean Architecture**: Strict separation of concerns across Data, Domain, and Presentation layers.

---

## 🛠️ Tech Stack

* **Language**: Kotlin
* **DI**: Dagger Hilt (including `HiltWorker` for WorkManager injection)
* **Database**: Room (with Flow for reactive UI updates)
* **Networking**: Retrofit & OkHttp
* **Background Tasks**: WorkManager (Periodic and Expedited requests)
* **Image Loading**: Coil
* **Navigation**: Jetpack Compose Type-Safe Navigation



---

## 🏗️ Architecture Overview

The project follows the **Clean Architecture** pattern:
1.  **Domain Layer**: Contains Business Logic, Models, and Repository interfaces.
2.  **Data Layer**: Contains Repository implementations, Room Database, DAO, and API Services.
3.  **Presentation Layer**: MVVM pattern using Compose, ViewModels, and Type-Safe Routes.

---

## 📦 Installation & Setup

1.  **Clone the repository**:
    ```
    git clone [https://github.com/Akash-singh9860/workManager.git](https://github.com/Akash-singh9860/workManager.git)
    ```
2.  **Configure API Key**:
    * Get an API key from [NewsAPI.org](https://newsapi.org/).
    * Add the key to your project constants.
3.  **Build**:
    * Open in **Android Studio Ladybug** or higher.
    * Sync Gradle and run on an emulator or device.

---

## 🧪 Testing Background Tasks

To verify the `NewsSyncWorker` without waiting for the periodic timer:
1.  Open **App Inspection** in Android Studio.
2.  Navigate to the **Background Task Inspector**.
3.  Find `NewsSyncWork` and click **Run Worker**.
4.  Switch to the **Database Inspector** to see the `articles` table update live.

---

## 👤 Author

**Akash Singh**
* **Role**: Senior Software Engineer & AI Practitioner
* **GitHub**: [@Akash-singh9860](https://github.com/Akash-singh9860)
* **Experience**: 6+ years in Android Development

---
Licensed under the MIT License.
