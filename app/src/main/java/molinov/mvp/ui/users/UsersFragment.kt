package molinov.mvp.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import molinov.mvp.App
import molinov.mvp.data.GitHubUsersRepo
import molinov.mvp.data.db.GitHubDatabase
import molinov.mvp.data.db.RoomGithubUsersCache
import molinov.mvp.databinding.FragmentUsersBinding
import molinov.mvp.navigation.BackButtonListener
import molinov.mvp.network.AndroidNetworkStatus
import molinov.mvp.ui.images.GlideImageLoader
import molinov.mvp.ui.users.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _vb: FragmentUsersBinding? = null
    private val vb get() = _vb!!
    private val presenter by moxyPresenter {
        UsersPresenter(
            GitHubUsersRepo(
                RoomGithubUsersCache(GitHubDatabase.getInstance()),
                AndroidNetworkStatus(requireContext()),
            ),
            App.instance.router
        )
    }
    private val adapter by lazy { UsersRVAdapter(presenter.usersListPresenter, GlideImageLoader()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentUsersBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        vb.rvUsers.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }
}
