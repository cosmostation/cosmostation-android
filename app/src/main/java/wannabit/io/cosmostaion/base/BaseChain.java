package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

import java.util.ArrayList;

public enum BaseChain {
    // chain_id is checked on-chain. no need update chain version  21.03.20
    COSMOS_MAIN("cosmoshub-mainnet"),
    IRIS_MAIN("irishub-mainnet"),
    IOV_MAIN("iov-mainnet"),
    BNB_MAIN("binance-mainnet"),
    KAVA_MAIN("kava-mainnet"),
    BAND_MAIN("band-mainnet"),
    CERTIK_MAIN("shentu-mainnet"),
    SECRET_MAIN("secret-mainnet"),
    AKASH_MAIN("akashnet-mainnet"),
    OKEX_MAIN("okexchain-mainnet"),
    PERSIS_MAIN("persistence-mainnet"),
    SENTINEL_MAIN("sentinel-mainnet"),
    FETCHAI_MAIN("fetchai-mainnet"),
    CRYPTO_MAIN("crytoorg-mainnet"),
    SIF_MAIN("sif-mainnet"),
    KI_MAIN("ki-mainnet"),
    OSMOSIS_MAIN("osmosis-mainnet"),
    MEDI_MAIN("medibloc-mainnet"),
    EMONEY_MAIN("emoney-mainnet"),
    RIZON_MAIN("rizon-mainnet"),
    JUNO_MAIN("juno-mainnet"),
    REGEN_MAIN("regen-mainnet"),
    BITCANNA_MAIN("bitcanna-mainnet"),
    ALTHEA_MAIN("althea-mainnet"),
    STARGAZE_MAIN("stargaze-mainnet"),
    GRABRIDGE_MAIN("GravityBridge-mainnet"),
    COMDEX_MAIN("comdex-mainnet"),
    INJ_MAIN("injective-mainnet"),
    BITSONG_MAIN("bitsong-mainnet"),
    DESMOS_MAIN("desmos-mainnet"),
    LUM_MAIN("lum-mainnet"),
    CHIHUAHUA_MAIN("chihuahua-mainnet"),
    AXELAR_MAIN("axelar-mainnet"),
    KONSTELL_MAIN("konstellation-mainnet"),
    UMEE_MAIN("umee-mainnet"),
    EVMOS_MAIN("evmos-mainnet"),

    COSMOS_TEST_LEGACY1("stargate-final"),
    COSMOS_TEST("cosmos-testnet"),
    IRIS_TEST_LEGACY1("bifrost-2"),
    IRIS_TEST("iris-testnet"),
    OK_TEST("okexchain-testnet"),
    RIZON_TEST("rizon-testnet2"),
    ALTHEA_TEST("althea-testnet");

    private final String chainName;

    BaseChain(final String chainname) {
        this.chainName = chainname;
    }

    public String getChain() {
        return chainName;
    }

    public static BaseChain getChain(String chainName) {
        if (chainName.equals(COSMOS_MAIN.chainName)) {
            return COSMOS_MAIN;
        }
        if (chainName.equals(IRIS_MAIN.chainName)) {
            return IRIS_MAIN;
        }
        if (chainName.equals(BNB_MAIN.chainName)) {
            return BNB_MAIN;
        }
        if (chainName.equals(IOV_MAIN.chainName)) {
            return IOV_MAIN;
        }
        if (chainName.equals(KAVA_MAIN.chainName)) {
            return KAVA_MAIN;
        }
        if (chainName.equals(BAND_MAIN.chainName)) {
            return BAND_MAIN;
        }
        if (chainName.equals(CERTIK_MAIN.chainName)) {
            return CERTIK_MAIN;
        }
        if (chainName.equals(SECRET_MAIN.chainName)) {
            return SECRET_MAIN;
        }
        if (chainName.equals(AKASH_MAIN.chainName)) {
            return AKASH_MAIN;
        }
        if (chainName.equals(OKEX_MAIN.chainName)) {
            return OKEX_MAIN;
        }
        if (chainName.equals(PERSIS_MAIN.chainName)) {
            return PERSIS_MAIN;
        }
        if (chainName.equals(SENTINEL_MAIN.chainName)) {
            return SENTINEL_MAIN;
        }
        if (chainName.equals(FETCHAI_MAIN.chainName)) {
            return FETCHAI_MAIN;
        }
        if (chainName.equals(CRYPTO_MAIN.chainName)) {
            return CRYPTO_MAIN;
        }
        if (chainName.equals(SIF_MAIN.chainName)) {
            return SIF_MAIN;
        }
        if (chainName.equals(KI_MAIN.chainName)) {
            return KI_MAIN;
        }
        if (chainName.equals(OSMOSIS_MAIN.chainName)) {
            return OSMOSIS_MAIN;
        }
        if (chainName.equals(MEDI_MAIN.chainName)) {
            return MEDI_MAIN;
        }
        if (chainName.equals(EMONEY_MAIN.chainName)) {
            return EMONEY_MAIN;
        }
        if (chainName.equals(RIZON_MAIN.chainName)) {
            return RIZON_MAIN;
        }
        if(chainName.equals(JUNO_MAIN.chainName)) {
            return JUNO_MAIN;
        }
        if(chainName.equals(REGEN_MAIN.chainName)) {
            return REGEN_MAIN;
        }
        if(chainName.equals(BITCANNA_MAIN.chainName)) {
            return BITCANNA_MAIN;
        }
        if(chainName.equals(ALTHEA_MAIN.chainName)) {
            return ALTHEA_MAIN;
        }
        if(chainName.equals(STARGAZE_MAIN.chainName)) {
            return STARGAZE_MAIN;
        }
        if(chainName.equals(GRABRIDGE_MAIN.chainName)) {
            return GRABRIDGE_MAIN;
        }
        if(chainName.equals(COMDEX_MAIN.chainName)) {
            return COMDEX_MAIN;
        }
        if(chainName.equals(INJ_MAIN.chainName)) {
            return INJ_MAIN;
        }
        if(chainName.equals(BITSONG_MAIN.chainName)) {
            return BITSONG_MAIN;
        }
        if(chainName.equals(DESMOS_MAIN.chainName)) {
            return DESMOS_MAIN;
        }
        if(chainName.equals(LUM_MAIN.chainName)) {
            return LUM_MAIN;
        }
        if(chainName.equals(CHIHUAHUA_MAIN.chainName)) {
            return CHIHUAHUA_MAIN;
        }
        if(chainName.equals(AXELAR_MAIN.chainName)) {
            return AXELAR_MAIN;
        }
        if(chainName.equals(KONSTELL_MAIN.chainName)) {
            return KONSTELL_MAIN;
        }
        if(chainName.equals(UMEE_MAIN.chainName)) {
            return UMEE_MAIN;
        }
        if(chainName.equals(EVMOS_MAIN.chainName)) {
            return EVMOS_MAIN;
        }

        if (chainName.equals(COSMOS_TEST_LEGACY1.chainName) ||
                chainName.equals(COSMOS_TEST.chainName)) {
            return COSMOS_TEST;
        }
        if (chainName.equals(IRIS_TEST_LEGACY1.chainName) ||
                chainName.equals(IRIS_TEST.chainName)) {
            return IRIS_TEST;
        }

        return null;
    }

