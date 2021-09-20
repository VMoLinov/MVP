package molinov.mvp.presentation

import molinov.mvp.model.Image
import molinov.mvp.view.ui.ImagesView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class ImagesPresenter(
    private val image: Image,
    private val router: Router
) : MvpPresenter<ImagesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun convert() {
        image.convertJpegToPng().subscribe {
            viewState.convert(it)
        }
    }

    fun dismiss() {

    }

    fun delete() {
        viewState.delete()
    }
}
