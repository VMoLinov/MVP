package molinov.mvp.di.modules

import dagger.Module
import dagger.Provides
import molinov.mvp.data.db.CacheRepositories
import molinov.mvp.data.db.GitHubDatabase
import molinov.mvp.data.db.RoomGithubRepositoriesCache
import molinov.mvp.di.scopes.RepositoryScope
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.GitHubRepositoriesRepo
import molinov.mvp.remote.IApiHolder

@Module
open class RepositoryModule {

    @Provides
    @RepositoryScope
    fun reposCache(db: GitHubDatabase): CacheRepositories =
        RoomGithubRepositoriesCache(db)

    @Provides
    @RepositoryScope
    fun repositoriesRepo(
        networkStatus: INetworkStatus,
        cache: CacheRepositories,
        apiHolder: IApiHolder,
    ): GitHubRepositoriesRepo {
        return GitHubRepositoriesRepo(cache, networkStatus, apiHolder)
    }
}
