package molinov.mvp.di.component

import dagger.Component
import molinov.mvp.di.modules.ApiModule
import molinov.mvp.di.modules.AppModule
import molinov.mvp.di.modules.CacheModule
import molinov.mvp.di.modules.CiceroneModule
import molinov.mvp.ui.activity.MainActivity
import molinov.mvp.ui.activity.MainPresenter
import molinov.mvp.ui.images.GlideImageLoader
import molinov.mvp.ui.repo.RepoPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
    ]
)
interface AppComponent {

    fun usersSubcomponent(): UsersSubcomponent

    fun mainPresenter(): MainPresenter

    fun repoPresenter(): RepoPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(glideImageLoader: GlideImageLoader)
}
