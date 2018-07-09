package mapping.routes.metro.la.losangelesmetroroutes.view_model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.VehiclesCurrentLocation
import mapping.routes.metro.la.losangelesmetroroutes.repositories.LAMetroListingRepository

/**
 * Created by apoorvakanaksiwach on 6/19/18.
 */

class VehiclesLocationViewModel(private val repository: LAMetroListingRepository) : ViewModel() {

    private val locations: LiveData<VehiclesCurrentLocation>? = null

    fun getVehicleLocations(routeId: String) {
        repository.fetchLocationForRoute(routeId)
    }
}
