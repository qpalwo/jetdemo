package me.xyxaini.jetdemo.model.service

import me.xyxaini.jetdemo.model.Resource
import me.xyxaini.jetdemo.model.bean.BaseData

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
interface BaseService {
    fun getData(): List<Resource<BaseData>>
}