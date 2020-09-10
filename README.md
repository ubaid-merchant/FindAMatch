# FindAMatch

Find A Match App

**About**  
FindAMatch is a sample match-making Android application built to
demonstrate use of Modern Android development tools - (Kotlin,
Coroutines, Flow, Dagger 2, Architecture Components, MVVM, Room,
Retrofit, Material Components).

**Contact**  
If you need any help, you can connect with me.  
Visit:- [ubaidmerchant.com](https://www.ubaidmerchant.com)

**Architecture**  
This app uses MVVM (Model View View-Model) architecture.

**Package Structure**  
com.ubaidmerchant.findamatch # Root Package

|  
├── data                # For data handling.
│   ├── local           # Local Persistence Database. Room (SQLite) database
|   │   ├── dao         # Data Access Object for Room 
│   ├── remote          # Remote Data Handlers
|   │   ├── api         # Retrofit API for remote end point.
│   └── repo			# Single source of data.
|
├── di                  # Dependency Injection
│   └── module          # DI Modules
|
├── model               # Model classes
|
├── ui                  # Activity/View layer
│   ├── base            # Base View
│   ├── main            # Main Screen Activity & ViewModel
|   │   ├── adapter     # Adapter for RecyclerView
|   │   └── viewmodel   # ViewHolder for RecyclerView
|
└── utils               # Utility Classes / Kotlin extensions  
|
