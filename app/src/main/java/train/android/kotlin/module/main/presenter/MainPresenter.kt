package train.android.kotlin.module.main.presenter

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import train.android.kotlin.httpmanager.AllServiceAPI
import train.android.kotlin.httpmanager.AppHttpManager
import train.android.kotlin.model.UsersModel
import train.android.kotlin.module.main.contractor.MainContractor

/**
 * Created by Nut on 2/7/2018.
 */
class MainPresenter(var view : MainContractor.MainView) : MainContractor.MainPresenter {

    private var mainAllAPI: AllServiceAPI?=null

    override fun getData() {

        mainAllAPI = AppHttpManager.getAPI()
        mainAllAPI!!.posts.enqueue(object : Callback<List<UsersModel>>{
            override fun onResponse(call: Call<List<UsersModel>>, response: Response<List<UsersModel>>) {
                Thread(Runnable {
                    view.getDataSuccess(response.body()!!)
                }).start()
            }

            override fun onFailure(call: Call<List<UsersModel>>, t: Throwable) {
                view.getDataFailure(t.message!!)
            }
        })
    }


}