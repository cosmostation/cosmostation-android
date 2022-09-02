package wannabit.io.cosmostaion.fragment.txs.ok;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.ok.OKValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class OKValidatorOtherFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private OKOtherValidatorAdapter mOKOtherValidatorAdapter;
    private TextView mValidatorSize;

    private ResOkStaking mOkDeposit;

    public static OKValidatorOtherFragment newInstance() {
        return new OKValidatorOtherFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_other, container, false);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mValidatorSize = rootView.findViewById(R.id.validator_cnt);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getSActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> getSActivity().onFetchAllData());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(50);
        mRecyclerView.setDrawingCacheEnabled(true);
        mOKOtherValidatorAdapter = new OKOtherValidatorAdapter();
        mRecyclerView.setAdapter(mOKOtherValidatorAdapter);

        onRefreshTab();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        mOkDeposit = getBaseDao().mOkStaking;
        mValidatorSize.setText("" + getBaseDao().mOtherValidators.size());
        onSortValidator();

        mOKOtherValidatorAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBusyFetch() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private OKValidatorListActivity getSActivity() {
        return (OKValidatorListActivity) getBaseActivity();
    }

    public class OKOtherValidatorAdapter extends RecyclerView.Adapter<OKOtherValidatorAdapter.OKOtherValidatorHolder> {

        @NonNull
        @Override
        public OKOtherValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new OKOtherValidatorHolder(getLayoutInflater().inflate(R.layout.item_ok_validator, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull OKOtherValidatorHolder holder, int position) {
            final Validator validator = getBaseDao().mOtherValidators.get(position);
            holder.itemTvMoniker.setText(validator.description.moniker);
            holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.delegator_shares), 0, 0));
            holder.itemTvCommission.setText(WDp.getCommissionRate("0"));

            try {
                Picasso.get().load(getSActivity().mChainConfig.monikerUrl() + validator.operator_address + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(holder.itemAvatar);
            } catch (Exception e) { }

            if (validator.jailed) {
                holder.itemAvatar.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorRed));
                holder.itemRevoked.setVisibility(View.VISIBLE);
            } else {
                holder.itemAvatar.setBorderColor(ContextCompat.getColor(getSActivity(), R.color.colorGray3));
                holder.itemRevoked.setVisibility(View.GONE);
            }

            if (checkIsMyValidator(validator.operator_address)) {
                holder.itemRoot.setCardBackgroundColor(ContextCompat.getColor(getSActivity(), getSActivity().mChainConfig.chainBgColor()));
            }
        }

        @Override
        public int getItemCount() {
            return getBaseDao().mOtherValidators.size();
        }

        public class OKOtherValidatorHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            CircleImageView itemAvatar;
            ImageView itemRevoked;
            TextView itemTvMoniker;
            TextView itemTvVotingPower;
            TextView itemTvCommission;

            public OKOtherValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_validator);
                itemAvatar = itemView.findViewById(R.id.avatar_validator);
                itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
                itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }

    private boolean checkIsMyValidator(String valAddress) {
        boolean myVal = false;
        if (mOkDeposit == null || mOkDeposit.validator_address == null) {
            return myVal;
        }
        for (String val : mOkDeposit.validator_address) {
            if (val.equals(valAddress)) {
                return true;
            }
        }
        return myVal;
    }

    public void onSortValidator() {
        WUtil.onSortByOKValidatorPower(getBaseDao().mOtherValidators);
    }
}
