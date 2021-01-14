package wannabit.io.cosmostaion.fragment.chains.ok;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ok.OKValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResOkStaking;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;

public class OKValidatorMyFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout          mSwipeRefreshLayout;
    private RecyclerView                mRecyclerView;
    private OKMyValidatorAdapter        mOKMyValidatorAdapter;
    private TextView                    mValidatorSize;
    private Button                      mVote;

    private ArrayList<Validator>        mMyValidators = new ArrayList<>();
    private ResOkStaking mOkDeposit;

    public static OKValidatorMyFragment newInstance(Bundle bundle) {
        OKValidatorMyFragment fragment = new OKValidatorMyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_okvalidator_my, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mValidatorSize          = rootView.findViewById(R.id.validator_cnt);
        mVote                   = rootView.findViewById(R.id.btn_vote);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSActivity().onFetchAllData();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(50);
        mRecyclerView.setDrawingCacheEnabled(true);
        mOKMyValidatorAdapter = new OKMyValidatorAdapter();
        mRecyclerView.setAdapter(mOKMyValidatorAdapter);

        mVote.setOnClickListener(this);

        onRefreshTab();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mMyValidators.clear();
        ArrayList<Validator> allValidator = new ArrayList<>();
        allValidator.addAll(getBaseDao().mTopValidators);
        allValidator.addAll(getBaseDao().mOtherValidators);
        mOkDeposit = getBaseDao().mOkStaking;

        for (Validator val:allValidator) {
            if (checkIsMyValidator(val.operator_address)) {
                mMyValidators.add(val);
            }
        }
        mValidatorSize.setText(""+mMyValidators.size());
        onSortValidator();

        mOKMyValidatorAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onBusyFetch() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }


    private OKValidatorListActivity getSActivity() {
        return (OKValidatorListActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mVote)) {
            getSActivity().onStartDirectVote();
        }
    }

    public class OKMyValidatorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_PROMOTION                 = 0;
        private static final int TYPE_MY_VALIDATOR              = 1;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_PROMOTION) {
                return new OKPromotionHolder(getLayoutInflater().inflate(R.layout.item_ok_promotion, viewGroup, false));
            } else if (viewType == TYPE_MY_VALIDATOR) {
                return new OKMyValidatorHolder(getLayoutInflater().inflate(R.layout.item_ok_validator, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_VALIDATOR) {
                final OKMyValidatorHolder holder = (OKMyValidatorHolder)viewHolder;
                final Validator validator  = mMyValidators.get(position);
                if (getSActivity().mBaseChain.equals(OKEX_MAIN) || getSActivity().mBaseChain.equals(OK_TEST)) {
                    holder.itemRoot.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgOkex));
                    holder.itemTvMoniker.setText(validator.description.moniker);
                    holder.itemTvVotingPower.setText(WDp.getDpAmount2(getContext(), new BigDecimal(validator.delegator_shares), 0, 0));
                    holder.itemTvCommission.setText(WDp.getCommissionRate("0"));

                    String imgUrl = validator.description.identity;
                    if (!TextUtils.isEmpty(imgUrl) && imgUrl.startsWith("logo|||")) {
                        imgUrl = imgUrl.replace("logo|||" , "");
                        try {
                            Picasso.get().load(imgUrl)
                                    .fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img)
                                    .into(holder.itemAvatar);
                        } catch (Exception e){}
                    } else {
                        holder.itemAvatar.setImageDrawable(getResources().getDrawable(R.drawable.validator_none_img));
                    }

                    if(validator.jailed) {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorRed));
                        holder.itemRevoked.setVisibility(View.VISIBLE);
                    } else {
                        holder.itemAvatar.setBorderColor(getResources().getColor(R.color.colorGray3));
                        holder.itemRevoked.setVisibility(View.GONE);
                    }
                }
            }

        }

        @Override
        public int getItemCount() {
            if (mMyValidators == null || mMyValidators.size() < 1) {
                return 1;
            } else {
                return mMyValidators.size();
            }
        }



        @Override
        public int getItemViewType(int position) {
            if (mMyValidators == null || mMyValidators.size() < 1) {
                return TYPE_PROMOTION;
            } else {
                return TYPE_MY_VALIDATOR;
            }
        }

        public class OKMyValidatorHolder extends RecyclerView.ViewHolder {
            CardView            itemRoot;
            CircleImageView     itemAvatar;
            ImageView           itemRevoked;
            ImageView           itemFree;
            TextView            itemTvMoniker;
            TextView            itemTvVotingPower;
            TextView            itemTvCommission;

            public OKMyValidatorHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_validator);
                itemAvatar          = itemView.findViewById(R.id.avatar_validator);
                itemRevoked         = itemView.findViewById(R.id.avatar_validator_revoke);
                itemFree            = itemView.findViewById(R.id.avatar_validator_free);
                itemTvMoniker       = itemView.findViewById(R.id.moniker_validator);
                itemTvVotingPower   = itemView.findViewById(R.id.delegate_power_validator);
                itemTvCommission    = itemView.findViewById(R.id.delegate_commission_validator);
            }
        }

        public class OKPromotionHolder extends RecyclerView.ViewHolder {

            public OKPromotionHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    private boolean checkIsMyValidator(String valAddress){
        boolean myVal = false;
        if (mOkDeposit == null || mOkDeposit.validator_address == null) {
            return myVal;
        }
        for (String val:mOkDeposit.validator_address) {
            if (val.equals(valAddress)){
                return true;
            }
        }
        return myVal;
    }

    public void onSortValidator() {
        WUtil.onSortByOKValidatorPower(mMyValidators);
    }

}
