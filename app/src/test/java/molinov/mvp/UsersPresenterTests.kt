package molinov.mvp

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import junit.framework.Assert.assertTrue
import molinov.mvp.data.GitHubUser
import molinov.mvp.data.GitHubUsersRepo
import molinov.mvp.ui.users.UsersPresenter
import molinov.mvp.ui.users.`UsersView$$State`
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Router


class UsersPresenterTests {

    lateinit var presenter: UsersPresenter

    @Mock
    lateinit var viewState: `UsersView$$State`

    @Mock
    lateinit var router: Router

    @Mock
    lateinit var usersRepo: GitHubUsersRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        `when`(usersRepo.getUsers()).thenReturn(Single.just(listOf(USER)))
        presenter = UsersPresenter(usersRepo, router)
        presenter.setViewState(viewState)
    }

    @Test
    fun usersPresenterLoadData() {
        presenter.loadData()
        verify(usersRepo, atLeastOnce()).getUsers()
        verify(viewState, atLeastOnce()).updateList()
    }

    @Test
    fun usersPresenterBackPressed() {
        assertTrue(presenter.backPressed())
        verify(router, atLeastOnce()).exit()
    }

    companion object {
        val USER = GitHubUser(1, "a", "b", "c")
    }
}
