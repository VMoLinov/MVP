package molinov.mvp.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepository(

    @Expose
    val id: Long,

    @Expose
    val name: String,

    @Expose
    val description: String?,

    @Expose
    val forks: Long,

    @Expose
    val watchers: Long
) : Parcelable
