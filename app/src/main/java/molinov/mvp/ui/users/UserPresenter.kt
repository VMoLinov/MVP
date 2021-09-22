package molinov.mvp.ui.users

import molinov.mvp.model.GithubUser
import molinov.mvp.ui.users.UserView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val user: GithubUser?,
    private val router: Router
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init(user?.login)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
