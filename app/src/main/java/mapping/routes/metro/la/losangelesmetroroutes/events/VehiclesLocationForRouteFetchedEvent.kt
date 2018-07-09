package mapping.routes.metro.la.losangelesmetroroutes.events

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.VehiclesCurrentLocation

/**
 * Created by apoorvakanaksiwach on 6/19/18.
 */

class VehiclesLocationForRouteFetchedEvent(fetchSuccessful: Boolean, listing: MutableLiveData<VehiclesCurrentLocation>) {

    lateinit var listing: LiveData<VehiclesCurrentLocation>
        internal set
    var isSuccesful = false

    init {
        setListing(listing)
        isSuccesful = fetchSuccessful
    }

    fun setListing(listing: MutableLiveData<VehiclesCurrentLocation>) {
        this.listing = listing
    }
}
