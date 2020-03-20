package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.broadcast.kava.CreateCdpActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_Help_Msg;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Staus;
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByDepositorTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByOwnerTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;

public class KavaCdpDetailActivity extends BaseActivity implements TaskListener, View.OnClickListener {

    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private NestedScrollView    mNestedScrollView;
    private RelativeLayout      mLoadingLayer;

    private CardView            mCdpInfoCard, mMyEmptyCard, mMyCard;

    private ImageView           mInfoMarketImg;
    private TextView            mInfoMarketId;
    private LinearLayout        mInfoCollateralRateLayer, mStabilityFeeLayer, mInfoLiquidationPenaltyLayer;
    private TextView            mInfoCollateralRate, mInfoStabilityFee, mInfoLiquidationPenalty,
                                mInfoCurrentPriceTitle, mInfoCurrentPrice, mInfoLiquidationPriceTitle, mInfoLiquidationPrice;
    private RelativeLayout      mInfoLiquidationPriceLayer;

    private RelativeLayout      mInfoMyLayer;
    private LinearLayout        mInfoEmptyLayer;
    private TextView            mInfoSafeRate;
    private ImageView           mInfoSafeBar;
    private TextView            mInfoCollateralRateTop;
    private RelativeLayout      mInfoCollateralRateView;


    private ImageView           mEmptyCollateralImg, mEmptyPrincipalImg;
    private TextView            mEmptyCollateralDenom, mEmptyCollateralAmount,
                                mEmptyPrincipalDenom, mEmptyPrincipalAmount,
                                mEmptyKavaDenom, mEmptyKavaAmount;

    private ImageView           mMyCollateralImg;
    private TextView            mMyCollateralDenom, mMyCollateralAvailableTitle, mMyCollateralAvailable;
    private LinearLayout        mMySelfDepositLayer;
    private TextView            mMySelfDepositAmount;
    private LinearLayout        mMyTotalDepositLayer;
    private TextView            mMyTotalDepositAmount;
    private LinearLayout        mMyWithdrawableLayer;
    private TextView            mMyWithdrawableAmountTitle;
    private TextView            mMyWithdrawableAmount;
    private RelativeLayout      mMyBtnDeposit, mMyBtnWithdraw;
    private TextView            mMyBtnDepositTxt, mMyBtnWithdrawTxt;
    private ImageView           mMyPrincipalImg;
    private TextView            mMyPrincipalDenom, mMyPrincipalAvailable;
    private LinearLayout        mMyLoadnedLayer;
    private TextView            mMyLoadnedAmount;
    private LinearLayout        mMyLoadableLayer;
    private TextView            mMyLoadableAmount;
    private LinearLayout        mMyOutstandingDebtLayer;
    private TextView            mMyOutstandingDebtAmount;
    private RelativeLayout      mMyBtnDrawdebt, mMyBtnRepay;
    private TextView            mMyKavaAvailable;

    private Button              mOpenCdp;


    private ResCdpParam.Result          mCdpParam;
    private ResKavaMarketPrice.Result   mKavaTokenPrice;
    private ResCdpOwnerStatus.Result    mMyOwenCdp;
    private ArrayList<ResCdpDepositStatus.Result> mMyDepositList = new ArrayList<>();

