package molinov.mvp.ui.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import molinov.mvp.databinding.ItemReposBinding
import molinov.mvp.ui.items.IReposListPresenter

class ReposRVAdapter(
    private val presenter: IReposListPresenter
) : RecyclerView.Adapter<ReposRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    override fun getItemCount(): Int = presenter.getCount()

    inner class ViewHolder(private val vb: ItemReposBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {

        override var pos: Int = -1

        override fun showName(name: String) {
            vb.name.text = name
        }

        override fun showDescription(description: String) {
            vb.description.text = description
        }
    }
}
