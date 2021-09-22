package molinov.mvp.disabled

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import java.io.File

@AddToEndSingle
interface ImagesView : MvpView {
    fun init()
    fun convert(file: File)
    fun dismiss()
    fun delete()
}
