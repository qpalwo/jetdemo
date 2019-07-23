package me.xyxaini.jetdemo.model

import android.app.Application
import me.xyxaini.jetdemo.model.repo.FunRepo
import me.xyxaini.jetdemo.model.repo.GitHubRepoRepo
import me.xyxaini.jetdemo.model.repo.IDemoRepo
import me.xyxaini.jetdemo.model.service.local.DemoDb
import me.xyxaini.jetdemo.model.service.remote.FunApi
import me.xyxaini.jetdemo.model.service.remote.GitHubApi

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
            IDemoRepo.Type.HUB_REPO -> {
                return GitHubRepoRepo(db, gitHubApi) as IDemoRepo<T>
            }
        }
    }

    private val db by lazy {
        DemoDb.create(app)
    }

    private val funAPi by lazy {
        FunApi.create()
    }

    private val gitHubApi by lazy {
        GitHubApi.create()
    }
}