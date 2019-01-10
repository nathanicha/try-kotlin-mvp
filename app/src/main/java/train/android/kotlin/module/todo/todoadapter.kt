package train.android.kotlin.module.todo

/**
 * Created by nack_ on 26/1/2561.
 */
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import train.android.kotlin.R



class todoadapter(var c: Context, var usercheck: List<Usercheck>) : RecyclerView.Adapter<Checkholder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Checkholder {

        var v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.list_todo, viewGroup, false)
        val chkhold = Checkholder(v)

        return chkhold
    }


    override fun onBindViewHolder(checkholder: Checkholder, position: Int) {

        checkholder.useridchk.text= ("USER ID    "+usercheck.get(position).userId.toString())
        checkholder.idchk.text=("ID  "+usercheck.get(position).id)
        checkholder.titlechk.text=("Title  "+(usercheck.get(position).title))
        checkholder.completechk.text=("Complete    "+(usercheck.get(position).completed))
    }

    override fun getItemCount(): Int { return usercheck.size }

}