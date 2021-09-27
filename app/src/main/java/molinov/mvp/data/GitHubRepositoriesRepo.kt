package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.CacheRepositories
import molinov.mvp.data.db.GithubDatabase
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder

class GitHubRepositoriesRepo(
    private val cache: CacheRepositories,
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) {

    fun getRepositories(user: GitHubUser): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                ApiHolder.api.getRepos(user.reposUrl).flatMap { repositories ->
                    Single.fromCallable {
                        val roomRepos = cache.fromModelToDb(repositories, user.id)
                        db.repositoryDao.insert(roomRepos)
                        repositories
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.getByLogin(user.login)
                    val repos = db.repositoryDao.getByUserId(roomUser.id.toString())
                    cache.fromDbToModel(repos)
                }
            }
        }
}
