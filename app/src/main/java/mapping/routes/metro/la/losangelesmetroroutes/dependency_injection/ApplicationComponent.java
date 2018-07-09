package mapping.routes.metro.la.losangelesmetroroutes.dependency_injection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import mapping.routes.metro.la.losangelesmetroroutes.views.MainActivity;
import mapping.routes.metro.la.losangelesmetroroutes.views.MapsActivity;
import mapping.routes.metro.la.losangelesmetroroutes.views.RoutesListFragment;

/**
 * Created by apoorvakanaksiwach on 2/8/18.
 */

@Singleton
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
    void inject(RoutesListFragment routesFragment);
    void inject(MapsActivity mapsActivity);

    Application application();
}
