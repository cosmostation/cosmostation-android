package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;

public class Iris extends ChainConfig {

    public int chainImg() { return R.drawable.chain_iris; }
    public int chainInfoImg() { return R.drawable.infoicon_iris; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_iris; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_iris; }
    public int chainColor() { return R.color.color_iris; }
    public int chainBgColor() { return R.color.colorTransBgIris; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_iris; }
    public String chainName() { return "iris"; }

    public int mainDenomImg() { return R.drawable.token_iris; }
    public String mainDenom() { return "uiris"; }
    public String mainSymbol() { return "IRIS"; }

    public String addressPrefix() { return "iaa"; }
    public String addressHdPath0() { return "m/44'/118'/0'/0/X"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return true; }
    public boolean wcSupport() { return false; }
    public BigDecimal blockTime() { return new BigDecimal("6.7884"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "iris/"; }
    public String monikerUrl() { return MONIKER_URL + "irishub/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "iris/relay-iris-unknown.png"; }
    public String homeInfoLink() { return  "https://www.irisnet.org"; }
    public String blogInfoLink() { return  "https://medium.com/irisnet-blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "irisnet"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList(addressHdPath0());
    }

    public String getHdPath(int customPath, String path) {
        return supportHdPaths().get(customPath).replace("X", path);
    }

}
