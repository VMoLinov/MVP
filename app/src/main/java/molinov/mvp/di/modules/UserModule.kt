package molinov.mvp.di.modules

import dagger.Module
import dagger.Provides
import molinov.mvp.data.db.CacheUsers
import molinov.mvp.di.scopes.UserScope
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.GitHubUsersRepo
import molinov.mvp.remote.IApiHolder

@Module
open class UserModule {

    @Provides
    @UserScope
    fun usersRepo(
        networkStatus: INetworkStatus,
        cache: CacheUsers,
        apiHolder: IApiHolder,
    ): GitHubUsersRepo {
        return GitHubUsersRepo(cache, networkStatus, apiHolder)
    }
}
