package pt.nextengineering.wtest.RemoteDataSource

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Streaming
import retrofit2.http.Url
//endpoint do serviço
interface Endpoint {
    @GET("codigos_postais.csv?raw=true")
    fun download(@Query("raw") raw: Boolean): Call<ResponseBody>
}