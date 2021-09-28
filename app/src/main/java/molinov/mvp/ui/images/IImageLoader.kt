package molinov.mvp.ui.images

interface IImageLoader<T> {

    fun loadTo(url: String, targetView: T)
}
