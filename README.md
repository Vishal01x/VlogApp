# 📹 Vlog App  

Vlog App is an Android application designed to display a list of vlogs using Jetpack Compose. It fetches vlog data from a remote API using **Paging 3**, allowing users to browse vlogs efficiently. Users can tap on a vlog item to open its details in a WebView.

---

## 🚀 Features  
- **Jetpack Compose UI**: Uses a modern declarative UI approach.  
- **Paging 3**: Implements infinite scrolling for efficient data fetching.  
- **Hilt Dependency Injection**: Manages dependencies efficiently.  
- **Retrofit API Calls**: Fetches vlog data from a remote API.  
- **WebView Integration**: Opens vlogs inside the app in a WebView.  
- **Error Handling**: Displays errors and empty state messages gracefully.  
- **Material 3 Design**: Provides a clean and responsive UI.  

---

## 🛠️ Tech Stack  
- **Language**: Kotlin  
- **Architecture**: MVVM (Model-View-ViewModel)  
- **UI Framework**: Jetpack Compose  
- **Dependency Injection**: Hilt  
- **Networking**: Retrofit  
- **Paging**: Paging 3  
- **Navigation**: Jetpack Navigation  

---

## 📲 How the App Works  

1. **Home Screen (Vlog List)**  
   - Displays a list of vlogs fetched from an API.  
   - Uses Paging 3 to load more items as the user scrolls.  
   - Clicking on a vlog item navigates to the WebView screen.  

2. **Vlog Details (WebView)**  
   - Opens the selected vlog inside an in-app WebView.  
   - Allows users to read the full vlog article.  

3. **Error & Loading States**  
   - Shows a progress indicator while loading data.  
   - Displays error messages in case of network failures.  
   - Shows a "No data available" message when the list is empty.  

## App Sources

### VlogScreen 
![Vlog Screen](https://github.com/user-attachments/assets/6a514e2a-df02-4db0-a1ec-da8fdcada2c0)
![Vlog Detail](https://github.com/user-attachments/assets/1df989b9-ba82-412d-bbd2-48c5c9a5a6be)

---

## 🔧 Installation & Setup  

1. Clone the repository:  
   ```bash
   git clone https://github.com/yourusername/VlogApp.git
   cd VlogApp
