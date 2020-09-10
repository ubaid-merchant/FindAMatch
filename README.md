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

com.ubaidmerchant.findamatch    # Root Package<br />
=> data                         # For data handling<br />
=> data => local                # Local Persistence Database. Room (SQLite) database<br />
=> data => local => dao         # Data Access Object for Room<br />
=> data => remote               # Remote Data Handlers<br />
=> data => remote => api        # Retrofit API for remote end point<br />
=> data => repo			        # Single source of data<br />
=> di                           # Dependency Injection<br />
=> di => module                 # DI Modules<br />
=> model                        # Model classes<br />
=> ui                           # Activity/View layer<br />
=> ui => base                   # Base View<br />
=> ui => main                   # Main Screen Activity & ViewModel<br />
=> ui => main => adapter        # Adapter for RecyclerView<br />
=> ui => main => viewmodel      # ViewHolder for RecyclerView<br />
=> utils                        # Utility Classes / Kotlin extensions<br />

