package mapping.routes.metro.la.losangelesmetroroutes;

import android.app.Application;

import mapping.routes.metro.la.losangelesmetroroutes.dependency_injection.AppModule;
import mapping.routes.metro.la.losangelesmetroroutes.dependency_injection.ApplicationComponent;
import mapping.routes.metro.la.losangelesmetroroutes.dependency_injection.DaggerApplicationComponent;
import mapping.routes.metro.la.losangelesmetroroutes.dependency_injection.RetrofitModule;

/**
 * Created by apoorvakanaksiwach on 2/8/18.
 */

public class MetroClientApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
