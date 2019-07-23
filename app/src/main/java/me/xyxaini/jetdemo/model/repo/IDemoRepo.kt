package me.xyxaini.jetdemo.model.repo

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-22
 */
interface IDemoRepo<T> {
    enum class Type {
        FUN,
        HUB_REPO
    }

    fun getPagedList(content: String): T
}