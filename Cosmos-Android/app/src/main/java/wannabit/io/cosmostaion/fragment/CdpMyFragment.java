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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.KavaCdpDetailActivity;
import wannabit.io.cosmostaion.activities.KavaCdpListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;

public class CdpMyFragment extends BaseFragment {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;

    private ArrayList<ResCdpOwnerStatus.Result> mMyOwenCdp = new ArrayList<>();
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
        mMyOwenCdp = new ArrayList<ResCdpOwnerStatus.Result>(getMainActivity().mMyOwenCdps.values());
        mMyCdpAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void onBusyFetch() { }

    public KavaCdpListActivity getMainActivity() {
        return (KavaCdpListActivity)getBaseActivity();
    }

    private class MyCdpAdapter extends RecyclerView.Adapter<MyCdpAdapter.MyCdpHolder> {

        @NonNull
        @Override
        public MyCdpHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyCdpHolder(getLayoutInflater().inflate(R.layout.item_cdp_list_my, viewGroup, false));

        }

        @Override
        public void onBindViewHolder(@NonNull MyCdpHolder holder, int position) {
            final ResCdpOwnerStatus.Result status = mMyOwenCdp.get(position);
            final ResKavaMarketPrice.Result price = getMainActivity().mKavaTokenPrices.get(status.getDenom());
            final int denomDecimal = WUtil.getKavaCoinDecimal(status.getDenom());
            final int denomPDecimal = WUtil.getKavaCoinDecimal(status.getPDenom());

            holder.itemLiquidationPriceTitle.setText(WDp.DpLiquidationPriceTitle(getContext(), status.getDenom().toUpperCase()));
            holder.itemCurrentPriceTitle.setText(WDp.DpCurrentPriceTitle(getContext(), status.getDenom().toUpperCase()));
            holder.itemCollateralAmountTitle.setText(WDp.DpCollateralTitle(getContext(), status.getDenom().toUpperCase()));
            holder.itemLoanedAmountTitle.setText(WDp.DpLoanedTitle(getContext(), status.getPDenom().toUpperCase()));

            holder.itemTitleMarket.setText(status.getDpMarketId());
            holder.itemLiquidationPrice.setText(WDp.getDpRawDollor(getContext(), WDp.getLiquidationPrice(status, getMainActivity().mCdpParam.getRawLiquidationRatio(status.getDenom())).toPlainString(), 4));
            holder.itemCurrentPrice.setText(WDp.getDpRawDollor(getContext(), price.price, 4));
            holder.itemCollateralAmount.setText(WDp.getDpAmount2(getContext(), status.getCollateralAmount(), denomDecimal, denomDecimal));
            holder.itemLoanedAmount.setText(WDp.getDpAmount2(getContext(), status.getPrincipalAmount(), denomPDecimal, denomPDecimal));

            BigDecimal liquidationRatio = getMainActivity().mCdpParam.getRawLiquidationRatio(status.getDenom());
            BigDecimal collateralizationRatio = new BigDecimal(status.collateralization_ratio);
            BigDecimal safeRate = collateralizationRatio.subtract(liquidationRatio).movePointRight(2).divide(liquidationRatio, 6, RoundingMode.DOWN);
            holder.itemSafeRate.setText(WDp.getPercentDp(safeRate, 2));
            if (safeRate.longValue() > 0.7) {
                holder.itemSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_safe));
                holder.itemSafeRate.setTextColor(getResources().getColor(R.color.colorCdpSafe));
            } else if (safeRate.longValue() > 0.3) {
                holder.itemSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_stable));
                holder.itemSafeRate.setTextColor(getResources().getColor(R.color.colorCdpStable));
            } else {
                holder.itemSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_danger));
                holder.itemSafeRate.setTextColor(getResources().getColor(R.color.colorCdpDanger));
            }

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

        @Override
        public int getItemCount() {
            return mMyOwenCdp.size();
        }

        public class MyCdpHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemImgMarket, itemSafeBar;
            TextView itemTitleMarket, itemSafeRate;
            TextView itemCurrentPrice, itemCurrentPriceTitle, itemLiquidationPrice, itemLiquidationPriceTitle,
            itemCollateralAmount, itemCollateralAmountTitle, itemLoanedAmount, itemLoanedAmountTitle;


            public MyCdpHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_cdp_my);
                itemImgMarket = itemView.findViewById(R.id.market_img);
                itemTitleMarket = itemView.findViewById(R.id.market_title);
                itemSafeBar = itemView.findViewById(R.id.cdp_safe_img);
                itemSafeRate = itemView.findViewById(R.id.cdp_safe_rate);
                itemLiquidationPriceTitle = itemView.findViewById(R.id.liquidation_price_title);
                itemLiquidationPrice = itemView.findViewById(R.id.liquidation_price);
                itemCurrentPriceTitle = itemView.findViewById(R.id.current_price_title);
                itemCurrentPrice = itemView.findViewById(R.id.current_price);
                itemCollateralAmountTitle = itemView.findViewById(R.id.collateral_amount_title);
                itemCollateralAmount = itemView.findViewById(R.id.collateral_amount);
                itemLoanedAmountTitle = itemView.findViewById(R.id.loaned_amount_title);
                itemLoanedAmount = itemView.findViewById(R.id.loaned_amount);
            }
        }
    }
}
