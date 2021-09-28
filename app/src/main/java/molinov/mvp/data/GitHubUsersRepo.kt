package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.CacheUsers
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder

class GitHubUsersRepo(
    private val cache: CacheUsers,
    private val networkStatus: INetworkStatus
) {

    fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            ApiHolder.api.loadUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        cache.fromModelToDb(users)
                        users
                    }
                }
        } else {
            Single.fromCallable {
                cache.allFromDbToModel()
            }
        }
    }
}
