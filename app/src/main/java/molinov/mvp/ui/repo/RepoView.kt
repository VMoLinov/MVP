package molinov.mvp.ui.repo

import molinov.mvp.data.GitHubRepository
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoView : MvpView {

    fun init(repo: GitHubRepository)
}