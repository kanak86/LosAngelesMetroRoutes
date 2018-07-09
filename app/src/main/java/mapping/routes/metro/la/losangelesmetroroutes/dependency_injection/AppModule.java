package mapping.routes.metro.la.losangelesmetroroutes.dependency_injection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mapping.routes.metro.la.losangelesmetroroutes.MetroClientApplication;

/**
 * Created by apoorvakanaksiwach on 2/8/18.
 */

@Module
public class AppModule {

    private final MetroClientApplication application;

    public AppModule(MetroClientApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

}
