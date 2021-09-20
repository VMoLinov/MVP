package molinov.mvp.view.ui

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import molinov.mvp.App
import molinov.mvp.R
import molinov.mvp.databinding.FragmentImagesBinding
import molinov.mvp.model.Image
import molinov.mvp.presentation.ImagesPresenter
import molinov.mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.io.File

class ImagesFragment : MvpAppCompatFragment(), ImagesView, BackButtonListener {

    private var _vb: FragmentImagesBinding? = null
    private val vb get() = _vb!!
    private val presenter by moxyPresenter {
        ImagesPresenter(image, App.instance.router)
    }
    private val dir by lazy {
        requireContext().getDir(Environment.DIRECTORY_PICTURES, Context.MODE_PRIVATE)
    }
    private val image by lazy {
        Image(dir).createJpegFile(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.nasa,
                requireContext().theme
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentImagesBinding.inflate(inflater, container, false)
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
        vb.imageJpeg.setImageBitmap(BitmapFactory.decodeFile(image.file.absolutePath))
        vb.back.setOnClickListener { presenter.onBackPressed() }
        vb.convert.setOnClickListener { presenter.convert() }
        vb.dismiss.setOnClickListener { presenter.dismiss() }
        vb.delete.setOnClickListener { presenter.delete() }
    }

    override fun convert(file: File) {
        vb.imagePng.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
    }

    override fun dismiss() {
//        TODO("Not yet implemented")
    }

    override fun delete() {
        vb.imagePng.setImageBitmap(null)
    }
}
