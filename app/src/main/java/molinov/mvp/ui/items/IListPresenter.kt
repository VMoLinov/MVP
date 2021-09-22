package molinov.mvp.ui.items

import molinov.mvp.ui.users.adapter.UserItemView


interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
