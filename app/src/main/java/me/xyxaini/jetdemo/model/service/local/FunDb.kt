package me.xyxaini.jetdemo.model.service.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.xyxaini.jetdemo.model.bean.FunEntity

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
@Database(
    entities = arrayOf(FunEntity::class),
    version = 1
)
abstract class FunDb : RoomDatabase() {

    companion object {
        fun create(context: Context): FunDb {
            return Room.databaseBuilder(context, FunDb::class.java, "demo.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun funApi(): FunDao
}