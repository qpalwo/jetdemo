package me.xyxaini.jetdemo.fragment.`fun`

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import me.xyxaini.jetdemo.model.bean.FunData
import me.xyxaini.jetdemo.model.bean.FunEntity
import me.xyxaini.jetdemo.model.repo.IDemoRepo

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-20
 */
class MainViewModel(private val funRepo: IDemoRepo<FunData<FunEntity>>) : ViewModel() {
    val pageNum = MutableLiveData<Int>()

    val funList = map(pageNum) {
        funRepo.getPagedList(it.toString())
    }

    val funDataSet = switchMap(funList) {
        it.funDataSet
    }

    val resState = switchMap(funList) {
        it.resState
    }

    fun updatePageNum(num: String): Boolean {
        val page = num.toInt()
        return updatePageNum(page)
    }

    fun updatePageNum(num: Int): Boolean {
        if (pageNum.value == num) {
            return false
        } else {
            pageNum.value = num
            return true
        }
    }

    fun retry() {
        funList.value?.retry?.invoke()
    }
}