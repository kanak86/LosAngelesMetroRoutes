package mapping.routes.metro.la.losangelesmetroroutes.datamanagers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VehiclesCurrentLocationItem {

    @SerializedName("run_id")
    @Expose
    var runId: String? = null
    @SerializedName("seconds_since_report")
    @Expose
    var secondsSinceReport: Int? = null
    @SerializedName("heading")
    @Expose
    var heading: Double? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("route_id")
    @Expose
    var routeId: String? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("predictable")
    @Expose
    var predictable: Boolean? = null

}