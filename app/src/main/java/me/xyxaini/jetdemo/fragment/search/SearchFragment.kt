package me.xyxaini.jetdemo.fragment.search

import android.text.Editable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_search.*
import me.xyxaini.jetdemo.R
import me.xyxaini.jetdemo.base.BaseFragment
import me.xyxaini.jetdemo.model.IRepoHolder
import me.xyxaini.jetdemo.model.bean.RepoData
import me.xyxaini.jetdemo.model.bean.RepoEntity
import me.xyxaini.jetdemo.model.repo.IDemoRepo

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
class SearchFragment : BaseFragment() {
    private lateinit var model: SearchViewModel

    private val DEFAULT_SEARCH = "jetdemo"

    override fun initView() {
        model = getViewModel()
        val adapter = RepoRvAdapter()
        rv_search.adapter = adapter
        rv_search.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        model.repoDataSet.observe(this, Observer {
            adapter.submitList(it)
        })
        model.searchContent.value = DEFAULT_SEARCH
        model.searchContent.observe(this, Observer {
            et_search.text = Editable.Factory.getInstance().newEditable(it)
        })
    }

    override fun initAction() {
        search_button.setOnClickListener {
            model.search(et_search.text.toString())
        }
    }

    override fun getLayoutResId() = R.layout.fragment_search

    private fun getViewModel(): SearchViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = IRepoHolder.instance(activity!!.application)
                    .getRepo<RepoData<RepoEntity>>(IDemoRepo.Type.HUB_REPO)
                return SearchViewModel(repo) as T
            }
        })[SearchViewModel::class.java]
    }
}