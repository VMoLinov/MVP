package molinov.mvp.di.component

import dagger.Subcomponent
import molinov.mvp.di.modules.RepositoryModule
import molinov.mvp.di.scopes.RepositoryScope
import molinov.mvp.ui.user.UserPresenter

@RepositoryScope
@Subcomponent(
    modules = [RepositoryModule::class]
)
interface RepositorySubcomponent {

    fun inject(userPresenter: UserPresenter)
}
