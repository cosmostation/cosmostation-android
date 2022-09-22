package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class StafiHub extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.STAFIHUB_MAIN; }
    public int chainImg() { return R.drawable.chain_stafihub; }
    public int chainInfoImg() { return R.drawable.infoicon_stafihub; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_stafihub; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_stafihub; }
    public int chainColor() { return R.color.color_stafihub; }
    public int chainBgColor() { return R.color.colorTransBgStafiHub; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_stafihub; }
    public String chainName() { return "stafihub"; }
    public String chainKoreanName() { return "스테파이허브"; }
    public String chainIdPrefix() { return "stafihub-"; }

    public int mainDenomImg() { return R.drawable.token_stafihub; }
    public String mainDenom() { return "ufis"; }
    public String addressPrefix() { return "stafi"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-stafihub-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-stafihub.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.000"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "stafihub/"; }
    public String monikerUrl() { return MONIKER_URL + "stafihub/"; }
    public String homeInfoLink() { return  "https://www.stafihub.io/"; }
    public String blogInfoLink() { return  "https://stafi-protocol.medium.com/"; }
    public String coingeckoLink() { return  COINGECKO_URL + "stafi"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.01ufis");
    }
}
