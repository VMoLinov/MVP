package molinov.mvp.ui.repo

import molinov.mvp.data.GitHubRepository
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class RepoPresenter(
    private val repo: GitHubRepository?,
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
