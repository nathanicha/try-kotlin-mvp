package train.android.kotlin.module.topost

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.RelativeLayout

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import train.android.kotlin.R
import train.android.kotlin.httpmanager.AllServiceAPI

class topostActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    var mAdapter: PostExpandableAdapter? = null
    lateinit var mProgress : RelativeLayout
    var pt = ArrayList<PostObj>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topost)
        mRecyclerView = this.findViewById(R.id.recycler_view_post)
        mProgress = findViewById(R.id.progress_relative)
        mProgress.visibility=GONE
        getList()

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
    }

    fun getList(){
        mProgress.visibility= VISIBLE
        var id = intent.getIntExtra("Id", 0)
        val retrofit = Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val apiService = retrofit.create<AllServiceAPI>(AllServiceAPI::class.java!!)
        var apiCall = apiService.getToPost(id)
        apiCall.enqueue(object : Callback<List<PostObj>> {

            override fun onFailure(call: Call<List<PostObj>>?, t: Throwable?) {
                Log.i("Failure", " ")
            }

            override fun onResponse(call: Call<List<PostObj>>?, response: Response<List<PostObj>>?) {


                var count:Int = response!!.body()!!.size

                for(i in 1..count) {

                    var cm = ArrayList<CmObj>()
                    val apiCall_1 = apiService.getToComment(response!!.body()!![i-1].id!!)
                    Log.i(""," "+response!!.body()!![i-1].id!!)

                    apiCall_1.enqueue(object : Callback<List<CmObj>> {

                        override fun onFailure(call: Call<List<CmObj>>?, t: Throwable?) {
                            Log.i("onFailure","Response Error")
                        }

                        override fun onResponse(call: Call<List<CmObj>>?, response_2: Response<List<CmObj>>?) {
                            var count_ = response_2!!.body()!!.size
                            for (k in 1..count_) {
                                cm.add(k-1, CmObj(response_2!!.body()!![k-1].body))
                            }

                            pt.add(PostObj(response.body()!![i-1].title!!,cm.subList(0,count_)))


                            if(i==count){
                                var list_all= ArrayList<PostObj>(pt)
                                mAdapter = PostExpandableAdapter(this@topostActivity,list_all)
                                mAdapter!!.setCustomParentAnimationViewId(R.id.title_expand_arrow)
                                mAdapter!!.setParentClickableViewAnimationDefaultDuration()
                                mAdapter!!.setParentAndIconExpandOnClick(true)
                                mRecyclerView.adapter = mAdapter
                                mProgress.visibility= GONE
                                pt.clear()
                            }


                        }

                    })

                }




            }
        })
    }

}



