package com.emirozturk.country.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emirozturk.country.R
import com.emirozturk.country.adapter.CountryAdapter.ViewHolder
import com.emirozturk.country.databinding.ItemCountryBinding
import com.emirozturk.country.model.Country
import com.emirozturk.country.view.FeedFragmentDirections

class CountryAdapter(private val list: ArrayList<Country>): RecyclerView.Adapter<ViewHolder>(), OnTabListener {
    class ViewHolder(view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root) {
        val binding = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemCountryBinding.inflate(inflater, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {
        holder.binding.country = list[i]
        holder.binding.listener = this
    }

    fun updateList(newList: List<Country>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onTab(view: View) {
        val id = view.findViewById<TextView>(R.id.textId).text.toString()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(id.toInt())
        Navigation.findNavController(view).navigate(action)
    }
}