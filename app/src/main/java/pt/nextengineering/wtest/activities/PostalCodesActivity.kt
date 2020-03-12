package pt.nextengineering.wtest.activities

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_postal_codes.*
import kotlinx.android.synthetic.main.activity_splash_screen.*
import pt.nextengineering.wtest.R
import pt.nextengineering.wtest.adapters.MyAdapter
import pt.nextengineering.wtest.viewmodels.PostalCodesViewModel


class PostalCodesActivity : AppCompatActivity() {
    private lateinit var postalCodesViewModel: PostalCodesViewModel
    private lateinit var postalCodesAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postal_codes)

        //showProgressBar()

        //Criar o ViewModel associado a esta activity
        postalCodesViewModel = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(PostalCodesViewModel::class.java)

        postalCodesViewModel.init(this)

        postalCodesViewModel.getPostalCodesLiveData()?.observe(this, Observer {
            postalCodesAdapter.submitList(it)
        })

        postalCodesViewModel.getIsUpdatingLiveData()?.observe( this, Observer {
            if(it){
                //showProgressBar()
            }
            else{
                //hideProgressBar()
                postalCodesViewModel.getPostalCodesLiveData()?.value?.size?.let {
                    recycler_view.smoothScrollToPosition(it - 1)
                }
            }
        })
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager =  getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Log.v("Information", "Looking for $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.v("Information", "Looking for $newText")
                return false
            }
        })
        return true
    }


    private fun initRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        postalCodesAdapter = MyAdapter()
        recycler_view.adapter = postalCodesAdapter
    }

    private fun showProgressBar() {
        progress_bar_splash.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar_splash.visibility = View.GONE
    }
}
