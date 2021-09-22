package molinov.mvp.disabled

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import molinov.mvp.disabled.Image
import molinov.mvp.disabled.ImagesView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class ImagesPresenter(
    private val image: Image,
    private val router: Router
) : MvpPresenter<ImagesView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun convert() {
        val disposable = image.convertJpegToPng()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                viewState.convert(it)
            }, {
                Log.e("error", it.printStackTrace().toString())
            })
        compositeDisposable.addAll(disposable)
    }

    fun dismiss() {
        compositeDisposable.clear()
        image.deletePng()
        viewState.dismiss()
    }

    fun delete() {
        image.deletePng()
        viewState.delete()
    }
}
