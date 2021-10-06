package molinov.mvp

import android.app.Application
import molinov.mvp.di.component.AppComponent
import molinov.mvp.di.component.DaggerAppComponent
import molinov.mvp.di.component.RepositorySubcomponent
import molinov.mvp.di.component.UsersSubcomponent
import molinov.mvp.di.modules.AppModule

class App : Application() {

    lateinit var appComponent: AppComponent
    var usersSubcomponent: UsersSubcomponent? = null
        private set
    var repositorySubcomponent: RepositorySubcomponent? = null
        private set


    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUsersSubComponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun initRepositorySubcomponent() =
        appComponent.usersSubcomponent().repositorySubcomponent().also {
            repositorySubcomponent = it
        }

    fun releaseUserScore() {
        usersSubcomponent = null
    }

    fun releaseRepoScope() {
        repositorySubcomponent = null
    }

    companion object {
        lateinit var instance: App
    }
}
