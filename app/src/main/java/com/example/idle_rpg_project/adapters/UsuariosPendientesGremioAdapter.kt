package com.example.idle_rpg_project.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.idle_rpg_project.R
import com.example.idle_rpg_project.models.Usuario
import com.example.idle_rpg_project.services.UsuarioService
import showCustomToast

class UsuariosPendientesGremioAdapter(val context: Activity, val element: Int, private val mList: List<Usuario>) : RecyclerView.Adapter<UsuariosPendientesGremioAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context).inflate(element, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @SuppressLint("Range")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemCard = mList[position]

//        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(R.drawable.guild)
        Glide.with(context).load(itemCard.urlImagen).into(holder.imageView)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = itemCard.username

        holder.textViewLevel.isGone = true

        // event click in the card element
        holder.btnAcceptMember.setOnClickListener {
            val anim = AnimationUtils.loadAnimation(context, R.anim.bounce)
            holder.btnAcceptMember.startAnimation(anim)
            acceptMember(itemCard)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textViewLevel: TextView = itemView.findViewById(R.id.textViewLevel)
        val btnAcceptMember: ImageButton = itemView.findViewById(R.id.btnAcceptMember)
    }

    @SuppressLint("Range")
    fun acceptMember(item: Usuario) {
        val data = Usuario(method = "acceptMember", idUsuario = item.id, idGremio = item.idGremio)

        val usuarioService = UsuarioService()

        usuarioService.post(data) {
            if (it == null) {
                Toast(context).showCustomToast (context.getString(R.string.error_color), "${context.getString(R.string.error_server_500)}",context)
            }
            else {
                if(it.estatus == 500){
                    Toast(context).showCustomToast (context.getString(R.string.error_color),"${context.getString(R.string.error_save_404)}", context)
                }
                else {
                    Toast(context).showCustomToast (context.getString(R.string.success_color),"${context.getString(R.string.success_accepted_member)}",context)

                    val data: Usuario = it.records[0]
                    context.finish()
                }
            }
        }
    }
}