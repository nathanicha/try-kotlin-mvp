package train.android.kotlin.module.todo


import train.android.kotlin.httpmanager.AllServiceAPI
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.RelativeLayout
import train.android.kotlin.httpmanager.AppHttpManager



class TodoPresenter(var view : Todointerface.TodoView,var id:Int) :AppCompatActivity(), Todointerface.TodoPresenter {

    lateinit var apiserver : AllServiceAPI
    lateinit var adapter:todoadapter
    lateinit var mProgress : RelativeLayout
    lateinit var recyclerView : RecyclerView


    override fun tododata() {

        apiserver = AppHttpManager.getAPI()

        apiserver.getTodo(id).enqueue(object : Callback<List<Usercheck>> {
            override fun onResponse(call: Call<List<Usercheck>>, response: Response<List<Usercheck>>) {


                var data=response.body()!!
                view.showTitle(data)



            }

            override fun onFailure(call: Call<List<Usercheck>>, t: Throwable) {
                view.showMessage(t.message!!)
                Log.i("Fail", "Fail " + t.toString())

            }

        })

    }



}