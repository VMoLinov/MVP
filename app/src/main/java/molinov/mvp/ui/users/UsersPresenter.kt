package molinov.mvp.ui.users

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import molinov.mvp.data.GitHubUser
import molinov.mvp.data.GitHubUsersRepo
import molinov.mvp.navigation.AndroidScreens
import molinov.mvp.ui.items.IUserListPresenter
import molinov.mvp.ui.users.adapter.UserItemView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val usersRepo: GitHubUsersRepo,
    private val router: Router
) : MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {

        val users = mutableListOf<GitHubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount(): Int = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.showLogin(user.login)
            view.loadAvatar(user.avatarUrl)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {
            router.navigateTo(AndroidScreens.UserScreen(usersListPresenter.users[it.pos]))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }, {
                Log.e("UsersPresenter", "Ошибка получения пользователей", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
