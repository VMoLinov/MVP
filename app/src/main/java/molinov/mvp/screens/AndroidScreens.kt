package molinov.mvp.screens

import molinov.mvp.model.GithubUser
import molinov.mvp.view.ui.ImagesFragment
import molinov.mvp.view.ui.UserFragment
import molinov.mvp.view.ui.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class UsersScreen : SupportAppScreen() {
        override fun getFragment() = UsersFragment()
    }

    class UserScreen(private val user: GithubUser) : SupportAppScreen() {
        override fun getFragment() = UserFragment.newInstance(user)
    }

    class ImageScreen : SupportAppScreen() {
        override fun getFragment() = ImagesFragment()
    }
}
