package com.example.idle_rpg_project.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.R
import com.example.idle_rpg_project.models.Gremio
import showCustomToast

class GremioAdapter(val context: Activity, val element: Int, private val mList: List<Gremio>) : RecyclerView.Adapter<GremioAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(element, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemCard = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(R.drawable.guild)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemCard.nombre

        // event click in the card element
        holder.btnAddGremio.setOnClickListener { addMember(itemCard) }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val btnAddGremio: ImageButton = itemView.findViewById(R.id.btnAddGremio)
    }

    fun addMember(item: Gremio) {
        Toast(context).showCustomToast ("#111111","${item.nombre}", context)
    }
}