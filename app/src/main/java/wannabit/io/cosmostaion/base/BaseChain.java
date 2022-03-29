package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;

public enum BaseChain {
    // chain_id is checked on-chain. no need update chain version  21.03.20
    COSMOS_MAIN(
            "cosmoshub-mainnet,cosmoshub-1,cosmoshub-2,cosmoshub-3,cosmoshub-4",
            R.drawable.cosmos_wh_main,
            R.string.str_cosmos_hub,
            "uatom",
            "Cosmos Staking Coin",
            R.color.colorAtom,
            R.drawable.atom_ic,
            R.drawable.box_round_atom,
            R.string.str_atom_c,
            true,
            false
    ),
    IMVERSED_MAIN(
            "imversed",
            R.drawable.imversed,
            R.string.str_imversed_net,
            "nimv",
            "Imversed Staking Coin",
            R.color.colorImversed,
            R.drawable.imversed_token_img,
            R.drawable.box_round_imversed,
            R.string.str_imversed_c,
            true,
            false
    ),
    IRIS_MAIN(
            "irishub-mainnet,irishub,irishub-1",
            R.drawable.iris_wh,
            R.string.str_iris_net,
            "uiris",
            "Iris Staking Coin",
            R.color.colorIris,
            R.drawable.iris_toket_img,
            R.drawable.box_round_iris,
            R.string.str_iris_c,
            true,
            false
    ),
    IOV_MAIN(
            "iov-mainnet,iov-mainnet-2",
            R.drawable.chain_starname,
            R.string.str_iov_net,
            "uiov",
            "Starname Staking Coin",
            R.color.colorIov,
            R.drawable.iov_token_img,
            R.drawable.box_round_iov,
            R.string.str_iov_c,
            true,
            false
    ),
    BNB_MAIN(
            "binance-mainnet,Binance-Chain-Tigris",
            R.drawable.binance_ch_img,
            R.string.str_binance_net,
            "BNB",
            "Binance Chain Native Coin",
            R.color.colorBnb,
            R.drawable.bnb_token_img,
            R.drawable.box_round_bnb,
            R.string.str_bnb_c,
            true,
            false,
            false
    ),
    KAVA_MAIN(
            "kava-mainnet,kava-1,kava-2,kava-3,kava-4,kava-5,kava-6",
            R.drawable.kava_img,
            R.string.str_kava_net,
            "ukava",
            "Kava Staking Coin",
            R.color.colorKava,
            R.drawable.kava_token_img,
            R.drawable.box_round_kava,
            R.string.str_kava_c,
            true,
            false
    ),
    BAND_MAIN(
            "band-mainnet,band-wenchang-mainnet,band-guanyu-mainnet",
            R.drawable.band_chain_img,
            R.string.str_band_chain,
            "uband",
            "Band Staking Coin",
            R.color.colorBand,
            R.drawable.band_token_img,
            R.drawable.box_round_band,
            R.string.str_band_c,
            true,
            false
    ),
    CERTIK_MAIN(
            "shentu-mainnet,shentu-1,shentu-2",
            R.drawable.certik_chain_img,
            R.string.str_certik_chain,
            "uctk",
            "Certik Staking Coin",
            R.color.colorCertik,
            R.drawable.certik_token_img,
            R.drawable.box_round_certik,
            R.string.str_ctk_c,
            true,
            false
    ),
    SECRET_MAIN(
            "secret-mainnet,secret-2",
            R.drawable.chainsecret,
            R.string.str_secret_chain,
            "uscrt",
            "Secret Native Coin",
            R.color.colorSecret,
            R.drawable.tokensecret,
            R.drawable.box_round_secret,
            R.string.str_scrt_c,
            true,
            false
    ),
    AKASH_MAIN(
            "akashnet-mainnet, akashnet-1,akashnet-2",
            R.drawable.akash_chain_img,
            R.string.str_akash_chain,
            "uakt",
            "Akash Staking Coin",
            R.color.colorAkash,
            R.drawable.akash_token_img,
            R.drawable.box_round_akash,
            R.string.str_akt_c,
            true,
            false
    ),
    OKEX_MAIN(
            "okexchain-mainnet,okexchain-66",
            R.drawable.chain_okx,
            R.string.str_ok_net,
            "okt",
            "OEC Staking Coin",
            R.color.colorOK,
            R.drawable.token_okx,
            R.drawable.box_round_okex,
            R.string.str_ok_c,
            true,
            false,
            false
    ),
    PERSIS_MAIN(
            "persistence-mainnet",
            R.drawable.chainpersistence,
            R.string.str_persis_net,
            "uxprt",
            "Persistence Staking Coin",
            R.color.colorPersis,
            R.drawable.tokenpersistence,
            R.drawable.box_round_persis,
            R.string.str_xprt_c,
            true,
            false
    ),
    SENTINEL_MAIN(
            "sentinel-mainnet",
            R.drawable.chainsentinel,
            R.string.str_sentinel_net,
            "udvpn",
            "Sentinel Native Coin",
            R.color.colorSentinel,
            R.drawable.tokensentinel,
            R.drawable.box_round_sentinel,
            R.string.str_dvpn_c,
            true,
            false
    ),
    FETCHAI_MAIN(
            "fetchai-mainnet",
            R.drawable.chainfetchai,
            R.string.str_fetch_net,
            "afet",
            "Fetch,ai Staking Coin",
            R.color.colorFetch,
            R.drawable.tokenfetchai,
            R.drawable.box_round_fetch,
            R.string.str_fet_c,
            true,
            false
    ),
    CRYPTO_MAIN(
            "crytoorg-mainnet",
            R.drawable.chaincrypto,
            R.string.str_crypto_net,
            "basecro",
            "Cronos",
            R.color.colorCryto,
            R.drawable.tokencrypto,
            R.drawable.box_round_cryto,
            R.string.str_cro_c,
            true,
            false
    ),
    SIF_MAIN(
            "sif-mainnet",
            R.drawable.chainsifchain,
            R.string.str_sif_net,
            "rowan",
            "Sif Staking Coin",
            R.color.colorSif,
            R.drawable.tokensifchain,
            R.drawable.box_round_sif,
            R.string.str_sif_c,
            true,
            false
    ),
    KI_MAIN(
            "ki-mainnet",
            R.drawable.chain_kifoundation,
            R.string.str_ki_net,
            "uxki",
            "KiChain Staking Coin",
            R.color.colorKi,
            R.drawable.token_kifoundation,
            R.drawable.box_round_ki,
            R.string.str_ki_c,
            true,
            false
    ),
    OSMOSIS_MAIN(
            "osmosis-mainnet",
            R.drawable.chain_osmosis,
            R.string.str_osmosis_net,
            "uosmo",
            "Osmosis Staking Coin",
            R.color.colorOsmosis,
            R.drawable.token_osmosis,
            R.drawable.box_round_osmosis,
            R.string.str_osmosis_c,
            true,
            false
    ),
    MEDI_MAIN(
            "medibloc-mainnet",
            R.drawable.chainmedibloc,
            R.string.str_medi_net,
            "umed",
            "Medibloc Staking Coin",
            R.color.colorMedi,
            R.drawable.tokenmedibloc,
            R.drawable.box_round_medi,
            R.string.str_medi_c,
            true,
            false
    ),
    EMONEY_MAIN(
            "emoney-mainnet",
            R.drawable.chain_emoney,
            R.string.str_emoney_net,
            "ungm",
            "E-Money Staking Coin",
            R.color.colorEmoney,
            R.drawable.token_emoney,
            R.drawable.box_round_emoney,
            R.string.str_ngm_c,
            true,
            false
    ),
    RIZON_MAIN(
            "rizon-mainnet",
            R.drawable.chain_rizon,
            R.string.str_rizon_net,
            "uatolo",
            "Rizon Staking Coin",
            R.color.colorRizon,
            R.drawable.token_rizon,
            R.drawable.box_round_rizon,
            R.string.str_rizon_c,
            true,
            false
    ),
    JUNO_MAIN(
            "juno-mainnet",
            R.drawable.chain_juno,
            R.string.str_juno_net,
            "ujuno",
            "Juno Staking Coin",
            R.color.colorJuno,
            R.drawable.token_juno,
            R.drawable.box_round_juno,
            R.string.str_juno_c,
            true,
            false
    ),
    REGEN_MAIN(
            "regen-mainnet",
            R.drawable.chain_regen,
            R.string.str_regen_net,
            "uregen",
            "Regen Staking Coin",
            R.color.colorRegen,
            R.drawable.token_regen,
            R.drawable.box_round_regen,
            R.string.str_regen_c,
            true,
            false
    ),
    BITCANNA_MAIN(
            "bitcanna-mainnet",
            R.drawable.chain_bitcanna,
            R.string.str_bitcanna_net,
            "ubcna",
            "Bitcanna Staking Coin",
            R.color.colorBitcanna,
            R.drawable.token_bitcanna,
            R.drawable.box_round_bitcanna,
            R.string.str_bitcanna_c,
            true,
            false
    ),
    ALTHEA_MAIN(
            "althea-mainnet",
            R.drawable.chain_althea,
            R.string.str_althea_net,
            "ualtg",
            "Althea Stacking Coin",
            R.color.colorAlthea,
            R.drawable.token_althea,
            R.drawable.box_round_althea,
            R.string.str_althea_c,
            false,
            false
    ),
    STARGAZE_MAIN(
            "stargaze-mainnet",
            R.drawable.chain_stargaze,
            R.string.str_stargaze_net,
            "ustars",
            "Stargaze Staking Coin",
            R.color.colorStargaze,
            R.drawable.token_stargaze,
            R.drawable.box_round_stargaze,
            R.string.str_stargaze_c,
            true,
            false
    ),
    GRABRIDGE_MAIN(
            "GravityBridge-mainnet",
            R.drawable.chain_gravitybridge,
            R.string.str_grabridge_net,
            "ugraviton",
            "G-Bridge Staking Coin",
            R.color.colorGraBridge,
            R.drawable.token_gravitybridge,
            R.drawable.box_round_grabridge,
            R.string.str_grabridge_c,
            true,
            false
    ),
    COMDEX_MAIN(
            "comdex-mainnet",
            R.drawable.chain_comdex,
            R.string.str_comdex_net,
            "ucmdx",
            "Comdex Staking Coin",
            R.color.colorComdex,
            R.drawable.token_comdex,
            R.drawable.box_round_comdex,
            R.string.str_comdex_c,
            true,
            false
    ),
    INJ_MAIN(
            "injective-mainnet",
            R.drawable.chain_injective,
            R.string.str_inj_net,
            "inj",
            "Injective Staking Coin",
            R.color.colorInj,
            R.drawable.token_injective,
            R.drawable.box_round_inj,
            R.string.str_inj_c,
            true,
            false
    ),
    BITSONG_MAIN(
            "bitsong-mainnet",
            R.drawable.chain_bitsong,
            R.string.str_bitsong_net,
            "ubtsg",
            "Bitsong Staking Coin",
            R.color.colorBitsong,
            R.drawable.token_bitsong,
            R.drawable.box_round_bitsong,
            R.string.str_bitsong_c,
            true,
            false
    ),
    DESMOS_MAIN(
            "desmos-mainnet",
            R.drawable.chain_desmos,
            R.string.str_desmos_net,
            "udsm",
            "Desmos Staking Coin",
            R.color.colorDesmos,
            R.drawable.token_desmos,
            R.drawable.box_round_desmos,
            R.string.str_desmos_c,
            true,
            false
    ),
    LUM_MAIN(
            "lum-mainnet",
            R.drawable.chain_lumnetwork,
            R.string.str_lum_net,
            "ulum",
            "Lum Staking Coin",
            R.color.colorLum,
            R.drawable.token_lum,
            R.drawable.box_round_lum,
            R.string.str_lum_c,
            true,
            false
    ),
    CHIHUAHUA_MAIN(
            "chihuahua-mainnet",
            R.drawable.chain_chihuahua,
            R.string.str_chihuahua_net,
            "uhuahua",
            "Chihuahua Staking Coin",
            R.color.colorChihuahua,
            R.drawable.token_huahua,
            R.drawable.box_round_chihuahua,
            R.string.str_chihuahua_c,
            true,
            false
    ),
    AXELAR_MAIN(
            "axelar-mainnet",
            R.drawable.chain_axelar,
            R.string.str_axelar_net,
            "uaxl",
            "Axelar Staking Coin",
            R.color.colorAxelar,
            R.drawable.token_axelar,
            R.drawable.box_round_axelar,
            R.string.str_axl_c,
            true,
            false
    ),
    KONSTELL_MAIN(
            "konstellation-mainnet",
            R.drawable.chain_konstellation,
            R.string.str_konstellation_net,
            "udarc",
            "Konstellation Staking Coin",
            R.color.colorKonstellation,
            R.drawable.token_konstellation,
            R.drawable.box_round_konstellattion,
            R.string.str_konstellation_c,
            true,
            false
    ),
    UMEE_MAIN(
            "umee-mainnet",
            R.drawable.chain_umee,
            R.string.str_umee_net,
            "uumee",
            "Umee Staking Coin",
            R.color.colorUmee,
            R.drawable.token_umee,
            R.drawable.box_round_umee,
            R.string.str_umee_c,
            true,
            false
    ),
    EVMOS_MAIN(
            "evmos-mainnet",
            R.drawable.chain_evmos,
            R.string.str_evmos_net,
            "aevmos",
            "Evmos Staking Coin",
            R.color.colorEvmos,
            R.drawable.token_evmos,
            R.drawable.box_round_evmos,
            R.string.str_evmos_c,
            true,
            false
    ),
    CUDOS_MAIN(
            "cudos-mainnet",
            R.drawable.chain_cudos,
            R.string.str_cudos_net,
            "acudos",
            "Cudos Staking Coin",
            R.color.colorCudos,
            R.drawable.token_cudos,
            R.drawable.box_round_cudos,
            R.string.str_cudos_c,
            false,
            true
    ),
    PROVENANCE_MAIN(
            "provenance-mainnet",
            R.drawable.chain_provenance,
            R.string.str_provenance_net,
            "nhash",
            "Provenance Staking Coin",
            R.color.colorProvenance,
            R.drawable.token_hash,
            R.drawable.box_round_provenance,
            R.string.str_provenance_c,
            true,
            false
    ),
    CERBERUS_MAIN(
            "cerberus-mainnet",
            R.drawable.chain_cerberus,
            R.string.str_cerberus_net,
            "ucrbrus",
            "Cerberus Staking Coin",
            R.color.colorCerberus,
            R.drawable.token_cerberus,
            R.drawable.box_round_cerberus,
            R.string.str_cerberus_c,
            true,
            false
    ),
    OMNIFLIX_MAIN(
            "omniflix-mainnet",
            R.drawable.chain_omniflix,
            R.string.str_omniflix_net,
            "uflix",
            "Omniflix Staking Coin",
            R.color.colorOmniflix,
            R.drawable.token_omniflix,
            R.drawable.box_round_omniflix,
            R.string.str_omniflix_c,
            true,
            false
    ),

