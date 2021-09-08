package molinov.mvp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import molinov.mvp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {

    private var _vb: ActivityMainBinding? = null
    private val vb get() = _vb!!
    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
        presenter.initButtons()
        vb.btnCounter1.setOnClickListener { presenter.firstCounterClick() }
        vb.btnCounter2.setOnClickListener { presenter.secondCounterClick() }
        vb.btnCounter3.setOnClickListener { presenter.firstThirdClick() }
    }

    override fun setFirstButtonText(text: String) {
        vb.btnCounter1.text = text
    }

    override fun setSecondButtonText(text: String) {
        vb.btnCounter2.text = text
    }

    override fun setThirdButtonText(text: String) {
        vb.btnCounter3.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        _vb = null
    }
}
