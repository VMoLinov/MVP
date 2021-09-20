package molinov.mvp.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import com.google.android.material.button.MaterialButton
import molinov.mvp.view.ui.ImageView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ImagePresenter(private val router: Router) : MvpPresenter<ImageView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun setBack(back: MaterialButton) {
        back.setOnClickListener { onBackPressed() }
    }

    fun setConvert(convert: MaterialButton, image: Bitmap) {
        convert.setOnClickListener {
            val dir = convert.context.getDir(Environment.DIRECTORY_PICTURES, Context.MODE_PRIVATE)
            val file = File.createTempFile(
                "nasa",
                ".png",
                dir
            )
            if (!file.exists()) Log.e("ERROR", "Not create")
            val storageDir = convert.context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val outStream: FileOutputStream?
            try {
                outStream = FileOutputStream(file)
                image.compress(Bitmap.CompressFormat.PNG, 100, outStream)
                outStream.flush()
                outStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            val png = BitmapFactory.decodeFile(file.absolutePath)
            viewState.convert(png)
        }
    }

    fun setDismiss(dismiss: MaterialButton) {
        dismiss.setOnClickListener {
//            TODO()
        }
    }

    fun setDelete(delete: MaterialButton) {
        delete.setOnClickListener { viewState.delete() }
    }
}
