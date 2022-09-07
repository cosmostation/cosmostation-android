package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Stride extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.STRIDE_MAIN; }
    public int chainImg() { return R.drawable.chain_stride; }
    public int chainInfoImg() { return R.drawable.infoicon_stride; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_stride; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_stride; }
    public int chainColor() { return R.color.color_stride; }
    public int chainBgColor() { return R.color.colorTransBgStride; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_stride; }
    public String chainName() { return "stride"; }
    public String chainIdPrefix() { return "stride-"; }

    public int mainDenomImg() { return R.drawable.token_stride; }
    public String mainDenom() { return "ustrd"; }
    public String addressPrefix() { return "stride"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "lcd-stride-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-stride.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.732"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "stride/"; }
    public String monikerUrl() { return MONIKER_URL + "stride/"; }
    public String homeInfoLink() { return  "https://stride.zone/"; }
    public String blogInfoLink() { return  "https://stride.zone/blog"; }
    public String coingeckoLink() { return  ""; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0ustrd");
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X");
    }
}
