# FindAMatch

Find A Match App

**About**
FindAMatch is a sample match-making Android application built to
demonstrate use of Modern Android development tools - (Kotlin,
Coroutines, Flow, Dagger 2, Architecture Components, MVVM, Room,
Retrofit, Material Components).

**Contact **  
If you need any help, you can connect with me.  
Visit:- [ubaidmerchant.com](https://www.ubaidmerchant.com)

**Architecture**  
This app uses MVVM (Model View View-Model) architecture.

**Package Structure**  
com.ubaidmerchant.findamatch # Root Package<br />
├── data                # For data handling.<br />
│   ├── local           # Local Persistence Database. Room (SQLite) database<br />
|   │   ├── dao         # Data Access Object for Room <br />
│   ├── remote          # Remote Data Handlers<br />
|   │   ├── api         # Retrofit API for remote end point.<br />
│   └── repo			   # Single source of data.<br />
|<br />
├── di                  # Dependency Injection<br />
│   └── module          # DI Modules<br />
|<br />
├── model               # Model classes<br />
|<br />
├── ui                  # Activity/View layer<br />
│   ├── base            # Base View<br />
│   ├── main            # Main Screen Activity & ViewModel<br />
|   │   ├── adapter     # Adapter for RecyclerView<br />
|   │   └── viewmodel   # ViewHolder for RecyclerView<br />
|<br />
└── utils               # Utility Classes / Kotlin extensions<br />


