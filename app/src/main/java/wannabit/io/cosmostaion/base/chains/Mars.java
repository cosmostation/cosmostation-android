package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Mars extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.MARS_MAIN; }
    public int chainImg() { return R.drawable.chain_mars; }
    public int chainInfoImg() { return R.drawable.infoicon_mars; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_mars; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_mars; }
    public int chainColor() { return R.color.color_mars; }
    public int chainBgColor() { return R.color.colorTransBgMars; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_mars; }
    public String chainName() { return "mars-protocol"; }
    public String chainKoreanName() { return "마스"; }
    public String chainIdPrefix() { return "mars-"; }

    public int mainDenomImg() { return R.drawable.token_mars; }
    public String mainDenom() { return "umars"; }
    public String addressPrefix() { return "mars"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "grpc-mars-protocol.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "mars-protocol/"; }
    public String homeInfoLink() { return  "https://marsprotocol.io/"; }
    public String blogInfoLink() { return  "https://blog.marsprotocol.io/"; }
    public String coingeckoLink() { return  COINGECKO_URL + "mars-protocol-a7fcbcfb-fd61-4017-92f0-7ee9f9cc6da3"; }
}

