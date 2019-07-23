package me.xyxaini.jetdemo.model.service.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.xyxaini.jetdemo.model.bean.RepoEntity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-23
 */
@Dao
interface GithubDao {
    @Query("SELECT * FROM repo WHERE searchContent = :searchContent")
    fun getRepo(searchContent: String): DataSource.Factory<Int, RepoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepo(repos: List<RepoEntity>)
}