    COSMOS_TEST(
            "cosmos-testnet,stargate-final",
            R.drawable.chain_test_cosmos,
            R.string.str_cosmos_test_net,
            "uatom",
            "Stargate Staking Coin",
            R.color.colorAtom,
            R.drawable.atom_ic,
            R.drawable.box_round_atom,
            R.string.str_muon_c,
            false,
            true
    ),
    IRIS_TEST(
            "iris-testnet,bifrost-2",
            R.drawable.chain_test_iris,
            R.string.str_iris_test_net,
            "uiris",
            "Bifrost Staking Coin",
            R.color.colorIris,
            R.drawable.iris_toket_img,
            R.drawable.box_round_darkgray,
            R.string.str_bif_c,
            false,
            true
    ),
    OK_TEST(
            "okexchain-testnet",
            R.drawable.chain_okx,
            R.string.str_ok_test_net,
            "okt",
            "OEC Staking Coin",
            R.color.colorOK,
            R.drawable.token_okx,
            R.drawable.box_round_okex,
            R.string.str_ok_c,
            false,
            true
    ),
    RIZON_TEST(
            "rizon-testnet2",
            R.drawable.chain_rizon,
            R.string.str_rizon_test_net,
            "uatolo",
            "Rizon Staking Coin",
            R.color.colorRizon,
            R.drawable.token_rizon,
            R.drawable.box_round_rizon,
            R.string.str_rizon_c,
            false,
            true
    ),
    ALTHEA_TEST(
            "althea-testnet",
            R.drawable.chain_althea,
            R.string.str_althea_test_net,
            "ualtg",
            "Althea Staking Coin",
            R.color.colorAlthea,
            R.drawable.token_althea,
            R.drawable.box_round_darkgray,
            R.string.str_althea_c,
            false,
            true
    );

