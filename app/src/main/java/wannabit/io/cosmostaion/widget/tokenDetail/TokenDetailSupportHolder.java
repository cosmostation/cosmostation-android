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

import irismod.nft.Nft;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

public class TokenDetailSupportHolder extends BaseHolder {
    private CardView            mAmountView;
    private TextView            mTvTotal;
    private TextView            mTvAvailable;
    private RelativeLayout      mLockedLayout;
    private TextView            mTvLocked;
    private RelativeLayout      mFrozenLayout;
    private TextView            mTvFrozen;
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
        mAmountView         = itemView.findViewById(R.id.card_root);
        mTvTotal            = itemView.findViewById(R.id.total_amount);
        mTvAvailable        = itemView.findViewById(R.id.available_amount);
        mLockedLayout       = itemView.findViewById(R.id.locked_layout);
        mTvLocked           = itemView.findViewById(R.id.locked_amount);
        mFrozenLayout       = itemView.findViewById(R.id.frozen_layout);
        mTvFrozen           = itemView.findViewById(R.id.frozen_amount);
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

    public void onBindNativeTokengRPC(Context c, BaseChain baseChain, BaseData baseData, String denom) {
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            dpDecimal = WUtil.getKavaCoinDecimal(baseData, denom);
            if (denom.equalsIgnoreCase(TOKEN_HARD)) {
                mAmountView.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBghard));
            } else if (denom.equalsIgnoreCase(TOKEN_USDX)) {
                mAmountView.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgusdx));
            } else if (denom.equalsIgnoreCase(TOKEN_SWP)) {
                mAmountView.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgswp));
            }

            mAvailableAmount = baseData.getAvailable(denom);
            BigDecimal vestingAmount = baseData.getVesting(denom);
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount.add(vestingAmount), dpDecimal, dpDecimal));
            mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
            if (vestingAmount.compareTo(BigDecimal.ZERO) > 0){
                mVestingLayout.setVisibility(View.VISIBLE);
                mTvVesting.setText(WDp.getDpAmount2(c, vestingAmount, dpDecimal, dpDecimal));
            }
        } else {
            dpDecimal = 6;
            mAvailableAmount = baseData.getAvailable(denom);
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
            mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        }
    }

    public void onBindPoolToken(Context c, BaseChain baseChain, BaseData baseData, String denom) {
        if (baseChain.equals(COSMOS_MAIN)) {
            dpDecimal = 6;
        } else if (baseChain.equals(CRESCENT_MAIN)) {
            dpDecimal = 12;
        } else {
            dpDecimal = 18;
        }
        mAvailableAmount = baseData.getAvailable(denom);
        mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
    }

    public void onBindKavaToken(Context c, BaseData baseData, String denom) {
        dpDecimal = WUtil.getKavaCoinDecimal(baseData, denom);
        mAvailableAmount = baseData.availableAmount(denom);
        if (denom.equalsIgnoreCase(TOKEN_HARD)) {
            mAmountView.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBghard));
        } else if (denom.equalsIgnoreCase(TOKEN_USDX)) {
            mAmountView.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgusdx));
        } else if (denom.equalsIgnoreCase(TOKEN_SWP)) {
            mAmountView.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgswp));
        }

        BigDecimal vestingAmount = baseData.lockedAmount(denom);
        mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount.add(vestingAmount), dpDecimal, dpDecimal));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, dpDecimal, dpDecimal));
        if (vestingAmount.compareTo(BigDecimal.ZERO) > 0){
            mVestingLayout.setVisibility(View.VISIBLE);
            mTvVesting.setText(WDp.getDpAmount2(c, vestingAmount, dpDecimal, dpDecimal));
        }

    }

    public void onBindBNBTokens(Context c, BaseData baseData, String denom) {
        BnbToken bnbToken = baseData.getBnbToken(denom);
        if (bnbToken != null) {
            mLockedLayout.setVisibility(View.VISIBLE);
            mFrozenLayout.setVisibility(View.VISIBLE);
        }
        mAvailableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal frozenAmount = baseData.frozenAmount(denom);
        mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, 0, 8));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, 0, 8));
        mTvLocked.setText(WDp.getDpAmount2(c, lockedAmount, 0, 8));
        mTvFrozen.setText(WDp.getDpAmount2(c, frozenAmount, 0, 8));
    }

    public void onBindOKTokens(Context c, BaseData baseData, String denom) {
        final OkToken okToken   = baseData.okToken(denom);
        if (okToken != null) {
            mLockedLayout.setVisibility(View.VISIBLE);
        }
        mAvailableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal totalAmount = mAvailableAmount.add(lockedAmount);

        mTvTotal.setText(WDp.getDpAmount2(c, totalAmount, 0, 18));
        mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, 0, 18));
        mTvLocked.setText(WDp.getDpAmount2(c, lockedAmount, 0, 18));
    }

    public void onBindBridgeToken(Context c, BaseChain baseChain, BaseData baseData, String denom) {
        final Assets assets = baseData.getAsset(denom);
        if (assets != null) {
            mAvailableAmount = baseData.getAvailable(assets.denom);
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, assets.decimal, assets.decimal));
            mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, assets.decimal, assets.decimal));
        }
    }

    public void onBindNftInfo(Context c, BaseChain baseChain, QueryOuterClass.QueryNFTResponse irisResponse, chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo, String denomId, String tokenId) {
        if (baseChain.equals(IRIS_MAIN) && irisResponse != null) {
            mNftInfo.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgIris));
            mNftName.setText(irisResponse.getNft().getName());
            mNftContent.setText(WUtil.getNftDescription(irisResponse.getNft().getData()));
            mNftIssuer.setText(WUtil.getNftIssuer(irisResponse.getNft().getData()));

        } else if (baseChain.equals(CRYPTO_MAIN) && myCryptoNftInfo != null) {
            mNftInfo.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgCryto));
            mNftName.setText(myCryptoNftInfo.getName());
            mNftContent.setText(WUtil.getNftDescription(myCryptoNftInfo.getData()));
            mNftIssuer.setText(myCryptoNftInfo.getOwner());
        }
        mNftDenomId.setText(denomId);
        mNftTokenId.setText(tokenId);
    }

    public void onBindNftRawData(Context c, BaseChain baseChain, QueryOuterClass.QueryNFTResponse irisResponse, chainmain.nft.v1.Nft.BaseNFT myCryptoNftInfo) {
        if (baseChain.equals(IRIS_MAIN) && irisResponse != null) {
            mNftRawRoot.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgIris));
            if (irisResponse.getNft().getData().isEmpty()) {
                mNftRawData.setText("");
            } else {
                mNftRawData.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(irisResponse.getNft().getData())));
            }
        } else if (baseChain.equals(CRYPTO_MAIN) && myCryptoNftInfo != null) {
            mNftRawRoot.setCardBackgroundColor(ContextCompat.getColor(c, R.color.colorTransBgCryto));
            if (myCryptoNftInfo.getData().isEmpty()) {
                mNftRawData.setText("");
            } else {
                mNftRawData.setText(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(myCryptoNftInfo.getData())));
            }
        }
    }

    public void onBindCw20Token(Context c, BaseChain baseChain, BaseData baseData, Cw20Assets asset) {
        if (asset != null) {
            mAvailableAmount = asset.getAmount();
            mTvTotal.setText(WDp.getDpAmount2(c, mAvailableAmount, asset.decimal, asset.decimal));
            mTvAvailable.setText(WDp.getDpAmount2(c, mAvailableAmount, asset.decimal, asset.decimal));
        }
    }
}
