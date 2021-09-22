package molinov.mvp.ui.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import molinov.mvp.App
import molinov.mvp.R
import molinov.mvp.databinding.FragmentUserBinding
import molinov.mvp.model.GithubUser
import molinov.mvp.navigation.BackButtonListener
import molinov.mvp.ui.activity.MainActivity
import molinov.mvp.ui.images.GlideImageLoader
import molinov.mvp.ui.user.adapter.ReposRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.terrakok.cicerone.android.support.SupportAppNavigator

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _vb: FragmentUserBinding? = null
    private val vb get() = _vb!!
    private val imageLoader = GlideImageLoader()
    private val adapter by lazy { ReposRVAdapter(presenter.reposListPresenter) }
    private val presenter by moxyPresenter {
        UserPresenter(
            this.arguments?.getParcelable(PARCELABLE),
            App.instance.router
        )
    }
    private val navigator by lazy {
        SupportAppNavigator(
            requireActivity(),
            parentFragmentManager,
            R.id.fragment_container
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentUserBinding.inflate(inflater, container, false)
        App.instance.navigationHolder.removeNavigator()
        App.instance.navigationHolder.setNavigator(navigator)
        return vb.root
    }

    override fun onDestroyView() {
        (activity as MainActivity).setNavigator()
        super.onDestroyView()
        _vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init(user: GithubUser) {
        user.avatarUrl?.let { imageLoader.loadTo(it, vb.avatar) }
        vb.textView.text = user.login
        vb.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        vb.rvRepos.adapter = adapter
        vb.rvRepos.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    companion object {
        const val PARCELABLE = "git_hub_user_key"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val b = Bundle()
            b.putParcelable(PARCELABLE, user)
            val f = UserFragment()
            f.arguments = b
            return f
        }
    }
}
