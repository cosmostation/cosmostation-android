package wannabit.io.cosmostaion.base;

import java.util.ArrayList;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

public enum BaseChain {
    // chain_id is checked on-chain. no need update chain version  21.03.20
    COSMOS_LEGACY1("cosmoshub-1"),
    COSMOS_LEGACY2("cosmoshub-2"),
    COSMOS_LEGACY3("cosmoshub-3"),
    COSMOS_LEGACY4("cosmoshub-4"),
    COSMOS_MAIN("cosmoshub-mainnet"),
    IRIS_LEGACY1("irishub"),
    IRIS_LEGACY2("irishub-1"),
    IRIS_MAIN("irishub-mainnet"),
    IOV_LEGACY1("iov-mainnet-2"),
    IOV_MAIN("iov-mainnet"),
    BNB_LEGACY1("Binance-Chain-Tigris"),
    BNB_MAIN("binance-mainnet"),
    KAVA_LEGACY1("kava-1"),
    KAVA_LEGACY2("kava-2"),
    KAVA_LEGACY3("kava-3"),
    KAVA_LEGACY4("kava-4"),
    KAVA_LEGACY5("kava-5"),
    KAVA_LEGACY6("kava-6"),
    KAVA_MAIN("kava-mainnet"),
    BAND_MAIN_LEGACY1("band-wenchang-mainnet"),
    BAND_MAIN_LEGACY2("band-guanyu-mainnet"),
    BAND_MAIN("band-mainnet"),
    CERTIK_LEGACY1("shentu-1"),
    CERTIK_MAIN("certic-mainnet"),
    SECRET_LEGACY1("secret-2"),
    SECRET_MAIN("secret-mainnet"),
    AKASH_LEGACY1("akashnet-1"),
    AKASH_LEGACY2("akashnet-2"),
    AKASH_MAIN("akashnet-mainnet"),
    OKEX_LEGACY1("okexchain-66"),
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

    COSMOS_TEST_LEGACY1("stargate-final"),
    COSMOS_TEST("cosmos-testnet"),
    IRIS_TEST_LEGACY1("bifrost-2"),
    IRIS_TEST("iris-testnet"),
    BNB_TEST_LEGACY1("Binance-Chain-Nile"),
    BNB_TEST("binance-testnet"),
    KAVA_TEST_LEGACY4("kava-testnet-4000"),
    KAVA_TEST_LEGACY5("kava-testnet-5000"),
    KAVA_TEST_LEGACY6("kava-testnet-6000"),
    KAVA_TEST_LEGACY8("kava-testnet-8000"),
    KAVA_TEST_LEGACY9("kava-testnet-9000"),
    KAVA_TEST_LEGACY11("kava-testnet-11000"),
    KAVA_TEST_LEGACY12("kava-testnet-12000"),
    KAVA_TEST_3("kava-3-test"),
    KAVA_TEST_4("kava-4-test"),
    KAVA_TEST("kava-testnet"),
    IOV_TEST_LEGACY1("iovns-galaxynet"),
    IOV_TEST("iov-testnet"),
    OK_TEST_LEGACY("okchain"),
    OK_TEST_LEGACY1("okchain-testnet1"),
    OK_TEST_LEGACY2("okexchain-testnet1"),
    OK_TEST_LEGACY3("okexchain-65"),
    OK_TEST("okexchain-testnet"),
    CERTIK_TEST_LEGACY1("shentu-incentivized-3"),
    CERTIK_TEST("certik-testnet"),
    RIZON_TEST("rizon-testnet"),
    MEDI_TEST("medi-testnet"),
    ALTHEA_TEST("althea-testnet"),
    UMEE_TEST("umee-testnet"),
    AXELAR_TEST("axelar-testnet");

    private final String chainName;

    BaseChain(final String chainname) {
        this.chainName = chainname;
    }

    public String getChain() {
        return chainName;
    }

