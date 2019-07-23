package me.xyxaini.jetdemo.model.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
@Entity(tableName = "repo")
data class RepoEntity(
    val htmlUrl: String,
    val name: String,
    val stars: Int,
    val language: String,
    val searchContent: String,
    val pageNum: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)