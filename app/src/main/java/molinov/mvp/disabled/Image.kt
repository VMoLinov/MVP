package molinov.mvp.disabled

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Image(var fileJPEG: File) {

    private val filePNG = File(fileJPEG, IMAGE_PNG)

    fun createJpegFile(drawable: Drawable?): Image {
        fileJPEG = File(fileJPEG, IMAGE_JPEG)
        if (!fileJPEG.exists()) {
            val outStream: FileOutputStream?
            try {
                outStream = FileOutputStream(fileJPEG)
                drawable?.toBitmap()
                    ?.compress(Bitmap.CompressFormat.JPEG, CONVERT_QUALITY, outStream)
                outStream.flush()
                outStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return this
    }

    fun convertJpegToPng(): Single<File> {
        return Single.create<File> {
            val image = BitmapFactory.decodeFile(fileJPEG.absolutePath)
            val outStream: FileOutputStream?
            try {
                outStream = FileOutputStream(filePNG)
                image.compress(Bitmap.CompressFormat.PNG, CONVERT_QUALITY, outStream)
                outStream.flush()
                outStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            it.onSuccess(filePNG)
        }.subscribeOn(Schedulers.io())
    }

    fun deletePng() {
        if (filePNG.exists()) {
            filePNG.delete()
        }
    }

    companion object {
        const val IMAGE_JPEG = "nasa.jpeg"
        const val IMAGE_PNG = "nasa.png"
        const val CONVERT_QUALITY = 100
    }
}
