package molinov.mvp.ui.repo

import molinov.mvp.model.UserRepo
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(
    private val repo: UserRepo?,
    private val router: Router
) : MvpPresenter<RepoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (repo != null) {
            viewState.init(repo)
        }
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}
