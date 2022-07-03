package wannabit.io.cosmostaion.dao;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;

public class Derive {
    public BaseChain baseChain;
    public int hdpathtype;
    public int path;
    public String fullPath;
    public String dpAddress;
    public int status = -1; // 0 == ready, 1 == overide, 2 == already imported
    public Coin coin;
    public boolean selected = false;

    public Derive(BaseChain baseChain, int hdpathtype, int path, String fullPath, String dpAddress, int status) {
        this.baseChain = baseChain;
        this.hdpathtype = hdpathtype;
        this.path = path;
        this.fullPath = fullPath;
        this.dpAddress = dpAddress;
        this.status = status;
    }
}
