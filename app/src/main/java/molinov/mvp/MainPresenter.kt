package molinov.mvp

class MainPresenter(private val view: MainView) {
    private val model = CountersModel(0, 0, 0)

    fun firstCounterClick() {
        val text = model.increaseFirst().toString()
        view.setFirstButtonText(text)
    }

    fun secondCounterClick() {
        val text = model.increaseSecond().toString()
        view.setSecondButtonText(text)
    }

    fun firstThirdClick() {
        val text = model.increaseThird().toString()
        view.setThirdButtonText(text)
    }

    fun initButtons() {
        view.setFirstButtonText(model.first.toString())
        view.setSecondButtonText(model.second.toString())
        view.setThirdButtonText(model.third.toString())
    }
}
