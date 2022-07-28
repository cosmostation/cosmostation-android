package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.UNKNOWN_RELAYER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Certik extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.CERTIK_MAIN; }
    public int chainImg() { return R.drawable.chain_certik; }
    public int chainInfoImg() { return R.drawable.infoicon_certik; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_certik; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_certik; }
    public int chainColor() { return R.color.color_certik; }
    public int chainBgColor() { return R.color.colorTransBgCertik; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_certik; }
    public String chainName() { return "certik"; }
    public String chainIdPrefix() { return "shentu-"; }

    public int mainDenomImg() { return R.drawable.token_certik; }
    public String mainDenom() { return "uctk"; }
    public String addressPrefix() { return "certik"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-certik-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-certik.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.9740"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "certik/"; }
    public String monikerUrl() { return MONIKER_URL + "certik/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "certik/relay-certik-unknown.png"; }
    public String homeInfoLink() { return  "https://www.certik.foundation"; }
    public String blogInfoLink() { return  "https://www.certik.foundation/blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "certik"; }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}
