package pt.nextengineering.wtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_postal_codes.*
import pt.nextengineering.wtest.R

class PostalCodesActivity : AppCompatActivity() {
    //private lateinit var breedsAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postal_codes)

        //initRecyclerView()
    }

    /*
    private fun initRecyclerView() {

        recycler_view.layoutManager = LinearLayoutManager(this)
        breedsAdapter = MyAdapter()
        recycler_view.adapter = breedsAdapter
    }*/
}
