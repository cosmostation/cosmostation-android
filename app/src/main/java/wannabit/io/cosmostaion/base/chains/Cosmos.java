package wannabit.io.cosmostaion.base.chains;

import wannabit.io.cosmostaion.R;

public class Cosmos extends ChainConfig {

    public int chainImg() { return R.drawable.chain_cosmos; }
    public int chainInfoImg() { return R.drawable.infoicon_cosmos; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_cosmos; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_cosmos; }
    public int chainColor() { return R.color.color_cosmos; }
    public int chainBgColor() { return R.color.colorTransBgCosmos; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_cosmos; }
    public String chainName() { return "cosmos"; }

    public int mainDenomImg() { return R.drawable.token_cosmos; }
    public String mainDenom() { return "uatom"; }
    public String mainSymbol() { return "ATOM"; }

    public String addressPrefix() { return "cosmos"; }
    public String addressHdPath0() { return "m/44'/118'/0'/0/X"; }

    public boolean pushSupport() { return true; }
}
