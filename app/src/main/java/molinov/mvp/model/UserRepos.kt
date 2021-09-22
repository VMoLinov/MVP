package molinov.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRepos(

    @Expose
    val id: Long? = null,

    @Expose
    val name: String? = null,

    @Expose
    val description: String? = null,

    @Expose
    val forks: Long? = null,

    @Expose
    val watchers: Long? = null
) : Parcelable
