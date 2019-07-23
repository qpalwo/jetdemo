package me.xyxaini.jetdemo.model.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import me.xyxaini.jetdemo.model.Resource
import me.xyxaini.jetdemo.model.Status
import me.xyxaini.jetdemo.model.bean.FunData
import me.xyxaini.jetdemo.model.bean.FunEntity
import me.xyxaini.jetdemo.model.service.local.DemoDb
import me.xyxaini.jetdemo.model.service.remote.Data
import me.xyxaini.jetdemo.model.service.remote.FunApi

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
class FunRepo(
    private val db: DemoDb,
    private val funApi: FunApi
) : IDemoRepo<FunData<FunEntity>> {

    private var defaultPageSize = 10
    private val resState = MutableLiveData<Status>()

    private fun insertIntoDb(res: Resource<Data>) {
        res.useData({ data ->
            db.runInTransaction {
                val funList = data.list.map {
                    FunEntity(data.page, it.content, it.updateTime)
                }
                db.funApi().insert(funList)
                defaultPageSize = data.limit
            }
            resState.postValue(Status.SUCCESS)
        }, {
            resState.postValue(Status.LOADING)
        }, {
            resState.postValue(Status.ERROR)
        })
    }

    private fun refresh() {

    }


    override fun getPagedList(content: String): FunData<FunEntity> {
        val callback = FunBoundaryCallback(this::insertIntoDb, funApi)
        val funDataSet = db.funApi().funByPage().toLiveData(
            pageSize = defaultPageSize,
            boundaryCallback = callback
        )
        return FunData(
            funDataSet,
            resState,
            this::refresh
        ) { callback.retry() }
    }
}