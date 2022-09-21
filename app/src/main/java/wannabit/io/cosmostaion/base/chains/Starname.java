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

public class Starname extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.IOV_MAIN; }
    public int chainImg() { return R.drawable.chain_starname; }
    public int chainInfoImg() { return R.drawable.infoicon_starname; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_starname; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_starname; }
    public int chainColor() { return R.color.color_starname; }
    public int chainBgColor() { return R.color.colorTransBgStarname; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_starname; }
    public String chainName() { return "starname"; }
    public String chainIdPrefix() { return "iov-"; }

    public int mainDenomImg() { return R.drawable.token_starname; }
    public String mainDenom() { return "uiov"; }
    public String addressPrefix() { return "star"; }

    public boolean dexSupport() { return true; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-iov-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-iov.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("6.0124"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "starname/"; }
    public String monikerUrl() { return MONIKER_URL + "iov/"; }
    public String homeInfoLink() { return  "https://www.starname.me"; }
    public String blogInfoLink() { return  "https://medium.com/iov-internet-of-values"; }
    public String coingeckoLink() { return  COINGECKO_URL + "starname"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.1uiov", "1.0uiov");
    }

    public String defaultPath() { return "m/44'/234'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(234, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}
