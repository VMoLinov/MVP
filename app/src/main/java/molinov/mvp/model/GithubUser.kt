package molinov.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    @Expose
    val id: Long? = null,

    @Expose
    val login: String? = null,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String? = null
) : Parcelable
