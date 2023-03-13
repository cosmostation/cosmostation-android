package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Kyve extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.KYVE_MAIN; }
    public int chainImg() { return R.drawable.chain_kyve; }
    public int chainInfoImg() { return R.drawable.infoicon_kyve; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_kyve; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_kyve; }
    public int chainColor() { return R.color.color_kyve; }
    public int chainBgColor() { return R.color.colorTransBgKyve; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_kyve; }
    public String chainName() { return "kyve"; }
    public String chainKoreanName() { return "카이브"; }
    public String chainTitle() { return "kyve"; }
    public String chainIdPrefix() { return "kyve-"; }

    public int mainDenomImg() { return R.drawable.token_kyve; }
    public String mainDenom() { return "ukyve"; }
    public String addressPrefix() { return "kyve"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "grpc-kyve.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "kyve/"; }
    public String homeInfoLink() { return  "https://www.kyve.network/"; }
    public String blogInfoLink() { return  "https://blog.kyve.network/"; }
    public String coingeckoLink() { return  COINGECKO_URL + "kyve"; }
}
