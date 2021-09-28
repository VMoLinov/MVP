package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.CacheRepositories
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder

class GitHubRepositoriesRepo(
    private val cache: CacheRepositories,
    private val networkStatus: INetworkStatus
) {

    fun getRepositories(user: GitHubUser): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                ApiHolder.api.getRepos(user.reposUrl).flatMap { repositories ->
                    Single.fromCallable {
                        cache.fromModelToDb(repositories, user.id)
                        repositories
                    }
                }
            } else {
                Single.fromCallable {
                    cache.fromDbToModel(user)
                }
            }
        }
}
