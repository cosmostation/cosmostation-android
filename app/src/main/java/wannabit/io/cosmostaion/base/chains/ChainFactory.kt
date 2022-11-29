package wannabit.io.cosmostaion.base.chains

import wannabit.io.cosmostaion.base.BaseChain

object ChainFactory {
    @JvmStatic
    fun getChain(chainName: String): ChainConfig? {
        if (chainName == BaseChain.COSMOS_LEGACY1.chainName || chainName == BaseChain.COSMOS_LEGACY2.chainName || chainName == BaseChain.COSMOS_LEGACY3.chainName || chainName == BaseChain.COSMOS_LEGACY4.chainName || chainName == BaseChain.COSMOS_MAIN.chainName) {
            return Cosmos()
        }
        if (chainName == BaseChain.IRIS_LEGACY1.chainName || chainName == BaseChain.IRIS_LEGACY2.chainName || chainName == BaseChain.IRIS_MAIN.chainName) {
            return Iris()
        }
        if (chainName == BaseChain.BNB_LEGACY1.chainName || chainName == BaseChain.BNB_MAIN.chainName) {
            return Binance()
        }
        if (chainName == BaseChain.IOV_LEGACY1.chainName || chainName == BaseChain.IOV_MAIN.chainName) {
            return Starname()
        }
        if (chainName == BaseChain.KAVA_LEGACY1.chainName || chainName == BaseChain.KAVA_LEGACY2.chainName || chainName == BaseChain.KAVA_LEGACY3.chainName || chainName == BaseChain.KAVA_LEGACY4.chainName || chainName == BaseChain.KAVA_LEGACY5.chainName || chainName == BaseChain.KAVA_LEGACY6.chainName || chainName == BaseChain.KAVA_MAIN.chainName) {
            return Kava()
        }
        if (chainName == BaseChain.BAND_MAIN_LEGACY1.chainName || chainName == BaseChain.BAND_MAIN_LEGACY2.chainName || chainName == BaseChain.BAND_MAIN.chainName) {
            return Band()
        }
        if (chainName == BaseChain.CERTIK_LEGACY1.chainName || chainName == BaseChain.CERTIK_MAIN.chainName) {
            return Shentu()
        }
        if (chainName == BaseChain.SECRET_LEGACY1.chainName || chainName == BaseChain.SECRET_MAIN.chainName) {
            return Secret()
        }
        if (chainName == BaseChain.AKASH_LEGACY1.chainName || chainName == BaseChain.AKASH_LEGACY2.chainName || chainName == BaseChain.AKASH_MAIN.chainName) {
            return Akash()
        }
        if (chainName == BaseChain.OKEX_LEGACY1.chainName || chainName == BaseChain.OKEX_MAIN.chainName) {
            return Okc()
        }
        if (chainName == BaseChain.PERSIS_MAIN.chainName) {
            return Persistence()
        }
        if (chainName == BaseChain.SENTINEL_MAIN.chainName) {
            return Sentinel()
        }
        if (chainName == BaseChain.FETCHAI_MAIN.chainName) {
            return FetchAi()
        }
        if (chainName == BaseChain.CRYPTO_MAIN.chainName) {
            return Crytoorg()
        }
        if (chainName == BaseChain.SIF_MAIN.chainName) {
            return Sif()
        }
        if (chainName == BaseChain.KI_MAIN.chainName) {
            return Ki()
        }
        if (chainName == BaseChain.OSMOSIS_MAIN.chainName) {
            return Osmosis()
        }
        if (chainName == BaseChain.MEDI_MAIN.chainName) {
            return Medibloc()
        }
        if (chainName == BaseChain.EMONEY_MAIN.chainName) {
            return Emoney()
        }
        if (chainName == BaseChain.RIZON_MAIN.chainName) {
            return Rizon()
        }
        if (chainName == BaseChain.JUNO_MAIN.chainName) {
            return Juno()
        }
        if (chainName == BaseChain.REGEN_MAIN.chainName) {
            return Regen()
        }
        if (chainName == BaseChain.BITCANNA_MAIN.chainName) {
            return Bitcanna()
        }
        if (chainName == BaseChain.STARGAZE_MAIN.chainName) {
            return Stargaze()
        }
        if (chainName == BaseChain.GRABRIDGE_MAIN.chainName) {
            return GravityBridge()
        }
        if (chainName == BaseChain.COMDEX_MAIN.chainName) {
            return Comdex()
        }
        if (chainName == BaseChain.INJ_MAIN.chainName) {
            return Injective()
        }
        if (chainName == BaseChain.BITSONG_MAIN.chainName) {
            return Bitsong()
        }
        if (chainName == BaseChain.DESMOS_MAIN.chainName) {
            return Desmos()
        }
        if (chainName == BaseChain.LUM_MAIN.chainName) {
            return Lum()
        }
        if (chainName == BaseChain.CHIHUAHUA_MAIN.chainName) {
            return Chihuahua()
        }
        if (chainName == BaseChain.AXELAR_MAIN.chainName) {
            return Axelar()
        }
        if (chainName == BaseChain.KONSTELL_MAIN.chainName) {
            return Konstellation()
        }
        if (chainName == BaseChain.UMEE_MAIN.chainName) {
            return Umee()
        }
        if (chainName == BaseChain.EVMOS_MAIN.chainName) {
            return Evmos()
        }
        if (chainName == BaseChain.CUDOS_MAIN.chainName) {
            return Cudos()
        }
        if (chainName == BaseChain.PROVENANCE_MAIN.chainName) {
            return Provenance()
        }
        if (chainName == BaseChain.CERBERUS_MAIN.chainName) {
            return Cerberus()
        }
        if (chainName == BaseChain.OMNIFLIX_MAIN.chainName) {
            return Omniflix()
        }
        if (chainName == BaseChain.CRESCENT_MAIN.chainName) {
            return Crescent()
        }
        if (chainName == BaseChain.ASSETMANTLE_MAIN.chainName) {
            return Assetmantle()
        }
        if (chainName == BaseChain.STATION_TEST.chainName) {
            return StationTest()
        }
        if (chainName == BaseChain.NYX_MAIN.chainName) {
            return Nyx()
        }
        if (chainName == BaseChain.PASSAGE_MAIN.chainName) {
            return Passage()
        }
        if (chainName == BaseChain.STRIDE_MAIN.chainName) {
            return Stride()
        }
        if (chainName == BaseChain.IXO_MAIN.chainName) {
            return Ixo()
        }
        if (chainName == BaseChain.SOMMELIER_MAIN.chainName) {
            return Sommelier()
        }
        if (chainName == BaseChain.LIKECOIN_MAIN.chainName) {
            return LikeCoin()
        }
        if (chainName == BaseChain.KUJIRA_MAIN.chainName) {
            return Kujira()
        }
        if (chainName == BaseChain.TERITORI_MAIN.chainName) {
            return Teritori()
        }
        if (chainName == BaseChain.XPLA_MAIN.chainName) {
            return Xpla()
        }
        if (chainName.startsWith("custom")) {
            for (info in TempCustomChainManager.instance.infos) {
                if (chainName.contains(info.chainId)) {
                    return CustomChain(info)
                }
            }
        }
        return null
    }