    private final String chainName;
    private final String mainDenom;
    private final String fullNameCoin;
    private final int denomColor;
    private final String[] aliases;
    private final boolean isSupported;
    private final boolean isTestNet;
    private final boolean isGRPC;
    @DrawableRes
    private final int mnemonicBackground;

    @DrawableRes
    private final int symbolTitle;
    @DrawableRes
    private final int coinIcon;
    @DrawableRes
    private final int chainIcon;
    @DrawableRes
    private final int chainTitle;

    /*
    names: String - format "{main chain name}, aliases"
    */
    BaseChain(final String names, final int chainIcon, final int chainTitle, final String mainDenom, final String fullNameCoin, final int denomColor, final int coinIcon, final int mnemonicBackground, final int symbolTitle, final boolean isSupported, final boolean isTestNet) {
        String[] chunks = names.split(",");
        this.chainName = chunks[0];
        this.aliases = chunks.length > 1 ? Arrays.copyOfRange(chunks, 1, chunks.length) : new String[0];
        this.chainIcon = chainIcon;
        this.chainTitle = chainTitle;
        this.mainDenom = mainDenom;
        this.fullNameCoin = fullNameCoin;
        this.denomColor = denomColor;
        this.mnemonicBackground = mnemonicBackground;
        this.symbolTitle = symbolTitle;
        this.coinIcon = coinIcon;
        this.isSupported = isSupported;
        this.isTestNet = isTestNet;
        this.isGRPC = true;
    }

