package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Passage extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.PASSAGE_MAIN; }
    public int chainImg() { return R.drawable.chain_passage; }
    public int chainInfoImg() { return R.drawable.infoicon_passage; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_passage; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_passage; }
    public int chainColor() { return R.color.color_passage; }
    public int chainBgColor() { return R.color.colorTransBgPassage; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_passage; }
    public String chainName() { return "passage"; }
    public String chainKoreanName() { return "파사지"; }
    public String chainIdPrefix() { return "passage-"; }

    public int mainDenomImg() { return R.drawable.token_passage; }
    public String mainDenom() { return "upasg"; }
    public String addressPrefix() { return "pasg"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-passage-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-passage.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.8680"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "passage/"; }
    public String monikerUrl() { return MONIKER_URL + "passage/"; }
    public String homeInfoLink() { return  "https://passage3d.com/"; }
    public String blogInfoLink() { return "https://medium.com/@Passage3D"; }
    public String coingeckoLink() { return  ""; }
}
