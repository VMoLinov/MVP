package molinov.mvp.view.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import molinov.mvp.App
import molinov.mvp.R
import molinov.mvp.databinding.FragmentImageViewBinding
import molinov.mvp.presentation.ImagePresenter
import molinov.mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class ImageScreenFragment : MvpAppCompatFragment(), ImageView, BackButtonListener {

    private var _vb: FragmentImageViewBinding? = null
    private val vb get() = _vb!!
    private val presenter by moxyPresenter { ImagePresenter(App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentImageViewBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    override fun init() {
        vb.imageJpeg.setImageResource(R.drawable.nasa)
        presenter.setBack(vb.back)
        presenter.setConvert(vb.convert, vb.imageJpeg.drawable.toBitmap())
        presenter.setDismiss(vb.dismiss)
        presenter.setDelete(vb.delete)
    }

    override fun convert(image: Bitmap) {
        vb.imagePng.setImageBitmap(image)
    }

    override fun delete() {
        vb.imagePng.setImageBitmap(null)
    }
}
