package molinov.mvp.ui.images

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : IImageLoader<ImageView> {

    override fun loadTo(url: String, target: ImageView) {
        Glide.with(target.context)
            .load(url)
            .into(target)
    }
}
