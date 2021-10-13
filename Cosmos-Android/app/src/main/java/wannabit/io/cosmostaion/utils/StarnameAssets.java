package wannabit.io.cosmostaion.utils;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

import static wannabit.io.cosmostaion.base.BaseConstant.CHAIN_IMG_URL;

public class StarnameAssets {
    public String name;
    public String url;
    public String symbol;
    public String chainImg;
    public BaseChain baseChain;

    public StarnameAssets(String name, String url, String symbol, String chainImg, BaseChain baseChain) {
        this.name = name;
        this.url = url;
        this.symbol = symbol;
        this.baseChain = baseChain;

        if (chainImg == null) {
            this.chainImg = CHAIN_IMG_URL + "unknown.png";
        } else {
            this.chainImg = CHAIN_IMG_URL + chainImg;
        }
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
        result.add(dai);
        result.add(dash);
        result.add(doge);
        result.add(dsm);
        result.add(dvpn);
        result.add(eos);
        result.add(eth);
        result.add(ion);
        result.add(iov);
        result.add(iris);
        result.add(kava);
        result.add(ksm);
        result.add(link);
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

    public static StarnameAssets ada    = new StarnameAssets("Cardano", "asset:ada", "ADA", null, null);
    public static StarnameAssets akt    = new StarnameAssets("Akash", "asset:akt", "AKT", "akash.png", BaseChain.AKASH_MAIN);
    public static StarnameAssets algo   = new StarnameAssets("Algorand", "asset:algo", "ALGO", null, null);
    public static StarnameAssets atom   = new StarnameAssets("Cosmos", "asset:atom", "ATOM", "cosmos.png", BaseChain.COSMOS_MAIN);
    public static StarnameAssets avax   = new StarnameAssets("Avalanche", "asset:avax", "AVAX", null, null);
    public static StarnameAssets band   = new StarnameAssets("Band Protocol", "asset:band", "BAND", "band.png", BaseChain.BAND_MAIN);
    public static StarnameAssets bat    = new StarnameAssets("Basic Attention Token", "asset:bat", "BAT", null, null);
    public static StarnameAssets bch    = new StarnameAssets("Bitcoin Cash", "asset:bch", "BCH", "bitcoincash.png", null);
    public static StarnameAssets bnb    = new StarnameAssets("BNB coin", "asset:bnb", "BNB", "binance.png", BaseChain.BNB_MAIN);
    public static StarnameAssets btc    = new StarnameAssets("Bitcoin", "asset:btc", "BTC", "bitcoin.png", null);
    public static StarnameAssets comp   = new StarnameAssets("Compound", "asset:comp", "COMP", null, null);
    public static StarnameAssets cro    = new StarnameAssets("Crypto.org", "asset:cro", "CRO", "crypto-org.png", BaseChain.CRYPTO_MAIN);
    public static StarnameAssets dai    = new StarnameAssets("Dai Stablecoin", "asset:dai", "DAI", null, null);
    public static StarnameAssets dash   = new StarnameAssets("Dash", "asset:dash", "DASH", null, null);
    public static StarnameAssets doge   = new StarnameAssets("Dogecoin", "asset:doge", "DOGE", null, null);
    public static StarnameAssets dsm    = new StarnameAssets("Desmos", "asset:dsm", "DSM", null, null);
    public static StarnameAssets dvpn   = new StarnameAssets("Sentinel", "asset:dvpn", "DVPN", "sentinel.png", BaseChain.SENTINEL_MAIN);
    public static StarnameAssets eos    = new StarnameAssets("EOS", "asset:eos", "EOS", null, null);
    public static StarnameAssets eth    = new StarnameAssets("Ethereum", "asset:eth", "ETH", "ethereum.png", null);
    public static StarnameAssets ion    = new StarnameAssets("ION", "asset:ion", "ION", "osmosis.png", BaseChain.OSMOSIS_MAIN);
    public static StarnameAssets iov    = new StarnameAssets("Starname (IOV)", "asset:iov", "IOV", "starname.png", BaseChain.IOV_MAIN);
    public static StarnameAssets iris   = new StarnameAssets("IRISnet", "asset:iris", "IRIS", "iris.png", BaseChain.IRIS_MAIN);
    public static StarnameAssets kava   = new StarnameAssets("Kava", "asset:kava", "KAVA", "kava.png", BaseChain.KAVA_MAIN);
    public static StarnameAssets ksm    = new StarnameAssets("Kusama", "asset:ksm", "KSM", null, null);
    public static StarnameAssets link   = new StarnameAssets("Chainlink", "asset:link", "LINK", null, null);
    public static StarnameAssets ltc    = new StarnameAssets("Litecoin", "asset:ltc", "LTC", "litecoin.png", null);
    public static StarnameAssets neo    = new StarnameAssets("Neo", "asset:neo", "NEO", null, null);
    public static StarnameAssets okb    = new StarnameAssets("OKB", "asset:okb", "OKB", "okex.png", BaseChain.OKEX_MAIN);
    public static StarnameAssets osmo   = new StarnameAssets("Osmosis", "asset:osmo", "OSMO", "osmosis.png", BaseChain.OSMOSIS_MAIN);
    public static StarnameAssets regen  = new StarnameAssets("Regen Network", "asset:regen", "REGEN", "regen.png", BaseChain.REGEN_MAIN);
    public static StarnameAssets ren    = new StarnameAssets("Ren", "asset:ren", "REN", null, null);
    public static StarnameAssets req    = new StarnameAssets("Request", "asset:req", "REQ", null, null);
    public static StarnameAssets rowan  = new StarnameAssets("Sifchain", "asset:rowan", "ROWAN", "sifchain.png", BaseChain.SIF_MAIN);
    public static StarnameAssets scrt   = new StarnameAssets("Secret", "asset:scrt", "SCRT", null, BaseChain.SECRET_MAIN);
    public static StarnameAssets shib   = new StarnameAssets("Shiba Inu", "asset:shib", "SHIB", null, null);
    public static StarnameAssets sol    = new StarnameAssets("Solana", "asset:sol", "SOL", null, null);
    public static StarnameAssets svt    = new StarnameAssets("Savitar Token", "asset:svt", "SVT", null, null);
    public static StarnameAssets trx    = new StarnameAssets("TRON", "asset:trx", "TRX", null, null);
    public static StarnameAssets usdc   = new StarnameAssets("USD Coin", "asset:usdc", "USDC", null, null);
    public static StarnameAssets usdt   = new StarnameAssets("Tether", "asset:usdt", "USDT", null, null);
    public static StarnameAssets wbtc   = new StarnameAssets("Wrapped Bitcoin", "asset:wbtc", "WBTC", null, null);
    public static StarnameAssets xlm    = new StarnameAssets("Stellar", "asset:xlm", "XLM", null, null);
    public static StarnameAssets xmr    = new StarnameAssets("Monero", "asset:xmr", "XMR", null, null);
    public static StarnameAssets xprt   = new StarnameAssets("Persistence", "asset:xprt", "XPRT", "persistence.png", BaseChain.PERSIS_MAIN);
    public static StarnameAssets xtz    = new StarnameAssets("Tezos", "asset:xtz", "XTZ", null, null);
    public static StarnameAssets zec    = new StarnameAssets("Zcash", "asset:zec", "ZEC", null, null);
}
