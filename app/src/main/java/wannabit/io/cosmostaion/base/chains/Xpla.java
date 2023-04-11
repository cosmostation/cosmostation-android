package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Xpla extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.XPLA_MAIN; }
    public int chainImg() { return R.drawable.chain_xpla; }
    public int chainInfoImg() { return R.drawable.infoicon_xpla; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_xpla; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_xpla; }
    public int chainColor() { return R.color.color_xpla; }
    public int chainBgColor() { return R.color.colorTransBgXpla; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_xpla; }
    public String chainName() { return "xpla"; }
    public String chainKoreanName() { return "엑스플라"; }
    public String chainIdPrefix() { return "dimension_"; }

    public int mainDenomImg() { return R.drawable.token_xpla; }
    public String mainDenom() { return "axpla"; }
    public int decimal() { return 18; }
    public String addressPrefix() { return "xpla"; }
    public boolean ethAccountType() { return true; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return true; }
    public boolean authzSupport() { return true; }
    public boolean evmSupport() { return true; }

    public String grpcUrl() { return "grpc-xpla.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "xpla/"; }
    public String homeInfoLink() { return  "https://xpla.io"; }
    public String blogInfoLink() { return  "https://medium.com/@XPLA_Official"; }
    public String coingeckoLink() { return  COINGECKO_URL + "xpla"; }

    public String defaultPath() { return "m/44'/60'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(60, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList(defaultPath(), defaultPath());
    }
}
