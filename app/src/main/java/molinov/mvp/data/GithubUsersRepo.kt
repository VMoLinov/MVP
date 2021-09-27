package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.CacheUsers
import molinov.mvp.data.db.GithubDatabase
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder

class GithubUsersRepo(
    private val cache: CacheUsers,
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) {

    fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.api.loadUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = cache.fromModelToDb(users)
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                val roomUsers = db.userDao.getAll()
                val users = cache.fromDbToModel(roomUsers)
                users
            }
        }
    }
}
