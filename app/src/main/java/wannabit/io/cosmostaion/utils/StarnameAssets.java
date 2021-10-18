package wannabit.io.cosmostaion.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

import static wannabit.io.cosmostaion.base.BaseConstant.CHAIN_IMG_URL;

public class StarnameAssets implements Parcelable {
    public String name;
    public String url;
    public String symbol;
    public String chainImg;
    public String chainName;

    public StarnameAssets(String name, String url, String symbol, String chainImg, String chainName) {
        this.name = name;
        this.url = url;
        this.symbol = symbol;
        this.chainName = chainName;

        if (chainImg == null) {
            this.chainImg = CHAIN_IMG_URL + "unknown.png";
        } else {
            this.chainImg = CHAIN_IMG_URL + chainImg;
        }
    }

    protected StarnameAssets(Parcel in) {
        name = in.readString();
        url = in.readString();
        symbol = in.readString();
        chainImg = in.readString();
        chainName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(symbol);
        dest.writeString(chainImg);
        dest.writeString(chainName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StarnameAssets> CREATOR = new Creator<StarnameAssets>() {
        @Override
        public StarnameAssets createFromParcel(Parcel in) {
            return new StarnameAssets(in);
        }

        @Override
        public StarnameAssets[] newArray(int size) {
            return new StarnameAssets[size];
        }
    };

    public static String getStarNameChainImgUrl(String url) {
        for (StarnameAssets assets: getStarnameAssets()) {
            if (assets.url.equalsIgnoreCase(url)) {
                return assets.chainImg;
            }
        }
        return CHAIN_IMG_URL + "unknown.png";
    }

    public static String getStarNameChainName(String url) {
        for (StarnameAssets assets: getStarnameAssets()) {
            if (assets.url.equalsIgnoreCase(url)) {
                return assets.name;
            }
        }
        return "Unknown";
    }

    public static String getStarNameGetChain(String url) {
        for (StarnameAssets assets: getStarnameAssets()) {
            if (assets.url.equalsIgnoreCase(url)) {
                return assets.chainName;
            }
        }
        return null;
    }

    public static ArrayList<StarnameAssets> getStarnameAssets() {
        ArrayList<StarnameAssets> result = new ArrayList<>();
        result.add(ada);
        result.add(akt);
        result.add(algo);
        result.add(atom);
        result.add(avax);
        result.add(band);
        result.add(bat);
        result.add(bch);
        result.add(bnb);
        result.add(btc);
        result.add(comp);
        result.add(cro);
        result.add(ctk);
        result.add(dai);
        result.add(dash);
        result.add(doge);
        result.add(dsm);
        result.add(dvpn);
        result.add(emy);
        result.add(eos);
        result.add(eth);
        result.add(ion);
        result.add(iov);
        result.add(iris);
        result.add(kava);
        result.add(ksm);
        result.add(link);
        result.add(lsk);
        result.add(ltc);
        result.add(neo);
        result.add(okb);
        result.add(osmo);
        result.add(regen);
        result.add(ren);
        result.add(req);
        result.add(rowan);
        result.add(scrt);
        result.add(shib);
        result.add(sol);
        result.add(svt);
        result.add(ter);
        result.add(trx);
        result.add(usdc);
        result.add(usdt);
        result.add(wbtc);
        result.add(xlm);
        result.add(xmr);
        result.add(xprt);
        result.add(xtz);
        result.add(zec);
        return result;
    }

    public static StarnameAssets ada    = new StarnameAssets("Cardano", "asset:ada", "ADA", "ada.png", null);
    public static StarnameAssets akt    = new StarnameAssets("Akash", "asset:akt", "AKT", "akash.png", BaseChain.AKASH_MAIN.getChain());
    public static StarnameAssets algo   = new StarnameAssets("Algorand", "asset:algo", "ALGO", "algo.png", null);
    public static StarnameAssets atom   = new StarnameAssets("Cosmos", "asset:atom", "ATOM", "cosmos.png", BaseChain.COSMOS_MAIN.getChain());
    public static StarnameAssets avax   = new StarnameAssets("Avalanche", "asset:avax", "AVAX", "avax.png", null);
    public static StarnameAssets band   = new StarnameAssets("Band Protocol", "asset:band", "BAND", "band.png", BaseChain.BAND_MAIN.getChain());
    public static StarnameAssets bat    = new StarnameAssets("Basic Attention Token", "asset:bat", "BAT", "bat.png", null);
    public static StarnameAssets bch    = new StarnameAssets("Bitcoin Cash", "asset:bch", "BCH", "bitcoincash.png", null);
    public static StarnameAssets bnb    = new StarnameAssets("BNB coin", "asset:bnb", "BNB", "binance.png", BaseChain.BNB_MAIN.getChain());
    public static StarnameAssets btc    = new StarnameAssets("Bitcoin", "asset:btc", "BTC", "bitcoin.png", null);
    public static StarnameAssets comp   = new StarnameAssets("Compound", "asset:comp", "COMP", "comp.png", null);
    public static StarnameAssets cro    = new StarnameAssets("Crypto.org", "asset:cro", "CRO", "crypto-org.png", BaseChain.CRYPTO_MAIN.getChain());
    public static StarnameAssets ctk    = new StarnameAssets("CertiK", "asset:ctk", "CTK", "certik.png", BaseChain.CERTIK_MAIN.getChain());
    public static StarnameAssets dai    = new StarnameAssets("Dai Stablecoin", "asset:dai", "DAI", "dai.png", null);
    public static StarnameAssets dash   = new StarnameAssets("Dash", "asset:dash", "DASH", "dash.png", null);
    public static StarnameAssets doge   = new StarnameAssets("Dogecoin", "asset:doge", "DOGE", "doge.png", null);
    public static StarnameAssets dsm    = new StarnameAssets("Desmos", "asset:dsm", "DSM", "dsm.png", null);
    public static StarnameAssets dvpn   = new StarnameAssets("Sentinel", "asset:dvpn", "DVPN", "sentinel.png", BaseChain.SENTINEL_MAIN.getChain());
    public static StarnameAssets eos    = new StarnameAssets("EOS", "asset:eos", "EOS", "eos.png", null);
    public static StarnameAssets eth    = new StarnameAssets("Ethereum", "asset:eth", "ETH", "ethereum.png", null);
    public static StarnameAssets ion    = new StarnameAssets("ION", "asset:ion", "ION", "osmosis.png", BaseChain.OSMOSIS_MAIN.getChain());
    public static StarnameAssets iov    = new StarnameAssets("Starname (IOV)", "asset:iov", "IOV", "starname.png", BaseChain.IOV_MAIN.getChain());
    public static StarnameAssets iris   = new StarnameAssets("IRISnet", "asset:iris", "IRIS", "iris.png", BaseChain.IRIS_MAIN.getChain());
    public static StarnameAssets kava   = new StarnameAssets("Kava", "asset:kava", "KAVA", "kava.png", BaseChain.KAVA_MAIN.getChain());
    public static StarnameAssets ksm    = new StarnameAssets("Kusama", "asset:ksm", "KSM", "ksm.png", null);
    public static StarnameAssets link   = new StarnameAssets("Chainlink", "asset:link", "LINK", "link.png", null);
    public static StarnameAssets ltc    = new StarnameAssets("Litecoin", "asset:ltc", "LTC", "litecoin.png", null);
    public static StarnameAssets neo    = new StarnameAssets("Neo", "asset:neo", "NEO", "neo.png", null);
    public static StarnameAssets okb    = new StarnameAssets("OKB", "asset:okb", "OKB", "okex.png", BaseChain.OKEX_MAIN.getChain());
    public static StarnameAssets osmo   = new StarnameAssets("Osmosis", "asset:osmo", "OSMO", "osmosis.png", BaseChain.OSMOSIS_MAIN.getChain());
    public static StarnameAssets regen  = new StarnameAssets("Regen Network", "asset:regen", "REGEN", "regen.png", BaseChain.REGEN_MAIN.getChain());
    public static StarnameAssets ren    = new StarnameAssets("Ren", "asset:ren", "REN", "ren.png", null);
    public static StarnameAssets req    = new StarnameAssets("Request", "asset:req", "REQ", "req.png", null);
    public static StarnameAssets rowan  = new StarnameAssets("Sifchain", "asset:rowan", "ROWAN", "sifchain.png", BaseChain.SIF_MAIN.getChain());
    public static StarnameAssets scrt   = new StarnameAssets("Secret", "asset:scrt", "SCRT", "secret.png", BaseChain.SECRET_MAIN.getChain());
    public static StarnameAssets shib   = new StarnameAssets("Shiba Inu", "asset:shib", "SHIB", "shib.png", null);
    public static StarnameAssets sol    = new StarnameAssets("Solana", "asset:sol", "SOL", "sol.png", null);
    public static StarnameAssets svt    = new StarnameAssets("Savitar Token", "asset:svt", "SVT", "svt.png", null);
    public static StarnameAssets trx    = new StarnameAssets("TRON", "asset:trx", "TRX", "trx.png", null);
    public static StarnameAssets usdc   = new StarnameAssets("USD Coin", "asset:usdc", "USDC", "usdc.png", null);
    public static StarnameAssets usdt   = new StarnameAssets("Tether", "asset:usdt", "USDT", "usdt.png", null);
    public static StarnameAssets wbtc   = new StarnameAssets("Wrapped Bitcoin", "asset:wbtc", "WBTC", null, null);
    public static StarnameAssets xlm    = new StarnameAssets("Stellar", "asset:xlm", "XLM", "xlm.png", null);
    public static StarnameAssets xmr    = new StarnameAssets("Monero", "asset:xmr", "XMR", "xmr.png", null);
    public static StarnameAssets xprt   = new StarnameAssets("Persistence", "asset:xprt", "XPRT", "persistence.png", BaseChain.PERSIS_MAIN.getChain());
    public static StarnameAssets xtz    = new StarnameAssets("Tezos", "asset:xtz", "XTZ", "tezos.png", null);
    public static StarnameAssets zec    = new StarnameAssets("Zcash", "asset:zec", "ZEC", "zec.png", null);

    public static StarnameAssets ter    = new StarnameAssets("Terra", "asset:luna", "LUNA", "terra.png", null);
    public static StarnameAssets emy    = new StarnameAssets("E-Money", "asset:ngm", "NGM", "emoney.png", BaseChain.EMONEY_MAIN.getChain());
    public static StarnameAssets lsk    = new StarnameAssets("LISK", "asset:lsk", "LSK", "lisk.png", null);
}
