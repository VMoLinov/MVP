package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.GithubDatabase
import molinov.mvp.data.db.RoomGithubUser
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder

class GithubUsersRepo(
    private val networkStatus: INetworkStatus,
    private val db: GithubDatabase
) {

    fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.api.loadUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        val roomUsers = users.map { user ->
                            RoomGithubUser(
                                id = user.id,
                                login = user.login,
                                avatarUrl = user.avatarUrl,
                                reposUrl = user.reposUrl
                            )
                        }
                        db.userDao.insert(roomUsers)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomUser ->
                    GitHubUser(
                        login = roomUser.login,
                        id = roomUser.id,
                        avatarUrl = roomUser.avatarUrl,
                        reposUrl = roomUser.reposUrl,
                    )
                }
            }
        }
    }
}