    @JvmStatic
    fun getChain(baseChain: BaseChain?): ChainConfig? {
        if (baseChain != null) {
            when (baseChain) {
                BaseChain.COSMOS_LEGACY1, BaseChain.COSMOS_LEGACY2, BaseChain.COSMOS_LEGACY3, BaseChain.COSMOS_LEGACY4, BaseChain.COSMOS_MAIN -> return Cosmos()
                BaseChain.IRIS_LEGACY1, BaseChain.IRIS_LEGACY2, BaseChain.IRIS_MAIN -> return Iris()
                BaseChain.AKASH_LEGACY1, BaseChain.AKASH_LEGACY2, BaseChain.AKASH_MAIN -> return Akash()
                BaseChain.ASSETMANTLE_MAIN -> return Assetmantle()
                BaseChain.AXELAR_MAIN -> return Axelar()
                BaseChain.BAND_MAIN_LEGACY1, BaseChain.BAND_MAIN_LEGACY2, BaseChain.BAND_MAIN -> return Band()
                BaseChain.BNB_LEGACY1, BaseChain.BNB_MAIN -> return Binance()
                BaseChain.BITCANNA_MAIN -> return Bitcanna()
                BaseChain.BITSONG_MAIN -> return Bitsong()
                BaseChain.CERBERUS_MAIN -> return Cerberus()
                BaseChain.CERTIK_LEGACY1, BaseChain.CERTIK_LEGACY2, BaseChain.CERTIK_MAIN -> return Shentu()
                BaseChain.CHIHUAHUA_MAIN -> return Chihuahua()
                BaseChain.COMDEX_MAIN -> return Comdex()
                BaseChain.CRESCENT_MAIN -> return Crescent()
                BaseChain.CRYPTO_MAIN -> return Crytoorg()
                BaseChain.CUDOS_MAIN -> return Cudos()
                BaseChain.DESMOS_MAIN -> return Desmos()
                BaseChain.EMONEY_MAIN -> return Emoney()
                BaseChain.EVMOS_MAIN -> return Evmos()
                BaseChain.FETCHAI_MAIN -> return FetchAi()
                BaseChain.GRABRIDGE_MAIN -> return GravityBridge()
                BaseChain.INJ_MAIN -> return Injective()
                BaseChain.IXO_MAIN -> return Ixo()
                BaseChain.JUNO_MAIN -> return Juno()
                BaseChain.KAVA_LEGACY1, BaseChain.KAVA_LEGACY2, BaseChain.KAVA_LEGACY3, BaseChain.KAVA_LEGACY4, BaseChain.KAVA_LEGACY5, BaseChain.KAVA_LEGACY6, BaseChain.KAVA_MAIN -> return Kava()
                BaseChain.KI_MAIN -> return Ki()
                BaseChain.KONSTELL_MAIN -> return Konstellation()
                BaseChain.KUJIRA_MAIN -> return Kujira()
                BaseChain.LIKECOIN_MAIN -> return LikeCoin()
                BaseChain.LUM_MAIN -> return Lum()
                BaseChain.MEDI_MAIN -> return Medibloc()
                BaseChain.NYX_MAIN -> return Nyx()
                BaseChain.OKEX_LEGACY1, BaseChain.OKEX_MAIN -> return Okc()
                BaseChain.OMNIFLIX_MAIN -> return Omniflix()
                BaseChain.OSMOSIS_MAIN -> return Osmosis()
                BaseChain.PASSAGE_MAIN -> return Passage()
                BaseChain.PERSIS_MAIN -> return Persistence()
                BaseChain.PROVENANCE_MAIN -> return Provenance()
                BaseChain.REGEN_MAIN -> return Regen()
                BaseChain.RIZON_MAIN -> return Rizon()
                BaseChain.SECRET_LEGACY1, BaseChain.SECRET_MAIN -> return Secret()
                BaseChain.SENTINEL_MAIN -> return Sentinel()
                BaseChain.SIF_MAIN -> return Sif()
                BaseChain.SOMMELIER_MAIN -> return Sommelier()
                BaseChain.STARGAZE_MAIN -> return Stargaze()
                BaseChain.STRIDE_MAIN -> return Stride()
                BaseChain.IOV_LEGACY1, BaseChain.IOV_MAIN -> return Starname()
                BaseChain.TERITORI_MAIN -> return Teritori()
                BaseChain.UMEE_MAIN -> return Umee()
                BaseChain.XPLA_MAIN -> return Xpla()
                BaseChain.STATION_TEST -> return StationTest()
                else -> return null
            }
        }
        return null
    }

    @JvmStatic
    fun SUPPRT_CONFIG(): ArrayList<ChainConfig> {
        val result = ArrayList<ChainConfig>()
        for (baseChain in BaseChain.SUPPORT_CHAINS()) {
            val chainConfig = getChain(baseChain)
            if (chainConfig != null) {
                result.add(chainConfig)
            }
        }
        return result
    }

    @JvmStatic
    fun SUPPRT_CONFIG_WITH_CUSTOM(): ArrayList<ChainConfig> {
        val result = ArrayList<ChainConfig>()
        for (baseChain in BaseChain.SUPPORT_CHAINS()) {
            val chainConfig = getChain(baseChain)
            if (chainConfig != null) {
                result.add(chainConfig)
            }
        }
        for (info in TempCustomChainManager.instance.infos) {
            result.add(CustomChain(info))
        }
        return result
    }
}