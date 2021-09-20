package molinov.mvp.view.ui

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import java.io.File

@AddToEndSingle
interface ImageView : MvpView {
    fun init()
    fun convert(file: File)
    fun delete()
}
