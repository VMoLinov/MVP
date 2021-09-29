package molinov.mvp

import android.app.Application
import molinov.mvp.di.AppComponent
import molinov.mvp.di.DaggerAppComponent
import molinov.mvp.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}
