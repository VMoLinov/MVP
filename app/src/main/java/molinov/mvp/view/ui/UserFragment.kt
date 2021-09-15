package molinov.mvp.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import molinov.mvp.App
import molinov.mvp.databinding.FragmentUserBinding
import molinov.mvp.model.GithubUser
import molinov.mvp.presentation.UserPresenter
import molinov.mvp.view.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _vb: FragmentUserBinding? = null
    private val vb get() = _vb!!
    private val presenter by moxyPresenter {
        UserPresenter(
            this.arguments?.getParcelable(PARCELABLE),
            App.instance.router
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentUserBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun init(name: String?) {
        vb.textView.text = name
    }

    companion object {
        const val PARCELABLE = "key"
        fun newInstance(user: GithubUser): MvpAppCompatFragment {
            val b = Bundle()
            b.putParcelable(PARCELABLE, user)
            val f = UserFragment()
            f.arguments = b
            return f
        }
    }
}
