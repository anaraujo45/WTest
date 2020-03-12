package pt.nextengineering.wtest.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
            //Done é usado para indicar que tudo foi feito com sucesso
            //FAIL é usado para indicar que algo falhou (download ou guardar na bd ou ambos)
            //LOAD é usado enquanto a app está a carregar dados
            when(it) {
                SplashScreenViewModel.States.DONE -> {
                    //intent para a nova activity
                    Toast.makeText(this, "All Data is import", Toast.LENGTH_LONG).show()
                    Handler().postDelayed({
                        startActivity(Intent(this, PostalCodesActivity::class.java))
                        finish()
                    }, 1000)
                }

                SplashScreenViewModel.States.LOAD ->{
                    Toast.makeText(this, "Loading data", Toast.LENGTH_SHORT).show()
                }

                SplashScreenViewModel.States.FAIL ->{
                    Toast.makeText(this, "Something is wrong", Toast.LENGTH_LONG).show()
                }
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
