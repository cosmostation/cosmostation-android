package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.*;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.network.HistoryApi;

public class Kava extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.KAVA_MAIN; }
    public int chainImg() { return R.drawable.chain_kava; }
    public int chainInfoImg() { return R.drawable.infoicon_kava; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_kava; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_kava; }
    public int chainColor() { return R.color.color_kava; }
    public int chainBgColor() { return R.color.colorTransBgKava; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_kava; }
    public String chainName() { return "kava"; }
    public String chainIdPrefix() { return "kava_"; }

    public int mainDenomImg() { return R.drawable.token_kava; }
    public String mainDenom() { return "ukava"; }
    public String mainSymbol() { return "KAVA"; }

    public String addressPrefix() { return "kava"; }
    public String addressHdPath0() { return "m/44'/118'/0'/0/X"; }
    public String addressHdPath1() { return "m/44'/459'/0'/0/X"; }

    public boolean pushSupport() { return false; }
    public boolean dexSupport() { return true; }
    public boolean wcSupport() { return true; }

    public String grpcUrl() { return "lcd-kava-app.cosmostation.io"; }
    public String lcdUrl() { return "https://lcd-kava-app.cosmostation.io/"; }
    public String apiUrl() { return "https://api-kava.cosmostation.io/"; }
    public HistoryApi getHistoryApi() { return ApiClient.getKavaApi(baseChain()); }

    public BigDecimal blockTime() { return new BigDecimal("6.7262"); }
    public String explorerUrl() { return EXPLORER_BASE_URL + "kava/"; }
    public String monikerUrl() { return MONIKER_URL + "kava/"; }
    public String relayerImgUrl() { return UNKNOWN_RELAYER_URL + "kava/relay-kava-unknown.png"; }
    public String homeInfoLink() { return  "https://www.kava.io"; }
    public String blogInfoLink() { return  "https://medium.com/kava-labs"; }
    public String coingeckoLink() { return  COINGECKO_URL + "kava"; }

    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(459, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList(addressHdPath0(), addressHdPath1());
    }

    public static String KAVA_HARD_DENOM = "hard";
    public static String KAVA_USDX_DENOM = "usdx";
    public static String KAVA_SWP_DENOM = "swp";

    public static String KAVA_CDP_IMG_URL = RESOURCE_BASE_URL + "kava/cdp/";
    public static String KAVA_HARD_POOL_IMG_URL = RESOURCE_BASE_URL + "kava/hard/";
    public static String KAVA_COIN_IMG_URL = RESOURCE_BASE_URL + "coin_image/kava/";
}
