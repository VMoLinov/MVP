package molinov.mvp.presentation

import molinov.mvp.model.Image
import molinov.mvp.view.ui.ImageView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class ImagePresenter(
    private val image: Image,
    private val router: Router
) : MvpPresenter<ImageView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun setConvert() {
        image.convertJpegToPng().subscribe {
            viewState.convert(it)
        }
    }

    fun setDismiss() {

    }

    fun setDelete() {
        viewState.delete()
    }
}
