package molinov.mvp.remote

import io.reactivex.rxjava3.core.Single
import molinov.mvp.model.GithubUser
import molinov.mvp.model.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun loadUser(
        @Path("login")
        login: String
    ): Single<GithubUser>

    @GET()
    fun getRepos(@Url reposUrl: String): Single<List<UserRepos>>
}
