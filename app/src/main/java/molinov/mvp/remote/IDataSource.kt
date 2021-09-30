package molinov.mvp.remote

import io.reactivex.rxjava3.core.Single
import molinov.mvp.data.GitHubRepository
import molinov.mvp.data.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {

    @GET("users")
    fun loadUsers(): Single<List<GitHubUser>>

    @GET("users/{login}")
    fun loadUser(
        @Path("login")
        login: String
    ): Single<GitHubUser>

    @GET()
    fun getRepos(@Url reposUrl: String): Single<List<GitHubRepository>>
}
