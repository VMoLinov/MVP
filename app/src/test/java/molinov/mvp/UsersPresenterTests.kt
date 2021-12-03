package molinov.mvp

import android.content.Context
import android.content.res.Resources
import android.os.Looper
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import molinov.mvp.data.GitHubUser
import molinov.mvp.data.GitHubUsersRepo
import molinov.mvp.data.db.CacheUsers
import molinov.mvp.network.AndroidNetworkStatus
import molinov.mvp.ui.users.UsersPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


class UsersPresenterTests {

    private val cicerone: Cicerone<Router> by lazy { Cicerone.create() }
    private val networkStatus = true
    lateinit var presenter: UsersPresenter
    lateinit var usersRepo: GitHubUsersRepo
    private val router get() = cicerone.router

    @Mock
    lateinit var cacheUsers: CacheUsers

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var resources: Resources

    @Mock
    lateinit var network: AndroidNetworkStatus

    @Mock
    lateinit var looper: Looper

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        `when`(context.applicationContext).thenReturn(context)
        `when`(context.resources).thenReturn(resources)
        `when`(context.mainLooper).thenReturn(looper)
        `when`(network.isOnline()).thenReturn(Observable.just(networkStatus))
        `when`(network.isOnlineSingle()).thenReturn(Single.just(networkStatus))
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        usersRepo = GitHubUsersRepo(cacheUsers, network)
        `when`(usersRepo.getUsers()).thenReturn(
            Single.just(listOf(GitHubUser(1, "fg", "sdg", "sdg"))))
        presenter = UsersPresenter(usersRepo, router)
    }

    @Test
    fun firstAttach() {
        presenter.loadData()
        verify(usersRepo, times(1)).getUsers()
    }
}
