package wannabit.io.cosmostaion.base.chains;

import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.HistoryApi;

abstract public class ChainConfig {
    public abstract BaseChain baseChain();

    public abstract int chainImg();

    public abstract int chainInfoImg();

    public abstract int chainInfoTitle();

    public abstract int chainInfoMsg();

    public abstract int chainColor();

    public abstract int chainBgColor();

    public abstract int chainTabColor();

    public abstract String chainName();

    public String chainTitle() {
        return "(" + chainName().substring(0, 1).toUpperCase() + chainName().substring(1) + ")";
    }

    public String chainTitleToUp() {
        return chainName().toUpperCase();
    }

    public String chainIdPrefix() {
        return "";
    }

    public int mainDenomImg() {
        return 0;
    }

    public String mainDenom() {
        return "";
    }

    public String mainSymbol() {
        return "";
    }

    public int decimal() {
        return 6;
    }

    public int sendImgColor() {
        return R.color.colorWhite;
    }

    public int sendBgColor() {
        return chainColor();
    }

    public String addressPrefix() {
        return "";
    }

    public boolean pushSupport() {
        return false;
    }

    public boolean dexSupport() {
        return false;
    }

    public boolean wcSupport() {
        return false;
    }

    public String grpcUrl() {
        return "";
    }

    public int grpcPort() {
        return 9090;
    }

    public String lcdUrl() {
        return "";
    }

    public String apiUrl() {
        return "";
    }

    public ManagedChannel channelMain() {
        return ManagedChannelBuilder.forAddress(grpcUrl(), grpcPort()).usePlaintext().build();
    }

    public abstract HistoryApi getHistoryApi();

    public BigDecimal blockTime() {
        return BigDecimal.ZERO;
    }

    public String explorerUrl() {
        return "";
    }

    public String monikerUrl() {
        return "";
    }

    public String relayerImgUrl() {
        return "";
    }

    public String homeInfoLink() {
        return "";
    }

    public String blogInfoLink() {
        return "";
    }

    public String coingeckoLink() {
        return "";
    }


    public List<ChildNumber> setParentPath(int customPath) {
        return null;
    }

    public abstract ArrayList<String> supportHdPaths();

    public String getHdPath(int customPath, String path) {
        return supportHdPaths().get(customPath).replace("X", path);
    }
}
