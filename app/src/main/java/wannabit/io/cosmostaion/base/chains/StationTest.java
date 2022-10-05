package wannabit.io.cosmostaion.base.chains;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class StationTest extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.STATION_TEST; }
    public int chainImg() { return R.drawable.testnet_station; }
    public int chainInfoImg() { return R.drawable.infoicon_station; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_station; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_station; }
    public int chainColor() { return R.color.color_station; }
    public int chainBgColor() { return R.color.colorTransBgStation; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_station; }
    public String chainName() { return "station"; }
    public String chainKoreanName() { return "스테이션"; }
    public String chainTitle() { return "(Station Testnet)"; }
    public String chainTitleToUp() { return "STATION TEST"; }
    public String chainIdPrefix() { return "station"; }

    public int mainDenomImg() { return R.drawable.token_iss; }
    public String mainDenom() { return "uiss"; }
    public String addressPrefix() { return "station"; }

    public boolean dexSupport() { return false; }
    public boolean wcSupport() { return true; }

    public String grpcUrl() { return "lcd-office.cosmostation.io"; }
    public int grpcPort() {
        return 10400;
    }
    public String apiUrl() { return "https://api-office.cosmostation.io/station-testnet/"; }

    public BigDecimal blockTime() { return new BigDecimal(""); }
    public String explorerUrl() { return "https://testnet.mintscan.io/station/"; }
    public String monikerUrl() { return ""; }
    public String homeInfoLink() { return  ""; }
    public String blogInfoLink() { return  ""; }
    public String coingeckoLink() { return  ""; }
}
