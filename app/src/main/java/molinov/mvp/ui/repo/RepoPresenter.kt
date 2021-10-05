package molinov.mvp.ui.repo

import molinov.mvp.data.GitHubRepository
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class RepoPresenter(
    private val repo: GitHubRepository?
) : MvpPresenter<RepoView>() {

    @Inject
    lateinit var router: Router

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
