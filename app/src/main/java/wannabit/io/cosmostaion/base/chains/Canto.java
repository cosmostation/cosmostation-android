package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import com.google.common.collect.ImmutableList;

import org.bitcoinj.crypto.ChildNumber;

import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Canto extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CANTO_MAIN; }
    public int chainImg() { return R.drawable.chain_canto; }
    public int chainInfoImg() { return R.drawable.infoicon_canto; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_canto; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_canto; }
    public int chainColor() { return R.color.color_canto; }
    public int chainBgColor() { return R.color.colorTransBgCanto; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_canto; }
    public String chainName() { return "canto"; }
    public String chainKoreanName() { return "칸토"; }
    public String chainIdPrefix() { return "canto_"; }

    public int mainDenomImg() { return R.drawable.token_canto; }
    public String mainDenom() { return "acanto"; }
    public int decimal() {
        return 18;
    }
    public String addressPrefix() { return "canto"; }
    public boolean ethAccountType() { return true; }

    public boolean evmSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }
    public boolean erc20CoinSupport() { return true; }

    public String grpcUrl() { return "grpc-canto.cosmostation.io"; }
    public String rpcUrl() { return "https://rpc-canto-app.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "canto/"; }
    public String homeInfoLink() { return  "https://canto.io/"; }
    public String blogInfoLink() { return  "https://canto.mirror.xyz/"; }
    public String coingeckoLink() { return  COINGECKO_URL + "canto"; }

    public String defaultPath() { return "m/44'/60'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}

