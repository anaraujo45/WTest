package pt.nextengineering.wtest.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_splash_screen.*
import pt.nextengineering.wtest.R
import pt.nextengineering.wtest.viewmodels.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var splashScreenViewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        showProgressBar()

        //Criar o ViewModel associado a esta activity
        splashScreenViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(SplashScreenViewModel::class.java)

        //mandar fazer load/init para conseguirmos observar abaixo
        splashScreenViewModel.init(this)

        //observar o isLoad que dará a indicação se tudo está concluido (download+guardar na bd)
        splashScreenViewModel.getIsUpdatingLiveDataLoaded()?.observe(this, Observer {
            //se true significa que o download e o storage na bd foi efetuado
            if (it) {
                hideProgressBar()
                //intent para a nova activity
                Toast.makeText(this, "Boa", Toast.LENGTH_LONG)
            }
            //algo correu mal
            else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG)
            }
        })
    }

    private fun showProgressBar() {
        progress_bar_splash.visibility=View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar_splash.visibility = View.GONE
    }
}
