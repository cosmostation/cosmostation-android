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

public class Persistence extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.PERSIS_MAIN; }
    public int chainImg() { return R.drawable.chain_persistence; }
    public int chainInfoImg() { return R.drawable.infoicon_persistence; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_persistence; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_persistence; }
    public int chainColor() { return R.color.color_persistence; }
    public int chainBgColor() { return R.color.colorTransBgPersistence; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_persistence; }
    public String chainName() { return "persistence"; }
    public String chainIdPrefix() { return "core-"; }

    public int mainDenomImg() { return R.drawable.token_persistence; }
    public String mainDenom() { return "uxprt"; }
    public int sendImgColor() { return chainColor(); }
    public int sendBgColor() { return R.color.colorBlack2; }
    public String addressPrefix() { return "persistence"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return false; }

    public String grpcUrl() { return "lcd-persistence-app.cosmostation.io"; }
    public String apiUrl() { return "https://api-persistence.cosmostation.io/"; }

    public BigDecimal blockTime() { return new BigDecimal("5.7982"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "persistence/"; }
    public String monikerUrl() { return MONIKER_URL + "persistence/"; }
    public String homeInfoLink() { return  "https://persistence.one"; }
    public String blogInfoLink() { return  "https://medium.com/persistence-blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "persistence"; }

    public ArrayList<String> gasRates() {
        return Lists.newArrayList("0.0uxprt", "0.025uxprt");
    }

    public String defaultPath() { return "m/44'/750'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(750, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }
}
