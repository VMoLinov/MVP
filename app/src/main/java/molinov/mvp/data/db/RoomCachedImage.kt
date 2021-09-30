package molinov.mvp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCachedImage(
    @PrimaryKey
    val url: String,
    val localPath: String
)
