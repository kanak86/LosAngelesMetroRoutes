package mapping.routes.metro.la.losangelesmetroroutes.views


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

import javax.inject.Inject

import mapping.routes.metro.la.losangelesmetroroutes.MetroClientApplication
import mapping.routes.metro.la.losangelesmetroroutes.R
import mapping.routes.metro.la.losangelesmetroroutes.adapters.RoutesListingAdapter
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing
import mapping.routes.metro.la.losangelesmetroroutes.events.RoutesFetchedEvent
import mapping.routes.metro.la.losangelesmetroroutes.view_model.RoutesListingViewModel
import mapping.routes.metro.la.losangelesmetroroutes.viewmodel_factory.LAMetroViewModelFactory


/**
 * A simple [Fragment] subclass.
 */
class RoutesListFragment : Fragment() {

    var viewModelFactory: LAMetroViewModelFactory? = null
    @Inject set

    private var recyclerView: RecyclerView? = null
    internal lateinit var layoutManager: RecyclerView.LayoutManager
    internal lateinit var viewModel: RoutesListingViewModel
    internal lateinit var adapter: RoutesListingAdapter
    internal var context: Context? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.context = context
        EventBus.getDefault().register(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_routes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity!!.application as MetroClientApplication).applicationComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory!!).get(RoutesListingViewModel::class.java)

        recyclerView = view.findViewById(R.id.routes_recycler_view)

        layoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = layoutManager

        adapter = RoutesListingAdapter(activity, recyclerView)
        recyclerView!!.adapter = adapter
        viewModel.getRoutes()
        //observeViewModel();
    }

    override fun onDetach() {
        super.onDetach()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onRoutesFetchedEvent(event: RoutesFetchedEvent) {
        adapter.updateListing(event.listing)
        adapter.setLoaded()
    }

    companion object {

        fun newInstance(): RoutesListFragment {

            val args = Bundle()

            val fragment = RoutesListFragment()
            fragment.arguments = args
            return fragment
        }
    }


    /*private void observeViewModel() {
        viewModel.getRoutes().observe(this, new Observer<RoutesListing>() {
            @Override
            public void onChanged(@Nullable RoutesListing listings) {
                adapter.updateListing(listings);
                adapter.setLoaded();
            }
        });
    }*/
}// Required empty public constructor
