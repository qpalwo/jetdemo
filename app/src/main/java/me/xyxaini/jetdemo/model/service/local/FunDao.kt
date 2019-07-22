package me.xyxaini.jetdemo.model.service.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.xyxaini.jetdemo.model.bean.FunEntity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
@Dao
interface FunDao {
    @Query("SELECT * FROM fun")
    fun funByPage(): DataSource.Factory<Int, FunEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(funEntities: List<FunEntity>)
}