package me.xyxaini.jetdemo.model.service.remote

import com.squareup.moshi.Moshi
import io.reactivex.Single
import me.xyxaini.jetdemo.model.Config
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author  : Xiao Yuxuan
 * @date    :  2019-07-19
 */
interface FunApi {

    @GET(Config.METHOD_GET_FUN)
    fun getFun(@Query("page") page: Int): Single<FunBean>

    companion object {
        fun create(): FunApi {
            val client = OkHttpClient.Builder()
                .build()
            return Retrofit.Builder()
                .client(client)
                .baseUrl(Config.FUN_BASE)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(FunApi::class.java)
        }
    }
}

data class FunBean(
    val data: Data,
    val code: Int,
    val msg: String
)

data class Data(
    val limit: Int,
    val list: List<FunContent>,
    val page: Int,
    val totalCount: Int,
    val totalPage: Int
)

data class FunContent(
    val content: String,
    val updateTime: String
)