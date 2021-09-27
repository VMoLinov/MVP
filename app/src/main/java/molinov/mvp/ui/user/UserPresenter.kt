package molinov.mvp.ui.user

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import molinov.mvp.data.GitHubRepository
import molinov.mvp.data.GitHubUser
import molinov.mvp.navigation.AndroidScreens
import molinov.mvp.ui.items.IReposListPresenter
import molinov.mvp.ui.user.adapter.RepoItemView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class UserPresenter(
    private val user: GitHubUser?,
    private val router: Router
) : MvpPresenter<UserView>() {

    class RepoListPresenter : IReposListPresenter {

        val repoList = mutableListOf<GitHubRepository>()

        override var itemClickListener: ((RepoItemView) -> Unit)? = null

        override fun getCount(): Int = repoList.size

        override fun bindView(view: RepoItemView) {
            val repo = repoList[view.pos]
            view.showName(repo.name.orEmpty())
            view.showDescription(repo.description.orEmpty())
        }
    }

    val reposListPresenter = RepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        if (user != null) {
            viewState.init(user)
            loadData(user)
        }
        reposListPresenter.itemClickListener = {
            router.navigateTo(AndroidScreens.RepoScreen(reposListPresenter.repoList[it.pos]))
        }
    }

    private fun loadData(user: GitHubUser) {
        user.getRepos(user.reposUrl.orEmpty())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                reposListPresenter.repoList.addAll(it)
                viewState.updateList()
            }, {
                Log.e("UserPresenter", "Ошибка получения репозиториев", it)
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
