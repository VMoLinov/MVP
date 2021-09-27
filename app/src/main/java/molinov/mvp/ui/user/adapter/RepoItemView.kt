package molinov.mvp.ui.user.adapter

import molinov.mvp.ui.items.IItemView

interface RepoItemView : IItemView {

    fun showName(name: String)
    fun showDescription(description: String)
}