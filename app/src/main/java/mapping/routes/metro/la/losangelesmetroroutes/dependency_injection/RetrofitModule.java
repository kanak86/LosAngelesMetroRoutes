package mapping.routes.metro.la.losangelesmetroroutes.dependency_injection;

import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mapping.routes.metro.la.losangelesmetroroutes.MetroClientApplication;
import mapping.routes.metro.la.losangelesmetroroutes.repositories.LAMetroListingRepository;
import mapping.routes.metro.la.losangelesmetroroutes.retrofitmanager.MetroListingService;
import mapping.routes.metro.la.losangelesmetroroutes.retrofitmanager.RetrofitClient;
import mapping.routes.metro.la.losangelesmetroroutes.utils.Utils;
import mapping.routes.metro.la.losangelesmetroroutes.viewmodel_factory.LAMetroViewModelFactory;

/**
 * Created by apoorvakanaksiwach on 2/8/18.
 */

@Module
public class RetrofitModule {

    private final MetroClientApplication application;

    public RetrofitModule(MetroClientApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MetroListingService provideRetrofit() {
        return RetrofitClient.INSTANCE.getClient(Utils.INSTANCE.getBASE_URL()).create(MetroListingService.class);
    }

    @Provides
    @Singleton
    LAMetroListingRepository provideListItemRepository(MetroListingService metroListingService){
        return new LAMetroListingRepository(metroListingService);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(LAMetroListingRepository repository){
        return new LAMetroViewModelFactory(repository);
    }
}
