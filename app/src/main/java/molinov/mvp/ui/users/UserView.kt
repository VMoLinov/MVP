package molinov.mvp.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface UserView : MvpView {

    fun init(name: String?)
}
