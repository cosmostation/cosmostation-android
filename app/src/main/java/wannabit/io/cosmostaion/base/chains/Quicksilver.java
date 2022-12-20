package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Quicksilver extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.QUICKSILVER_MAIN; }
    public int chainImg() { return R.drawable.chain_quicksilver; }
    public int chainInfoImg() { return R.drawable.infoicon_quicksilver; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_quicksilver; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_quicksilver; }
    public int chainColor() { return R.color.color_quicksilver; }
    public int chainBgColor() { return R.color.colorTransBgQuicksilver; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_quicksilver; }
    public String chainName() { return "quicksilver"; }
    public String chainKoreanName() { return "퀵실버"; }
    public String chainIdPrefix() { return "quicksilver-"; }

    public int mainDenomImg() { return R.drawable.token_quicksilver; }
    public String mainDenom() { return "uqck"; }
    public String addressPrefix() { return "quick"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "grpc-quicksilver.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "quicksilver/"; }
    public String homeInfoLink() { return  "https://quicksilver.zone/"; }
    public String blogInfoLink() { return  "https://medium.com/quicksilverzone"; }
    public String coingeckoLink() { return  COINGECKO_URL + "quicksilver"; }
}