    public static BaseChain getChain(String chainName) {
        if (chainName.equals(COSMOS_LEGACY1.chainName) ||
                chainName.equals(COSMOS_LEGACY2.chainName) ||
                chainName.equals(COSMOS_LEGACY3.chainName) ||
                chainName.equals(COSMOS_LEGACY4.chainName) ||
                chainName.equals(COSMOS_MAIN.chainName)) {
            return COSMOS_MAIN;
        }
        if (chainName.equals(IRIS_LEGACY1.chainName) ||
                chainName.equals(IRIS_LEGACY2.chainName) ||
                chainName.equals(IRIS_MAIN.chainName)) {
            return IRIS_MAIN;
        }
        if (chainName.equals(BNB_LEGACY1.chainName) ||
                chainName.equals(BNB_MAIN.chainName)) {
            return BNB_MAIN;
        }
        if (chainName.equals(IOV_LEGACY1.chainName) ||
                chainName.equals(IOV_MAIN.chainName)) {
            return IOV_MAIN;
        }
        if (chainName.equals(KAVA_LEGACY1.chainName) ||
                chainName.equals(KAVA_LEGACY2.chainName) ||
                chainName.equals(KAVA_LEGACY3.chainName) ||
                chainName.equals(KAVA_LEGACY4.chainName) ||
                chainName.equals(KAVA_LEGACY5.chainName) ||
                chainName.equals(KAVA_LEGACY6.chainName) ||
                chainName.equals(KAVA_MAIN.chainName)) {
            return KAVA_MAIN;
        }
        if (chainName.equals(BAND_MAIN_LEGACY1.chainName) ||
                chainName.equals(BAND_MAIN_LEGACY2.chainName) ||
                chainName.equals(BAND_MAIN.chainName)) {
            return BAND_MAIN;
        }
        if (chainName.equals(CERTIK_LEGACY1.chainName) ||
                chainName.equals(CERTIK_MAIN.chainName)) {
            return CERTIK_MAIN;
        }
        if (chainName.equals(SECRET_LEGACY1.chainName) ||
                chainName.equals(SECRET_MAIN.chainName)) {
            return SECRET_MAIN;
        }
        if (chainName.equals(AKASH_LEGACY1.chainName) ||
                chainName.equals(AKASH_LEGACY2.chainName) ||
                chainName.equals(AKASH_MAIN.chainName)) {
            return AKASH_MAIN;
        }
        if (chainName.equals(OKEX_LEGACY1.chainName) ||
                chainName.equals(OKEX_MAIN.chainName)) {
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


        if (chainName.equals(COSMOS_TEST_LEGACY1.chainName) ||
                chainName.equals(COSMOS_TEST.chainName)) {
            return COSMOS_TEST;
        }
        if (chainName.equals(IRIS_TEST_LEGACY1.chainName) ||
                chainName.equals(IRIS_TEST.chainName)) {
            return IRIS_TEST;
        }
        if (chainName.equals(BNB_TEST_LEGACY1.chainName) ||
                chainName.equals(BNB_TEST.chainName)) {
            return BNB_TEST;
        }
        if (chainName.equals(KAVA_TEST_LEGACY4.chainName) ||
                chainName.equals(KAVA_TEST_LEGACY5.chainName) ||
                chainName.equals(KAVA_TEST_LEGACY6.chainName) ||
                chainName.equals(KAVA_TEST_LEGACY8.chainName) ||
                chainName.equals(KAVA_TEST_LEGACY9.chainName) ||
                chainName.equals(KAVA_TEST_LEGACY11.chainName) ||
                chainName.equals(KAVA_TEST_LEGACY12.chainName) ||
                chainName.equals(KAVA_TEST_3.chainName) ||
                chainName.equals(KAVA_TEST_4.chainName) ||
                chainName.equals(KAVA_TEST.chainName)) {
            return KAVA_TEST;
        }
        if (chainName.equals(IOV_TEST_LEGACY1.chainName) ||
                chainName.equals(IOV_TEST.chainName)) {
            return IOV_TEST;
        }
        if (chainName.equals(OK_TEST_LEGACY.chainName) ||
                chainName.equals(OK_TEST_LEGACY1.chainName) ||
                chainName.equals(OK_TEST_LEGACY2.chainName) ||
                chainName.equals(OK_TEST_LEGACY3.chainName) ||
                chainName.equals(OK_TEST.chainName)) {
            return OK_TEST;
        }
        if (chainName.equals(CERTIK_TEST_LEGACY1.chainName) ||
                chainName.equals(CERTIK_TEST.chainName)) {
            return CERTIK_TEST;
        }
        if (chainName.equals(RIZON_TEST.chainName)) {
            return RIZON_TEST;
        }
        if (chainName.equals(MEDI_TEST.chainName)) {
            return MEDI_TEST;
        }
        if (chainName.equals(ALTHEA_TEST.chainName)) {
            return ALTHEA_TEST;
        }
        if (chainName.equals(UMEE_TEST.chainName)) {
            return UMEE_TEST;
        }
        if (chainName.equals(AXELAR_TEST.chainName)) {
            return AXELAR_TEST;
        }

        return null;
    }

    public static ArrayList<BaseChain> SUPPORT_CHAINS() {
        ArrayList<BaseChain> result = new ArrayList<>();
        result.add(COSMOS_MAIN);
        result.add(IRIS_MAIN);
        result.add(BNB_MAIN);
        result.add(OKEX_MAIN);
        result.add(KAVA_MAIN);
        result.add(BAND_MAIN);
        result.add(PERSIS_MAIN);
        result.add(IOV_MAIN);
        result.add(CERTIK_MAIN);
        result.add(AKASH_MAIN);
        result.add(SENTINEL_MAIN);
        result.add(FETCHAI_MAIN);
        result.add(CRYPTO_MAIN);
        result.add(SIF_MAIN);
        result.add(KI_MAIN);
        result.add(OSMOSIS_MAIN);
        result.add(MEDI_MAIN);
        result.add(EMONEY_MAIN);
        result.add(SECRET_MAIN);


//        result.add(COSMOS_TEST);
//        result.add(IRIS_TEST);
//        result.add(BNB_TEST);
//        result.add(KAVA_TEST);
//        result.add(IOV_TEST);
//        result.add(OK_TEST);
//        result.add(CERTIK_TEST);
//        result.add(RIZON_TEST);
//        result.add(MEDI_TEST);
//        result.add(ALTHEA_TEST);
        result.add(UMEE_TEST);
        result.add(AXELAR_TEST);
        return result;
    }

    public static boolean IS_SUPPORT_CHAIN(String chain) {
        return SUPPORT_CHAINS().contains(getChain(chain));
    }

    public static ArrayList<BaseChain> getHtlcSendable(BaseChain fromChain) {
        ArrayList<BaseChain> result = new ArrayList<>();
        if (fromChain.equals(BNB_TEST)) {
            result.add(KAVA_TEST);

        } else if (fromChain.equals(KAVA_TEST)) {
            result.add(BNB_TEST);

        } else if (fromChain.equals(KAVA_MAIN)) {
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

        } else if (fromChain.equals(BNB_TEST)) {
            result.add(TOKEN_HTLC_BINANCE_TEST_BNB);
            result.add(TOKEN_HTLC_BINANCE_TEST_BTC);

        } else if (fromChain.equals(KAVA_TEST)) {
            result.add(TOKEN_HTLC_KAVA_TEST_BNB);
            result.add(TOKEN_HTLC_KAVA_TEST_BTC);
        }
        return result;
    }

    public static boolean isGRPC(BaseChain baseChain) {
        if (baseChain.equals(COSMOS_MAIN)) {
            return true;
        } else if (baseChain.equals(IRIS_MAIN)) {
            return true;
        } else if (baseChain.equals(AKASH_MAIN)) {
            return true;
        } else if (baseChain.equals(SENTINEL_MAIN)) {
            return true;
        } else if (baseChain.equals(PERSIS_MAIN)) {
            return true;
        } else if (baseChain.equals(CRYPTO_MAIN)) {
            return true;
        } else if (baseChain.equals(OSMOSIS_MAIN)) {
            return true;
        } else if (baseChain.equals(IOV_MAIN)) {
            return true;
        } else if (baseChain.equals(SIF_MAIN)) {
            return true;
        } else if (baseChain.equals(MEDI_MAIN)) {
            return true;
        } else if (baseChain.equals(CERTIK_MAIN)) {
            return true;
        } else if (baseChain.equals(EMONEY_MAIN)) {
            return true;
        } else if (baseChain.equals(FETCHAI_MAIN)) {
            return true;
        }
//        } else if (baseChain.equals(BAND_MAIN)) {
//            return true;
          else if (baseChain.equals(RIZON_TEST)) {
            return true;
        } else if (baseChain.equals(ALTHEA_TEST)) {
            return true;
        } else if (baseChain.equals(UMEE_TEST)) {
            return true;
        } else if (baseChain.equals(AXELAR_TEST)) {
            return true;
        }

        else if (baseChain.equals(COSMOS_TEST)) {
            return true;
        } else if (baseChain.equals(IRIS_TEST)) {
            return true;
        }
        return false;
    }
}
