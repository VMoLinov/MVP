package molinov.mvp

import android.app.Application
import molinov.mvp.data.db.GitHubDatabase
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    val navigationHolder get() = cicerone.navigatorHolder
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
        GitHubDatabase.create(this)
    }

    companion object {
        lateinit var instance: App
    }
}
