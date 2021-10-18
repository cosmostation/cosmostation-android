package wannabit.io.cosmostaion.widget;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dialog.Dialog_Help_Msg;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class CdpDetailMyStatusHolder extends BaseHolder {
    private ImageView           mMyCollateralImg;
    private TextView            mMyCollateralDenom;
    private LinearLayout        mMySelfDepositLayer;
    private TextView            mMySelfDepositAmount;
    private LinearLayout        mMyTotalDepositLayer;
    private TextView            mMyTotalDepositAmount;
    private LinearLayout        mMyWithdrawableLayer;
    private TextView            mMyWithdrawableAmountTitle;
    private TextView            mMyWithdrawableAmount;
    private TextView            mMySelfDepositValue, mMyTotalDepositValue, mMyWithdrawableValue;
    private RelativeLayout      mMyBtnDeposit, mMyBtnWithdraw;
    private TextView            mMyBtnDepositTxt, mMyBtnWithdrawTxt;
    private ImageView           mMyPrincipalImg;
    private TextView            mMyPrincipalDenom;
    private LinearLayout        mMyLoadnedLayer;
    private TextView            mMyLoadnedAmount, mMyLoadedValue;
    private LinearLayout        mMyCdpFeeLayer;
    private TextView            mMyCdpFeeAmount, mMyCdpFeeValue;
    private LinearLayout        mMyLoadableLayer;
    private TextView            mMyLoadableAmount, mMyLoadableValue;
    private RelativeLayout      mMyBtnDrawdebt, mMyBtnRepay;

    public CdpDetailMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        mMyCollateralImg                = itemView.findViewById(R.id.collateral_icon);
        mMyCollateralDenom              = itemView.findViewById(R.id.collateral_denom);
        mMySelfDepositLayer             = itemView.findViewById(R.id.self_deposited_amount_layer);
        mMySelfDepositAmount            = itemView.findViewById(R.id.self_deposited_amount);
        mMyTotalDepositLayer            = itemView.findViewById(R.id.total_deposited_amount_layer);
        mMyTotalDepositAmount           = itemView.findViewById(R.id.total_deposited_amount);
        mMyWithdrawableLayer            = itemView.findViewById(R.id.expected_withdrawable_amount_layer);
        mMyWithdrawableAmountTitle      = itemView.findViewById(R.id.expected_withdrawable_amount_title);
        mMyWithdrawableAmount           = itemView.findViewById(R.id.expected_withdrawable_amount);
        mMySelfDepositValue             = itemView.findViewById(R.id.self_deposited_value);
        mMyTotalDepositValue            = itemView.findViewById(R.id.total_deposited_value);
        mMyWithdrawableValue            = itemView.findViewById(R.id.expected_withdrawable_value);
        mMyBtnDeposit                   = itemView.findViewById(R.id.btn_deposit);
        mMyBtnDepositTxt                = itemView.findViewById(R.id.btn_deposit_txt);
        mMyBtnWithdraw                  = itemView.findViewById(R.id.btn_withdraw);
        mMyBtnWithdrawTxt               = itemView.findViewById(R.id.btn_withdraw_txt);
        mMyPrincipalImg                 = itemView.findViewById(R.id.principal_icon);
        mMyPrincipalDenom               = itemView.findViewById(R.id.principal_denom);
        mMyLoadnedLayer                 = itemView.findViewById(R.id.loaned_amount_layer);
        mMyLoadnedAmount                = itemView.findViewById(R.id.loaned_amount);
        mMyLoadedValue                  = itemView.findViewById(R.id.loaned_value);
        mMyCdpFeeLayer                  = itemView.findViewById(R.id.cdp_fee_amount_layer);
        mMyCdpFeeAmount                 = itemView.findViewById(R.id.cdp_fee_amount);
        mMyCdpFeeValue                  = itemView.findViewById(R.id.fee_value);
        mMyLoadableLayer                = itemView.findViewById(R.id.expected_loanable_amount_layer);
        mMyLoadableAmount               = itemView.findViewById(R.id.expected_loanable_amount);
        mMyLoadableValue                = itemView.findViewById(R.id.loanable_value);
        mMyBtnDrawdebt                  = itemView.findViewById(R.id.btn_drawdebt);
        mMyBtnRepay                     = itemView.findViewById(R.id.btn_repay);
    }

    @Override
    public void onBindCdpDetailMyStatus(CdpDetail5Activity context, BaseData baseData, MyCdp myCdp, String collateralType, BigDecimal selfDepositAmount) {
        final CollateralParam collateralParam   = baseData.mCdpParam.getCollateralParamByType(collateralType);
        final String cDenom                     = collateralParam.denom;
        final String pDenom                     = collateralParam.debt_limit.denom;
        final BigDecimal currentPrice           = new BigDecimal(baseData.mKavaTokenPrices.get(collateralParam.liquidation_market_id).price);

        mMySelfDepositAmount.setText(WDp.getDpAmount2(context, selfDepositAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
        BigDecimal selfDepositValue = selfDepositAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
        mMySelfDepositValue.setText(WDp.getDpRawDollor(context, selfDepositValue, 2));

        mMyTotalDepositAmount.setText(WDp.getDpAmount2(context, new BigDecimal(myCdp.cdp.collateral.amount), WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
        BigDecimal totalDepositValue = new BigDecimal(myCdp.cdp.collateral.amount).movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
        mMyTotalDepositValue.setText(WDp.getDpRawDollor(context, totalDepositValue, 2));

        mMyWithdrawableAmountTitle.setText(context.getString(R.string.str_expected_withdrawable_amount) + " " + cDenom.toUpperCase());
        BigDecimal maxWithdrawableAmount = myCdp.getWithdrawableAmount(context, collateralParam, currentPrice, selfDepositAmount);
        BigDecimal maxWithdrawableValue = maxWithdrawableAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice);
        mMyWithdrawableAmount.setText(WDp.getDpAmount2(context, maxWithdrawableAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
        mMyWithdrawableValue.setText(WDp.getDpRawDollor(context, maxWithdrawableValue, 2));

        final BigDecimal debtValue = myCdp.getPrincipalAmount();
        mMyLoadnedAmount.setText(WDp.getDpAmount2(context, debtValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
        mMyLoadedValue.setText(WDp.getDpRawDollor(context, debtValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

        final BigDecimal totalFeeValue = myCdp.getEstimatedTotalFee(context, collateralParam);
        mMyCdpFeeAmount.setText(WDp.getDpAmount2(context, totalFeeValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
        mMyCdpFeeValue.setText(WDp.getDpRawDollor(context, totalFeeValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

        final BigDecimal moreDebtAmount = myCdp.getMoreLoanableAmount(context, collateralParam);
        mMyLoadableAmount.setText(WDp.getDpAmount2(context, moreDebtAmount, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
        mMyLoadableValue.setText(WDp.getDpRawDollor(context, moreDebtAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

        mMyBtnDepositTxt.setText(String.format(context.getString(R.string.str_btn_text_deposit), cDenom.toUpperCase()));
        mMyBtnWithdrawTxt.setText(String.format(context.getString(R.string.str_btn_text_withdraw), cDenom.toUpperCase()));

        mMyPrincipalDenom.setText(pDenom.toUpperCase());
        mMyCollateralDenom.setText(collateralParam.denom.toUpperCase());

        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + cDenom + ".png").fit().into(mMyCollateralImg);
            Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mMyPrincipalImg);
        } catch (Exception e) { }

        mMySelfDepositLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_self_deposited_collateral_t),
                        String.format(context.getString(R.string.str_help_self_deposited_collateral_), collateralParam.denom.toUpperCase()));
            }
        });
        mMyTotalDepositLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_total_deposited_collateral_t),
                        String.format(context.getString(R.string.str_help_total_deposited_collateral), collateralParam.denom.toUpperCase()));
            }
        });
        mMyWithdrawableLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_expected_withdrawable_amount) + " " + collateralParam.denom.toUpperCase(),
                        context.getString(R.string.str_help_withdrawable));
            }
        });
        mMyLoadnedLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_loaned_amount_t), context.getString(R.string.str_help_loaned_amount));
            }
        });
        mMyCdpFeeLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_total_fee_t), context.getString(R.string.str_help_total_fee));
            }
        });
        mMyLoadableLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_remaining_loan_capacity_t), context.getString(R.string.str_help_remaining_loan_capacity));
            }
        });

        mMyBtnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onCheckStartDepositCdp();

            }
        });
        mMyBtnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onCheckStartWithdrawCdp();

            }
        });
        mMyBtnDrawdebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onCheckStartDrawDebtCdp();

            }
        });
        mMyBtnRepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onCheckStartRepayCdp();

            }
        });
    }



    private void onShowHelpPopup(CdpDetail5Activity context, String title, String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("msg", msg);
        Dialog_Help_Msg dialog = Dialog_Help_Msg.newInstance(bundle);
        dialog.setCancelable(true);
        context.getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
    }
}
