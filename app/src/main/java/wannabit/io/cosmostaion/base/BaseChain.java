package wannabit.io.cosmostaion.base;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;

import android.text.TextUtils;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;

public enum BaseChain {
    // chain_id is checked on-chain. no need update chain version  21.03.20
    COSMOS_MAIN(
            "cosmoshub-mainnet,cosmoshub-1,cosmoshub-2,cosmoshub-3,cosmoshub-4",
            "cosmos1",
            R.drawable.cosmos_wh_main,
            R.string.str_cosmos_hub,
            R.string.str_cosmos,
            "uatom",
            "Cosmos Staking Coin",
            "atom",
            R.color.colorAtom,
            R.drawable.atom_ic,
            R.drawable.box_round_atom,
            R.string.str_atom_c,
            R.color.colorTransBgCosmos,
            R.color.color_tab_myvalidator,
            "7.6597",
            "https://api.cosmostation.io/",
            true,
            false
    ),
    IMVERSED_MAIN(
            "imversed",
            "imv1",
            R.drawable.imversed,
            R.string.str_imversed_net,
            R.string.str_imversed,
            "nimv",
            "Imversed Staking Coin",
            "",
            R.color.colorImversed,
            R.drawable.imversed_token_img,
            R.drawable.box_round_imversed,
            R.string.str_imversed_c,
            R.color.colorTransBgImversed,
            R.color.color_tab_myvalidator_imversed,
            "",
            "",
            true,
            false
    ),
    IRIS_MAIN(
            "irishub-mainnet,irishub,irishub-1",
            "iaa1",
            R.drawable.iris_wh,
            R.string.str_iris_net,
            R.string.str_iris,
            "uiris",
            "Iris Staking Coin",
            "iris",
            R.color.colorIris,
            R.drawable.iris_toket_img,
            R.drawable.box_round_iris,
            R.string.str_iris_c,
            R.color.colorTransBgIris,
            R.color.color_tab_myvalidator_iris,
            "6.7884",
            "https://api-iris.cosmostation.io/",
            true,
            false
    ),
    IOV_MAIN(
            "iov-mainnet,iov-mainnet-2",
            "star1",
            R.drawable.chain_starname,
            R.string.str_iov_net,
            R.string.str_iov,
            "uiov",
            "Starname Staking Coin",
            "iov",
            R.color.colorIov,
            R.drawable.iov_token_img,
            R.drawable.box_round_iov,
            R.string.str_iov_c,
            R.color.colorTransBgStarname,
            R.color.color_tab_myvalidator_iov,
            "6.0124",
            "https://api-iov.cosmostation.io/",
            true,
            false
    ),
    BNB_MAIN(
            "binance-mainnet,Binance-Chain-Tigris",
            "bnb1",
            R.drawable.binance_ch_img,
            R.string.str_binance_net,
            R.string.str_binance,
            "BNB",
            "Binance Chain Native Coin",
            "bnb",
            R.color.colorBnb,
            R.drawable.bnb_token_img,
            R.drawable.box_round_bnb,
            R.string.str_bnb_c,
            R.color.colorTransBgBinance,
            R.color.color_tab_myvalidator,
            "0.4124",
            "",
            true,
            false,
            false
    ),
    KAVA_MAIN(
            "kava-mainnet,kava-1,kava-2,kava-3,kava-4,kava-5,kava-6",
            "kava1",
            R.drawable.kava_img,
            R.string.str_kava_net,
            R.string.str_kava,
            "ukava",
            "Kava Staking Coin",
            "kava",
            R.color.colorKava,
            R.drawable.kava_token_img,
            R.drawable.box_round_kava,
            R.string.str_kava_c,
            R.color.colorTransBgKava,
            R.color.color_tab_myvalidator_kava,
            "6.7262",
            "https://api-kava.cosmostation.io/",
            true,
            false
    ),
    BAND_MAIN(
            "band-mainnet,band-wenchang-mainnet,band-guanyu-mainnet",
            "band1",
            R.drawable.band_chain_img,
            R.string.str_band_chain,
            R.string.str_band,
            "uband",
            "Band Staking Coin",
            "band",
            R.color.colorBand,
            R.drawable.band_token_img,
            R.drawable.box_round_band,
            R.string.str_band_c,
            R.color.colorTransBgBand,
            R.color.color_tab_myvalidator_band,
            "3.0236",
            "https://api-band.cosmostation.io/",
            true,
            false
    ),
    CERTIK_MAIN(
            "shentu-mainnet,shentu-1,shentu-2",
            "certik1",
            R.drawable.certik_chain_img,
            R.string.str_certik_chain,
            R.string.str_certik_main,
            "uctk",
            "Certik Staking Coin",
            "ctk",
            R.color.colorCertik,
            R.drawable.certik_token_img,
            R.drawable.box_round_certik,
            R.string.str_ctk_c,
            R.color.colorTransBgCertik,
            R.color.color_tab_myvalidator_certik,
            "5.9740",
            "https://api-certik.cosmostation.io/",
            true,
            false
    ),
    SECRET_MAIN(
            "secret-mainnet,secret-2",
            "secret1",
            R.drawable.chainsecret,
            R.string.str_secret_chain,
            R.string.str_secret_main,
            "uscrt",
            "Secret Native Coin",
            "scrt",
            R.color.colorSecret,
            R.drawable.tokensecret,
            R.drawable.box_round_secret,
            R.string.str_scrt_c,
            R.color.colorTransBgSecret,
            R.color.color_tab_myvalidator_secret,
            "6.0408",
            "https://api-secret.cosmostation.io/",
            true,
            false
    ),
    AKASH_MAIN(
            "akashnet-mainnet, akashnet-1,akashnet-2",
            "akash1",
            R.drawable.akash_chain_img,
            R.string.str_akash_chain,
            R.string.str_akash_main,
            "uakt",
            "Akash Staking Coin",
            "akt",
            R.color.colorAkash,
            R.drawable.akash_token_img,
            R.drawable.box_round_akash,
            R.string.str_akt_c,
            R.color.colorTransBgAkash,
            R.color.color_tab_myvalidator_akash,
            "6.4526",
            "https://api-akash.cosmostation.io/",
            true,
            false
    ),
    OKEX_MAIN(
            "okexchain-mainnet,okexchain-66",
            "0x",
            R.drawable.chain_okx,
            R.string.str_ok_net,
            R.string.str_okex_main,
            "okt",
            "OEC Staking Coin",
            "okb",
            R.color.colorOK,
            R.drawable.token_okx,
            R.drawable.box_round_okex,
            R.string.str_ok_c,
            R.color.colorTransBgOkex,
            R.color.color_tab_myvalidator_ok,
            "4.0286",
            "",
            true,
            false,
            false
    ),
    PERSIS_MAIN(
            "persistence-mainnet",
            "persistence1",
            R.drawable.chainpersistence,
            R.string.str_persis_net,
            R.string.str_persis_main,
            "uxprt",
            "Persistence Staking Coin",
            "xprt",
            R.color.colorPersis,
            R.drawable.tokenpersistence,
            R.drawable.box_round_persis,
            R.string.str_xprt_c,
            R.color.colorTransBgPersis,
            R.color.color_tab_myvalidator_persis,
            "5.7982",
            "https://api-persistence.cosmostation.io/",
            true,
            false
    ),
    SENTINEL_MAIN(
            "sentinel-mainnet",
            "sent1",
            R.drawable.chainsentinel,
            R.string.str_sentinel_net,
            R.string.str_sentinel_main,
            "udvpn",
            "Sentinel Native Coin",
            "dvpn",
            R.color.colorSentinel,
            R.drawable.tokensentinel,
            R.drawable.box_round_sentinel,
            R.string.str_dvpn_c,
            R.color.colorTransBgSentinel,
            R.color.color_tab_myvalidator_sentinel,
            "6.3113",
            "https://api-sentinel.cosmostation.io/",
            true,
            false
    ),
    FETCHAI_MAIN(
            "fetchai-mainnet",
            "fetch1",
            R.drawable.chainfetchai,
            R.string.str_fetch_net,
            R.string.str_fetch_main,
            "afet",
            "Fetch,ai Staking Coin",
            "", // "fet"
            R.color.colorFetch,
            R.drawable.tokenfetchai,
            R.drawable.box_round_fetch,
            R.string.str_fet_c,
            R.color.colorTransBgFetch,
            R.color.color_tab_myvalidator_fetch,
            "6.0678",
            "https://api-fetchai.cosmostation.io/",
            true,
            false
    ),
    CRYPTO_MAIN(
            "crytoorg-mainnet",
            "cro1",
            R.drawable.chaincrypto,
            R.string.str_crypto_net,
            R.string.str_crypto_main,
            "basecro",
            "Cronos",
            "cro",
            R.color.colorCryto,
            R.drawable.tokencrypto,
            R.drawable.box_round_cryto,
            R.string.str_cro_c,
            R.color.colorTransBgCryto,
            R.color.color_tab_myvalidator_cryto,
            "6.1939",
            "https://api-cryptocom.cosmostation.io/",
            true,
            false
    ),
    SIF_MAIN(
            "sif-mainnet",
            "sif1",
            R.drawable.chainsifchain,
            R.string.str_sif_net,
            R.string.str_sif_main,
            "rowan",
            "Sif Staking Coin",
            "rowan",
            R.color.colorSif,
            R.drawable.tokensifchain,
            R.drawable.box_round_sif,
            R.string.str_sif_c,
            R.color.colorTransBgSif,
            R.color.color_tab_myvalidator_sif,
            "5.7246",
            "https://api-sifchain.cosmostation.io/",
            true,
            false
    ),
    KI_MAIN(
            "ki-mainnet",
            "ki1",
            R.drawable.chain_kifoundation,
            R.string.str_ki_net,
            R.string.str_ki_main,
            "uxki",
            "KiChain Staking Coin",
            "", // "ki"
            R.color.colorKi,
            R.drawable.token_kifoundation,
            R.drawable.box_round_ki,
            R.string.str_ki_c,
            R.color.colorTransBgKi,
            R.color.color_tab_myvalidator_ki,
            "5.7571",
            "https://api-kichain.cosmostation.io/",
            true,
            false
    ),
    OSMOSIS_MAIN(
            "osmosis-mainnet",
            "osmo1",
            R.drawable.chain_osmosis,
            R.string.str_osmosis_net,
            R.string.str_osmosis_main,
            "uosmo",
            "Osmosis Staking Coin",
            "osmo",
            R.color.colorOsmosis,
            R.drawable.token_osmosis,
            R.drawable.box_round_osmosis,
            R.string.str_osmosis_c,
            R.color.colorTransBgOsmosis,
            R.color.color_tab_myvalidator_osmosis,
            "6.5324",
            "https://api-osmosis.cosmostation.io/",
            true,
            false
    ),
    MEDI_MAIN(
            "medibloc-mainnet",
            "panacea1",
            R.drawable.chainmedibloc,
            R.string.str_medi_net,
            R.string.str_medi_main,
            "umed",
            "Medibloc Staking Coin",
            "", // "med"
            R.color.colorMedi,
            R.drawable.tokenmedibloc,
            R.drawable.box_round_medi,
            R.string.str_medi_c,
            R.color.colorTransBgMedi,
            R.color.color_tab_myvalidator_med,
            "5.7849",
            "https://api-medibloc.cosmostation.io/",
            true,
            false
    ),
    EMONEY_MAIN(
            "emoney-mainnet",
            "emoney1",
            R.drawable.chain_emoney,
            R.string.str_emoney_net,
            R.string.str_emoney_main,
            "ungm",
            "E-Money Staking Coin",
            "", // "ngm"
            R.color.colorEmoney,
            R.drawable.token_emoney,
            R.drawable.box_round_emoney,
            R.string.str_ngm_c,
            R.color.colorTransBgEmoney,
            R.color.color_tab_myvalidator_emoney,
            "24.8486",
            "https://api-emoney.cosmostation.io/",
            true,
            false
    ),
    RIZON_MAIN(
            "rizon-mainnet",
            "rizon1",
            R.drawable.chain_rizon,
            R.string.str_rizon_net,
            R.string.str_rizon_main,
            "uatolo",
            "Rizon Staking Coin",
            "atolo",
            R.color.colorRizon,
            R.drawable.token_rizon,
            R.drawable.box_round_rizon,
            R.string.str_rizon_c,
            R.color.colorTransBgRizon,
            R.color.color_tab_myvalidator_rizon,
            "5.8850",
            "https://api-rizon.cosmostation.io/",
            true,
            false
    ),
    JUNO_MAIN(
            "juno-mainnet",
            "juno1",
            R.drawable.chain_juno,
            R.string.str_juno_net,
            R.string.str_juno_main,
            "ujuno",
            "Juno Staking Coin",
            "", // "ujuno"
            R.color.colorJuno,
            R.drawable.token_juno,
            R.drawable.box_round_juno,
            R.string.str_juno_c,
            R.color.colorTransBgJuno,
            R.color.color_tab_myvalidator_juno,
            "6.3104",
            "https://api-juno.cosmostation.io/",
            true,
            false
    ),
    REGEN_MAIN(
            "regen-mainnet",
            "regen1",
            R.drawable.chain_regen,
            R.string.str_regen_net,
            R.string.str_regen_main,
            "uregen",
            "Regen Staking Coin",
            "regen",
            R.color.colorRegen,
            R.drawable.token_regen,
            R.drawable.box_round_regen,
            R.string.str_regen_c,
            R.color.colorTransBgRegen,
            R.color.color_tab_myvalidator_regen,
            "6.2491",
            "https://api-regen.cosmostation.io/",
            true,
            false
    ),
    BITCANNA_MAIN(
            "bitcanna-mainnet",
            "bcna1",
            R.drawable.chain_bitcanna,
            R.string.str_bitcanna_net,
            R.string.str_bitcanna_main,
            "ubcna",
            "Bitcanna Staking Coin",
            "", // "bcna"
            R.color.colorBitcanna,
            R.drawable.token_bitcanna,
            R.drawable.box_round_bitcanna,
            R.string.str_bitcanna_c,
            R.color.colorTransBgBitcanna,
            R.color.color_tab_myvalidator_bitcanna,
            "6.0256",
            "https://api-bitcanna.cosmostation.io/",
            true,
            false
    ),
    ALTHEA_MAIN(
            "althea-mainnet",
            "althea1",
            R.drawable.chain_althea,
            R.string.str_althea_net,
            R.string.str_althea_main,
            "ualtg",
            "Althea Stacking Coin",
            "",
            R.color.colorAlthea,
            R.drawable.token_althea,
            R.drawable.box_round_althea,
            R.string.str_althea_c,
            R.color.colorTransBgAlthea,
            R.color.color_tab_myvalidator_althea,
            "",
            "https://api-althea.cosmostation.io/",
            false,
            false
    ),
    STARGAZE_MAIN(
            "stargaze-mainnet",
            "stars1",
            R.drawable.chain_stargaze,
            R.string.str_stargaze_net,
            R.string.str_stargaze_main,
            "ustars",
            "Stargaze Staking Coin",
            "", // "stars"
            R.color.colorStargaze,
            R.drawable.token_stargaze,
            R.drawable.box_round_stargaze,
            R.string.str_stargaze_c,
            R.color.colorTransBgStargaze,
            R.color.color_tab_myvalidator_stargaze,
            "5.8129",
            "https://api-stargaze.cosmostation.io/",
            true,
            false
    ),
    GRABRIDGE_MAIN(
            "GravityBridge-mainnet",
            "gravity1",
            R.drawable.chain_gravitybridge,
            R.string.str_grabridge_net,
            R.string.str_grabridge_main,
            "ugraviton",
            "G-Bridge Staking Coin",
            "",
            R.color.colorGraBridge,
            R.drawable.token_gravitybridge,
            R.drawable.box_round_grabridge,
            R.string.str_grabridge_c,
            R.color.colorTransBgGraBridge,
            R.color.color_tab_myvalidator_grabridge,
            "6.4500",
            "https://api-gravity-bridge.cosmostation.io/",
            true,
            false
    ),
    COMDEX_MAIN(
            "comdex-mainnet",
            "comdex1",
            R.drawable.chain_comdex,
            R.string.str_comdex_net,
            R.string.str_comdex_main,
            "ucmdx",
            "Comdex Staking Coin",
            "",
            R.color.colorComdex,
            R.drawable.token_comdex,
            R.drawable.box_round_comdex,
            R.string.str_comdex_c,
            R.color.colorTransBgComdex,
            R.color.color_tab_myvalidator_comdex,
            "6.1746",
            "https://api-comdex.cosmostation.io/",
            true,
            false
    ),
    INJ_MAIN(
            "injective-mainnet",
            "inj1",
            R.drawable.chain_injective,
            R.string.str_inj_net,
            R.string.str_inj_main,
            "inj",
            "Injective Staking Coin",
            "",
            R.color.colorInj,
            R.drawable.token_injective,
            R.drawable.box_round_inj,
            R.string.str_inj_c,
            R.color.colorTransBgInj,
            R.color.color_tab_myvalidator_inj,
            "2.4865",
            "https://api-inj.cosmostation.io/",
            true,
            false
    ),
    BITSONG_MAIN(
            "bitsong-mainnet",
            "bitsong1",
            R.drawable.chain_bitsong,
            R.string.str_bitsong_net,
            R.string.str_bitsong_main,
            "ubtsg",
            "Bitsong Staking Coin",
            "",
            R.color.colorBitsong,
            R.drawable.token_bitsong,
            R.drawable.box_round_bitsong,
            R.string.str_bitsong_c,
            R.color.colorTransBgBitsong,
            R.color.color_tab_myvalidator_bitsong,
            "5.9040",
            "https://api-bitsong.cosmostation.io/",
            true,
            false
    ),
    DESMOS_MAIN(
            "desmos-mainnet",
            "desmos1",
            R.drawable.chain_desmos,
            R.string.str_desmos_net,
            R.string.str_desmos_main,
            "udsm",
            "Desmos Staking Coin",
            "",
            R.color.colorDesmos,
            R.drawable.token_desmos,
            R.drawable.box_round_desmos,
            R.string.str_desmos_c,
            R.color.colorTransBgDesmos,
            R.color.color_tab_myvalidator_desmos,
            "6.1605",
            "https://api-desmos.cosmostation.io/",
            true,
            false
    ),
    LUM_MAIN(
            "lum-mainnet",
            "lum1",
            R.drawable.chain_lumnetwork,
            R.string.str_lum_net,
            R.string.str_lum_main,
            "ulum",
            "Lum Staking Coin",
            "",
            R.color.colorLum,
            R.drawable.token_lum,
            R.drawable.box_round_lum,
            R.string.str_lum_c,
            R.color.colorTransBgLum,
            R.color.color_tab_myvalidator_lum,
            "5.7210",
            "https://api-lum.cosmostation.io/",
            true,
            false
    ),
    CHIHUAHUA_MAIN(
            "chihuahua-mainnet",
            "chihuahua1",
            R.drawable.chain_chihuahua,
            R.string.str_chihuahua_net,
            R.string.str_chihuahua_main,
            "uhuahua",
            "Chihuahua Staking Coin",
            "",
            R.color.colorChihuahua,
            R.drawable.token_huahua,
            R.drawable.box_round_chihuahua,
            R.string.str_chihuahua_c,
            R.color.colorTransBgChihuahua,
            R.color.color_tab_myvalidator_chihuahua,
            "5.8172",
            "https://api-chihuahua.cosmostation.io/",
            true,
            false
    ),
    AXELAR_MAIN(
            "axelar-mainnet",
            "axelar1",
            R.drawable.chain_axelar,
            R.string.str_axelar_net,
            R.string.str_axelar_main,
            "uaxl",
            "Axelar Staking Coin",
            "",
            R.color.colorAxelar,
            R.drawable.token_axelar,
            R.drawable.box_round_axelar,
            R.string.str_axl_c,
            R.color.colorTransBgAxelar,
            R.color.color_tab_myvalidator_axelar,
            "5.5596",
            "https://api-axelar.cosmostation.io/",
            true,
            false
    ),
    KONSTELL_MAIN(
            "konstellation-mainnet",
            "darc1",
            R.drawable.chain_konstellation,
            R.string.str_konstellation_net,
            R.string.str_konstellation_main,
            "udarc",
            "Konstellation Staking Coin",
            "",
            R.color.colorKonstellation,
            R.drawable.token_konstellation,
            R.drawable.box_round_konstellattion,
            R.string.str_konstellation_c,
            R.color.colorTransBgKonstellation,
            R.color.color_tab_myvalidator_konstellation,
            "5.376",
            "https://api-konstellation.cosmostation.io/",
            true,
            false
    ),
    UMEE_MAIN(
            "umee-mainnet",
            "umee1",
            R.drawable.chain_umee,
            R.string.str_umee_net,
            R.string.str_umee_main,
            "uumee",
            "Umee Staking Coin",
            "",
            R.color.colorUmee,
            R.drawable.token_umee,
            R.drawable.box_round_umee,
            R.string.str_umee_c,
            R.color.colorTransBgUmee,
            R.color.color_tab_myvalidator_umee,
            "5.658",
            "https://api-umee.cosmostation.io/",
            true,
            false
    ),
    EVMOS_MAIN(
            "evmos-mainnet",
            "evmos1",
            R.drawable.chain_evmos,
            R.string.str_evmos_net,
            R.string.str_evmos_main,
            "aevmos",
            "Evmos Staking Coin",
            "",
            R.color.colorEvmos,
            R.drawable.token_evmos,
            R.drawable.box_round_evmos,
            R.string.str_evmos_c,
            R.color.colorTransBgEvmos,
            R.color.color_tab_myvalidator_evmos,
            "5.824",
            "https://api-evmos.cosmostation.io/",
            true,
            false
    ),
    CUDOS_MAIN(
            "cudos-mainnet",
            "cudos1",
            R.drawable.chain_cudos,
            R.string.str_cudos_net,
            R.string.str_cudos_main,
            "acudos",
            "Cudos Staking Coin",
            "",
            R.color.colorCudos,
            R.drawable.token_cudos,
            R.drawable.box_round_cudos,
            R.string.str_cudos_c,
            R.color.colorTransBgCudos,
            R.color.color_tab_myvalidator_cudos,
            "",
            "https://api-cudos-testnet.cosmostation.io/",
            false,
            true
    ),
    PROVENANCE_MAIN(
            "provenance-mainnet",
            "pb1",
            R.drawable.chain_provenance,
            R.string.str_provenance_net,
            R.string.str_provenance_main,
            "nhash",
            "Provenance Staking Coin",
            "",
            R.color.colorProvenance,
            R.drawable.token_hash,
            R.drawable.box_round_provenance,
            R.string.str_provenance_c,
            R.color.colorTransBgProvenance,
            R.color.color_tab_myvalidator_provenance,
            "6.3061",
            "https://api-provenance.cosmostation.io/",
            true,
            false
    ),
    CERBERUS_MAIN(
            "cerberus-mainnet",
            "cerberus1",
            R.drawable.chain_cerberus,
            R.string.str_cerberus_net,
            R.string.str_cerberus_main,
            "ucrbrus",
            "Cerberus Staking Coin",
            "",
            R.color.colorCerberus,
            R.drawable.token_cerberus,
            R.drawable.box_round_cerberus,
            R.string.str_cerberus_c,
            R.color.colorTransBgCerberus,
            R.color.color_tab_myvalidator_cerberus,
            "5.9666",
            "https://api-cerberus.cosmostation.io/",
            true,
            false
    ),
    OMNIFLIX_MAIN(
            "omniflix-mainnet",
            "omniflix1",
            R.drawable.chain_omniflix,
            R.string.str_omniflix_net,
            R.string.str_omniflix_main,
            "uflix",
            "Omniflix Staking Coin",
            "",
            R.color.colorOmniflix,
            R.drawable.token_omniflix,
            R.drawable.box_round_omniflix,
            R.string.str_omniflix_c,
            R.color.colorTransBgOmniflix,
            R.color.color_tab_myvalidator_omniflix,
            "5.7970",
            "https://api-omniflix.cosmostation.io/",
            true,
            false
    ),

