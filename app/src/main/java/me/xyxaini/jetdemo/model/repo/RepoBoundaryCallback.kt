package me.xyxaini.jetdemo.model.repo

import androidx.paging.PagedList
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.xyxaini.jetdemo.model.Resource
import me.xyxaini.jetdemo.model.bean.RepoEntity
import me.xyxaini.jetdemo.model.service.remote.GitHubApi
import me.xyxaini.jetdemo.model.service.remote.Item
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
class RepoBoundaryCallback(
    private val mSearchContent: String,
    private val mPageCount: Int,
    private val dataCallBack: (Resource<List<Item>>, Int) -> Unit,
    private val api: GitHubApi
) : PagedList.BoundaryCallback<RepoEntity>() {
    private var mRunning = AtomicBoolean(false)
    private var disposable: Disposable? = null

    override fun onZeroItemsLoaded() {
        loadPage(0)
    }

    override fun onItemAtEndLoaded(itemAtEnd: RepoEntity) {
        loadPage(itemAtEnd.pageNum + 1)
    }

    private fun loadPage(pageNum: Int) {
        if (mRunning.get()) {
            return
        }
        mRunning.compareAndSet(false, true)
        dataCallBack.invoke(Resource.loading(), -1)
        disposable?.dispose()
        disposable = api.searchRepo(mSearchContent, pageNum, mPageCount)
            .subscribeOn(Schedulers.io())
            .subscribe({
                dataCallBack.invoke(Resource.success(it.items), pageNum)
                mRunning.compareAndSet(true, false)
            }, {
                dataCallBack.invoke(Resource.error("load error"), -1)
                mRunning.compareAndSet(true, false)
            })
    }
}