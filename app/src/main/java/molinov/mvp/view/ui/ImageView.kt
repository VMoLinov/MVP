package molinov.mvp.view.ui

import android.graphics.Bitmap
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ImageView : MvpView {
    fun init()
    fun convert(image: Bitmap)
    fun delete()
}
