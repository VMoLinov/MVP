package molinov.mvp.ui.repo

import molinov.mvp.model.UserRepo
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface RepoView : MvpView {

    fun init(repo: UserRepo)
}