package molinov.mvp.model

import molinov.mvp.remote.ApiHolder

class GithubUsersRepo {

    fun getUsers() = ApiHolder.api.loadUsers()

    fun getRepos(reposUrl: String) = ApiHolder.api.getRepos(reposUrl)
}