    private String                      mMarketDenom;
    private String                      mMaketId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kava_cdp_detail);
        mToolbar                        = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout             = findViewById(R.id.layer_refresher);
        mNestedScrollView               = findViewById(R.id.layer_scrollview);
        mLoadingLayer                   = findViewById(R.id.loadingLayer);
        mOpenCdp                        = findViewById(R.id.btn_open_cdp);

        mCdpInfoCard                    = findViewById(R.id.card_cdp_info);
        mInfoMarketImg                  = mCdpInfoCard.findViewById(R.id.market_img);
        mInfoMarketId                   = mCdpInfoCard.findViewById(R.id.market_id);
        mInfoMyLayer                    = mCdpInfoCard.findViewById(R.id.cdp_my_layer);
        mInfoSafeRate                   = mCdpInfoCard.findViewById(R.id.cdp_safe_rate);
        mInfoSafeBar                    = mCdpInfoCard.findViewById(R.id.cdp_safe_img);
        mInfoEmptyLayer                 = mCdpInfoCard.findViewById(R.id.cdp_empty_layer);
        mInfoCollateralRateTop          = mCdpInfoCard.findViewById(R.id.cdp_collateral_rate);
        mInfoCollateralRateView         = mCdpInfoCard.findViewById(R.id.collateral_rate_view);
        mInfoCollateralRateLayer        = mCdpInfoCard.findViewById(R.id.collateral_rate_layer);
        mInfoCollateralRate             = mCdpInfoCard.findViewById(R.id.collateral_rate);
        mStabilityFeeLayer              = mCdpInfoCard.findViewById(R.id.stability_fee_layer);
        mInfoStabilityFee               = mCdpInfoCard.findViewById(R.id.stability_fee);
        mInfoLiquidationPenaltyLayer    = mCdpInfoCard.findViewById(R.id.liquidation_penalty_layer);
        mInfoLiquidationPenalty         = mCdpInfoCard.findViewById(R.id.liquidation_penalty);
        mInfoCurrentPriceTitle          = mCdpInfoCard.findViewById(R.id.current_price_title);
        mInfoCurrentPrice               = mCdpInfoCard.findViewById(R.id.current_price);
        mInfoLiquidationPriceLayer      = mCdpInfoCard.findViewById(R.id.liquidation_price_layer);
        mInfoLiquidationPriceTitle      = mCdpInfoCard.findViewById(R.id.liquidation_price_title);
        mInfoLiquidationPrice           = mCdpInfoCard.findViewById(R.id.liquidation_price);


        mMyEmptyCard                    = findViewById(R.id.card_cdp_my_empty);
        mEmptyCollateralImg             = mMyEmptyCard.findViewById(R.id.collateral_icon);
        mEmptyCollateralAmount          = mMyEmptyCard.findViewById(R.id.collateral_amount);
        mEmptyCollateralDenom           = mMyEmptyCard.findViewById(R.id.collateral_denom);
        mEmptyPrincipalImg              = mMyEmptyCard.findViewById(R.id.principal_icon);
        mEmptyPrincipalAmount           = mMyEmptyCard.findViewById(R.id.principal_amount);
        mEmptyPrincipalDenom            = mMyEmptyCard.findViewById(R.id.principal_denom);
        mEmptyKavaAmount                = mMyEmptyCard.findViewById(R.id.kava_amount);
        mEmptyKavaDenom                 = mMyEmptyCard.findViewById(R.id.kava_denom);

        mMyCard                         = findViewById(R.id.card_cdp_my);
        mMyCollateralImg                = mMyCard.findViewById(R.id.collateral_icon);
        mMyCollateralDenom              = mMyCard.findViewById(R.id.collateral_denom);
        mMyCollateralAvailableTitle     = mMyCard.findViewById(R.id.collateral_available_title);
        mMyCollateralAvailable          = mMyCard.findViewById(R.id.collateral_available);
        mMySelfDepositLayer             = mMyCard.findViewById(R.id.self_deposited_amount_layer);
        mMySelfDepositAmount            = mMyCard.findViewById(R.id.self_deposited_amount);
        mMyTotalDepositLayer            = mMyCard.findViewById(R.id.total_deposited_amount_layer);
        mMyTotalDepositAmount           = mMyCard.findViewById(R.id.total_deposited_amount);
        mMyWithdrawableLayer            = mMyCard.findViewById(R.id.expected_withdrawable_amount_layer);
        mMyWithdrawableAmountTitle      = mMyCard.findViewById(R.id.expected_withdrawable_amount_title);
        mMyWithdrawableAmount           = mMyCard.findViewById(R.id.expected_withdrawable_amount);
        mMyBtnDeposit                   = mMyCard.findViewById(R.id.btn_deposit);
        mMyBtnDepositTxt                = mMyCard.findViewById(R.id.btn_deposit_txt);
        mMyBtnWithdraw                  = mMyCard.findViewById(R.id.btn_withdraw);
        mMyBtnWithdrawTxt               = mMyCard.findViewById(R.id.btn_withdraw_txt);
        mMyPrincipalImg                 = mMyCard.findViewById(R.id.principal_icon);
        mMyPrincipalDenom               = mMyCard.findViewById(R.id.principal_denom);
        mMyPrincipalAvailable           = mMyCard.findViewById(R.id.principal_available);
        mMyLoadnedLayer                 = mMyCard.findViewById(R.id.loaned_amount_layer);
        mMyLoadnedAmount                = mMyCard.findViewById(R.id.loaned_amount);
        mMyLoadableLayer                = mMyCard.findViewById(R.id.expected_loanable_amount_layer);
        mMyLoadableAmount               = mMyCard.findViewById(R.id.expected_loanable_amount);
        mMyOutstandingDebtLayer         = mMyCard.findViewById(R.id.outstanding_debt_amount_layer);
        mMyOutstandingDebtAmount        = mMyCard.findViewById(R.id.outstanding_debt_amount);
        mMyBtnDrawdebt                  = mMyCard.findViewById(R.id.btn_drawdebt);
        mMyBtnRepay                     = mMyCard.findViewById(R.id.btn_repay);
        mMyKavaAvailable                = mMyCard.findViewById(R.id.kava_available);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mBalances = mAccount.getBalances();

        mMarketDenom = getIntent().getStringExtra("denom");
        mMaketId = getIntent().getStringExtra("marketId");
