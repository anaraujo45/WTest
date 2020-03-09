package pt.nextengineering.wtest.RemoteDataSource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {  //simple singleton
        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://github.com/centraldedados/codigos_postais/blob/master/data/")
                    //https://github.com/centraldedados/codigos_postais/blob/master/data/codigos_postais.csv?raw=true
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}