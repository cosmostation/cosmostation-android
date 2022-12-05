package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Kujira extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.KUJIRA_MAIN; }
    public int chainImg() { return R.drawable.chain_kujira; }
    public int chainInfoImg() { return R.drawable.infoicon_kujira; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_kujira; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_kujira; }
    public int chainColor() { return R.color.color_kujira; }
    public int chainBgColor() { return R.color.colorTransBgKujira; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_kujira; }
    public String chainName() { return "kujira"; }
    public String chainKoreanName() { return "쿠지라"; }
    public String chainIdPrefix() { return "kaiyo-"; }

    public int mainDenomImg() { return R.drawable.token_kujira; }
    public String mainDenom() { return "ukuji"; }
    public String addressPrefix() { return "kujira"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }
    public boolean kadoMoneySupport() { return true; }

    public String grpcUrl() { return "grpc-kujira.cosmostation.io"; }
    public String apiUrl() { return "https://api-kujira.cosmostation.io/"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "kujira/"; }
    public String homeInfoLink() { return  "https://kujira.app/"; }
    public String blogInfoLink() { return  "https://medium.com/team-kujira"; }
    public String coingeckoLink() { return  COINGECKO_URL + "kujira"; }
}
