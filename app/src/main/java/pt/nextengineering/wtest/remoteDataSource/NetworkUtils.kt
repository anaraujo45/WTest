package pt.nextengineering.wtest.RemoteDataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//url base do serviço
class NetworkUtils {
    companion object {  //simple singleton
        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://github.com/centraldedados/codigos_postais/blob/master/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}