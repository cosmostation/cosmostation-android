package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Lum extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.LUM_MAIN; }
    public int chainImg() { return R.drawable.chain_lum; }
    public int chainInfoImg() { return R.drawable.infoicon_lum; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_lum; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_lum; }
    public int chainColor() { return R.color.color_lum; }
    public int chainBgColor() { return R.color.colorTransBgLum; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_lum; }
    public String chainName() { return "lum"; }
    public String chainIdPrefix() { return "lum-"; }

    public int mainDenomImg() { return R.drawable.token_lum; }
    public String mainDenom() { return "ulum"; }
    public String addressPrefix() { return "lum"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-lum-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-lum.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.7210"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "lum/"; }
    public String monikerUrl() { return MONIKER_URL + "lum-network/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "lum-network/relay-lum-unknown.png"; }
    public String homeInfoLink() { return  "https://lum.network"; }
    public String blogInfoLink() { return  "https://medium.com/lum-network"; }
    public String coingeckoLink() { return  COINGECKO_URL + "lum-network"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.001ulum");
    }

    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(880, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X", "m/44'/880'/0'/0/X");
    }
}
