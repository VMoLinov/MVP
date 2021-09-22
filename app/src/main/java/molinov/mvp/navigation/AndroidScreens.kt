package molinov.mvp.navigation

import molinov.mvp.model.GithubUser
import molinov.mvp.ui.user.UserFragment
import molinov.mvp.ui.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

//    class ImageScreen : SupportAppScreen() {
//        override fun getFragment() = ImagesFragment()
//    }
}
