package molinov.mvp.di.modules

import android.content.Context
import android.media.Image
import android.widget.ImageView
import androidx.room.Room
import dagger.Module
import dagger.Provides
import molinov.mvp.data.db.*
import molinov.mvp.ui.images.GlideImageLoader
import molinov.mvp.ui.images.IImageLoader
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    fun cacheUsers(db: GitHubDatabase): CacheUsers = RoomGithubUsersCache(db)

    @Provides
    fun cacheRepos(db: GitHubDatabase): CacheRepositories = RoomGithubRepositoriesCache(db)

    @Singleton
    @Provides
    fun db(context: Context): GitHubDatabase {
        return Room.databaseBuilder(context, GitHubDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        private const val DB_NAME = "database.db"
    }
}