//        WLog.w("mMarketDenom " + mMarketDenom);
//        WLog.w("marketId " + mMaketId);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchCdpInfo();
            }
        });
        onFetchCdpInfo();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {

        final ResCdpParam.KavaCollateralParam cParam = mCdpParam.getCollateralParamByDenom(mMarketDenom);
        if (cParam == null || mKavaTokenPrice == null) {onBackPressed();}

        final String cDenom = cParam.denom;
        final String pDenom = cParam.debt_limit.get(0).denom;
        final BigDecimal cAvailable = WUtil.getTokenBalance(mBalances, cDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, cDenom).balance;
        final BigDecimal pAvailable = WUtil.getTokenBalance(mBalances, pDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, pDenom).balance;
        final BigDecimal kAvailable = WUtil.getTokenBalance(mBalances, COSMOS_KAVA) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, COSMOS_KAVA).balance;
        final BigDecimal currentPrice = new BigDecimal(mKavaTokenPrice.price);

        //insert data for commonInfo
        mInfoMarketId.setText(cParam.getDpMarketId());
        mInfoCollateralRateTop.setText(WDp.getPercentDp(cParam.getDpLiquidationRatio(), 2));
        mInfoCollateralRate.setText(WDp.getPercentDp(cParam.getDpLiquidationRatio(), 2));
        mInfoStabilityFee.setText(WDp.getPercentDp(cParam.getDpStabilityFee(), 2));
        mInfoLiquidationPenalty.setText(WDp.getPercentDp(cParam.getDpLiquidationPenalty(), 2));
        mInfoCurrentPriceTitle.setText(WDp.DpCurrentPriceTitle(getBaseContext(), cDenom.toUpperCase()));
        mInfoCurrentPrice.setText(WDp.getDpRawDollor(getBaseContext(), currentPrice, 4));
        try {
            Picasso.get().load(KAVA_CDP_MARKET_IMG_URL+  cParam.getImagePath()).fit().into(mInfoMarketImg);
        } catch (Exception e) { }
        mInfoCollateralRateLayer.setOnClickListener(this);
        mStabilityFeeLayer.setOnClickListener(this);
        mInfoLiquidationPenaltyLayer.setOnClickListener(this);

        if (mMyOwenCdp == null) {
            //info view
            mInfoEmptyLayer.setVisibility(View.VISIBLE);
            mInfoMyLayer.setVisibility(View.GONE);
            mInfoCollateralRateView.setVisibility(View.GONE);
            mInfoLiquidationPriceLayer.setVisibility(View.GONE);
            mInfoEmptyLayer.setOnClickListener(this);

            mEmptyCollateralDenom.setText(cParam.denom.toUpperCase());
            mEmptyCollateralAmount.setText(WDp.getDpAmount2(getBaseContext(), cAvailable, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            mEmptyPrincipalDenom.setText(cParam.debt_limit.get(0).denom.toUpperCase());
            mEmptyPrincipalAmount.setText(WDp.getDpAmount2(getBaseContext(), pAvailable, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mEmptyKavaAmount.setText(WDp.getDpAmount2(getBaseContext(), kAvailable, WUtil.getKavaCoinDecimal(COSMOS_KAVA), WUtil.getKavaCoinDecimal(COSMOS_KAVA)));
            try {
                Picasso.get().load(KAVA_COIN_IMG_URL + cDenom + ".png").fit().into(mEmptyCollateralImg);
                Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mEmptyPrincipalImg);
            } catch (Exception e) { }
            mOpenCdp.setVisibility(View.VISIBLE);
            mCdpInfoCard.setVisibility(View.VISIBLE);
            mMyEmptyCard.setVisibility(View.VISIBLE);
            mOpenCdp.setOnClickListener(this);


        } else {
            //info view
            mInfoEmptyLayer.setVisibility(View.GONE);
            mInfoMyLayer.setVisibility(View.VISIBLE);
            mInfoCollateralRateView.setVisibility(View.VISIBLE);
            mInfoLiquidationPriceLayer.setVisibility(View.VISIBLE);
            mInfoMyLayer.setOnClickListener(this);

            final BigDecimal liquidationPrice = WDp.getLiquidationPrice(mMyOwenCdp, new BigDecimal(cParam.liquidation_ratio));
            final BigDecimal safeRate = (currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN);
            final BigDecimal riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

            mInfoLiquidationPriceTitle.setText(WDp.DpLiquidationPriceTitle(getBaseContext(), cDenom.toUpperCase()));
            mInfoLiquidationPrice.setText(WDp.getDpRawDollor(getBaseContext(), liquidationPrice, 4));

            mInfoSafeRate.setText(WDp.getDpAmount2(getBaseContext(), riskRate, 0, 2));
            if (riskRate.longValue() < 50) {
                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_safe));
                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpSafe));
            } else if (riskRate.longValue() < 80) {
                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_stable));
                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpStable));
            } else {
                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_danger));
                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpDanger));
            }

