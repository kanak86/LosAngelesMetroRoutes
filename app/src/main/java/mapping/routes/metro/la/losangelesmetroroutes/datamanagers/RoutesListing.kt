package mapping.routes.metro.la.losangelesmetroroutes.datamanagers

/**
 * Created by apoorvakanaksiwach on 6/19/18.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RoutesListing {

    @SerializedName("items")
    @Expose
    var items: List<RoutesListingItem>? = null
}
