package train.android.kotlin.module.album

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.fragment_two.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import train.android.kotlin.R
import train.android.kotlin.httpmanager.AllServiceAPI
import train.android.kotlin.model.AlbumModel

class albumActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    override fun onRefresh() {
        loadRecyclerViewData()
        swipeContainer.setRefreshing(false)
    }
    private fun loadRecyclerViewData() {


        recyclerView?.adapter = adapter

    }


    var recyclerView: RecyclerView? = null
    lateinit var adapter : albumAdapter
    lateinit var mProgress : RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)


        var id = intent.getIntExtra("Id",0)
        System.out.println("aaaaaaaaaaaaaaaaaa"+id)
        recyclerView = findViewById(R.id.recycle_viewalbum) as RecyclerView
        mProgress=findViewById(R.id.progress_rel_album)
        mProgress.visibility=GONE
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(this)

        mProgress.visibility= VISIBLE
        val retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val apiService = retrofit.create<AllServiceAPI>(AllServiceAPI::class.java!!)
        val apiCall = apiService.getAlbums(id)


            apiCall.enqueue(object : Callback<List<AlbumModel>> {
                override fun onResponse(call: Call<List<AlbumModel>>, response: Response<List<AlbumModel>>) {


                  adapter = albumAdapter(this@albumActivity, response.body()!!)
                    var aa =  albumAdapter(this@albumActivity, response.body()!!)
                    var bb =  albumAdapter(this@albumActivity, response.body()!!)

                    
                    recyclerView?.adapter = adapter
                    val swipeContainer =  findViewById(R.id.swipeContainer) as SwipeRefreshLayout
                    swipeContainer.setOnRefreshListener(this@albumActivity)
                    mProgress.visibility= GONE

                }

                override fun onFailure(call: Call<List<AlbumModel>>, t: Throwable) {
                    Log.i("Fail", "Fail " + t.toString())
                }
            })
        }










    }

