package me.xyxaini.jetdemo.fragment.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.xyxaini.jetdemo.R
import me.xyxaini.jetdemo.model.bean.RepoEntity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
class RepoRvAdapter : PagedListAdapter<RepoEntity, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        val REPO_COMPARATOR = object : DiffUtil.ItemCallback<RepoEntity>() {
            override fun areContentsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean {
                return oldItem.htmlUrl == newItem.htmlUrl
            }

            override fun areItemsTheSame(oldItem: RepoEntity, newItem: RepoEntity): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<AppCompatTextView>(R.id.repo_name)
        private val star = view.findViewById<AppCompatTextView>(R.id.repo_star_num)
        private val language = view.findViewById<AppCompatTextView>(R.id.repo_language)

        companion object {
            fun create(parent: ViewGroup): RepoViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_repo, parent, false)
                return RepoViewHolder(view)
            }
        }

        fun bind(item: RepoEntity?) {
            item?.let {
                name.text = it.name
                star.text = it.stars.toString()
                language.text = it.language
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RepoViewHolder) {
            holder.bind(getItem(position))
        }
    }

}