package me.xyxaini.jetdemo.model

import android.app.Application
import me.xyxaini.jetdemo.model.repo.FunRepo
import me.xyxaini.jetdemo.model.repo.IDemoRepo
import me.xyxaini.jetdemo.model.service.local.FunDb
import me.xyxaini.jetdemo.model.service.remote.FunApi

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-22
 */
interface IRepoHolder {
    companion object {
        private var INSTANCE: IRepoHolder? = null

        fun instance(app: Application): IRepoHolder {
            if (INSTANCE == null) {
                INSTANCE = RepoHolder(app)
            }
            return INSTANCE!!
        }
    }

    fun <T> getRepo(type: IDemoRepo.Type): IDemoRepo<T>
}


class RepoHolder(val app: Application) : IRepoHolder {
    override fun <T> getRepo(type: IDemoRepo.Type): IDemoRepo<T> {
        when (type) {
            IDemoRepo.Type.FUN -> {
                return FunRepo(db, funAPi) as IDemoRepo<T>
            }
        }
    }

    private val db by lazy {
        FunDb.create(app)
    }

    private val funAPi by lazy {
        FunApi.create()
    }
}