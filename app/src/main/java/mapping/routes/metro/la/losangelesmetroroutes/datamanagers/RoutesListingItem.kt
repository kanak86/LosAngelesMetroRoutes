package mapping.routes.metro.la.losangelesmetroroutes.datamanagers

/**
 * Created by apoorvakanaksiwach on 6/19/18.
 */

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RoutesListingItem {

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
}
