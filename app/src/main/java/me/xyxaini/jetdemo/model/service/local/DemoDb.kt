package me.xyxaini.jetdemo.model.service.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.xyxaini.jetdemo.model.bean.FunEntity
import me.xyxaini.jetdemo.model.bean.RepoEntity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
@Database(
    entities = arrayOf(FunEntity::class, RepoEntity::class),
    version = 2
)
abstract class DemoDb : RoomDatabase() {

    companion object {
        fun create(context: Context): DemoDb {
            return Room.databaseBuilder(context, DemoDb::class.java, "demo.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun githubApi(): GithubDao
    abstract fun funApi(): FunDao
}