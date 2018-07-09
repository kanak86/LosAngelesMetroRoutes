package mapping.routes.metro.la.losangelesmetroroutes.viewmodel_factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import javax.inject.Inject

import mapping.routes.metro.la.losangelesmetroroutes.repositories.LAMetroListingRepository
import mapping.routes.metro.la.losangelesmetroroutes.view_model.RoutesListingViewModel
import mapping.routes.metro.la.losangelesmetroroutes.view_model.VehiclesLocationViewModel

/**
 * Created by apoorvakanaksiwach on 2/6/18.
 */

class LAMetroViewModelFactory @Inject
constructor(private val repository: LAMetroListingRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RoutesListingViewModel::class.java)) {
            RoutesListingViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(VehiclesLocationViewModel::class.java)) {
            VehiclesLocationViewModel(repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}
