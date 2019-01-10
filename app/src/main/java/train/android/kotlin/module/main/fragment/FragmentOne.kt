package train.android.kotlin.module.main.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import train.android.kotlin.R
import com.google.android.gms.maps.*

import android.content.pm.PackageManager
import android.location.Location


import android.os.Build
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog

import android.widget.Button
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import train.android.kotlin.module.album.albumActivity

import train.android.kotlin.room.SosRoom
import train.android.kotlin.module.main.adapter.CusInfoWindowAdapter
import train.android.kotlin.module.todo.todoAcitivity
import train.android.kotlin.module.topost.topostActivity


/**
 * A simple [Fragment] subclass.
 */
class FragmentOne : Fragment() , OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener
 {


     var mMap: GoogleMap? = null
     var mGoogleApiClient: GoogleApiClient? = null
     lateinit var mLastLocation: Location
     var mCurrLocationMarker: Marker? = null
     lateinit var mLocationRequest: LocationRequest

     lateinit var todo: Button
     lateinit var topost: Button
     lateinit var toalbum: Button


     override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val v = inflater!!.inflate(R.layout.fragment_one, container, false)
        //check version android
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission()
        }
         var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
         mapFragment.getMapAsync(this)

        return v

    }

    override fun onMapReady(googleMap: GoogleMap) {
        //Dialog
        val mBuilder = AlertDialog.Builder(context)
        val mView = layoutInflater.inflate(R.layout.dialog_popup, null)
        todo = mView.findViewById(R.id.button_todo) as Button
        topost = mView.findViewById(R.id.button_topost) as Button
        toalbum = mView.findViewById(R.id.button_album) as Button


        mMap = googleMap
        mMap!!.setMapType(GoogleMap.MAP_TYPE_NORMAL)
        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (ContextCompat.checkSelfPermission(context,
                                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient()
                    mMap!!.setMyLocationEnabled(true)
                }

        } else {
            buildGoogleApiClient()
            mMap!!.setMyLocationEnabled(true)
        }



        for (data in SosRoom.getAppDatabase(context).callAppDao().getUsersAll()) {

//            data.address!!.geoModel!!.lat
            var markerOpt = MarkerOptions()
            markerOpt.position(LatLng(data.address!!.geoModel!!.lat!!.toDouble(), data.address!!.geoModel!!.lng!!.toDouble() ))
                    .title(data.name)

                //Info
                var adapter = CusInfoWindowAdapter(this)
                //set adapter info + add marker
                mMap?.setInfoWindowAdapter(adapter)
                mMap?.addMarker(markerOpt)?.showInfoWindow()
                //OnClick Info
                mBuilder.setView(mView)
                val dialog: AlertDialog = mBuilder.create()

                mMap?.setOnInfoWindowClickListener {
                    //OnClick Button
                    dialog.show()
                    todo.setOnClickListener {
                        val intent = Intent(context, todoAcitivity::class.java)
                        this.startActivity(intent)
                    }

                    topost.setOnClickListener{
                        val intent =  Intent(context, topostActivity::class.java)
                        this.startActivity(intent)
                    }

                    toalbum.setOnClickListener{
                        val intent =  Intent(context,albumActivity::class.java)
                        this.startActivity(intent)
                    }

            }//Info

        }//loop

    }//OnMapReady

    @Synchronized
    protected fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
        mGoogleApiClient!!.connect()
    }

    val MY_PERMISSIONS_REQUEST_LOCATION = 99
    fun checkLocationPermission(): Boolean {

        if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                            Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(context as Activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        MY_PERMISSIONS_REQUEST_LOCATION)
            }
            return false
        } else {
            return true
        }

    }//check permission

    override fun onConnectionFailed(p0: ConnectionResult) {
    }
    override fun onConnected(p0: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
           var mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            mFusedLocationClient.requestLocationUpdates(mLocationRequest,null)

        }
    }

    override fun onConnectionSuspended(p0: Int) {
      }

    override fun onLocationChanged(location: Location) {

        mLastLocation = location
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker?.remove()
        }

        //Place current location marker
        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        mCurrLocationMarker = mMap?.addMarker(markerOptions)

        //move map camera
        mMap?.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap?.animateCamera(CameraUpdateFactory.zoomTo(11f))

        //stop location updates
        if (mGoogleApiClient != null) {


            var mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context as Activity)
            //mFusedLocationClient.removeLocationUpdates()
            //mFusedLocationClient.removeLocationUpdates(mGoogleApiClient, this)
        }

    }

     override fun onRequestPermissionsResult(requestCode: Int,
                                             permissions: Array<String>, grantResults: IntArray) {
         when (requestCode) {
             MY_PERMISSIONS_REQUEST_LOCATION -> {
                 // If request is cancelled, the result arrays are empty.
                 if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                     // permission was granted. Do the
                     // contacts-related task you need to do.
                     if (ContextCompat.checkSelfPermission(context,
                                     Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                         if (mGoogleApiClient == null) {
                             buildGoogleApiClient()
                         }
                         mMap?.setMyLocationEnabled(true)
                     }

                 } else {

                     // Permission denied, Disable the functionality that depends on this permission.
                     Toast.makeText(context, "permission denied", Toast.LENGTH_LONG).show()
                 }
                 return
             }
         }// other 'case' lines to check for other permissions this app might request.
         // You can add here other case statements according to your requirement.
     }

}//class










