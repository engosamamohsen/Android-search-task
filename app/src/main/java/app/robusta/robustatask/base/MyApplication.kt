package app.robusta.robustatask.base

import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    companion object {
        lateinit var instance: MyApplication
            private set
    }

}