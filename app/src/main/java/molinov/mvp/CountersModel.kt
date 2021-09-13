package molinov.mvp

data class CountersModel(
    var first: Int,
    var second: Int,
    var third: Int
) {
    fun increaseFirst() = ++first
    fun increaseSecond() = ++second
    fun increaseThird() = ++third
}
