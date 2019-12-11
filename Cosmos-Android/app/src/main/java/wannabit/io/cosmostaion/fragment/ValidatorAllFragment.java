package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_ValidatorSorting;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.IRIS_VAL_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_IMG_URL;

public class ValidatorAllFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_All_VALIDATOR_SORTING = 6002;

    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private AllValidatorAdapter     mAllValidatorAdapter;
    private TextView                mValidatorSize, mSortType;
    private LinearLayout            mBtnSort;

    private ArrayList<Validator>        mMyValidators = new ArrayList<>();
    private ArrayList<Validator>        mTopValidators = new ArrayList<>();

    public static ValidatorAllFragment newInstance(Bundle bundle) {
        ValidatorAllFragment fragment = new ValidatorAllFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_validator_all, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mValidatorSize          = rootView.findViewById(R.id.validator_cnt);
        mSortType               = rootView.findViewById(R.id.token_sort_type);
        mBtnSort                = rootView.findViewById(R.id.btn_validator_sort);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(50);
        mRecyclerView.setDrawingCacheEnabled(true);
        mAllValidatorAdapter = new AllValidatorAdapter();
        mRecyclerView.setAdapter(mAllValidatorAdapter);
        mBtnSort.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mTopValidators  = getMainActivity().mTopValidators;
        mMyValidators   = getMainActivity().mMyValidators;
        mValidatorSize.setText(""+mTopValidators.size());
        onSortValidator();

        mAllValidatorAdapter.notifyDataSetChanged();
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

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSort)) {
            onShowAllValidatorSort();
        }
    }


    private class AllValidatorAdapter extends RecyclerView.Adapter<AllValidatorAdapter.AllValidatorHolder> {

        @NonNull
        @Override
        public AllValidatorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AllValidatorHolder(getLayoutInflater().inflate(R.layout.item_reward_validator, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull final AllValidatorHolder holder, final int position) {
            final Validator validator  = mTopValidators.get(position);

            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                if (getMainActivity().mBondedToken != null && getMainActivity().mProvisions != null) {
                    holder.itemTvCommission.setText(WDp.getYieldString(getMainActivity().mBondedToken, getMainActivity().mProvisions, new BigDecimal(validator.commission.commission_rates.rate)));
                }
                try {
                    Picasso.get().load(COSMOS_VAL_URL+validator.operator_address+".png")
                            .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                            .into(holder.itemAvatar);
                } catch (Exception e){}

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens).movePointRight(18), 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                holder.itemTvCommission.setText(WDp.getIrisYieldString(getMainActivity().mIrisPool, new BigDecimal(validator.commission.rate)));
                try {
                    Picasso.get().load(IRIS_VAL_URL+validator.operator_address+".png")
                            .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                            .into(holder.itemAvatar);
                } catch (Exception e){}

            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                holder.itemTvVotingPower.setText(WDp.getDpAmount(getContext(), new BigDecimal(validator.tokens), 6, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
                if (getMainActivity().mBondedToken != null && getMainActivity().mProvisions != null) {
                    holder.itemTvCommission.setText(WDp.getYieldString(getMainActivity().mBondedToken, getMainActivity().mProvisions, new BigDecimal(validator.commission.commission_rates.rate)));
                }
                try {
                    Picasso.get().load(KAVA_IMG_URL+validator.operator_address+".png")
                            .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                            .into(holder.itemAvatar);
                } catch (Exception e){}
            }

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

            if(checkIsMyValidator(mMyValidators, validator.description.moniker)) {
                if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
                } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
                } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
                }
            } else {
                holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            }

        }

        private boolean checkIsMyValidator(ArrayList<Validator> userList, final String targetName){
            return FluentIterable.from(userList).anyMatch(new Predicate<Validator>() {
                @Override
                public boolean apply(@Nullable Validator input) {
                    return input.description.moniker.equals(targetName);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mTopValidators.size();
        }

        public class AllValidatorHolder extends RecyclerView.ViewHolder {
            CardView        itemRoot;
            CircleImageView itemAvatar;
            ImageView       itemRevoked;
            ImageView       itemFree;
            TextView        itemTvMoniker;
            TextView        itemTvVotingPower;
            TextView        itemTvCommission;

            public AllValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemRevoked         = itemView.findViewById(R.id.avatar_validator_revoke);
                itemFree            = itemView.findViewById(R.id.avatar_validator_free);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission    = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }
    }


    public void onSortValidator() {
        if(getBaseDao().getValSorting() == 2){
            WUtil.onSortingByCommission(mTopValidators, getMainActivity().mBaseChain);
            mSortType.setText(getString(R.string.str_sorting_by_yield));
        } else if (getBaseDao().getValSorting() == 0){
            WUtil.onSortByValidatorName(mTopValidators);
            mSortType.setText(getString(R.string.str_sorting_by_name));
        } else {
            WUtil.onSortByValidatorPower(mTopValidators);
            mSortType.setText(getString(R.string.str_sorting_by_power));
        }
    }

    public void onShowAllValidatorSort() {
        Dialog_ValidatorSorting bottomSheetDialog = Dialog_ValidatorSorting.getInstance();
        bottomSheetDialog.setArguments(null);
        bottomSheetDialog.setTargetFragment(ValidatorAllFragment.this, SELECT_All_VALIDATOR_SORTING);
        bottomSheetDialog.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_All_VALIDATOR_SORTING && resultCode == Activity.RESULT_OK) {
            getBaseDao().setValSorting(data.getIntExtra("sorting", 1));
            onRefreshTab();
        }
    }

}