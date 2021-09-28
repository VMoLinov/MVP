package molinov.mvp.ui.images

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import molinov.mvp.data.db.GitHubDatabase
import molinov.mvp.data.db.RoomCachedImage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class GlideImageLoader(
    private val db: GitHubDatabase
) : IImageLoader<ImageView> {

    override fun loadTo(url: String, targetView: ImageView) {
        Glide.with(targetView.context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .listener(object : RequestListener<Drawable> {

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    val handler = Handler(Looper.getMainLooper())
                    Thread {
                        val dir = db.imageDao.getByUrl(url)?.localPath
                        handler.post { targetView.setImageURI(dir?.toUri()) }
                    }.start()
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    val dir = targetView.context.getDir(
                        Environment.DIRECTORY_PICTURES,
                        Context.MODE_PRIVATE
                    )
                    val split = url.split("/")
                    val name = split[split.size - 1] + ".png"
                    val file = File(dir, name)
                    if (!file.exists()) {
                        val outStream: FileOutputStream?
                        try {
                            outStream = FileOutputStream(file)
                            resource?.toBitmap()
                                ?.compress(
                                    Bitmap.CompressFormat.PNG,
                                    100, outStream
                                )
                            outStream.flush()
                            outStream.close()
                            Thread {
                                db.imageDao.insert(RoomCachedImage(url, file.path))
                            }.start()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                    targetView.setImageDrawable(resource)
                    return true
                }
            })
            .into(targetView)
    }
}
