@file:Suppress("DEPRECATION")

import android.app.Activity
import android.graphics.Color
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.example.idle_rpg_project.R

fun Toast.showCustomToast(color: String, message: String, activity: Activity)
{
    val layout = activity.layoutInflater.inflate (
        R.layout.custom_toast,
        activity.findViewById(R.id.toast_container)
    )

    // set the color of the FrameLayout of the message
    val buttonStatus = layout.findViewById<FrameLayout>(R.id.button_accent_border)
    buttonStatus.setBackgroundColor(Color.parseColor(color));

    // set the text of the TextView of the message
    val textView = layout.findViewById<TextView>(R.id.toast_text)
    textView.text = message

    // use the application extension function
    this.apply {
        setGravity(Gravity.TOP, 0, 40)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}