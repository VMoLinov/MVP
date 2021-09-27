package molinov.mvp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGithubUser::class, RoomGithubRepository::class],
    version = 1
)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: GithubDatabase? = null
        fun getInstance() =
            instance ?: throw IllegalStateException("База данных не инициализирована")

        fun create(context: Context) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, GithubDatabase::class.java, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}
