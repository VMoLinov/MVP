package molinov.mvp.presentation

import molinov.mvp.model.GithubUser
import molinov.mvp.view.ui.UserView
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
