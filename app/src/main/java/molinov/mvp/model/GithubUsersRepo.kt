package molinov.mvp.model

import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {

    private val users = listOf(
        GithubUser("user1"),
        GithubUser("user2"),
        GithubUser("user3"),
        GithubUser("user4"),
        GithubUser("user5")
    )

    fun getUsers(): Observable<GithubUser> {
        return Observable.fromIterable(users)
    }
}
