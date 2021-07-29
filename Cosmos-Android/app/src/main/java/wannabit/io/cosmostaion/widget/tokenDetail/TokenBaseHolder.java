package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.ALTHEA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;

public class TokenBaseHolder extends BaseHolder {
    private ImageView       mIvToken;
    private TextView        mTvTokenTitle, mTvTokenDenom;
    private TextView        mTvTokenTotal, mTvTokenValue, mTvTokenAvailable;
    private RelativeLayout  mLayerLocked, mLayerFrozen;
    private TextView        mTvTokenLocked, mTvTokenFrozen;

    public TokenBaseHolder(@NonNull View itemView) {
        super(itemView);
        mIvToken            = itemView.findViewById(R.id.token_icon);
        mTvTokenTitle       = itemView.findViewById(R.id.token_title);
        mTvTokenDenom       = itemView.findViewById(R.id.token_denom);
        mTvTokenTotal       = itemView.findViewById(R.id.token_amount);
        mTvTokenValue       = itemView.findViewById(R.id.token_value);
        mTvTokenAvailable   = itemView.findViewById(R.id.token_available);

        mLayerLocked        = itemView.findViewById(R.id.token_locked_layer);
        mTvTokenLocked      = itemView.findViewById(R.id.token_locked);
        mLayerFrozen        = itemView.findViewById(R.id.token_frozen_layer);
        mTvTokenFrozen      = itemView.findViewById(R.id.token_frozen);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        if (isGRPC(chain)) {
            if (chain.equals(ALTHEA_TEST)) {
                onBindAltheaToken(c, chain, baseData, denom);
            } else if (chain.equals(OSMOSIS_MAIN)) {
                onBindOsmosisToken(c, chain, baseData, denom);
            } else if (chain.equals(SIF_MAIN)) {
                onBindSifToken(c, chain, baseData, denom);
            }

        } else if (chain.equals(BNB_MAIN) || chain.equals(BNB_TEST)) {
            onBindBnbToken(c, chain, baseData, denom);

        } else if (chain.equals(KAVA_MAIN) || chain.equals(KAVA_TEST)) {
            onBindKavaToken(c, chain, baseData, denom);

        } else if (chain.equals(OKEX_MAIN) || chain.equals(OK_TEST)) {
            onBindOkToken(c, chain, baseData, denom);

        }
    }

    public void onBindBnbToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        mLayerLocked.setVisibility(View.VISIBLE);
        mLayerFrozen.setVisibility(View.VISIBLE);

        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal frozenAmount = baseData.frozenAmount(denom);
        final BigDecimal totalAmount = availableAmount.add(lockedAmount).add(frozenAmount);
        final BigDecimal convertAmount = WUtil.getBnbConvertAmount(baseData, denom, totalAmount);

        mTvTokenAvailable.setText(WDp.getDpAmount2(context, availableAmount, 0, 8));
        mTvTokenLocked.setText(WDp.getDpAmount2(context, lockedAmount, 0, 8));
        mTvTokenFrozen.setText(WDp.getDpAmount2(context, frozenAmount, 0, 8));
        mTvTokenTotal.setText(WDp.getDpAmount2(context, totalAmount, 0, 8));
        mTvTokenValue.setText(WDp.dpUserCurrencyValue(baseData, TOKEN_BNB, convertAmount, 0));