    public static ArrayList<BaseChain> SUPPORT_CHAINS() {
        ArrayList<BaseChain> result = new ArrayList<>();
        result.add(COSMOS_MAIN);
        result.add(IRIS_MAIN);
        result.add(AKASH_MAIN);
        result.add(AXELAR_MAIN);
        result.add(BAND_MAIN);
        result.add(BNB_MAIN);
        result.add(BITCANNA_MAIN);
        result.add(BITSONG_MAIN);
        result.add(CERTIK_MAIN);
        result.add(CHIHUAHUA_MAIN);
        result.add(COMDEX_MAIN);
        result.add(CRYPTO_MAIN);
        result.add(DESMOS_MAIN);
        result.add(EMONEY_MAIN);
//        result.add(EVMOS_MAIN);
        result.add(FETCHAI_MAIN);
        result.add(GRABRIDGE_MAIN);
        result.add(INJ_MAIN);
        result.add(JUNO_MAIN);
        result.add(KAVA_MAIN);
        result.add(KI_MAIN);
        result.add(KONSTELL_MAIN);
        result.add(LUM_MAIN);
        result.add(MEDI_MAIN);
        result.add(OKEX_MAIN);
        result.add(OSMOSIS_MAIN);
        result.add(PERSIS_MAIN);
        result.add(REGEN_MAIN);
        result.add(RIZON_MAIN);
        result.add(SECRET_MAIN);
        result.add(SENTINEL_MAIN);
        result.add(SIF_MAIN);
        result.add(STARGAZE_MAIN);
        result.add(IOV_MAIN);
        result.add(UMEE_MAIN);

//        result.add(ALTHEA_MAIN);
        return result;
    }

    public static boolean IS_SUPPORT_CHAIN(String chain) {
        return SUPPORT_CHAINS().contains(getChain(chain));
    }

    public static boolean IS_TESTNET(BaseChain chain) {
        if (chain.equals(ALTHEA_TEST) || chain.equals(EVMOS_MAIN)) {
            return true;
        }
        return false;
    }

    public static ArrayList<BaseChain> getHtlcSendable(BaseChain fromChain) {
        ArrayList<BaseChain> result = new ArrayList<>();
        if (fromChain.equals(KAVA_MAIN)) {
            result.add(BNB_MAIN);

        } else if (fromChain.equals(BNB_MAIN)) {
            result.add(KAVA_MAIN);

        }
        return result;
    }

    public static ArrayList<String>  getHtlcSwappableCoin(BaseChain fromChain) {
        ArrayList<String> result = new ArrayList<>();
        if (fromChain.equals(BNB_MAIN)) {
            result.add(TOKEN_HTLC_BINANCE_BNB);
            result.add(TOKEN_HTLC_BINANCE_BTCB);
            result.add(TOKEN_HTLC_BINANCE_XRPB);
            result.add(TOKEN_HTLC_BINANCE_BUSD);

        } else if (fromChain.equals(KAVA_MAIN)) {
            result.add(TOKEN_HTLC_KAVA_BNB);
            result.add(TOKEN_HTLC_KAVA_BTCB);
            result.add(TOKEN_HTLC_KAVA_XRPB);
            result.add(TOKEN_HTLC_KAVA_BUSD);

        }
        return result;
    }

    public static boolean isGRPC(BaseChain baseChain) {
        if (baseChain.equals(BNB_MAIN) || baseChain.equals(OKEX_MAIN)) {
            return false;
        }
        return true;
    }
}
