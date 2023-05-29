package com.example.idle_rpg_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.R
import com.example.idle_rpg_project.models.Usuario

class HistoryBattleAdapter(val element: Int, private val mList: ArrayList<String>) : RecyclerView.Adapter<HistoryBattleAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(element, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemCard = mList[position]

        // sets the text to the textview from our itemHolder class
//        holder.textView.text = ItemsViewModel.text
        holder.textView.text = itemCard
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.txtBattle)
    }
}