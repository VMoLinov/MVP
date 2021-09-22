package molinov.mvp.model

import molinov.mvp.remote.ApiHolder

class GithubUsersRepo {

    fun getUsers() = ApiHolder.api.loadUsers()
}
