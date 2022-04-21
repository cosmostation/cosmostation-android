package wannabit.io.cosmostaion.chain;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ALTHEA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AXELAR;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BITCANNA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BITSONG;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CHIHUAHUA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COMDEX;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CRBRUS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CRE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CRO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CUDOS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DARC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DESMOS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_EVMOS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_FET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_FLIX;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_GRABRIDGE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_INJ;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_JUNO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_LUM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MANTLE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MEDI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_NGM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_REGEN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_RIZON;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_STARGAZE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_UMEE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_XPRT;

import wannabit.io.cosmostaion.base.BaseChain;

public class ChainFactory {

    public static Chain getChain(BaseChain baseChain) {
        if (baseChain != null) {
            switch (baseChain) {
                case COSMOS_MAIN:

                case COSMOS_TEST:
                    return new Cosmos();
                case IRIS_MAIN:

                case IRIS_TEST:
                    return new Iris();

                case AKASH_MAIN:
                    return new Akash();

                case ALTHEA_MAIN:

                case ALTHEA_TEST:
                    return new Althea();

                case ASSETMANTLE_MAIN:
                    return new AssetMantle();

                case AXELAR_MAIN:
                    return new Axelar();

                case BAND_MAIN:
                    return new Band();

                case BNB_MAIN:
                    return new Bnb();

                case BITCANNA_MAIN:
                    return new Bitcanna();

                case BITSONG_MAIN:
                    return new Bitsong();

                case CERBERUS_MAIN:
                    return new Cerberus();

                case CERTIK_MAIN:
                    return new Certik();

                case CHIHUAHUA_MAIN:
                    return new Chihuahua();

                case COMDEX_MAIN:
                    return new Comdex();

                case CRYPTO_MAIN:
                    return new Crypto();

                case CRESCENT_MAIN:

                case CRESCENT_TEST:
                    return new Crescent();

                case CUDOS_MAIN:
                    return new Cudos();

                case DESMOS_MAIN:
                    return new Desmos();

                case EMONEY_MAIN:
                    return new Emoney();

                case EVMOS_MAIN:
                    return new Evmos();

                case FETCHAI_MAIN:
                    return new Fetchai();

                case GRABRIDGE_MAIN:
                    return new GravityBridge();

                case INJ_MAIN:
                    return new Injective();

                case JUNO_MAIN:
                    return new Juno();

                case KAVA_MAIN:
                    return new Kava();

                case KI_MAIN:
                    return new Ki();

                case KONSTELL_MAIN:
                    return new Konstellation();

                case LUM_MAIN:
                    return new Lum();

                case MEDI_MAIN:
                    return new Medibloc();

                case OKEX_MAIN:
                    return new Oec();

                case OMNIFLIX_MAIN:
                    return new Omniflix();

                case OSMOSIS_MAIN:
                    return new Osmosis();

                case PERSIS_MAIN:
                    return new Persistence();

                case PROVENANCE_MAIN:
                    return new Provenance();

                case REGEN_MAIN:
                    return new Regen();

                case RIZON_MAIN:
                    return new Rizon();

                case SECRET_MAIN:
                    return new Secret();

                case SENTINEL_MAIN:
                    return new Sentinel();

                case SIF_MAIN:
                    return new Sif();

                case STARGAZE_MAIN:
                    return new Stargaze();

                case IOV_MAIN:
                    return new Starname();

                case UMEE_MAIN:
                    return new Umee();
            }

        }
        return new Cosmos();
    }

