package train.android.kotlin.module.main.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_two.*
import train.android.kotlin.R

import train.android.kotlin.room.SosRoom
import train.android.kotlin.model.UsersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import train.android.kotlin.httpmanager.AllServiceAPI
import train.android.kotlin.module.main.adapter.FragtwoAdapter


/**
 * A simple [Fragment] subclass.
 */
class FragmentTwo : Fragment(), SwipeRefreshLayout.OnRefreshListener {


    lateinit var recyclerView: RecyclerView
    lateinit var adapter : FragtwoAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //View
        val v:View = inflater!!.inflate(R.layout.fragment_two, container, false)

        //RecyclerView
        recyclerView = v.findViewById(R.id.recycle_view) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        //Set Adapter to RecyclerView
        adapter = FragtwoAdapter(context, SosRoom.getAppDatabase(context).callAppDao().getUsersAll())
        recyclerView.adapter = adapter

        val swipeContainer =  v.findViewById(R.id.swipeContainer) as SwipeRefreshLayout
        swipeContainer.setOnRefreshListener(this)






        return v
    }


    override fun onRefresh() {

        //Retrofit

        //refresh
        loadRecyclerViewData()
        swipeContainer.setRefreshing(false)



    }

    private fun loadRecyclerViewData() {

        val appDatabase = SosRoom.getAppDatabase(context)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val apiService = retrofit.create<AllServiceAPI>(AllServiceAPI::class.java!!)
        val apiCall = apiService.posts

        if (appDatabase.callAppDao().getCountAll() < 5) {
            apiCall.enqueue(object : Callback<List<UsersModel>> {
                override fun onResponse(call: Call<List<UsersModel>>?, response: Response<List<UsersModel>>?) {

                    appDatabase.callAppDao().insertUsersAll(response?.body()!!)
                    adapter = FragtwoAdapter(context, SosRoom.getAppDatabase(context).callAppDao().getUsersAll())

                }

                override fun onFailure(call: Call<List<UsersModel>>, t: Throwable) {
                    Log.i("Fail", "Fail " + t.toString())
                }
            })
        }
        //set adapter
        adapter = FragtwoAdapter(context, SosRoom.getAppDatabase(context).callAppDao().getUsersAll())
        recyclerView.adapter = adapter

    }

}