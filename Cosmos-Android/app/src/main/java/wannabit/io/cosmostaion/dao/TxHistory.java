package wannabit.io.cosmostaion.dao;

import java.util.ArrayList;

public class TxHistory {
    public Long                     id;
    public Long                     accountId;
    public String                   txHash;
    public int                      blockHeight;
    public ArrayList<MsgHistory>    msgHistories;
    public String                   dpType;
    public String                   dpMsg;
    public Long                     txTime;
}
