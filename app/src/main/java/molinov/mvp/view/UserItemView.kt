package molinov.mvp.view

import molinov.mvp.model.GithubUser

interface UserItemView : IItemView {

    fun showLogin(login: String)
    fun getUser(): GithubUser
}
