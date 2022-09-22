package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.MONIKER_URL;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Secret extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.SECRET_MAIN; }
    public int chainImg() { return R.drawable.chain_secret; }
    public int chainInfoImg() { return R.drawable.infoicon_secret; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_secret; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_secret; }
    public int chainColor() { return R.color.color_secret; }
    public int chainBgColor() { return R.color.colorTransBgSecret; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_secret; }
    public String chainName() { return "secret"; }
    public String chainIdPrefix() { return "secret-"; }

    public int mainDenomImg() { return R.drawable.token_secret; }
    public String mainDenom() { return "uscrt"; }
    public String addressPrefix() { return "secret"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-secret.cosmostation.io"; }
    public String apiUrl() { return "https://api-secret.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.0408"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "secret/"; }
    public String monikerUrl() { return MONIKER_URL + "secret/"; }
    public String homeInfoLink() { return  "https://scrt.network"; }
    public String blogInfoLink() { return  "https://blog.scrt.network"; }
    public String coingeckoLink() { return  COINGECKO_URL + "secret"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.25uscrt");
    }

    public String defaultPath() { return "m/44'/529'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(529, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList("m/44'/118'/0'/0/X", defaultPath());
    }
}