    /*
    names: String - format "{main chain name}, aliases"
    */
    BaseChain(final String names, final int chainIcon, final int chainTitle, final String mainDenom, final String fullNameCoin, final int denomColor, final int coinIcon, final int mnemonicBackground, final int symbolTitle, final boolean isSupported, final boolean isTestNet, final boolean isGRPC) {
        String[] chunks = names.split(",");
        this.chainName = chunks[0];
        this.aliases = chunks.length > 1 ? Arrays.copyOfRange(chunks, 1, chunks.length) : new String[0];
        this.chainIcon = chainIcon;
        this.chainTitle = chainTitle;
        this.mainDenom = mainDenom;
        this.fullNameCoin = fullNameCoin;
        this.denomColor = denomColor;
        this.mnemonicBackground = mnemonicBackground;
        this.symbolTitle = symbolTitle;
        this.coinIcon = coinIcon;
        this.isSupported = isSupported;
        this.isTestNet = isTestNet;
        this.isGRPC = isGRPC;
    }

    public String getChain() {
        return chainName;
    }

    public String getMainDenom() {
        return mainDenom;
    }

    public int getSymbolTitle() {
        return symbolTitle;
    }

    public int getCoinIcon() {
        return coinIcon;
    }

    public int getChainTitle() {
        return chainTitle;
    }

    public int getChainIcon() {
        return chainIcon;
    }

    public String getFullNameCoin() {
        return fullNameCoin;
    }

    @DrawableRes
    public int getMnemonicBackground() {
        return mnemonicBackground;
    }

    public int getDenomColor() {
        return denomColor;
    }

    public boolean isSupported() {
        return isSupported;
    }

    public boolean isTestNet() {
        return isTestNet;
    }

    public boolean isGRPC() {
        return isGRPC;
    }

    public static BaseChain getChain(String chainName) {
        for (BaseChain chain : BaseChain.values()) {
            if (chain.isChainName(chainName)) {
                return chain;
            }
        }
        return null;
    }

    public static BaseChain getChainByDenom(String denom) {
        for (BaseChain chain : BaseChain.values()) {
            if (chain.mainDenom.equals(denom)) {
                return chain;
            }
        }
        return null;
    }

    public boolean isChainName(String chain) {
        boolean result = chainName.equals(chain);
        for (String alias : aliases) {
            if (alias.equals(chain)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public static boolean isSupported(String chainName) {
        BaseChain chain = getChain(chainName);
        return chain != null && chain.isSupported;
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

    public static ArrayList<String> getHtlcSwappableCoin(BaseChain fromChain) {
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
}
