package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Ixo extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.IXO_MAIN; }
    public int chainImg() { return R.drawable.chain_ixo; }
    public int chainInfoImg() { return R.drawable.infoicon_ixo; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_ixo; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_ixo; }
    public int chainColor() { return R.color.color_ixo; }
    public int chainBgColor() { return R.color.colorTransBgIxo; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_ixo; }
    public String chainName() { return "ixo"; }
    public String chainIdPrefix() { return "impacthub-"; }

    public int mainDenomImg() { return R.drawable.token_ixo; }
    public String mainDenom() { return "uixo"; }
    public String addressPrefix() { return "ixo"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-ixo-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-ixo.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.821"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "ixo/"; }
    public String monikerUrl() { return MONIKER_URL + "ixo/"; }
    public String homeInfoLink() { return  "https://www.ixo.world/"; }
    public String blogInfoLink() { return "https://medium.com/ixo-blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "ixo"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.025uixo");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }

}
