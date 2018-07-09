package mapping.routes.metro.la.losangelesmetroroutes.views

import android.content.Intent
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

import mapping.routes.metro.la.losangelesmetroroutes.R
import mapping.routes.metro.la.losangelesmetroroutes.adapters.RoutesListingAdapter
import mapping.routes.metro.la.losangelesmetroroutes.base.BaseActivity
import mapping.routes.metro.la.losangelesmetroroutes.events.RoutesListItemClickedEvent

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        val manager = supportFragmentManager
        val ft = manager.beginTransaction()
        var mainFragment: RoutesListFragment? = manager.findFragmentByTag(ROUTES_FRAG) as? RoutesListFragment

        if (mainFragment == null) {
            mainFragment = RoutesListFragment.newInstance()
        }
        BaseActivity.Companion.addFragmentToActivity(manager, mainFragment!!, R.id.root_activity_frame, ROUTES_FRAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onListItemClicked(event: RoutesListItemClickedEvent) {
        val mapIntent = Intent(this, MapsActivity::class.java)
        mapIntent.putExtra(RoutesListingAdapter.ROUTE_ID, event.routeId)
        this.startActivity(mapIntent)
    }

    companion object {

        private val ROUTES_FRAG = "ROUTES_FRAG"
    }
}
