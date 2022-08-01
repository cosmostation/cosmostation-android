package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Medibloc extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.MEDI_MAIN; }
    public int chainImg() { return R.drawable.chain_medibloc; }
    public int chainInfoImg() { return R.drawable.infoicon_medibloc; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_medi; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_medi; }
    public int chainColor() { return R.color.color_medi; }
    public int chainBgColor() { return R.color.colorTransBgMedi; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_medi; }
    public String chainName() { return "medibloc"; }
    public String chainIdPrefix() { return "panacea-"; }

    public int mainDenomImg() { return R.drawable.token_medibloc; }
    public String mainDenom() { return "umed"; }
    public String addressPrefix() { return "panacea"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-medibloc-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-medibloc.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.7849"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "medibloc/"; }
    public String monikerUrl() { return MONIKER_URL + "medibloc/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "medibloc/relay-medibloc-unknown.png"; }
    public String homeInfoLink() {
        if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
            return "https://medibloc.com";
        } else {
            return "https://medibloc.com/en";
        }
    }
    public String blogInfoLink() {
        if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
            return "https://blog.medibloc.org";
        } else {
            return "https://medium.com/medibloc";
        }
    }

    public String coingeckoLink() { return  COINGECKO_URL + "medibloc"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("5umed");
    }

    public int gasDefault() { return 0; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(371, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/371'/0'/0/X");
    }
}
