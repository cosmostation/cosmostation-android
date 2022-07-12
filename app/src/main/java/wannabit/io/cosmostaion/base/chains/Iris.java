package wannabit.io.cosmostaion.base.chains;

import wannabit.io.cosmostaion.R;

public class Iris extends ChainConfig {

    public int chainImg() { return R.drawable.chain_iris; }
    public int chainInfoImg() { return R.drawable.infoicon_iris; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_iris; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_iris; }
    public int chainColor() { return R.color.color_iris; }
    public int chainBgColor() { return R.color.colorTransBgIris; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_iris; }
    public String chainName() { return "iris"; }

    public int mainDenomImg() { return R.drawable.token_iris; }
    public String mainDenom() { return "uiris"; }
    public String mainSymbol() { return "IRIS"; }

    public String addressPrefix() { return "iaa"; }
    public String addressHdPath0() { return "m/44'/118'/0'/0/X"; }

    public boolean pushSupport() { return false; }
}
