package pt.nextengineering.wtest.repositories

import android.content.ContentValues.TAG
import android.content.Context
import android.content.ContextWrapper
import android.os.Environment
import android.util.Log
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


class BreedsRepository {

    fun downloadFile(appDirectory : String) {
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

                        //println("******************   bytes e bitessss: ${response.body()?.bytes()}")
                        IOUtils.write(response.body()?.bytes(), fileOutputStream)
                    }
                    //ficheiro não ficou guardado
                    catch (ex: Exception) {
                        Log.d(TAG, "file not storage")
                    }

                } else {
                    Log.d(TAG, "server contact failed")
                }

            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }
}
