package pt.nextengineering.wtest.repositories

import android.content.ContentValues.TAG
import android.content.Context
import android.content.ContextWrapper
import android.os.Environment
import android.util.Log
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import okhttp3.ResponseBody
import org.apache.commons.io.IOUtils
import pt.nextengineering.wtest.RemoteDataSource.Endpoint
import pt.nextengineering.wtest.RemoteDataSource.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.viewmodels.SplashScreenViewModel


class NetworkingRepository {
    companion object {
        val sharedInstance = NetworkingRepository()
    }

    fun downloadFile(appDirectory : String, onFinnish : (Boolean) -> Unit) {
        val retrofitClient = NetworkUtils.getRetrofitInstance()
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callDownLoad = endpoint.download(true)

        callDownLoad.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "server contacted and has file")
                    try {
                        val file = File(appDirectory, BuildConfig.FILE_NAME)
                        val fileOutputStream = FileOutputStream(file) //abrir o ficheiro numa determinada diretoria
                        IOUtils.write(response.body()?.bytes(), fileOutputStream)
                        //dizer que o download terminou com o uso de callback
                        onFinnish(false)
                    }
                    //ficheiro não ficou guardado
                    catch (ex: Exception) {
                        Log.d(TAG, "file not storage")
                        //dizer que o download não foi feito
                        onFinnish(true)
                    }
                } else {
                    Log.d(TAG, "server contact failed")
                    //dizer que o download não foi feito
                    onFinnish(true)
                }
            }
            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
                //dizer que o download não foi feito
                onFinnish(true)
            }
        })
    }
}
