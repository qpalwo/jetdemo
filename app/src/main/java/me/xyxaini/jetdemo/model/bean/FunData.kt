package me.xyxaini.jetdemo.model.bean

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import me.xyxaini.jetdemo.model.Status

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-22
 */
data class FunData<T>(
    val funDataSet: LiveData<PagedList<T>>,
    val resState: LiveData<Status>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)