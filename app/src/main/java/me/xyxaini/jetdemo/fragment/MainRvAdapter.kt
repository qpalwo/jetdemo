package me.xyxaini.jetdemo.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.xyxaini.jetdemo.R
import me.xyxaini.jetdemo.model.Status
import me.xyxaini.jetdemo.model.bean.FunEntity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-22
 */
class MainRvAdapter(
    val retry: () -> Unit
) : PagedListAdapter<FunEntity, RecyclerView.ViewHolder>(FUN_COMPARATOR) {

    private var resState = Status.SUCCESS

    companion object {
        val FUN_COMPARATOR = object : DiffUtil.ItemCallback<FunEntity>() {
            override fun areItemsTheSame(oldItem: FunEntity, newItem: FunEntity): Boolean =
                oldItem.createdTime == newItem.createdTime

            override fun areContentsTheSame(oldItem: FunEntity, newItem: FunEntity): Boolean =
                oldItem.createdTime == newItem.createdTime

        }
    }

    class FunViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val content = view.findViewById<AppCompatTextView>(R.id.content)
        private val time = view.findViewById<AppCompatTextView>(R.id.created_time)

        companion object {
            fun create(parent: ViewGroup): FunViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_fun, parent, false)
                return FunViewHolder(view)
            }
        }

        fun bind(funEntity: FunEntity?) {
            funEntity?.let {
                content.text = funEntity.content
                time.text = funEntity.createdTime
            }
        }

    }

    class FunRetryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun create(parent: ViewGroup): FunRetryViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_retry, parent, false)
                return FunRetryViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            R.layout.item_fun -> FunViewHolder.create(parent)
            R.layout.item_retry -> FunRetryViewHolder.create(parent)
            else -> FunRetryViewHolder.create(parent)
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FunViewHolder) {
            holder.bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int) =
        if (showRetry() && position == itemCount) {
            R.layout.item_retry
        } else {
            R.layout.item_fun
        }

    override fun getItemCount() = super.getItemCount() + if (showRetry()) 1 else 0


    fun setResState(state: Status) {
        val oldState = resState
        val oldShow = showRetry()
        resState = state
        if (oldShow != showRetry()) {
            if (!showRetry()) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        }
    }

    private fun showRetry(): Boolean = false
}