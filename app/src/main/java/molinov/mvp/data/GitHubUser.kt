package molinov.mvp.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import molinov.mvp.remote.ApiHolder

@Parcelize
data class GitHubUser(
    @Expose
    val id: Long,

    @Expose
    val login: String,

    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,

    @Expose
    @SerializedName("repos_url")
    val reposUrl: String
) : Parcelable {

    fun getRepos(reposUrl: String) = ApiHolder.api.getRepos(reposUrl)
}