    public static Chain getChain(String chainInfo) {
        if (chainInfo.startsWith("cosmoshub-") || chainInfo.startsWith("cosmos1") || chainInfo.equalsIgnoreCase(TOKEN_ATOM)) {
            return new Cosmos();
        } else if (chainInfo.startsWith("irishub-") || chainInfo.startsWith("iaa1") || chainInfo.equalsIgnoreCase(TOKEN_IRIS)) {
            return new Iris();
        } else if (chainInfo.startsWith("akashnet-") || chainInfo.startsWith("akash1") || chainInfo.equalsIgnoreCase(TOKEN_AKASH)) {
            return new Akash();
        } else if (chainInfo.startsWith("althea-") || chainInfo.startsWith("althea1") || chainInfo.equalsIgnoreCase(TOKEN_ALTHEA)) {
            return new Althea();
        } else if (chainInfo.startsWith("mantle-") || chainInfo.startsWith("mantle1") || chainInfo.equalsIgnoreCase(TOKEN_MANTLE)) {
            return new AssetMantle();
        } else if (chainInfo.startsWith("axelar-") || chainInfo.startsWith("axelar1") || chainInfo.equalsIgnoreCase(TOKEN_AXELAR)) {
            return new Axelar();
        } else if (chainInfo.startsWith("laozi-mainnet") || chainInfo.startsWith("band1") || chainInfo.equalsIgnoreCase(TOKEN_BAND)) {
            return new Band();
        } else if (chainInfo.startsWith("bnb1") || chainInfo.equalsIgnoreCase(TOKEN_BNB)) {
            return new Bnb();
        } else if (chainInfo.startsWith("bitcanna-") || chainInfo.startsWith("bcna1") || chainInfo.equalsIgnoreCase(TOKEN_BITCANNA)) {
            return new Bitcanna();
        } else if (chainInfo.startsWith("bitsong-") || chainInfo.startsWith("bitsong1") || chainInfo.equalsIgnoreCase(TOKEN_BITSONG)) {
            return new Bitsong();
        } else if (chainInfo.startsWith("cerberus-") || chainInfo.startsWith("cerberus1") || chainInfo.equalsIgnoreCase(TOKEN_CRBRUS)) {
            return new Cerberus();
        } else if (chainInfo.startsWith("shentu-") || chainInfo.startsWith("certik1") || chainInfo.equalsIgnoreCase(TOKEN_CERTIK)) {
            return new Certik();
        } else if (chainInfo.startsWith("chihuahua-") || chainInfo.startsWith("chihuahua1") || chainInfo.equalsIgnoreCase(TOKEN_CHIHUAHUA)) {
            return new Chihuahua();
        } else if (chainInfo.startsWith("comdex-") || chainInfo.startsWith("comdex1") || chainInfo.equalsIgnoreCase(TOKEN_COMDEX)) {
            return new Comdex();
        } else if (chainInfo.startsWith("crypto-org-") || chainInfo.startsWith("cro1") || chainInfo.equalsIgnoreCase(TOKEN_CRO)) {
            return new Crypto();
        } else if (chainInfo.startsWith("crescent-") || chainInfo.startsWith("cre1") || chainInfo.equalsIgnoreCase(TOKEN_CRE)) {
            return new Crescent();
        } else if (chainInfo.startsWith("cudos-") || chainInfo.startsWith("cudos1") || chainInfo.equalsIgnoreCase(TOKEN_CUDOS)) {
            return new Cudos();
        } else if (chainInfo.startsWith("desmos-") || chainInfo.startsWith("desmos1") || chainInfo.equalsIgnoreCase(TOKEN_DESMOS)) {
            return new Desmos();
        } else if (chainInfo.startsWith("emoney-") || chainInfo.startsWith("emoney1") || chainInfo.equalsIgnoreCase(TOKEN_NGM)) {
            return new Emoney();
        } else if (chainInfo.startsWith("evmos") || chainInfo.startsWith("evmos1") || chainInfo.equalsIgnoreCase(TOKEN_EVMOS)) {
            return new Evmos();
        } else if (chainInfo.startsWith("fetchhub-") || chainInfo.startsWith("fetch1") || chainInfo.equalsIgnoreCase(TOKEN_FET)) {
            return new Fetchai();
        } else if (chainInfo.startsWith("gravity-bridge-") || chainInfo.startsWith("gravity1") || chainInfo.equalsIgnoreCase(TOKEN_GRABRIDGE)) {
            return new GravityBridge();
        } else if (chainInfo.startsWith("injective-") || chainInfo.startsWith("inj1") || chainInfo.equalsIgnoreCase(TOKEN_INJ)) {
            return new Injective();
        } else if (chainInfo.startsWith("juno-") || chainInfo.startsWith("juno1") || chainInfo.equalsIgnoreCase(TOKEN_JUNO)) {
            return new Juno();
        } else if (chainInfo.startsWith("kava-") || chainInfo.startsWith("kava1") || chainInfo.equalsIgnoreCase(TOKEN_KAVA)) {
            return new Kava();
        } else if (chainInfo.startsWith("kichain-") || chainInfo.startsWith("ki1") || chainInfo.equalsIgnoreCase(TOKEN_KI)) {
            return new Ki();
        } else if (chainInfo.startsWith("darchub") || chainInfo.startsWith("darc1") || chainInfo.equalsIgnoreCase(TOKEN_DARC)) {
            return new Konstellation();
        } else if (chainInfo.startsWith("lum-network-") || chainInfo.startsWith("lum1") || chainInfo.equalsIgnoreCase(TOKEN_LUM)) {
            return new Lum();
        } else if (chainInfo.startsWith("panacea-") || chainInfo.startsWith("panacea1") || chainInfo.equalsIgnoreCase(TOKEN_MEDI)) {
            return new Medibloc();
        } else if (chainInfo.startsWith("omniflixhub-") || chainInfo.startsWith("omniflix1") || chainInfo.equalsIgnoreCase(TOKEN_FLIX)) {
            return new Omniflix();
        } else if (chainInfo.startsWith("osmosis-") || chainInfo.startsWith("osmo1") || chainInfo.equalsIgnoreCase(TOKEN_OSMOSIS)) {
            return new Osmosis();
        } else if (chainInfo.startsWith("core-") || chainInfo.startsWith("persistence1") || chainInfo.equalsIgnoreCase(TOKEN_XPRT)) {
            return new Persistence();
        } else if (chainInfo.startsWith("pio-mainnet-") || chainInfo.startsWith("pb1") || chainInfo.equalsIgnoreCase(TOKEN_HASH)) {
            return new Provenance();
        } else if (chainInfo.startsWith("regen-") || chainInfo.startsWith("regen1") || chainInfo.equalsIgnoreCase(TOKEN_REGEN)) {
            return new Regen();
        } else if (chainInfo.startsWith("titan-") || chainInfo.startsWith("rizon1") || chainInfo.equalsIgnoreCase(TOKEN_RIZON)) {
            return new Rizon();
        } else if (chainInfo.startsWith("secret-") || chainInfo.startsWith("secret1") || chainInfo.equalsIgnoreCase(TOKEN_SECRET)) {
            return new Secret();
        } else if (chainInfo.startsWith("sifchain-") || chainInfo.startsWith("sif1") || chainInfo.equalsIgnoreCase(TOKEN_SIF)) {
            return new Sif();
        } else if (chainInfo.startsWith("sentinelhub-") || chainInfo.startsWith("sent1") || chainInfo.equalsIgnoreCase(TOKEN_DVPN)) {
            return new Sentinel();
        } else if (chainInfo.startsWith("stargaze-") || chainInfo.startsWith("stars1") || chainInfo.equalsIgnoreCase(TOKEN_STARGAZE)) {
            return new Stargaze();
        } else if (chainInfo.startsWith("iov-") || chainInfo.startsWith("star1") || chainInfo.equalsIgnoreCase(TOKEN_IOV)) {
            return new Starname();
        } else if (chainInfo.startsWith("umee-") || chainInfo.startsWith("umee1") || chainInfo.equalsIgnoreCase(TOKEN_UMEE)) {
            return new Umee();
        }
        return new Cosmos();
    }
}
