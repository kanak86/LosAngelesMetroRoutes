package mapping.routes.metro.la.losangelesmetroroutes.events

import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing

/**
 * Created by apoorvakanaksiwach on 6/19/18.
 */

class RoutesFetchedEvent(fetchSuccessful: Boolean, listing: RoutesListing) {

    lateinit var listing: RoutesListing
    var isSuccesful = false

    init {
        this.listing = listing
        isSuccesful = fetchSuccessful
    }
}
