package molinov.mvp.ui.user

import molinov.mvp.data.GitHubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {

    fun init(user: GitHubUser)
    fun updateList()
}
