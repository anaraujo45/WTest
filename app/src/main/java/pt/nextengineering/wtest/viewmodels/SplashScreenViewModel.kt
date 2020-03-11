package pt.nextengineering.wtest.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pt.nextengineering.wtest.repositories.DataBaseQueriesRepository
import pt.nextengineering.wtest.repositories.NetworkingRepository

class SplashScreenViewModel : ViewModel(){
    //private var repositoryDataBaseRepository : DataBaseQueriesRepository = DataBaseQueriesRepository()
    private var repositoryNetworkingRepository : NetworkingRepository = NetworkingRepository.sharedInstance
    private var isFileSaved: MutableLiveData<Boolean> = MutableLiveData()
    private var isDownloading: MutableLiveData<Boolean> = MutableLiveData()

    fun init(appDirectory : String){
        //é mandado fazer o download e é usado um callback listener para termos a indicação de que o download foi finalizado
        repositoryNetworkingRepository.downloadFile(appDirectory) {
            //o download foi efetuado
            println("********************   ${isDownloading.value}")
            isDownloading.value = it
        }
    }

    fun getIsUpdatingLiveDataDownload(): LiveData<Boolean>? {
        return isDownloading
    }

    fun getIsUpdatingLiveDataStorage(): LiveData<Boolean>? {
        return isFileSaved
    }
}