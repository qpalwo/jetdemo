package me.xyxaini.jetdemo.model.repo

import androidx.lifecycle.MutableLiveData
import androidx.paging.toLiveData
import me.xyxaini.jetdemo.model.Resource
import me.xyxaini.jetdemo.model.Status
import me.xyxaini.jetdemo.model.bean.RepoData
import me.xyxaini.jetdemo.model.bean.RepoEntity
import me.xyxaini.jetdemo.model.service.local.DemoDb
import me.xyxaini.jetdemo.model.service.remote.GitHubApi
import me.xyxaini.jetdemo.model.service.remote.Item

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
class GitHubRepoRepo(
    private val db: DemoDb,
    private val hubApi: GitHubApi
) : IDemoRepo<RepoData<RepoEntity>> {
    private var defaultPageSize = 10
    private lateinit var mContent: String

    private val resStatus = MutableLiveData<Status>()

    override fun getPagedList(content: String): RepoData<RepoEntity> {
        mContent = content
        val callback = RepoBoundaryCallback(
            content,
            defaultPageSize,
            this::insertIntoDb,
            hubApi
        )
        val repoDataSet = db.githubApi().getRepo(content).toLiveData(
            pageSize = defaultPageSize,
            boundaryCallback = callback
        )
        return RepoData(
            repoDataSet,
            resStatus
        )
    }

    private fun insertIntoDb(data: Resource<List<Item>>, page: Int) {
        data.useData({
            db.runInTransaction {
                val repos = it.map {
                    RepoEntity(
                        it.html_url,
                        it.name,
                        it.stargazers_count,
                        it.language ?: "",
                        mContent,
                        page
                    )
                }
                if (repos.size == 0) {
                    return@runInTransaction
                }
                db.githubApi().insertRepo(repos)
            }
            resStatus.postValue(Status.SUCCESS)
        }, {
            resStatus.postValue(Status.LOADING)
        }, {
            resStatus.postValue(Status.ERROR)
        })
    }
}