//            mInfoSafeRate.setText(WDp.getDpAmount2(getBaseContext(), safeRate, 0, 2));
//            if (safeRate.longValue() >= 0.5) {
//                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_safe));
//                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpSafe));
//            } else if (safeRate.longValue() >= 0.2) {
//                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_stable));
//                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpStable));
//            } else {
//                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_danger));
//                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpDanger));
//            }

//            BigDecimal liquidationRatio = new BigDecimal(cParam.liquidation_ratio);
//            BigDecimal collateralizationRatio = new BigDecimal(mMyOwenCdp.collateralization_ratio);
//            BigDecimal safeRate = collateralizationRatio.subtract(liquidationRatio).movePointRight(2).divide(liquidationRatio, 6, RoundingMode.DOWN);
//            mInfoSafeRate.setText(WDp.getPercentDp(safeRate, 2));
//            if (safeRate.longValue() > 0.7) {
//                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_safe));
//                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpSafe));
//            } else if (safeRate.longValue() > 0.3) {
//                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_stable));
//                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpStable));
//            } else {
//                mInfoSafeBar.setImageDrawable(getResources().getDrawable(R.drawable.cdp_bar_danger));
//                mInfoSafeRate.setTextColor(getResources().getColor(R.color.colorCdpDanger));
//            }
//            mInfoLiquidationPrice.setText(WDp.getDpRawDollor(getBaseContext(), WDp.getLiquidationPrice(mMyOwenCdp, liquidationRatio).toPlainString(), 4));


            mMyCollateralDenom.setText(cParam.denom.toUpperCase());
            mMyCollateralAvailableTitle.setText(getString(R.string.str_available) + " " + cParam.denom.toUpperCase());
            mMyCollateralAvailable.setText(WDp.getDpAmount2(getBaseContext(), cAvailable, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            BigDecimal selfDepositAmount = BigDecimal.ZERO;
            for (ResCdpDepositStatus.Result deposit:mMyDepositList) {
                if (deposit.cdp_id.equals(mMyOwenCdp.cdp.id) && deposit.depositor.equals(mAccount.address)) {
                    selfDepositAmount = new BigDecimal(deposit.amount.get(0).amount);
                }
            }
            BigDecimal cValue = new BigDecimal(mMyOwenCdp.collateral_value.amount);
            BigDecimal debtValue = new BigDecimal(mMyOwenCdp.cdp.principal.get(0).amount);
            BigDecimal feeValue = new BigDecimal(mMyOwenCdp.cdp.accumulated_fees.get(0).amount);
            BigDecimal toRepayValue = debtValue.add(feeValue);
            BigDecimal withdrawableValue = cValue.subtract(toRepayValue.multiply(new BigDecimal(cParam.liquidation_ratio)).setScale(0));
            BigDecimal withdrawableAmount = withdrawableValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).divide(new BigDecimal(mKavaTokenPrice.price), 0, RoundingMode.HALF_DOWN);
            BigDecimal approxWithdrawableAmount = withdrawableAmount.multiply(new BigDecimal(0.95)).setScale(0, RoundingMode.DOWN);
            BigDecimal drawDebtValue = withdrawableValue.divide(new BigDecimal(cParam.liquidation_ratio), 0, RoundingMode.DOWN);
            BigDecimal approxDrawDebtValue = drawDebtValue.multiply(new BigDecimal(0.95)).setScale(0, RoundingMode.DOWN);
            WLog.w("withdrawableValue " +  withdrawableValue);
            WLog.w("drawDebtValue " +  drawDebtValue);
            WLog.w("approxDrawDebtValue " +  approxDrawDebtValue);


            mMySelfDepositAmount.setText(WDp.getDpAmount2(getBaseContext(), selfDepositAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            mMyTotalDepositAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(mMyOwenCdp.cdp.collateral.get(0).amount), WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            mMyWithdrawableAmountTitle.setText(getString(R.string.str_expected_withdrawable_amount) + " " + cDenom.toUpperCase());
            mMyWithdrawableAmount.setText(WDp.getDpAmount2(getBaseContext(), approxWithdrawableAmount.compareTo(selfDepositAmount) > 0 ? selfDepositAmount : approxWithdrawableAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            mMyBtnDepositTxt.setText(getString(R.string.str_collateral_deposit) + " " + cDenom.toUpperCase());
            mMyBtnWithdrawTxt.setText(getString(R.string.str_collateral_withdraw) + " " + cDenom.toUpperCase());

            mMyPrincipalDenom.setText(pDenom.toUpperCase());
            mMyPrincipalAvailable.setText(WDp.getDpAmount2(getBaseContext(), pAvailable, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyLoadnedAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(mMyOwenCdp.cdp.principal.get(0).amount), WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyLoadableAmount.setText(WDp.getDpAmount2(getBaseContext(), approxDrawDebtValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyOutstandingDebtAmount.setText(WDp.getDpAmount2(getBaseContext(), toRepayValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));

            mMyKavaAvailable.setText(WDp.getDpAmount2(getBaseContext(), kAvailable, WUtil.getKavaCoinDecimal(COSMOS_KAVA), WUtil.getKavaCoinDecimal(COSMOS_KAVA)));

            try {
                Picasso.get().load(KAVA_COIN_IMG_URL + cDenom + ".png").fit().into(mMyCollateralImg);
                Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mMyPrincipalImg);
            } catch (Exception e) { }

            mMySelfDepositLayer.setOnClickListener(this);
            mMyTotalDepositLayer.setOnClickListener(this);
            mMyWithdrawableLayer.setOnClickListener(this);
            mMyBtnDeposit.setOnClickListener(this);
            mMyBtnWithdraw.setOnClickListener(this);
            mMyLoadnedLayer.setOnClickListener(this);
            mMyLoadableLayer.setOnClickListener(this);
            mMyOutstandingDebtLayer.setOnClickListener(this);
            mMyBtnDrawdebt.setOnClickListener(this);
            mMyBtnRepay.setOnClickListener(this);

            mOpenCdp.setVisibility(View.GONE);
            mCdpInfoCard.setVisibility(View.VISIBLE);
            mMyCard.setVisibility(View.VISIBLE);

        }
        mLoadingLayer.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mInfoCollateralRateLayer) || v.equals(mInfoEmptyLayer)) {
            onShowHelpPopup(getString(R.string.str_help_collateral_rate_t), getString(R.string.str_help_collateral_rate));

        } else if (v.equals(mStabilityFeeLayer)) {
            onShowHelpPopup(getString(R.string.str_help_stability_fee_t), getString(R.string.str_help_stability_fee));

        } else if (v.equals(mInfoLiquidationPenaltyLayer)) {
            onShowHelpPopup(getString(R.string.str_help_liquidation_penalty_t), getString(R.string.str_help_liquidation_penalty));

        } else if (v.equals(mMySelfDepositLayer)) {
            onShowHelpPopup(getString(R.string.str_help_self_deposited_collateral_t),
                    String.format(getString(R.string.str_help_self_deposited_collateral_), mMarketDenom.toUpperCase()));

        } else if (v.equals(mMyTotalDepositLayer)) {
            onShowHelpPopup(getString(R.string.str_help_total_deposited_collateral_t),
                    String.format(getString(R.string.str_help_total_deposited_collateral), mMarketDenom.toUpperCase()));

        } else if (v.equals(mMyWithdrawableLayer)) {
            onShowHelpPopup(getString(R.string.str_help_withdrawable_t) + " " + mMarketDenom.toUpperCase(),
                    getString(R.string.str_help_withdrawable));

        } else if (v.equals(mMyLoadnedLayer)) {
            onShowHelpPopup(getString(R.string.str_help_loaned_amount_t), getString(R.string.str_help_loaned_amount));

        } else if (v.equals(mMyLoadableLayer)) {
            onShowHelpPopup(getString(R.string.str_help_remaining_loan_capacity_t), getString(R.string.str_help_remaining_loan_capacity));

        } else if (v.equals(mMyOutstandingDebtLayer)) {
            onShowHelpPopup(getString(R.string.str_help_outstanding_debt_t), getString(R.string.str_help_outstanding_debt));

        } else if (v.equals(mInfoMyLayer)) {
            Bundle bundle = new Bundle();
            Dialog_Safe_Score_Staus dialog = Dialog_Safe_Score_Staus.newInstance(bundle);
            dialog.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mMyBtnDeposit)) {
            WLog.w("mMyBtnDeposit");

        } else if (v.equals(mMyBtnWithdraw)) {
            WLog.w("mMyBtnWithdraw");

        } else if (v.equals(mMyBtnDrawdebt)) {
            WLog.w("mMyBtnDrawdebt");

        } else if (v.equals(mMyBtnRepay)) {
            WLog.w("mMyBtnRepay");

        } else if (v.equals(mOpenCdp)) {
            onCheckStartCreateCdp();

        }
    }

    private void onShowHelpPopup(String title, String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("msg", msg);
        Dialog_Help_Msg dialog = Dialog_Help_Msg.newInstance(bundle);
        dialog.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
    }

    private void onCheckStartCreateCdp() {
        //TODO add check logic!
        Intent intent = new Intent(this, CreateCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }



    private int mTaskCount = 0;
    public void onFetchCdpInfo() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            //not yet

        } else if (mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mTaskCount = 3;
            new KavaCdpParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaMarketPriceTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mMaketId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaCdpByOwnerTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, mMarketDenom).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_KAVA_CDP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                mCdpParam = (ResCdpParam.Result)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_TOKEN_PRICE) {
            if (result.isSuccess && result.resultData != null) {
                mKavaTokenPrice = (ResKavaMarketPrice.Result)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_OWENER) {
            if (result.isSuccess && result.resultData != null) {
                mMyOwenCdp = (ResCdpOwnerStatus.Result)result.resultData;
                mTaskCount = mTaskCount + 1;
                new KavaCdpByDepositorTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, mMarketDenom).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMyDepositList = (ArrayList<ResCdpDepositStatus.Result>)result.resultData;
            }
        }

        if (mTaskCount == 0) {
            if (mCdpParam == null || mKavaTokenPrice == null) {
                WLog.w("ERROR");
                Toast.makeText(getBaseContext(), getString(R.string.str_network_error_title), Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
            onUpdateView();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }



}
