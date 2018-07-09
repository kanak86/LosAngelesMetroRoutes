package mapping.routes.metro.la.losangelesmetroroutes.retrofitmanager;

import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing;
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.StopsListing;
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.VehiclesCurrentLocation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by apoorvakanaksiwach on 2/4/18.
 */

public interface MetroListingService {

    /**
     * To directly a get a list of routes
     * @return
     */
    @GET("routes/")
    Call<RoutesListing> getLAMetroRoutes();

    @GET("routes/{routeNumber}/sequence/")
    Call<StopsListing> getStopsListInSequence(@Path("routeNumber") String routeId);

    @GET("routes/{routeNumber}/vehicles/")
    Call<VehiclesCurrentLocation> getVehiclesPositionForGivenRouteNumber(@Path("routeNumber") String routeId);

    @GET("vehicles/")
    Call<VehiclesCurrentLocation> getVehiclesPositionForAllRoutes();
}
