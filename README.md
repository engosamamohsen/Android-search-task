<h1 align="center">Android Search Task</h1>

<p align="center">
Android Search is a sample Android project using <a href="https://www.themoviedb.org/">The Movie DB</a> API based on MVVM architecture. It showcases the app development with well-designed architecture and up-to-date Android tech stacks.

</p>

## Features
* 100% Kotlin
* MVVM architecture
* Coroutine
* Retrofit
* Reactive pattern
* Android architecture components and Jetpack
* Single activity
* Dependency injection

## Tech Stacks
* [Retrofit](http://square.github.io/retrofit/) + [OkHttp](http://square.github.io/okhttp/) - RESTful API and networking client.
* [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - A collections of libraries that help you design rebust, testable and maintainable apps.
    * [ViewModel](https://developer.android.com/reference/androidx/lifecycle/ViewModel) - UI related data holder, lifecycle aware.
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Observable data holder that notify views when underlying data changes.
    * [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Declarative way to bind data to UI layout.
    * [Navigation component](https://developer.android.com/guide/navigation) - Fragment routing handler. (Upcoming)
    * [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Tasks scheduler in background jobs. (Upcoming)
* [RxJava](https://github.com/ReactiveX/RxJava) - Asynchronous programming with observable streams.
* [Glide](https://github.com/bumptech/glide) - Image loading.
* [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative and simplified way for UI development. (Upcoming)
* [Coroutines](https://developer.android.com/kotlin/coroutines) - Light-weight threads for background operations. (Upcoming)

## Architectures

We follow Google recommended [Guide to app architecture](https://developer.android.com/jetpack/guide) to structure our architecture based on MVVM, reactive UI using LiveData / RxJava observables and data binding.

* **View**: Activity/Fragment with UI-specific logics only.
* **ViewModel**: It keeps the logic away from View layer, provides data streams for UI and handle user interactions.
* **Model**: Repository pattern, data layers that provide interface to manipulate data from both the local and remote data sources. The local data sources will serve as [single source of truth](https://en.wikipedia.org/wiki/Single_source_of_truth).
