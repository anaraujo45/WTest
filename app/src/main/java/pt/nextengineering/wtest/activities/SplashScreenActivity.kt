package pt.nextengineering.wtest.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.android.synthetic.main.activity_splash_screen.*
import pt.nextengineering.wtest.BuildConfig
import pt.nextengineering.wtest.R

import pt.nextengineering.wtest.viewmodels.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashScreenViewModel : SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreenViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(SplashScreenViewModel::class.java)

        checkDownload()

        /*
        splashScreenViewModel.getIsUpdatingLiveDataStorage()?.observe( this, Observer {
            if(it) {
                //o download não foi feito
                showProgressBar()
            }
            else {
                //o download foi feito
                editor.putBoolean("downloadFinish",it)
                editor.apply()
                editor.commit()

                hideProgressBar()
            }
        } )
         */

        //isDownloading.value = true
        //downloadFile.downloadFile(appDirectory)

        /*
        val db = DataBaseQueriesRepository(this)

        val res = db.insertData(appDirectory)
        if(res>0){
            AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Successfully!!")
                .setPositiveButton("ok", null)
                .show()
        }
        else{
            AlertDialog.Builder(this)
                .setTitle("Message")
                .setMessage("Something is wrong...")
                .setPositiveButton("ok", null)
                .show()
        }
         */
    }

    fun checkDownload(){
        //verificação se o download já aconteceu
        val isDownloading = getSharedPreference()
        println("******************* isDownloading = $isDownloading")

        //se o valor da sharedpreference for != de false significa que o download não foi feito
        if(isDownloading != false){
            val appDirectory = this.applicationContext.applicationInfo.dataDir
            splashScreenViewModel.init(appDirectory)

            //verificação se o download terminou (ou seja se é retornado false)
            splashScreenViewModel.getIsUpdatingLiveDataDownload()?.observe( this, Observer {
                if(it==true) {
                    //o download não foi feito, tornar a pedir o download
                    checkDownload()
                }
                else {
                    //o download foi feito (isDownloading = false)
                    println("******************* isDownloading = $isDownloading")
                    readFile()
                    //sharedpreference()
                }
            } )
        }
        else {
            //eliminar ficheiro
            //verificar se está tudo na bd
            println("******************* isDownloading = $isDownloading")
            readFile()
            showProgressBar()
        }
    }

    private fun readFile() {
        val appDirectory = this.applicationContext.applicationInfo.dataDir

        csvReader().open(appDirectory.plus("/").plus(BuildConfig.FILE_NAME)) {
            readAllWithHeader().forEach { row ->
                println("$row")
            }
        }
    }

    fun sharedpreference(){
        val sharedPref = this.getSharedPreferences(BuildConfig.PREFS_NAME_DOWNLOAD, Context.MODE_PRIVATE)

        val editor =  sharedPref.edit()
        editor.putBoolean("downloadFinish",false)
        editor.apply()
        editor.commit()

        //delete sharedpreferences
        //val editor = sharedPref.edit().clear()
        //editor.apply()
        //editor.commit()
    }

    fun getSharedPreference(): Boolean {
        val sharedPref = this.getSharedPreferences(BuildConfig.PREFS_NAME_DOWNLOAD, Context.MODE_PRIVATE)
        return sharedPref.getBoolean("downloadFinish", true)
    }

    private fun showProgressBar() {
        progress_bar_splash.visibility=View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar_splash.visibility = View.GONE
    }
}
