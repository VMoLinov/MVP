package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.GithubDatabase
import molinov.mvp.data.db.RoomGithubRepository
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder

class GitHubRepositoriesRepo(
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) {

    fun getRepositories(user: GitHubUser): Single<List<GitHubRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                ApiHolder.api.getRepos(user.reposUrl).flatMap { repositories ->
                    Single.fromCallable {
                        val roomUser = db.userDao.getByLogin(user.login)
                        val roomRepos = repositories.map {
                            RoomGithubRepository(
                                id = it.id,
                                name = it.name,
                                description = it.description,
                                forks = it.forks,
                                watchers = it.watchers,
                                userId = roomUser.id
                            )
                        }
                        db.repositoryDao.insert(roomRepos)
                        repositories
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = db.userDao.getByLogin(user.login)
                    db.repositoryDao.getByUserId(roomUser.id.toString()).map {
                        GitHubRepository(
                            id = it.id,
                            name = it.name,
                            description = it.description,
                            forks = it.forks,
                            watchers = it.watchers
                        )
                    }
                }
            }
        }
}
