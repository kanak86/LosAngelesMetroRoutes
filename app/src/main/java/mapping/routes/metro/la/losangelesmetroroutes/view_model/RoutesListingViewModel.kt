package mapping.routes.metro.la.losangelesmetroroutes.view_model

import android.arch.lifecycle.ViewModel

import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing
import mapping.routes.metro.la.losangelesmetroroutes.repositories.LAMetroListingRepository

/**
 * Created by apoorvakanaksiwach on 2/6/18.
 */

class RoutesListingViewModel(private val repository: LAMetroListingRepository) : ViewModel() {

    private val routes: RoutesListing? = null

    fun getRoutes() {
        repository.fetchRoutes()
    }

}
