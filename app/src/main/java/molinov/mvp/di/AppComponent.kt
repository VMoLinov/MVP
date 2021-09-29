package molinov.mvp.di

import dagger.Component
import molinov.mvp.data.GitHubUser
import molinov.mvp.di.modules.ApiModule
import molinov.mvp.di.modules.AppModule
import molinov.mvp.di.modules.CacheModule
import molinov.mvp.di.modules.CiceroneModule
import molinov.mvp.ui.activity.MainActivity
import molinov.mvp.ui.activity.MainPresenter
import molinov.mvp.ui.images.GlideImageLoader
import molinov.mvp.ui.repo.RepoPresenter
import molinov.mvp.ui.user.UserPresenter
import molinov.mvp.ui.users.UsersPresenter
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

    fun mainPresenter(): MainPresenter
    fun usersPresenter(): UsersPresenter

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(userPresenter: UserPresenter)
    fun inject(glideImageLoader: GlideImageLoader)
    fun inject(repoPresenter: RepoPresenter)
}
