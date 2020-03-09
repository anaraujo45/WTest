package pt.nextengineering.wtest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_list_item.view.*
import pt.nextengineering.wtest.R
import pt.nextengineering.wtest.models.PostalCodes

class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items: List<PostalCodes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BreedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_list_item, parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BreedViewHolder -> {
                holder.bind(items.get(position))
            }
        }
    }

    fun submitList(breedsList: List<PostalCodes>) {
        this.items = breedsList
        notifyDataSetChanged()
    }

    class BreedViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val stringPostCode : TextView = itemView.textView_postalCode

        fun bind(breed: PostalCodes) {
            /*
            val codPostal = breed.codPostal
            val extCod = breed.extCod
            val desig = breed.desig
             */

            val codPostal = 5100
            val extCod = 520
            val desig = "Lamego"

            this.stringPostCode.text = codPostal.toString().plus(" - ").plus(extCod).plus(", ").plus(desig)
        }
    }
}