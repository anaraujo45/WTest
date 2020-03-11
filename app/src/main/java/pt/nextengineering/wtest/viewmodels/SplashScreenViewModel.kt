package pt.nextengineering.wtest.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.nextengineering.wtest.repositories.LocalDataBaseRepository
import pt.nextengineering.wtest.repositories.NetworkingRepository

class SplashScreenViewModel() : ViewModel(){
    private lateinit var repositoryLocalDataBaseRepository : LocalDataBaseRepository
    private var repositoryNetworkingRepository : NetworkingRepository = NetworkingRepository.sharedInstance
    private var isLoaded: MutableLiveData<Boolean> = MutableLiveData()

    fun init(context: Context){
        repositoryLocalDataBaseRepository = LocalDataBaseRepository(context)

        //diretorio para o ficheiro
        val appDirectory = context.applicationContext.applicationInfo.dataDir

        //listener responsável por mandar ou não efetuar o download
        //caso seja dada a garantia de que o ficheiro existe não é mandado fazer novamente o download
        repositoryNetworkingRepository.fileExists(context, appDirectory){ it ->
            //se o ficheiro existe (it=true) então não é mandado fazer o download
            if (it){
                //como o ficheiro existe é mandado guardar os dados do ficheiro na bd
                repositoryLocalDataBaseRepository.insertData(appDirectory, context){ note ->
                    //se it=true significa que está guardado o ficheiro na bd
                    if(note){
                        //é notificada a activity de que pode avançar, pois tudo está guardado na bd
                        isLoaded.value = note
                    }
                    else{
                        //não foi guardado com sucesso os dados na bd
                        //é retornado false
                        isLoaded.value = note
                    }
                }
            }
            //o ficheiro não existe, é mandado fazer o download
            //else{
            //    repositoryNetworkingRepository.downloadFile(appDirectory)
            //}
        }
    }

    fun getIsUpdatingLiveDataLoaded(): LiveData<Boolean>? {
        return isLoaded
    }

}