    COSMOS_TEST(
            "cosmos-testnet,stargate-final",
            "cosmos1",
            R.drawable.chain_test_cosmos,
            R.string.str_cosmos_test_net,
            R.string.str_cosmos_test,
            "uatom",
            "Stargate Staking Coin",
            "",
            R.color.colorAtom,
            R.drawable.atom_ic,
            R.drawable.box_round_atom,
            R.string.str_muon_c,
            R.color.colorTransBgCosmos,
            R.color.color_tab_myvalidator,
            "7.6597",
            "https://api-office.cosmostation.io/stargate-final/",
            false,
            true
    ),
    IRIS_TEST(
            "iris-testnet,bifrost-2",
            "iaa1",
            R.drawable.chain_test_iris,
            R.string.str_iris_test_net,
            R.string.str_iris_test,
            "uiris",
            "Bifrost Staking Coin",
            "",
            R.color.colorIris,
            R.drawable.iris_toket_img,
            R.drawable.box_round_darkgray,
            R.string.str_bif_c,
            R.color.colorTransBgIris,
            R.color.color_tab_myvalidator_iris,
            "6.7884",
            "https://api-office.cosmostation.io/bifrost/",
            false,
            true
    ),
    OK_TEST(
            "okexchain-testnet",
            "0x",
            R.drawable.chain_okx,
            R.string.str_ok_test_net,
            R.string.str_okex_test,
            "okt",
            "OEC Staking Coin",
            "",
            R.color.colorOK,
            R.drawable.token_okx,
            R.drawable.box_round_okex,
            R.string.str_ok_c,
            R.color.colorTransBgOkex,
            R.color.color_tab_myvalidator_ok,
            "",
            "",
            false,
            true
    ),
    RIZON_TEST(
            "rizon-testnet2",
            "rizon1",
            R.drawable.chain_rizon,
            R.string.str_rizon_test_net,
            R.string.str_rizon_test,
            "uatolo",
            "Rizon Staking Coin",
            "",
            R.color.colorRizon,
            R.drawable.token_rizon,
            R.drawable.box_round_rizon,
            R.string.str_rizon_c,
            R.color.colorTransBgRizon,
            R.color.color_tab_myvalidator_rizon,
            "",
            "https://api-rizon-testnet.cosmostation.io/",
            false,
            true
    ),
    ALTHEA_TEST(
            "althea-testnet",
            "althea1",
            R.drawable.chain_althea,
            R.string.str_althea_test_net,
            R.string.str_althea_test,
            "ualtg",
            "Althea Staking Coin",
            "",
            R.color.colorAlthea,
            R.drawable.token_althea,
            R.drawable.box_round_darkgray,
            R.string.str_althea_c,
            R.color.colorTransBgAlthea,
            R.color.color_tab_myvalidator_althea,
            "",
            "https://api-office.cosmostation.io/althea-testnet2v1/",
            false,
            true
    );

