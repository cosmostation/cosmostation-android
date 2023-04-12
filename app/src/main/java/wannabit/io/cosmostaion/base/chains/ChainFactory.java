package wannabit.io.cosmostaion.base.chains;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

public class ChainFactory {

    public static ChainConfig getChain(String baseChainName) {
        return getChain(BaseChain.getChain(baseChainName));
    }

    public static ChainConfig getChain(BaseChain baseChain) {
        if (baseChain != null) {
            switch (baseChain) {
                case COSMOS_LEGACY1:
                case COSMOS_LEGACY2:
                case COSMOS_LEGACY3:
                case COSMOS_LEGACY4:
                case COSMOS_MAIN:
                    return new Cosmos();

                case IRIS_LEGACY1:
                case IRIS_LEGACY2:
                case IRIS_MAIN:
                    return new Iris();

                case AKASH_LEGACY1:
                case AKASH_LEGACY2:
                case AKASH_MAIN:
                    return new Akash();

                case ASSETMANTLE_MAIN:
                    return new Assetmantle();

                case AXELAR_MAIN:
                    return new Axelar();

                case BAND_MAIN_LEGACY1:
                case BAND_MAIN_LEGACY2:
                case BAND_MAIN:
                    return new Band();

                case BNB_LEGACY1:
                case BNB_MAIN:
                    return new Binance();

                case BITCANNA_MAIN:
                    return new Bitcanna();

                case BITSONG_MAIN:
                    return new Bitsong();

                case CANTO_MAIN:
                    return new Canto();

                case CERBERUS_MAIN:
                    return new Cerberus();

                case CERTIK_LEGACY1:
                case CERTIK_LEGACY2:
                case CERTIK_MAIN:
                    return new Shentu();

                case CHIHUAHUA_MAIN:
                    return new Chihuahua();

                case COMDEX_MAIN:
                    return new Comdex();

                case COREUM_MAIN:
                    return new Coreum();

                case CRESCENT_MAIN:
                    return new Crescent();

                case CRYPTO_MAIN:
                    return new Crytoorg();

                case CUDOS_MAIN:
                    return new Cudos();

                case DESMOS_MAIN:
                    return new Desmos();

                case EMONEY_MAIN:
                    return new Emoney();

                case EVMOS_MAIN:
                    return new Evmos();

                case FETCHAI_MAIN:
                    return new FetchAi();

                case GRABRIDGE_MAIN:
                    return new GravityBridge();

                case INJ_MAIN:
                    return new Injective();

                case IXO_MAIN:
                    return new Ixo();

                case JUNO_MAIN:
                    return new Juno();

                case KAVA_LEGACY1:
                case KAVA_LEGACY2:
                case KAVA_LEGACY3:
                case KAVA_LEGACY4:
                case KAVA_LEGACY5:
                case KAVA_LEGACY6:
                case KAVA_MAIN:
                    return new Kava();

                case KI_MAIN:
                    return new Ki();

                case KONSTELL_MAIN:
                    return new Konstellation();

                case KUJIRA_MAIN:
                    return new Kujira();

                case KYVE_MAIN:
                    return new Kyve();

                case LIKECOIN_MAIN:
                    return new LikeCoin();

                case LUM_MAIN:
                    return new Lum();

                case MARS_MAIN:
                    return new Mars();

                case MEDI_MAIN:
                    return new Medibloc();

                case NOBLE_MAIN:
                    return new Noble();

                case NYX_MAIN:
                    return new Nyx();

                case OKEX_LEGACY1:
                case OKEX_MAIN:
                    return new Okc();

                case OMNIFLIX_MAIN:
                    return new Omniflix();

                case ONOMY_MAIN:
                    return new Onomy();

                case OSMOSIS_MAIN:
                    return new Osmosis();

                case PASSAGE_MAIN:
                    return new Passage();

                case PERSIS_MAIN:
                    return new Persistence();

                case PROVENANCE_MAIN:
                    return new Provenance();

                case QUASAR_MAIN:
                    return new Quasar();

                case QUICKSILVER_MAIN:
                    return new Quicksilver();

                case REGEN_MAIN:
                    return new Regen();

                case RIZON_MAIN:
                    return new Rizon();

                case SECRET_LEGACY1:
                case SECRET_MAIN:
                    return new Secret();

                case SENTINEL_MAIN:
                    return new Sentinel();

                case SIF_MAIN:
                    return new Sif();

                case SOMMELIER_MAIN:
                    return new Sommelier();

                case STAFI_MAIN:
                    return new Stafi();

                case STARGAZE_MAIN:
                    return new Stargaze();

                case STRIDE_MAIN:
                    return new Stride();

                case IOV_LEGACY1:
                case IOV_MAIN:
                    return new Starname();

                case TERITORI_MAIN:
                    return new Teritori();

                case UMEE_MAIN:
                    return new Umee();

                case XPLA_MAIN:
                    return new Xpla();

                case STATION_TEST:
                    return new StationTest();

                case NEUTRON_TEST:
                    return new NeutronTest();
            }
        }
        return null;
    }

    public static ArrayList<ChainConfig> SUPPRT_CONFIG() {
        ArrayList<ChainConfig> result = new ArrayList<>();
        for (BaseChain baseChain : BaseChain.SUPPORT_CHAINS()) {
            ChainConfig chainConfig = getChain(baseChain);
            if (chainConfig != null) {
                result.add(chainConfig);
            }
        }
        return result;
    }
}
