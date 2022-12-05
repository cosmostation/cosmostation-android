package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Sif extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.SIF_MAIN; }
    public int chainImg() { return R.drawable.chain_sif; }
    public int chainInfoImg() { return R.drawable.infoicon_sif; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_sif; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_sif; }
    public int chainColor() { return R.color.color_sif; }
    public int chainBgColor() { return R.color.colorTransBgSif; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_sif; }
    public String chainName() { return "sifchain"; }
    public String chainKoreanName() { return "시프"; }
    public String chainTitle() { return "(SifChain)"; }
    public String chainTitleToUp() { return "SIF"; }
    public String chainIdPrefix() { return "sifchain-"; }

    public int mainDenomImg() { return R.drawable.token_sif; }
    public String mainDenom() { return "rowan"; }
    public String mainSymbol() { return "ROWAN"; }
    public int decimal() { return 18; }
    public String addressPrefix() { return "sif"; }

    public boolean bridgeCoinSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "grpc-sifchain.cosmostation.io"; }
    public String apiUrl() { return "https://api-sifchain.cosmostation.io/"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "sifchain/"; }
    public String homeInfoLink() { return  "https://sifchain.finance"; }
    public String blogInfoLink() { return  "https://medium.com/sifchain-finance"; }
    public String coingeckoLink() { return  COINGECKO_URL + "sifchain"; }
}