        final BnbToken bnbToken = baseData.getBnbToken(denom);
        if (bnbToken != null) {
            mTvTokenTitle.setText(bnbToken.original_symbol.toUpperCase());
            mTvTokenDenom.setText("(" + bnbToken.symbol + ")");
            Picasso.get().load(TOKEN_IMG_URL+bnbToken.original_symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mIvToken);
        }
    }

    public void onBindKavaToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        final int dpDecimal                     = WUtil.getKavaCoinDecimal(denom);
        final BigDecimal availableTokenAmount   = baseData.availableAmount(denom);
        final BigDecimal convertedToKava        = WDp.convertTokenToKava(baseData, denom);

        mTvTokenTitle.setText(denom.toUpperCase());
        mTvTokenDenom.setText("(" + denom + ")");
        mTvTokenAvailable.setText(WDp.getDpAmount2(context, availableTokenAmount, dpDecimal, dpDecimal));
        mTvTokenTotal.setText(WDp.getDpAmount2(context, availableTokenAmount, dpDecimal, dpDecimal));
        mTvTokenValue.setText(WDp.dpUserCurrencyValue(baseData, TOKEN_KAVA, convertedToKava, 6));
        Picasso.get().load(KAVA_COIN_IMG_URL+denom+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mIvToken);
    }

    public void onBindOkToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        mLayerLocked.setVisibility(View.VISIBLE);
        final OkToken okToken = baseData.okToken(denom);
        if (okToken != null) {
            mTvTokenTitle.setText(okToken.original_symbol.toUpperCase());
            mTvTokenDenom.setText("(" + denom + ")");

            final BigDecimal availableAmount = baseData.availableAmount(denom);
            final BigDecimal lockedAmount = baseData.lockedAmount(denom);
            final BigDecimal totalAmount = availableAmount.add(lockedAmount);
            final BigDecimal convertedAmount = WDp.convertTokenToOkt(baseData, denom);

            mTvTokenAvailable.setText(WDp.getDpAmount2(context, availableAmount, 0, 18));
            mTvTokenLocked.setText(WDp.getDpAmount2(context, lockedAmount, 0, 18));
            mTvTokenTotal.setText(WDp.getDpAmount2(context, totalAmount, 0, 18));
            mTvTokenValue.setText(WDp.dpUserCurrencyValue(baseData, TOKEN_OK, convertedAmount, 0));
            Picasso.get().load(OKEX_COIN_IMG_URL+okToken.original_symbol+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mIvToken);
        }
    }

    public void onBindSifToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        mTvTokenTitle.setText(denom.substring(1).toUpperCase());
        mTvTokenDenom.setText("(" + denom + ")");

        final int dpDecimal = WUtil.getSifCoinDecimal(denom);
        final BigDecimal totalAmount = baseData.getAvailable(denom);

        mTvTokenAvailable.setText(WDp.getDpAmount2(context, totalAmount, dpDecimal, dpDecimal));
        mTvTokenTotal.setText(WDp.getDpAmount2(context, totalAmount, dpDecimal, dpDecimal));
        mTvTokenValue.setText(WDp.dpUserCurrencyValue(baseData, denom.substring(1), totalAmount, dpDecimal));
        Picasso.get().load(SIF_COIN_IMG_URL+denom+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mIvToken);
    }

    public void onBindAltheaToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        mTvTokenTitle.setText(denom.substring(1).toUpperCase());
        mTvTokenDenom.setText("(" + denom + ")");

        final BigDecimal totalAmount = baseData.getAvailable(denom);

        mTvTokenAvailable.setText(WDp.getDpAmount2(context, totalAmount, 6, 6));
        mTvTokenTotal.setText(WDp.getDpAmount2(context, totalAmount, 6, 6));
        mTvTokenValue.setText(WDp.dpUserCurrencyValue(baseData, denom.substring(1), totalAmount, 6));
        Picasso.get().load(ALTHEA_COIN_IMG_URL+denom+".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(mIvToken);
    }

    public void onBindOsmosisToken(Context context, BaseChain chain, BaseData baseData, String denom) {
        if (denom.startsWith("uion")) {
            mIvToken.setImageDrawable(context.getDrawable(R.drawable.token_ion));
            mTvTokenTitle.setText(denom.substring(1).toUpperCase());
            mTvTokenTitle.setTextColor(context.getColor(R.color.colorIon));
            mTvTokenDenom.setText("(" + denom + ")");

        } else if (denom.startsWith("ibc/")) {
            mIvToken.setImageDrawable(context.getDrawable(R.drawable.token_default_ibc));
            mTvTokenTitle.setText("IBC");
            mTvTokenTitle.setTextColor(context.getColor(R.color.colorWhite));
            mTvTokenDenom.setText("(" + denom + ")");
            mTvTokenDenom.setEllipsize(TextUtils.TruncateAt.MIDDLE);


        } else if (denom.startsWith("gamm/")) {
            mIvToken.setImageDrawable(context.getDrawable(R.drawable.token_ic));
            mTvTokenTitle.setText("AMM");
            mTvTokenTitle.setTextColor(context.getColor(R.color.colorWhite));
            mTvTokenDenom.setText("(" + denom + ")");
        }

        final BigDecimal totalAmount = baseData.getAvailable(denom);
        mTvTokenAvailable.setText(WDp.getDpAmount2(context, totalAmount, 6, 6));
        mTvTokenTotal.setText(WDp.getDpAmount2(context, totalAmount, 6, 6));
        mTvTokenValue.setText(WDp.dpUserCurrencyValue(baseData, denom.substring(1), totalAmount, 6));
    }
}
