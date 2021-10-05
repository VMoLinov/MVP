package molinov.mvp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import molinov.mvp.App
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun app(): Context {
        return app
    }
}
