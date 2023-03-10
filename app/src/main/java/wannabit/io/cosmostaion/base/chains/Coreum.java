package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Coreum extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.COREUM_MAIN; }
    public int chainImg() { return R.drawable.chain_coreum; }
    public int chainInfoImg() { return R.drawable.infoicon_coreum; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_coreum; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_coreum; }
    public int chainColor() { return R.color.color_coreum; }
    public int chainBgColor() { return R.color.colorTransBgCoreum; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_coreum; }
    public String chainName() { return "coreum"; }
    public String chainKoreanName() { return "코리움"; }
    public String chainIdPrefix() { return "testcore-"; }

    public int mainDenomImg() { return R.drawable.token_coreum; }
    public String mainDenom() { return "utestcore"; }
    public int decimal() {
        return 9;
    }
    public String addressPrefix() { return "testcore"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return false; }

    public String grpcUrl() { return "grpc-coreum.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "coreum/"; }
    public String homeInfoLink() { return  "https://www.coreum.com/"; }
    public String blogInfoLink() { return  ""; }
    public String coingeckoLink() { return  COINGECKO_URL + "coreum"; }
}
