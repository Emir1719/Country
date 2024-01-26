package com.emirozturk.country.adapter
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emirozturk.country.R
import com.emirozturk.country.adapter.CountryAdapter.ViewHolder
import com.emirozturk.country.model.Country

class CountryAdapter(private val list: ArrayList<Country>): RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.itemView.findViewById<TextView>(R.id.textCountryName).setText(list.get(i).name)
        holder.itemView.findViewById<TextView>(R.id.textCountryRegion).setText(list.get(i).region)
    }

    fun updateList(newList: ArrayList<Country>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}