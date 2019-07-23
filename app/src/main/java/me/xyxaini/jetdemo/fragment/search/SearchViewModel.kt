package me.xyxaini.jetdemo.fragment.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import me.xyxaini.jetdemo.model.bean.RepoData
import me.xyxaini.jetdemo.model.bean.RepoEntity
import me.xyxaini.jetdemo.model.repo.IDemoRepo

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
class SearchViewModel(private val gitHubRepoRepo: IDemoRepo<RepoData<RepoEntity>>) : ViewModel() {
    val searchContent = MutableLiveData<String>()

    val repoData = map(searchContent) {
        gitHubRepoRepo.getPagedList(it)
    }

    val repoDataSet = switchMap(repoData) {
        it.repos
    }

    val resStatus = switchMap(repoData) {
        it.resState
    }

    fun search(content: String) {
        if (content != searchContent.value) {
            searchContent.value = content
        }
    }
}