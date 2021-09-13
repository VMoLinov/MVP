package molinov.mvp.presentation

import molinov.mvp.view.IItemView
import molinov.mvp.view.UserItemView


interface IListPresenter<V : IItemView> {

    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IUserListPresenter : IListPresenter<UserItemView>
