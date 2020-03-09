package pt.nextengineering.wtest.activities

import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import pt.nextengineering.wtest.R
import pt.nextengineering.wtest.models.InsertData
import pt.nextengineering.wtest.repositories.BreedsRepository

class SplashScreenActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT:Long=1000 // 1s
    private val downloadFile = BreedsRepository()
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //full screen
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)

        val appDirectory = this.applicationContext.applicationInfo.dataDir

        /*
        //send to the new activity
        Handler().postDelayed({
            startActivity(Intent(this, PostalCodesActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
        */

        //verificar se é necessário fazer o download
        //a flag deve ser capaz de identificar se o ficheiro está armazenado e não funcionar diretamente desta forma
        /*if (flag == false){
            Toast.makeText(this, "Download in execution, please wait a moment...", Toast.LENGTH_LONG).show()

            downloadFile.export(this)
            startActivity(Intent(this, PostalCodesActivity::class.java))
        }*/

        downloadFile.downloadFile(appDirectory)

        val db = InsertData(this)
        val res = db.insertData(appDirectory)
        if (res != null) {
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
        }
    }
}
