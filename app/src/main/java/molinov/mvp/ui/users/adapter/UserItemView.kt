package molinov.mvp.ui.users.adapter

import molinov.mvp.ui.items.IItemView

interface UserItemView : IItemView {

    fun showLogin(login: String)
    fun loadAvatar(url: String)
}
