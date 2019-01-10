
package train.android.kotlin.module.main.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import train.android.kotlin.R


class FragtwoViewHolder(var view: View) : RecyclerView.ViewHolder(view) {


        val userid = view.findViewById(R.id.user_id) as TextView
        val name = view.findViewById(R.id.user_name) as TextView
        val username = view.findViewById(R.id.user_username) as TextView
        val email = view.findViewById(R.id.user_email) as TextView
        var relativeLayout = view.findViewById(R.id.relLayout) as RelativeLayout
        val button_website = view.findViewById(R.id.button_website) as Button
        val button_todo = view.findViewById(R.id.button_todo) as Button
        val button_topost = view.findViewById(R.id.button_topost) as Button
        val button_album = view.findViewById(R.id.button_album) as Button
        val button_edit = view.findViewById(R.id.button_edit) as Button








}

