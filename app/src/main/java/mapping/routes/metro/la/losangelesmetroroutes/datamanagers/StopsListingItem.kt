package mapping.routes.metro.la.losangelesmetroroutes.datamanagers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class StopsListingItem {

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

}