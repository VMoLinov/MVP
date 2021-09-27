package molinov.mvp.navigation

import molinov.mvp.data.GitHubRepository
import molinov.mvp.data.GitHubUser
import molinov.mvp.ui.repo.RepoFragment
import molinov.mvp.ui.user.UserFragment
import molinov.mvp.ui.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GitHubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class RepoScreen(private val repo: GitHubRepository) : SupportAppScreen() {
        override fun getFragment() = RepoFragment.newInstance(repo)
    }

//    class ImageScreen : SupportAppScreen() {
//        override fun getFragment() = ImagesFragment()
//    }
}
