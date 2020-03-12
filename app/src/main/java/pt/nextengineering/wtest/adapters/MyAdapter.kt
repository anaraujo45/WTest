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
    private  var listPostalCodes : List<PostalCodes> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BreedViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_list_item, parent,
                false)
        )
    }

    override fun getItemCount(): Int {
        return listPostalCodes.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BreedViewHolder -> {
                holder.bind(listPostalCodes.get(position))
            }
        }
    }

    fun submitList(breedsList: List<PostalCodes>) {
        this.listPostalCodes = breedsList
        notifyDataSetChanged()
    }

    class BreedViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val stringPostCode : TextView = itemView.textView_postalCode

        fun bind(postalCodes: PostalCodes) {
            val codPostal = postalCodes.num_cod_postal
            val extCod = postalCodes.ext_cod_postal
            val desig = postalCodes.desig_postal

            this.stringPostCode.text = codPostal.toString().plus(" - ").plus(extCod).plus(", ").plus(desig)
        }
    }
}

