package me.xyxaini.jetdemo.model.repo

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-22
 */
interface IDemoRepo<T> {
    enum class Type {
        FUN
    }

    fun getPagedList(page: Int): T
}