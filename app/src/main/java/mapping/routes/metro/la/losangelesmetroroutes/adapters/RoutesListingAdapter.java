package mapping.routes.metro.la.losangelesmetroroutes.adapters;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mapping.routes.metro.la.losangelesmetroroutes.R;
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListing;
import mapping.routes.metro.la.losangelesmetroroutes.datamanagers.RoutesListingItem;
import mapping.routes.metro.la.losangelesmetroroutes.events.RoutesListItemClickedEvent;

/**
 * Created by apoorvakanaksiwach on 2/4/18.
 */

public class RoutesListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    public static final String ROUTE_ID = "ROUTE_ID";
    private boolean isLoading = false;
    public static List<RoutesListingItem> childrenList = new ArrayList<>();

    public class ListingsCardViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        @BindView(R.id.route_id)
        public TextView routeId;
        @BindView(R.id.title)
        public TextView itemTitle;
        @BindView(R.id.card_view)
        CardView cardView;

        public ListingsCardViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.card_view})
        public void clickListItem() {
            int position = getAdapterPosition();
            RoutesListingItem item = childrenList.get(position);
            EventBus.getDefault().post(new RoutesListItemClickedEvent(item.getId()));
            notifyDataSetChanged();
        }
    }

    public RoutesListingAdapter(Context context, RecyclerView recyclerView) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.card_layout, parent, false);
            return new ListingsCardViewHolder(v);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ListingsCardViewHolder) {
            ListingsCardViewHolder listingsCardViewHolder = (ListingsCardViewHolder) holder;
            RoutesListingItem item = childrenList.get(position);
            listingsCardViewHolder.routeId.setText("Route Number: "+item.getId());
            listingsCardViewHolder.routeId.setAllCaps(true);
            listingsCardViewHolder.itemTitle.setText(item.getDisplayName());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar1);
        }
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    private RoutesListingItem getItem(int adapterPosition) {
        return childrenList.get(adapterPosition);
    }

    public void updateListing(RoutesListing posts) {
        childrenList.addAll(posts.getItems());
        notifyDataSetChanged();
    }

    public void setLoaded() {
        isLoading = false;

    }

    @Override
    public int getItemViewType(int position) {
        return childrenList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

}
