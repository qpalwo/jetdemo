package me.xyxaini.jetdemo.fragment

import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main.*
import me.xyxaini.jetdemo.R
import me.xyxaini.jetdemo.base.BaseFragment
import me.xyxaini.jetdemo.model.IRepoHolder
import me.xyxaini.jetdemo.model.bean.FunData
import me.xyxaini.jetdemo.model.bean.FunEntity
import me.xyxaini.jetdemo.model.repo.FunRepo
import me.xyxaini.jetdemo.model.repo.IDemoRepo
import me.xyxaini.jetdemo.model.service.local.FunDb

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
class MainFragment : BaseFragment() {
    private lateinit var model: MainViewModel

    override fun initView() {
        model = getViewModel(context!!)
        val adapter = MainRvAdapter {
            model.retry()
        }
        model.funDataSet.observe(this, Observer {
            adapter.submitList(it)
        })
        model.resState.observe(this, Observer {
            adapter.setResState(it)
        })
        rv_main.adapter = adapter
    }

    override fun initAction() {
        confirm_button.setOnClickListener {
            if (model.updatePageNum(page_num.text.toString())) {
                rv_main.scrollToPosition(0)
                (rv_main.adapter as? MainRvAdapter)?.submitList(null)
            }
        }
        model.updatePageNum(1)
    }

    override fun getLayoutResId(): Int = R.layout.fragment_main

    private fun getViewModel(context: Context): MainViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repo = IRepoHolder.instance(activity!!.application).getRepo<FunData<FunEntity>>(IDemoRepo.Type.FUN)
                return MainViewModel(repo) as T
            }
        })[MainViewModel::class.java]
    }
}