    private final String chainName;
    private final String chainAddressPrefix;
    private final String mainDenom;
    private final String fullNameCoin;
    @ColorRes
    private final int denomColor;
    private final String[] aliases;
    private final String ticker;
    private final String blockTime;
    private final String historyApiUrl;
    private final boolean isSupported;
    private final boolean isTestNet;
    private final boolean isGRPC;
    @DrawableRes
    private final int mnemonicBackground;
    @ColorRes
    private final int chainBackground;
    @ColorRes
    private final int chainTabColor;

    @DrawableRes
    private final int symbolTitle;
    @DrawableRes
    private final int coinIcon;
    @DrawableRes
    private final int chainIcon;
    @StringRes
    private final int chainTitle;
    @StringRes
    private final int chainAlterTitle;

    /*
    names: String - format "{main chain name}, aliases"
    */
    BaseChain(
            final String names,
            final String chainAddressPrefix,
            final int chainIcon,
            final int chainTitle,
            final int chainAlterTitle,
            final String mainDenom,
            final String fullNameCoin,
            final String ticker,
            final int denomColor,
            final int coinIcon,
            final int mnemonicBackground,
            final int symbolTitle,
            final int chainBackground,
            final int chainTabColor,
            final String blockTime,
            final String historyApiUrl,
            final boolean isSupported,
            final boolean isTestNet
    ) {
        String[] chunks = names.split(",");
        this.chainName = chunks[0];
        this.aliases = chunks.length > 1 ? Arrays.copyOfRange(chunks, 1, chunks.length) : new String[0];
        this.chainAddressPrefix = chainAddressPrefix;
        this.chainIcon = chainIcon;
        this.chainTitle = chainTitle;
        this.chainAlterTitle = chainAlterTitle;
        this.mainDenom = mainDenom;
        this.fullNameCoin = fullNameCoin;
        this.ticker = ticker;
        this.denomColor = denomColor;
        this.mnemonicBackground = mnemonicBackground;
        this.symbolTitle = symbolTitle;
        this.chainBackground = chainBackground;
        this.chainTabColor = chainTabColor;
        this.coinIcon = coinIcon;
        this.blockTime = blockTime;
        this.historyApiUrl = historyApiUrl;
        this.isSupported = isSupported;
        this.isTestNet = isTestNet;
        this.isGRPC = true;
    }

