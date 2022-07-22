package wannabit.io.cosmostaion.base.chains;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseChain;

public class ChainFactory {

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

                case BITCANNA_MAIN:
                    return new Bitcanna();

                case BITSONG_MAIN:
                    return new Bitsong();

                case CERBERUS_MAIN:
                    return new Cerberus();

                case CERTIK_LEGACY1:
                case CERTIK_LEGACY2:
                case CERTIK_MAIN:
                    return new Certik();

                case CHIHUAHUA_MAIN:
                    return new Chihuahua();

                case COMDEX_MAIN:
                    return new Comdex();

                case CRESCENT_MAIN:
                    return new Crescent();

                case CRYPTO_MAIN:
                    return new Crytoorg();

                case CUDOS_MAIN:
                    return new Cudos();

                case INJ_MAIN:
                    return new Injective();

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

                case OSMOSIS_MAIN:
                    return new Osmosis();
            }
        }
//        return null;
        return new Cosmos();
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
