package molinov.mvp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class, RoomCachedImage::class],
    version = 1
)
abstract class GitHubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val imageDao: CachedImagesDao
}
