package mapping.routes.metro.la.losangelesmetroroutes.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log

import org.greenrobot.eventbus.EventBus

import javax.inject.Inject

import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.StopsListing
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.VehiclesCurrentLocation
import mapping.routes.metro.la.losangelesmetroroutes.events.RoutesFetchedEvent
import mapping.routes.metro.la.losangelesmetroroutes.events.VehiclesLocationForRouteFetchedEvent
import mapping.routes.metro.la.losangelesmetroroutes.retrofitmanager.MetroListingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by apoorvakanaksiwach on 2/6/18.
 */

class LAMetroListingRepository @Inject
constructor(internal var serviceInterface: MetroListingService) {

    private var routes: RoutesListing? = null
    private var stops: StopsListing? = null
    private val locations = MutableLiveData<VehiclesCurrentLocation>()

    fun fetchRoutes() {

        serviceInterface.laMetroRoutes.enqueue(object : Callback<RoutesListing> {
            override fun onResponse(call: Call<RoutesListing>, response: Response<RoutesListing>) {

                if (response.isSuccessful) {
                    routes = response.body()
                    EventBus.getDefault().post(RoutesFetchedEvent(true, routes!!))
                } else {
                    val statusCode = response.code()
                    EventBus.getDefault().post(RoutesFetchedEvent(false, routes!!))
                }
            }

            override fun onFailure(call: Call<RoutesListing>, t: Throwable) {
                //showErrorMessage();
                EventBus.getDefault().post(RoutesFetchedEvent(false, routes!!))
                Log.d("MainActivity", "error loading from API")

            }
        })
    }

    fun fetchStops(routeNumber: String): StopsListing? {

        serviceInterface.getStopsListInSequence(routeNumber).enqueue(object : Callback<StopsListing> {
            override fun onResponse(call: Call<StopsListing>, response: Response<StopsListing>) {

                if (response.isSuccessful) {
                    stops = response.body()
                } else {
                    val statusCode = response.code()
                }
            }

            override fun onFailure(call: Call<StopsListing>, t: Throwable) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API")
            }
        })
        return stops
    }

    fun fetchLocationForRoute(routeNumber: String) {
        serviceInterface.getVehiclesPositionForGivenRouteNumber(routeNumber).enqueue(object : Callback<VehiclesCurrentLocation> {
            override fun onResponse(call: Call<VehiclesCurrentLocation>, response: Response<VehiclesCurrentLocation>) {

                if (response.isSuccessful) {
                    locations.value = response.body()
                    EventBus.getDefault().post(VehiclesLocationForRouteFetchedEvent(true, locations))
                } else {
                    val statusCode = response.code()
                }
            }

            override fun onFailure(call: Call<VehiclesCurrentLocation>, t: Throwable) {
                //showErrorMessage();
                Log.d("MainActivity", "error loading from API")
            }
        })
    }

}
