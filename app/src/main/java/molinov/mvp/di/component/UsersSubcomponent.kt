package molinov.mvp.di.component

import dagger.Subcomponent
import molinov.mvp.di.modules.UserModule
import molinov.mvp.di.scopes.UserScope
import molinov.mvp.ui.users.UsersPresenter

@UserScope
@Subcomponent(
    modules = [
        UserModule::class,
    ]
)
interface UsersSubcomponent {

    fun repositorySubcomponent(): RepositorySubcomponent

    fun inject(usersPresenter: UsersPresenter)
}
