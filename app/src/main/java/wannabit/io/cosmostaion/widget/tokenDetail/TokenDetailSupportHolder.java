package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.math.BigDecimal;

import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenDetailSupportHolder extends BaseHolder {
    private TextView            mTvTotal;
    private TextView            mTvAvailable;
    private RelativeLayout      mVestingLayout;
    private TextView            mTvVesting;

    // nft
    private CardView            mNftInfo;
    private TextView            mNftName;
    private TextView            mNftContent;
    private TextView            mNftDenomId;
    private TextView            mNftTokenId;
    private TextView            mNftIssuer;

    private CardView            mNftRawRoot;
    private TextView            mNftRawData;

    private int                 dpDecimal = 6;
    private BigDecimal          mAvailableAmount = BigDecimal.ZERO;

    public TokenDetailSupportHolder(@NonNull View itemView) {
        super(itemView);
        mTvTotal            = itemView.findViewById(R.id.total_amount);
        mTvAvailable        = itemView.findViewById(R.id.available_amount);
        mVestingLayout      = itemView.findViewById(R.id.vesting_layout);
        mTvVesting          = itemView.findViewById(R.id.vesrting_amount);

        mNftInfo            = itemView.findViewById(R.id.nft_card_root);
        mNftName            = itemView.findViewById(R.id.nft_name);
        mNftContent         = itemView.findViewById(R.id.nft_content);
        mNftDenomId         = itemView.findViewById(R.id.denom_id);
        mNftTokenId         = itemView.findViewById(R.id.token_id);
        mNftIssuer          = itemView.findViewById(R.id.issuer);

        mNftRawRoot         = itemView.findViewById(R.id.nft_raw_card_root);
        mNftRawData         = itemView.findViewById(R.id.nft_raw_data);
    }

    public void onBindNativeTokengRPC(Context c, ChainConfig chainConfig, BaseData baseData, String denom) {
        dpDecimal = WDp.getDenomDecimal(baseData, chainConfig, denom);
        mAvailableAmount = baseData.getAvailable(denom);
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));

        if (chainConfig.baseChain().equals(BaseChain.KAVA_MAIN)) {
            BigDecimal vestingAmount = baseData.getVesting(denom);
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount.add(vestingAmount), dpDecimal, dpDecimal));
            if (vestingAmount.compareTo(BigDecimal.ZERO) > 0){
                mVestingLayout.setVisibility(View.VISIBLE);
                mTvVesting.setText(WDp.getDpAmount2(c, vestingAmount, dpDecimal, dpDecimal));
            }
        } else {
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        }
    }

    public void onBindNftInfo(Context c, BaseChain baseChain, QueryOuterClass.QueryNFTResponse irisResponse, chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo, String denomId, String tokenId) {
        final ChainConfig chainConfig = ChainFactory.getChain(baseChain);
        mNftInfo.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
        if (baseChain.equals(BaseChain.IRIS_MAIN) && irisResponse != null) {
            mNftName.setText(irisResponse.getNft().getName());
            mNftContent.setText(WUtil.getNftDescription(irisResponse.getNft().getData()));
            mNftIssuer.setText(WUtil.getNftIssuer(irisResponse.getNft().getData()));

        } else if (baseChain.equals(BaseChain.CRYPTO_MAIN) && myCryptoNftInfo != null) {
            mNftName.setText(myCryptoNftInfo.getName());
            mNftContent.setText(WUtil.getNftDescription(myCryptoNftInfo.getData()));
            mNftIssuer.setText(myCryptoNftInfo.getOwner());
        }
        mNftDenomId.setText(denomId);
        mNftTokenId.setText(tokenId);
    }

    public void onBindNftRawData(Context c, BaseChain baseChain, QueryOuterClass.QueryNFTResponse irisResponse, chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo) {
        final ChainConfig chainConfig = ChainFactory.getChain(baseChain);
        mNftRawRoot.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
        if (baseChain.equals(BaseChain.IRIS_MAIN) && irisResponse != null) {
            if (irisResponse.getNft().getData().isEmpty()) {
                mNftRawData.setText("");
            } else {
                mNftRawData.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(irisResponse.getNft().getData())));
            }
        } else if (baseChain.equals(BaseChain.CRYPTO_MAIN) && myCryptoNftInfo != null) {
            if (myCryptoNftInfo.getData().isEmpty()) {
                mNftRawData.setText("");
            } else {
                mNftRawData.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(myCryptoNftInfo.getData())));
            }
        }
    }
}
