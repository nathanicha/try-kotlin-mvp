package train.android.kotlin.module.main.adapter

import android.content.Context
import android.content.Intent

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import train.android.kotlin.R

import train.android.kotlin.room.SosRoom
import train.android.kotlin.model.UsersModel
import train.android.kotlin.module.todo.todoAcitivity
import train.android.kotlin.module.topost.topostActivity
import train.android.kotlin.module.webview.webActivity
import android.view.View.OnLongClickListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import train.android.kotlin.module.main.viewholder.FragtwoViewHolder
import train.android.kotlin.module.map.ActivityEditmap
import train.android.kotlin.module.album.albumActivity

import train.android.kotlin.httpmanager.AllServiceAPI


class FragtwoAdapter(var c: Context, var user: List<UsersModel>) : RecyclerView.Adapter<FragtwoViewHolder>() {


    val db=SosRoom.getAppDatabase(c).callAppDao()
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): FragtwoViewHolder {


        var v = LayoutInflater.from(c)
                .inflate(R.layout.list_layout, parent, false)
        val holders = FragtwoViewHolder(v)




        return holders
    }


    override fun onBindViewHolder(holder: FragtwoViewHolder, position: Int) {




        holder.userid.text= "USER ID    "+user.get(position).id.toString()
        holder.name.text="NAME  "+user.get(position).name
        holder.username.text="USERNAME  "+(user.get(position).username)
        holder.email.text="EMAIL    "+(user.get(position).email)

        //click button to Website
        holder.button_website.setOnClickListener(View.OnClickListener {
            val intent =  Intent(c, webActivity::class.java)

            intent.putExtra("Url", user.get (position).website)
            c.startActivity(intent)
        })
        //click button to To do
        holder.button_todo.setOnClickListener(View.OnClickListener {
            val intent =  Intent(c,todoAcitivity::class.java)

            intent.putExtra("Id", user.get (position).id)
            c.startActivity(intent)
        })
        //click button to To post/comment
        holder.button_topost.setOnClickListener(View.OnClickListener {
            val intent =  Intent(c,topostActivity::class.java)
            intent.putExtra("Id",user.get(position).id)
            c.startActivity(intent)
        })
        //click button to AlbumModel
        holder.button_album.setOnClickListener(View.OnClickListener {
            val intent =  Intent(c, albumActivity::class.java)

            intent.putExtra("Id",user.get(position).id)

            c.startActivity(intent)
        })
        //click
        holder.button_edit.setOnClickListener(View.OnClickListener {
            val intent = Intent(c, ActivityEditmap::class.java)
            intent.putExtra("Id", user.get (position).id)
            c.startActivity(intent)
        })

        //delete User
        holder.relativeLayout.setOnLongClickListener(OnLongClickListener {

            var aa=user.get(position)
            Toast.makeText(c, " Delete ", Toast.LENGTH_SHORT).show()
            //delete user in database
            db.deleteUsers(aa)

            //update data in Adapter
            user = SosRoom.getAppDatabase(c).callAppDao().getUsersAll()

            //Retrofit

            if ( SosRoom.getAppDatabase(c).callAppDao().getCountAll() <5) {

                val retrofit = Retrofit.Builder()
                        .baseUrl("http://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                val apiService = retrofit.create<AllServiceAPI>(AllServiceAPI::class.java!!)
                val apiCall = apiService.posts
                val appDatabase = SosRoom.getAppDatabase(c)

                apiCall.enqueue(object : Callback<List<UsersModel>> {
                    override fun onResponse(call: Call<List<UsersModel>>?, response: Response<List<UsersModel>>?) {
                        appDatabase.callAppDao().insertUsersAll(response?.body()!!)
                    }
                    override fun onFailure(call: Call<List<UsersModel>>, t: Throwable) {
                        Log.i("Fail", "Fail " + t.toString())
                    }
                })
            }

            //delete data
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,db.getCountAll())


            true
        })


    }


    override fun getItemCount(): Int {
        return user.size
    }



}