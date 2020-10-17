package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class HarvestMarketFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RelativeLayout mProgress;

    public static HarvestMarketFragment newInstance(Bundle bundle) {
        HarvestMarketFragment fragment = new HarvestMarketFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_harvest_market, container, false);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
    }






    private class HarvestMarketAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_STAKER_HEADER         = 0;
        private static final int TYPE_STAKER                = 1;
        private static final int TYPE_LP_HEADER             = 3;
        private static final int TYPE_LP_REWARD             = 4;
        private static final int TYPE_MY_LP                 = 5;
        private static final int TYPE_OTHER_LP              = 6;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_STAKER_HEADER) {
                return new HeaderStakerHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_stake_header, viewGroup, false));
            } else if (viewType == TYPE_STAKER) {
                return new StakerHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_stake, viewGroup, false));
            } else if (viewType == TYPE_LP_HEADER) {
                return new HeaderLpHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_lp_header, viewGroup, false));
            } else if (viewType == TYPE_LP_REWARD) {
                return new LpRewardHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_reward_lp, viewGroup, false));
            } else if (viewType == TYPE_MY_LP) {
                return new MyLpHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_my_lp, viewGroup, false));
            } else if (viewType == TYPE_OTHER_LP) {
                return new OtherLpHolder(getLayoutInflater().inflate(R.layout.item_harvest_list_other_lp, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_STAKER_HEADER) {

            } else if (getItemViewType(position) == TYPE_STAKER) {

            } else if (getItemViewType(position) == TYPE_LP_HEADER) {

            } else if (getItemViewType(position) == TYPE_LP_REWARD) {

            } else if (getItemViewType(position) == TYPE_MY_LP) {

            } else if (getItemViewType(position) == TYPE_OTHER_LP) {

            }
        }



        @Override
        public int getItemCount() {
            return 5;
        }

        @Override
        public int getItemViewType(int position) {
            return TYPE_STAKER_HEADER;
        }



        public class HeaderStakerHolder extends RecyclerView.ViewHolder {
            public HeaderStakerHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class StakerHolder extends RecyclerView.ViewHolder {
            public StakerHolder(@NonNull View itemView) {
                super(itemView);
            }
        }


        public class HeaderLpHolder extends RecyclerView.ViewHolder {
            public HeaderLpHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class LpRewardHolder extends RecyclerView.ViewHolder {
            public LpRewardHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class MyLpHolder extends RecyclerView.ViewHolder {
            public MyLpHolder(@NonNull View itemView) {
                super(itemView);
            }
        }

        public class OtherLpHolder extends RecyclerView.ViewHolder {
            public OtherLpHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
