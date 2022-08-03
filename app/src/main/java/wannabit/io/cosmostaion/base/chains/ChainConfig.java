package wannabit.io.cosmostaion.base.chains;

import com.google.common.collect.ImmutableList;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.crypto.ChildNumber;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;

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

    public abstract String chainIdPrefix();

    public abstract int mainDenomImg();

    public abstract String mainDenom();

    public String mainSymbol() {
        return mainDenom().substring(1).toUpperCase();
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

    public abstract String addressPrefix();

    public boolean etherAddressSupport() { return false; }

    public abstract boolean pushSupport();

    public abstract boolean dexSupport();

    public abstract boolean wcSupport();

    public String grpcUrl() { return ""; }

    public int grpcPort() {
        return 9090;
    }

    public String lcdUrl() {
        return "";
    }

    public abstract String apiUrl();

    public ManagedChannel channelMain() {
        return ManagedChannelBuilder.forAddress(grpcUrl(), grpcPort()).usePlaintext().build();
    }

    public Retrofit lcdMain() {
        return new Retrofit.Builder().baseUrl(lcdUrl()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Retrofit apiMain() {
        return new Retrofit.Builder().baseUrl(apiUrl()).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public abstract BigDecimal blockTime();

    public abstract String explorerUrl();

    public abstract String monikerUrl();

    public abstract String relayerImgUrl();

    public abstract String homeInfoLink();

    public abstract String blogInfoLink();

    public abstract String coingeckoLink();

    public abstract ArrayList<String> gasRates();

    public int gasDefault() { return 0; }

    public List<ChildNumber> setParentPath(int customPath) {
        return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
    }

    public abstract ArrayList<String> supportHdPaths();

    public String getHdPath(int customPath, String path) {
        return supportHdPaths().get(customPath).replace("X", path);
    }

    public String coinFullName(String denom) { return StringUtils.capitalize(chainName()) + " Staking Coin"; }

    public String explorerAccountLink() {
        return explorerUrl() + "account/";
    }

    public String explorerHistoryLink(String hash) {
        return explorerUrl() + "txs/" + hash;
    }

    public String noticeLink() {
        return BaseConstant.EXPLORER_NOTICE_MINTSCAN + chainName() + "/";
    }
}
