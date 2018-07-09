package mapping.routes.metro.la.losangelesmetroroutes.datamanagers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VehiclesCurrentLocation {

    @SerializedName("items")
    @Expose
    var items: List<VehiclesCurrentLocationItem>? = null

}

