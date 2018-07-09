package mapping.routes.metro.la.losangelesmetroroutes.utils

/**
 * Created by apoorvakanaksiwach on 2/4/18.
 */

object Utils {

    val BASE_URL = "http://api.metro.net/agencies/lametro/"

    fun convertMillisToHours(millis: Double?): String {
        val hours: Double
        val seconds: Double

        seconds = millis!! / 1000
        hours = seconds / (60 * 60 * 60)

        return java.lang.Double.toString(hours)
    }
}
