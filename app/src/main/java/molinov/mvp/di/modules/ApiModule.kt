package molinov.mvp.di.modules

import android.content.Context
import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import molinov.mvp.network.AndroidNetworkStatus
import molinov.mvp.network.INetworkStatus
import molinov.mvp.remote.ApiHolder
import molinov.mvp.remote.IApiHolder
import molinov.mvp.remote.IDataSource
import molinov.mvp.ui.images.GlideImageLoader
import molinov.mvp.ui.images.IImageLoader
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
interface ApiModule {

    @Binds
    fun api(impl: ApiHolder): IApiHolder

    @Binds
    fun glide(impl: GlideImageLoader): IImageLoader<ImageView>

    companion object {

        @Provides
        @Singleton
        @Named("baseUrl")
        fun baseUrl(): String = "https://api.github.com"

        @Provides
        @Singleton
        fun gson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        @Singleton
        @Provides
        fun githubUsersService(
            @Named("baseUrl") baseUrl: String,
            gson: Gson
        ): IDataSource {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(IDataSource::class.java)
        }

        @Provides
        @Singleton
        fun networkStatus(context: Context): INetworkStatus =
            AndroidNetworkStatus(context)
    }
}
