package train.android.kotlin.httpmanager


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Nut on 2/7/2018.
 */
class AppHttpManager {
    companion object {
        fun getAPI(): AllServiceAPI {
            var retrofit = Retrofit.Builder()
                    .baseUrl("http://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retrofit.create<AllServiceAPI>(AllServiceAPI::class.java)
        }

    }

}