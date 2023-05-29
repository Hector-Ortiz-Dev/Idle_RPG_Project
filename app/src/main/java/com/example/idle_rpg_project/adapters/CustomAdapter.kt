
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.idle_rpg_project.R
import com.example.idle_rpg_project.models.ItemsViewModel
//import com.example.idle_rpg_project.models.Usuario

class CustomAdapter(val context: Activity, private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemCard = mList[position]

        // sets the image to the imageview from our itemHolder class
//        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.imageView.setImageResource(itemCard.image)

        // sets the text to the textview from our itemHolder class
//        holder.textView.text = ItemsViewModel.text
        holder.textView.text = itemCard.text

        // event click in the card element
        holder.itemView.findViewById<Button>(R.id.btn_rv).setOnClickListener {
//            Log.e("button", "item $position")
//            Toast.makeText(context, ItemsViewModel.correo, Toast.LENGTH_SHORT).show()
//            Toast(context).showCustomToast (
//                "#DD0000",
//                ItemsViewModel.correo.toString(),
//                context)
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
    }
}
