package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Umee extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.UMEE_MAIN; }
    public int chainImg() { return R.drawable.chain_umee; }
    public int chainInfoImg() { return R.drawable.infoicon_umee; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_umee; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_umee; }
    public int chainColor() { return R.color.color_umee; }
    public int chainBgColor() { return R.color.colorTransBgUmee; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_umee; }
    public String chainName() { return "umee"; }
    public String chainKoreanName() { return "우미"; }
    public String chainIdPrefix() { return "umee-"; }

    public int mainDenomImg() { return R.drawable.token_umee; }
    public String mainDenom() { return "uumee"; }
    public String addressPrefix() { return "umee"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "grpc-umee.cosmostation.io"; }
    public String apiUrl() { return "https://api-umee.cosmostation.io/"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "umee/"; }
    public String homeInfoLink() { return  "https://www.umee.cc"; }
    public String blogInfoLink() { return  "https://umee.cc/blog/"; }
    public String coingeckoLink() { return  COINGECKO_URL + "umee"; }
}

