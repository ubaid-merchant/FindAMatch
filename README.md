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

com.ubaidmerchant.findamatch            # Root Package

=> data                                 # For data handling

=> data => local                        # Local Persistence Database. Room (SQLite) database

=> data => local => dao                 # Data Access Object for Room

=> data => remote                       # Remote Data Handlers

=> data => remote => api                # Retrofit API for remote end point

=> data => repo			                    # Single source of data

=> data => di                           # Dependency Injection

=> data => di => module                 # DI Modules

=> data => model                        # Model classes

=> data => ui                           # Activity/View layer

=> data => ui => base                   # Base View

=> data => ui => main                   # Main Screen Activity & ViewModel

=> data => ui => main => adapter        # Adapter for RecyclerView

=> data => ui => main => viewmodel      # ViewHolder for RecyclerView

=> data => utils                        # Utility Classes / Kotlin extensions

