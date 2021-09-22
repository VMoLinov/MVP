package molinov.mvp.ui.user

import android.util.ArrayMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Cicerone.create
import ru.terrakok.cicerone.Router

class LocalCiceroneHolder {

    private val containers = ArrayMap<String, Cicerone<Router>>()

    fun getCicerone(containerTag: String): Cicerone<Router> =
        containers.getOrPut(containerTag) {
            create()
        }
}