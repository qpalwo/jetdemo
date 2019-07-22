package me.xyxaini.jetdemo.model.repo

import androidx.annotation.MainThread
import androidx.paging.PagedList
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.xyxaini.jetdemo.model.Resource
import me.xyxaini.jetdemo.model.bean.FunEntity
import me.xyxaini.jetdemo.model.service.remote.Data
import me.xyxaini.jetdemo.model.service.remote.FunApi

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-22
 */
class FunBoundaryCallback(
    private val handleResponse: (Resource<Data>) -> Unit,
    private val api: FunApi
) : PagedList.BoundaryCallback<FunEntity>() {

    private var disposable: Disposable? = null
    private var errorPage = -1
    private var running = false
    private val mLock = Unit
    private var nowEntity: FunEntity? = null

    @MainThread
    override fun onZeroItemsLoaded() {
        synchronized(mLock) {
            if (running) {
                return
            }
            running = true
            disposable?.dispose()
            handleResponse(Resource.loading())
            disposable = api.getFun(1)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handleResponse(Resource.success(it.data))
                    errorPage = -1
                    running = false
                }, {
                    handleResponse(Resource.error("load error"))
                    errorPage = 1
                    running = false
                })
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: FunEntity) {
        synchronized(mLock) {
            if (running) {
                return
            }
            running = true
            nowEntity = itemAtEnd
            disposable?.dispose()
            val nextPage = itemAtEnd.page + 1
            handleResponse(Resource.loading())
            disposable = api.getFun(nextPage)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handleResponse(Resource.success(it.data))
                    errorPage = -1
                    running = false
                }, {
                    handleResponse(Resource.error("load error"))
                    errorPage = nextPage
                    running = false
                })
        }
    }

    fun retry() {
        synchronized(mLock) {
            if (errorPage == -1 || running) {
                return
            }
            running = true
            disposable?.dispose()
            handleResponse(Resource.loading())
            disposable = api.getFun(errorPage)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    handleResponse(Resource.success(it.data))
                    errorPage = -1
                    running = false
                }, {
                    handleResponse(Resource.error("load error"))
                    running = false
                })
        }
    }

}