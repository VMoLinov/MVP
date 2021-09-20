package molinov.mvp.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toBitmap
import io.reactivex.rxjava3.core.Observable
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Image(var file: File) {

    fun createJpegFile(drawable: Drawable?): Image {
        file = File(file, IMAGE_JPEG)
        if (!file.exists()) {
            val outStream: FileOutputStream?
            try {
                outStream = FileOutputStream(file)
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

    fun convertJpegToPng(): Observable<File> {
        return Observable.create { emitter ->
            val fileJPEG = file
            val filePNG = File(fileJPEG.parent, IMAGE_PNG)
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
            emitter.onNext(filePNG)
        }
    }

    companion object {
        const val IMAGE_JPEG = "nasa.jpeg"
        const val IMAGE_PNG = "nasa.png"
        const val CONVERT_QUALITY = 100
    }
}