    /*
    names: String - format "{main chain name}, aliases"
    */
    BaseChain(
            final String names,
            final String chainAddressPrefix,
            final int chainIcon,
            final int chainTitle,
            final int chainAlterTitle,
            final String mainDenom,
            final String fullNameCoin,
            final String ticker,
            final int denomColor,
            final int coinIcon,
            final int mnemonicBackground,
            final int symbolTitle,
            final int chainBackground,
            final int chainTabColor,
            final String blockTime,
            final String historyApiUrl,
            final boolean isSupported,
            final boolean isTestNet,
            final boolean isGRPC
    ) {
        String[] chunks = names.split(",");
        this.chainName = chunks[0];
        this.aliases = chunks.length > 1 ? Arrays.copyOfRange(chunks, 1, chunks.length) : new String[0];
        this.chainAddressPrefix = chainAddressPrefix;
        this.chainIcon = chainIcon;
        this.chainTitle = chainTitle;
        this.chainAlterTitle = chainAlterTitle;
        this.mainDenom = mainDenom;
        this.fullNameCoin = fullNameCoin;
        this.ticker = ticker;
        this.denomColor = denomColor;
        this.mnemonicBackground = mnemonicBackground;
        this.symbolTitle = symbolTitle;
        this.chainBackground = chainBackground;
        this.chainTabColor = chainTabColor;
        this.coinIcon = coinIcon;
        this.blockTime = blockTime;
        this.historyApiUrl = historyApiUrl;
        this.isSupported = isSupported;
        this.isTestNet = isTestNet;
        this.isGRPC = isGRPC;
    }

