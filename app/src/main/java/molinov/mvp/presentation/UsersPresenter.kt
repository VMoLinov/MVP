package molinov.mvp.presentation

import android.util.Log
import molinov.mvp.model.GithubUser
import molinov.mvp.model.GithubUsersRepo
import molinov.mvp.screens.AndroidScreens
import molinov.mvp.view.UserItemView
import molinov.mvp.view.ui.UsersView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UsersPresenter(
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        private val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login)
        }

        fun subscribeData(usersRepo: GithubUsersRepo) {
            usersRepo.getUsers().subscribe({
                users.add(it)
            }, {
                Log.d(RX, "error subscribe data ${it.message}")
            }, {
                Log.d(RX, "subscribe complete")
            }).dispose()
        }

        companion object {
            const val RX = "RxJava"
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(AndroidScreens.UserScreen(itemView.getUser()))
        }
    }

    private fun loadData() {
        usersListPresenter.subscribeData(GithubUsersRepo())
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
