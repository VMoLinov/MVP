package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.CacheRepositories
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.IApiHolder
import javax.inject.Inject

class GitHubRepositoriesRepo @Inject constructor(
    private val cache: CacheRepositories,
    private val networkStatus: INetworkStatus,
    private val apiHolder: IApiHolder
) {

    fun getRepositories(user: GitHubUser): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                apiHolder.apiService.getRepos(user.reposUrl).flatMap { repositories ->
                    Single.fromCallable {
                        cache.fromModelToDb(repositories, user.id)
                        repositories
                    }
                }
            } else {
                Single.fromCallable {
                    val ret = cache.fromDbToModel(user)
                    ret
                }
            }
        }
}
