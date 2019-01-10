package train.android.kotlin.module.map

import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import train.android.kotlin.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_edit.*
import train.android.kotlin.room.SosRoom

/**
 * Created by 58050314 on 1/29/2018.
 */
class ActivityEditmap : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener
{

    var mMark : LatLng?=null
    var mMap: GoogleMap? = null
    var mGoogleApiClient: GoogleApiClient? = null
    val marker: Marker? = null
    var markerClicked: Boolean = false
    var lat:Double = 0.0
    var lng:Double = 0.0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        var aa = intent.getIntExtra("Id",0)
        var users = SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1]


        var button_edit:Button = findViewById(R.id.edit_button)
        var edit_name:EditText = findViewById(R.id.edit_name)
        var edit_username:EditText = findViewById(R.id.edit_username)
        var edit_email:EditText = findViewById(R.id.edit_email)
        var user_edit:TextView = findViewById(R.id.user_edit)
        var longitude:TextView = findViewById(R.id.longitude)
        var latitude:TextView = findViewById(R.id.latitude)

        var street:TextView = findViewById(R.id.street)
        var suite:TextView = findViewById(R.id.suite)
        var city:TextView = findViewById(R.id.city)
        var phone:TextView = findViewById(R.id.phone)
        var website:TextView = findViewById(R.id.website)
        var name_company:TextView = findViewById(R.id.name_company)
        var catchPhrase:TextView = findViewById(R.id.catchPhrase)
        var edit_bs:TextView = findViewById(R.id.edit_bs)
        var zipcode:TextView = findViewById(R.id.zipcode)


        edit_name.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].name)
        edit_username.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].username)
        edit_email.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].email)
        street.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].address!!.street)
        suite.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].address!!.suite)
        city.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].address!!.city)
        phone.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].phone)
        website.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].website)
        name_company.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].company!!.name_company)
        catchPhrase.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].company!!.catchPhrase)
        zipcode.setText(SosRoom.getAppDatabase(this).callAppDao().getUsersAll()[aa-1].address!!.zipcode)





        var mLng ="%.4f".format(lng)
        longitude.setText(mLng)
        latitude.setText(lat.toString().format(2))


        Log.i("Edit",edit_name.toString())


        val db = SosRoom.getAppDatabase(this).callAppDao()
        user_edit.text = ("UserID : "+aa)



        longitude.text = (" Longitude :"+users.address!!.geoModel!!.lng!!.toDouble())
        latitude.text = ("Latitude : "+users.address!!.geoModel!!.lat!!.toDouble())




//        val bundle = this.intent.extras
//        val Mode = bundle.getInt("Mode")


        button_edit.setOnClickListener {
            Log.i("Edit",edit_name.toString())
            Log.i("username", edit_username.toString())
            Toast.makeText(this,"Name : "+edit_name.text+" \n "+"Username : "+edit_username.text
                    +" \n "+"Email : "+edit_email.text, Toast.LENGTH_SHORT).show()

            users.name= edit_name.text.toString()
            users.username = edit_username.text.toString()
            users.email = edit_email.text.toString()
            users.address!!.geoModel!!.lat = lat.toString()
            users.address!!.geoModel!!.lng = lng.toString()

            users.phone = phone.text.toString()
            users.website = website.text.toString()
            users.address!!.street = street.text.toString()
            users.address!!.suite = suite.text.toString()
            users.address!!.city = city.text.toString()
            users.company!!.name_company = name_company.text.toString()
            users.company!!.catchPhrase = catchPhrase.text.toString()
            users.company!!.bs = edit_bs.text.toString()
            users.address!!.zipcode = zipcode.text.toString()


            Log.i("aaa111",users.address!!.geoModel!!.lng)
            Log.i("bbb222",users.address!!.geoModel!!.lat)

            db.updateUsers(users)


        }

        //Google map
        val mapFragment = MapFragment.newInstance()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.map_edit, mapFragment)
        fragmentTransaction.commit()
        mapFragment.getMapAsync(this)

        markerClicked = false

//        setMarkerClickListener(map)
//        setMarkerDragListener(map)
//        setInfoWindowClickListener(map)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap = googleMap
        mMap!!.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap!!.setOnMapClickListener(this)
        mMap!!.setOnMapLongClickListener(this)
        mMap!!.setOnMarkerDragListener(this)





    }

    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient!!.connect()
    }

    override fun onConnected(p0: Bundle?) {

    }

    override fun onConnectionSuspended(p0: Int) {

    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    override fun onLocationChanged(location: Location) {

    }

    //Drag Marker
    override fun onMarkerDragEnd(maker: Marker) {
        Toast.makeText(applicationContext," End     "+maker.id, Toast.LENGTH_SHORT).show()
//        var aa = intent.getIntExtra("Id",0)
//        var bb = SosRoom.getAppDatabase(applicationContext).callAppDao().getUsersAll()[aa-1]
//        var cc = SosRoom.getAppDatabase(applicationContext).callAppDao()
//        bb.address!!.geoModel!!.lat= maker.position.latitude.toString()
//        bb.address!!.geoModel!!.lng= maker.position.longitude.toString()



        lat = maker.position.latitude
        lng = maker.position.longitude

    }

    override fun onMarkerDragStart(maker: Marker) {
        Toast.makeText(applicationContext," Start   "+maker.id, Toast.LENGTH_SHORT).show()
    }

    override fun onMarkerDrag(maker: Marker) {
        longitude.setText(maker.position.longitude.toString())
        latitude.setText(maker.position.latitude.toString())
        //Toast.makeText(applicationContext," Drag    "+maker.position, Toast.LENGTH_SHORT).show()
    }

    //**********************
    override fun onMapClick(point: LatLng) {
        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(point))
        markerClicked = false
//        tv.setText(point.toString())
//        mMap!!.animateCamera(CameraUpdateFactory.newLatLng(point))
//
//        markerClicked = false
    }

    override fun onMapLongClick(point: LatLng) {
        mMap!!.addMarker(MarkerOptions()
        .position(point)
                .draggable(true))

        markerClicked = false
    }



}
