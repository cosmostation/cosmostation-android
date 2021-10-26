package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class SifPoolOtherHolder extends BaseHolder {
    CardView    itemRoot;
    ImageView   itemExternalImg;
    TextView    itemPoolType;
    TextView    itemTotalDepositValue;
    TextView    itemTotalDepositAmount0, itemTotalDepositSymbol0, itemTotalDepositAmount1, itemTotalDepositSymbol1;
    TextView    itemMyAvailableAmount0, itemMyAvailableSymbol0, itemMyAvailableAmount1, itemMyAvailableSymbol1;

    public SifPoolOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemExternalImg = itemView.findViewById(R.id.pool_external_img);
        itemPoolType = itemView.findViewById(R.id.pool_market_type);
        itemTotalDepositValue = itemView.findViewById(R.id.pool_total_liquidity_value);
        itemTotalDepositAmount0 = itemView.findViewById(R.id.pool_total_liquidity_amount1);
        itemTotalDepositSymbol0 = itemView.findViewById(R.id.pool_total_liquidity_symbol1);
        itemTotalDepositAmount1 = itemView.findViewById(R.id.pool_total_liquidity_amount2);
        itemTotalDepositSymbol1 = itemView.findViewById(R.id.pool_total_liquidity_symbol2);

        itemMyAvailableAmount0 = itemView.findViewById(R.id.mypool_amount1);
        itemMyAvailableSymbol0 = itemView.findViewById(R.id.mypool_symbol1);
        itemMyAvailableAmount1 = itemView.findViewById(R.id.mypool_amount2);
        itemMyAvailableSymbol1 = itemView.findViewById(R.id.mypool_symbol2);
    }

    @Override
    public void onBindSifOtherEthPool(Context context, SifDexListActivity activity, BaseData baseData, Types.Pool otherPool) {
        int rowanDecimal = WDp.mainDisplayDecimal(BaseConstant.TOKEN_SIF);
        BigDecimal rowanAmount = new BigDecimal(otherPool.getNativeAssetBalance());
        int externalDecimal = WUtil.getSifCoinDecimal(baseData, otherPool.getExternalAsset().getSymbol());
        BigDecimal externalAmount = new BigDecimal(otherPool.getExternalAssetBalance());
        String exteranlDenom = otherPool.getExternalAsset().getSymbol();
        BigDecimal poolValue = WUtil.getSifPoolValue(baseData, otherPool);

        WUtil.DpSifTokenImg(itemExternalImg, exteranlDenom);
        itemPoolType.setText("ROWAN : " + WUtil.dpSifTokenName(exteranlDenom).toUpperCase());
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, poolValue, 2));

        WUtil.dpSifTokenName(context, itemTotalDepositSymbol0, BaseConstant.TOKEN_SIF);
        WUtil.dpSifTokenName(context, itemTotalDepositSymbol1, exteranlDenom);
        itemTotalDepositAmount0.setText(WDp.getDpAmount2(context, rowanAmount, rowanDecimal, 6));
        itemTotalDepositAmount1.setText(WDp.getDpAmount2(context, externalAmount, externalDecimal, 6));

        //dp available
        BigDecimal availableRowan = baseData.getAvailable(BaseConstant.TOKEN_SIF);
        BigDecimal availableExternal = baseData.getAvailable(exteranlDenom);
        WUtil.dpSifTokenName(context, itemMyAvailableSymbol0, BaseConstant.TOKEN_SIF);
        WUtil.dpSifTokenName(context, itemMyAvailableSymbol1, exteranlDenom);
        itemMyAvailableAmount0.setText(WDp.getDpAmount2(context, availableRowan, rowanDecimal, 6));
        itemMyAvailableAmount1.setText(WDp.getDpAmount2(context, availableExternal, externalDecimal, 6));

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SifDexListActivity)activity).onCheckStartDepositPool(otherPool);
            }
        });
    }
}
