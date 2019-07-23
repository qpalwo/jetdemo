package me.xyxaini.jetdemo.model.bean

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import me.xyxaini.jetdemo.model.Status

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
data class RepoData<T>(
    val repos: LiveData<PagedList<T>>,
    val resState: LiveData<Status>
)