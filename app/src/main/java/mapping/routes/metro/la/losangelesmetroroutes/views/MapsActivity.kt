package mapping.routes.metro.la.losangelesmetroroutes.views

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

import javax.inject.Inject

import mapping.routes.metro.la.losangelesmetroroutes.MetroClientApplication
import mapping.routes.metro.la.losangelesmetroroutes.R
import mapping.routes.metro.la.losangelesmetroroutes.adapters.RoutesListingAdapter
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.VehiclesCurrentLocation
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.VehiclesCurrentLocationItem
import mapping.routes.metro.la.losangelesmetroroutes.events.VehiclesLocationForRouteFetchedEvent
import mapping.routes.metro.la.losangelesmetroroutes.view_model.RoutesListingViewModel
import mapping.routes.metro.la.losangelesmetroroutes.view_model.VehiclesLocationViewModel
import mapping.routes.metro.la.losangelesmetroroutes.viewmodel_factory.LAMetroViewModelFactory

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    var viewModelFactory: LAMetroViewModelFactory? = null
    @Inject set

    internal var layoutManager: RecyclerView.LayoutManager? = null
    lateinit var viewModel: VehiclesLocationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        (application as MetroClientApplication).applicationComponent.inject(this)
        EventBus.getDefault().register(this)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory!!).get(VehiclesLocationViewModel::class.java)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        viewModel.getVehicleLocations(intent.getStringExtra(RoutesListingAdapter.ROUTE_ID))
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onVehiclesLocationFetchedEvent(event: VehiclesLocationForRouteFetchedEvent) {
        mMap!!.clear()
        val vehicleLocations = event.listing
        val locationItems = vehicleLocations.value!!.items
        for (item in locationItems!!) {
            val newMarker = LatLng(item.latitude!!, item.longitude!!)
            val cameraPosition = CameraPosition.Builder()
                    .target(newMarker)
                    .zoom(10f)                   // Sets the zoom
                    .bearing(90f)                // Sets the orientation of the camera to east
                    .tilt(30f)                   // Sets the tilt of the camera to 30 degrees
                    .build()
            mMap!!.addMarker(MarkerOptions().position(newMarker)
                    .title("This Location for RunId: "+item.runId+
                            " on route: "+item.routeId+
                            " was reported " + item.secondsSinceReport + " secs ago.")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)))
            mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
            mMap!!.setOnMyLocationButtonClickListener {
                mMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                true
            }
        }
    }
}
