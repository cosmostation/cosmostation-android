package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.Validator_V1;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.AKASH_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.BAND_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.CERTIK_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SECRET_VAL_URL;

public class ValidatorOtherFragment extends BaseFragment {

    private SwipeRefreshLayout          mSwipeRefreshLayout;
    private RecyclerView                mRecyclerView;
    private OtherValidatorAdapter       mOtherValidatorAdapter;
    private TextView                    mValidatorSize;

    public static ValidatorOtherFragment newInstance(Bundle bundle) {
        ValidatorOtherFragment fragment = new ValidatorOtherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_other, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mValidatorSize          = rootView.findViewById(R.id.validator_cnt);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(20);
        mRecyclerView.setDrawingCacheEnabled(true);
        mOtherValidatorAdapter = new OtherValidatorAdapter();
        mRecyclerView.setAdapter(mOtherValidatorAdapter);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        if (getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
            mValidatorSize.setText(""+getBaseDao().mOtherValidators_V1.size());
            WUtil.onSortByValidatorPowerV1(getBaseDao().mOtherValidators_V1);
        } else {
            mValidatorSize.setText(""+getBaseDao().mOtherValidators.size());
            WUtil.onSortByValidatorPower(getBaseDao().mOtherValidators);
        }

        mOtherValidatorAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBusyFetch() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public ValidatorListActivity getMainActivity() {
        return (ValidatorListActivity)getBaseActivity();
    }

    private class OtherValidatorAdapter extends RecyclerView.Adapter<OtherValidatorAdapter.OtherValidatorHolder> {

        @NonNull
        @Override
        public OtherValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new OtherValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final OtherValidatorHolder holder, final int position) {
            holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
            if (getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                final Validator_V1 validator  = getBaseDao().mOtherValidators_V1.get(position);
                String monikerUrl = "";
                if (getMainActivity().mBaseChain.equals(COSMOS_TEST)) {
                    monikerUrl = COSMOS_VAL_URL + validator.operator_address + ".png";
                } else if (getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                    monikerUrl = IRIS_VAL_URL + validator.operator_address + ".png";
                }
                try {
                    Picasso.get().load(monikerUrl).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img) .into(holder.itemAvatar);
                } catch (Exception e){}

                holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.tokens), 6, 6));
                holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                holder.itemTvMoniker.setText(validator.description.moniker);
                if(validator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }
                if (getBaseDao().mMyValidators_V1.contains(validator)) {
                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().mBaseChain));
                } else {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                }
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartValidatorDetailV1(validator.operator_address);
                    }
                });

            } else {
                final Validator validator  = getBaseDao().mOtherValidators.get(position);
                String monikerUrl = "";
                if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = COSMOS_VAL_URL + validator.operator_address + ".png";

                } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens).movePointRight(18), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getCommissionRate("0"));
                    monikerUrl = IRIS_VAL_URL + validator.operator_address + ".png";

                } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = KAVA_VAL_URL + validator.operator_address + ".png";

                } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = BAND_VAL_URL + validator.operator_address + ".png";
                    if (getBaseDao().mBandOracles != null && !getBaseDao().mBandOracles.isEnable(validator.operator_address)) {
                        holder.itemBandOracleOff.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemBandOracleOff.setVisibility(View.INVISIBLE);
                    }

                } else if (getMainActivity().mBaseChain.equals(IOV_MAIN) || getMainActivity().mBaseChain.equals(IOV_TEST)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = IOV_VAL_URL + validator.operator_address + ".png";

                } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = CERTIK_VAL_URL + validator.operator_address + ".png";

                } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = SECRET_VAL_URL + validator.operator_address + ".png";

                } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
                    holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, getChain(getMainActivity().mAccount.baseChain)));
                    holder.itemTvCommission.setText(WDp.getDpEstAprCommission(getBaseDao(), getMainActivity().mBaseChain, BigDecimal.ONE));
                    monikerUrl = AKASH_VAL_URL + validator.operator_address + ".png";

                }

                try {
                    Picasso.get().load(monikerUrl).fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img) .into(holder.itemAvatar);
                } catch (Exception e){}

                holder.itemTvMoniker.setText(validator.description.moniker);
                holder.itemFree.setVisibility(View.GONE);
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMainActivity().onStartValidatorDetail(validator);
                    }
                });

                if(validator.jailed) {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                    holder.itemRevoked.setVisibility(View.VISIBLE);
                } else {
                    holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                    holder.itemRevoked.setVisibility(View.GONE);
                }

                if (checkIsMyValidator(getBaseDao().mMyValidators, validator.description.moniker)) {
                    holder.itemRoot.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), getMainActivity().mBaseChain));
                } else {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
                }
            }
        }


        private boolean checkIsMyValidator(ArrayList<Validator> userList, final String targetName) {
            return FluentIterable.from(userList).anyMatch(new Predicate<Validator>() {
                @Override
                public boolean apply(@Nullable Validator input) {
                    return input.description.moniker.equals(targetName);
                }
            });
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(COSMOS_TEST) || getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                return getBaseDao().mOtherValidators_V1.size();
            } else {
                return getBaseDao().mOtherValidators.size();
            }
        }

        public class OtherValidatorHolder extends RecyclerView.ViewHolder {
            CardView        itemRoot;
            CircleImageView itemAvatar;
            ImageView       itemRevoked, itemBandOracleOff;
            ImageView       itemFree;
            TextView        itemTvMoniker;
            TextView        itemTvVotingPower;
            TextView        itemTvSubtitle;
            TextView        itemTvCommission;

            public OtherValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemRevoked         = itemView.findViewById(R.id.avatar_validator_revoke);
                itemFree            = itemView.findViewById(R.id.avatar_validator_free);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemBandOracleOff   = itemView.findViewById(R.id.band_oracle_off);
                itemTvVotingPower   = itemView.findViewById(R.id.delegate_power_validator);
                itemTvSubtitle      = itemView.findViewById(R.id.subTitle2);
                itemTvCommission    = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }
}
