package molinov.mvp.data

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.db.CacheUsers
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.IApiHolder
import javax.inject.Inject

class GitHubUsersRepo @Inject constructor(
    private val cache: CacheUsers,
    private val networkStatus: INetworkStatus,
    private val apiHolder: IApiHolder
) {

    fun getUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            apiHolder.apiService.loadUsers()
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
