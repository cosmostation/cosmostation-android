package wannabit.io.cosmostaion.base.chains;

import wannabit.io.cosmostaion.R;

public class ChainConfig {

    public int chainImg() { return R.drawable.chain_default; }
    public int chainInfoImg() { return 0; }
    public int chainInfoTitle() { return 0; }
    public int chainInfoMsg() { return 0; }
    public int chainColor() { return 0; }
    public int chainBgColor() { return R.color.colorTransBg; }
    public int chainTabColor() { return 0; }
    public String chainName() { return "unknown"; }
    public String chainTitle() { return "(" + chainName().substring(0, 1).toUpperCase() + chainName().substring(1) + ")"; }
    public String chainTitleToUp() { return chainName().toUpperCase(); }

    public int mainDenomImg() { return 0; }
    public String mainDenom() { return ""; }
    public String mainSymbol() { return ""; }

    public String addressPrefix() { return ""; }
    public String addressHdPath0() { return ""; }

    public boolean pushSupport() { return false; }
}