    public String getChain() {
        return chainName;
    }

    public String getChainAddressPrefix() {
        return chainAddressPrefix;
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

    public int getChainAlterTitle() {
        return chainAlterTitle;
    }

    public int getChainIcon() {
        return chainIcon;
    }

    public int getChainTabColor() {
        return chainTabColor;
    }

    public int getChainBackground() {
        return chainBackground;
    }

    public String getFullNameCoin() {
        return fullNameCoin;
    }

    public String getTicker() {
        return ticker;
    }

    public String getHistoryApiUrl() {
        return historyApiUrl;
    }

    public BigDecimal getBlockTime() {
        BigDecimal result = BigDecimal.ZERO;
        try {
            if (!TextUtils.isEmpty(blockTime)) {
                result = new BigDecimal(blockTime);
            }
        } catch (Exception ignore) {
        }
        return result;
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

    public int getFloatButtonColor() {
        int colorResId;
        switch (this) {
            case PERSIS_MAIN:
                colorResId = R.color.colorPersis;
                break;
            case MEDI_MAIN:
                colorResId = R.color.colorMedi;
                break;
            case COMDEX_MAIN:
                colorResId = R.color.colorComdex;
                break;
            case INJ_MAIN:
            case AXELAR_MAIN:
                colorResId = R.color.colorBlack;
                break;
            case KONSTELL_MAIN:
                colorResId = R.color.colorKonstellation;
                break;
            case EVMOS_MAIN:
                colorResId = R.color.colorEvmos;
                break;
            default:
                colorResId = R.color.colorWhite;
        }
        return colorResId;
    }

    public int getFloatButtonBackground() {
        int colorResId;
        switch (this) {
            case SECRET_MAIN:
                colorResId = R.color.colorSecret2;
                break;
            case PERSIS_MAIN:
            case EVMOS_MAIN:
                colorResId = R.color.colorBlack;
                break;
            case SENTINEL_MAIN:
                colorResId = R.color.colorSentinel3;
                break;
            case CRYPTO_MAIN:
                colorResId = R.color.colorCryto2;
                break;
            case MEDI_MAIN:
                colorResId = R.color.colorWhite;
                break;
            case COMDEX_MAIN:
                colorResId = R.color.colorTransBgComdex;
                break;
            case KONSTELL_MAIN:
                colorResId = R.color.colorKonstellation3;
                break;
            default:
                colorResId = getDenomColor();
        }
        return colorResId;
    }

    public static BaseChain getChain(String chainName) {
        for (BaseChain chain : BaseChain.values()) {
            if (chain.hasChainName(chainName)) {
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

    public boolean hasChainName(String chain) {
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
