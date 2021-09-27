package molinov.mvp.ui.user

import molinov.mvp.model.GithubUser
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {

    fun init(user: GithubUser)
    fun updateList()
}
