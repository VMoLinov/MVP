package molinov.mvp.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import molinov.mvp.App
import molinov.mvp.R
import molinov.mvp.databinding.FragmentRepoBinding
import molinov.mvp.model.UserRepo
import molinov.mvp.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoFragment : MvpAppCompatFragment(), BackButtonListener, RepoView {

    private val presenter by moxyPresenter {
        RepoPresenter(this.arguments?.getParcelable(REPO), App.instance.router)
    }
    private var _vb: FragmentRepoBinding? = null
    private val vb get() = _vb!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentRepoBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun init(repo: UserRepo) {
        vb.header.findViewById<TextView>(R.id.repo_name).text = repo.name
        vb.forks.findViewById<TextView>(R.id.repo_forks).text = repo.forks.toString()
        vb.watchers.findViewById<TextView>(R.id.repo_watchers).text = repo.watchers.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.onBackPressed()
    }

    companion object {
        private const val REPO = "repo_parcelable_key"
        fun newInstance(repo: UserRepo): RepoFragment {
            val b = Bundle()
            b.putParcelable(REPO, repo)
            val f = RepoFragment()
            f.arguments = b
            return f
        }
    }
}
