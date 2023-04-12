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

public class TokenDetailHolder extends BaseHolder {
    private CardView            mCardRoot;
    private RelativeLayout      mVestingLayer;

    private TextView            mTotalAmount, mAvailableAmount;
    private TextView            mVestingAmount;
    private TextView            mDelegatedAmount, mUnbondingAmount, mRewardAmount;

    // nft
    private CardView            mNftInfo;
    private TextView            mNftName;
    private TextView            mNftContent;
    private TextView            mNftDenomId;
    private TextView            mNftTokenId;
    private TextView            mNftIssuer;

    private CardView            mNftRawRoot;
    private TextView            mNftRawData;

    // neutron
    private TextView            mBondedAmount;

    public TokenDetailHolder(@NonNull View itemView) {
        super(itemView);
        mCardRoot           = itemView.findViewById(R.id.card_root);
        mTotalAmount        = itemView.findViewById(R.id.total_amount);
        mAvailableAmount    = itemView.findViewById(R.id.available_amount);
        mVestingAmount      = itemView.findViewById(R.id.vesting_amount);
        mDelegatedAmount    = itemView.findViewById(R.id.delegated_amount);
        mUnbondingAmount    = itemView.findViewById(R.id.unbonding_amount);
        mRewardAmount       = itemView.findViewById(R.id.reward_amount);
        mVestingLayer       = itemView.findViewById(R.id.vesting_layer);

        mNftInfo            = itemView.findViewById(R.id.nft_card_root);
        mNftName            = itemView.findViewById(R.id.nft_name);
        mNftContent         = itemView.findViewById(R.id.nft_content);
        mNftDenomId         = itemView.findViewById(R.id.denom_id);
        mNftTokenId         = itemView.findViewById(R.id.token_id);
        mNftIssuer          = itemView.findViewById(R.id.issuer);

        mNftRawRoot         = itemView.findViewById(R.id.nft_raw_card_root);
        mNftRawData         = itemView.findViewById(R.id.nft_raw_data);

        mBondedAmount         = itemView.findViewById(R.id.bond_amount);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final int stakingDivideDecimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(chain), denom);
        final int stakingDisplayDecimal = WDp.mainDisplayDecimal(chain);

        if (chain.equals(BaseChain.NEUTRON_TEST)) {
            BigDecimal availableAmount = baseData.getAvailable(denom);
            BigDecimal bondAmount = BigDecimal.ZERO;
            BigDecimal totalAmount = availableAmount.add(bondAmount);

            mTotalAmount.setText(WDp.getDpAmount2(totalAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(availableAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mBondedAmount.setText(WDp.getDpAmount2(bondAmount, stakingDivideDecimal, stakingDisplayDecimal));
        } else {
            final BigDecimal totalToken = baseData.getAllMainAsset(denom);

            mTotalAmount.setText(WDp.getDpAmount2(totalToken, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(baseData.getAvailable(denom), stakingDivideDecimal, stakingDisplayDecimal));
            mDelegatedAmount.setText(WDp.getDpAmount2(baseData.getDelegationSum(), stakingDivideDecimal, stakingDisplayDecimal));
            mUnbondingAmount.setText(WDp.getDpAmount2(baseData.getUndelegationSum(), stakingDivideDecimal, stakingDisplayDecimal));
            mRewardAmount.setText(WDp.getDpAmount2(baseData.getRewardSum(denom), stakingDivideDecimal, stakingDisplayDecimal));

            final BigDecimal vestingAmount = baseData.getVesting(denom);
            if (vestingAmount.compareTo(BigDecimal.ZERO) > 0) {
                mVestingLayer.setVisibility(View.VISIBLE);
                mVestingAmount.setText(WDp.getDpAmount2(vestingAmount, stakingDivideDecimal, stakingDisplayDecimal));
            }
        }
        mCardRoot.setCardBackgroundColor(ContextCompat.getColor(c, ChainFactory.getChain(chain).chainBgColor()));
    }

    public void onBindNativeTokengRPC(Context c, ChainConfig chainConfig, BaseData baseData, String denom) {
        final int decimal = WDp.getDenomDecimal(baseData, chainConfig, denom);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        mAvailableAmount.setText(WDp.getDpAmount2(availableAmount, decimal, decimal));

        if (chainConfig.baseChain().equals(BaseChain.KAVA_MAIN)) {
            BigDecimal vestingAmount = baseData.getVesting(denom);
            mTotalAmount.setText(WDp.getDpAmount2(availableAmount.add(vestingAmount), decimal, decimal));
            if (vestingAmount.compareTo(BigDecimal.ZERO) > 0) {
                mVestingLayer.setVisibility(View.VISIBLE);
                mVestingAmount.setText(WDp.getDpAmount2(c, vestingAmount, decimal, decimal));
            }
        } else {
            mTotalAmount.setText(WDp.getDpAmount2(availableAmount, decimal, decimal));
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
