package wannabit.io.cosmostaion.fragment;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.KavaCdpDetailActivity;
import wannabit.io.cosmostaion.activities.KavaCdpListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;

public class CdpMyFragment extends BaseFragment {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;

    private ArrayList<ResCdpOwnerStatus.MyCDP> mMyOwenCdp = new ArrayList<>();
    private MyCdpAdapter mMyCdpAdapter;

    public static CdpMyFragment newInstance(Bundle bundle) {
        CdpMyFragment fragment = new CdpMyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_cdp, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchCdpInfo();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMyCdpAdapter = new MyCdpAdapter();
        mRecyclerView.setAdapter(mMyCdpAdapter);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefreshTab();
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mMyOwenCdp = new ArrayList<ResCdpOwnerStatus.MyCDP>(getMainActivity().mMyOwenCdps.values());
        onSortMyCdp(mMyOwenCdp);
        mMyCdpAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onBusyFetch() { }

    public KavaCdpListActivity getMainActivity() {
        return (KavaCdpListActivity)getBaseActivity();
    }

    private class MyCdpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_CDP                    = 1;
        private static final int TYPE_PROMOTION                 = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_MY_CDP) {
                return new MyCdpHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_my, viewGroup, false));
            } else {
                return new PromotionCdpHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_promotion, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_PROMOTION) {
                final PromotionCdpHolder holder = (PromotionCdpHolder)viewHolder;

            } else {
                final MyCdpHolder holder = (MyCdpHolder)viewHolder;
                final ResCdpOwnerStatus.MyCDP status = mMyOwenCdp.get(position);
                final ResKavaMarketPrice.Result price = getMainActivity().mKavaTokenPrices.get(status.getMarketId());
                final int denomPDecimal = WUtil.getKavaCoinDecimal(status.getPDenom());
                final ResCdpParam.KavaCollateralParam param = getMainActivity().mCdpParam.getCollateralParamByDenom(status.getDenom());

                final BigDecimal currentPrice = new BigDecimal(price.price);
                final BigDecimal liquidationPrice = status.getLiquidationPrice(getContext(), param);
                final BigDecimal riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

//            WLog.w("currentPrice " +  currentPrice);
//            WLog.w("liquidationPrice " +  liquidationPrice);
//            WLog.w("riskRate " +  riskRate);

                holder.itemTitleMarket.setText(param.getDpMarketId());
                holder.itemDebtValueTitle.setText(String.format(getString(R.string.str_debt_value), status.getPDenom().toUpperCase()));
                holder.itemCollateralValueTitle.setText(String.format(getString(R.string.str_collateral_value_title3), status.getDenom().toUpperCase()));

                final BigDecimal debtValue = status.getPrincipalAmount();
                final BigDecimal feeValue = status.getAccumulatedFees();
                final BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(getContext(), debtValue.add(feeValue), param, status.cdp);
                final BigDecimal totalDebtValue = debtValue.add(feeValue).add(hiddenFeeValue);
                holder.itemDebtValue.setText(WDp.getDpRawDollor(getContext(), totalDebtValue.movePointLeft(denomPDecimal), 2));

                final BigDecimal currentCollateralValue = new BigDecimal(status.collateral_value.amount);
                holder.itemCollateralValue.setText(WDp.getDpRawDollor(getContext(), currentCollateralValue.movePointLeft(denomPDecimal), 2));

                holder.itemStabilityFee.setText(WDp.getPercentDp(param.getDpStabilityFee(), 2));
                holder.itemLiquidationPenalty.setText(WDp.getPercentDp(param.getDpLiquidationPenalty(), 2));

                holder.itemCurrentPriceTitle.setText(String.format(getString(R.string.str_current_title3), status.getDenom().toUpperCase()));
                holder.itemCurrentPrice.setText(WDp.getDpRawDollor(getContext(), currentPrice, 4));

                holder.itemLiquidationPriceTitle.setText(String.format(getString(R.string.str_liquidation_title3), status.getDenom().toUpperCase()));
                holder.itemLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), liquidationPrice, 4));

                WDp.DpRiskRate(getContext(), riskRate, holder.itemRiskScore,  holder.itemImgRisk);
                try {
                    Picasso.get().load(KAVA_CDP_MARKET_IMG_URL+  status.getImagePath()).fit().into(holder.itemImgMarket);

                } catch (Exception e) { }

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getMainActivity(), KavaCdpDetailActivity.class);
                        intent.putExtra("denom", status.getDenom());
                        intent.putExtra("marketId", status.getMarketId());
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mMyOwenCdp.size() == 0) {
                return 1;
            }
            return mMyOwenCdp.size();
        }

        @Override
        public int getItemViewType(int position) {
            if(mMyOwenCdp == null || mMyOwenCdp.size() < 1) {
                return TYPE_PROMOTION;
            } else {
                return TYPE_MY_CDP;
            }
        }

        public class MyCdpHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket, itemImgRisk;
            TextView itemTitleMarket, itemRiskScore;
            TextView itemDebtValueTitle, itemDebtValue, itemCollateralValueTitle, itemCollateralValue;
            TextView itemStabilityFee, itemLiquidationPenalty;
            TextView itemCurrentPriceTitle, itemCurrentPrice, itemLiquidationPriceTitle, itemLiquidationPrice;


            public MyCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_cdp_my);
                itemImgMarket = itemView.findViewById(R.id.market_img);
                itemTitleMarket = itemView.findViewById(R.id.market_title);
                itemImgRisk = itemView.findViewById(R.id.cdp_safe_img);
                itemRiskScore = itemView.findViewById(R.id.cdp_safe_rate);
                itemDebtValueTitle = itemView.findViewById(R.id.cdp_debt_value_title);
                itemDebtValue = itemView.findViewById(R.id.cdp_debt_value);
                itemCollateralValueTitle = itemView.findViewById(R.id.cdp_collateral_value_title);
                itemCollateralValue = itemView.findViewById(R.id.cdp_collateral_value);
                itemStabilityFee = itemView.findViewById(R.id.cdp_stability_fee);
                itemLiquidationPenalty = itemView.findViewById(R.id.cdp_str_liquidation_penalty);
                itemCurrentPriceTitle = itemView.findViewById(R.id.current_price_title);
                itemCurrentPrice = itemView.findViewById(R.id.current_price);
                itemLiquidationPriceTitle = itemView.findViewById(R.id.liquidation_price_title);
                itemLiquidationPrice = itemView.findViewById(R.id.liquidation_price);
            }
        }

        public class PromotionCdpHolder extends RecyclerView.ViewHolder {
            CardView    itemRoot;

            public PromotionCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot                = itemView.findViewById(R.id.card_root);
            }
        }
    }


    public static void onSortMyCdp(ArrayList<ResCdpOwnerStatus.MyCDP> cdps) {
        Collections.sort(cdps, new Comparator<ResCdpOwnerStatus.MyCDP>() {
            @Override
            public int compare(ResCdpOwnerStatus.MyCDP o1, ResCdpOwnerStatus.MyCDP o2) {
                return o1.cdp.getCdpId() > o2.cdp.getCdpId() ? -1 : 1;
            }
        });
    }
}
