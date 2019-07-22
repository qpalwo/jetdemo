package me.xyxaini.jetdemo.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
@Entity(tableName = "fun")
data class FunEntity(
    val page: Int,
    @PrimaryKey
    val content: String,
    val createdTime: String)
