package molinov.mvp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(
    @PrimaryKey val id: Long,
    val login: String,
    val avatarUrl: String,
    val reposUrl: String
)
