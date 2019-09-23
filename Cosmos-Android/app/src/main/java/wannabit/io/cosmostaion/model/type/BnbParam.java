package wannabit.io.cosmostaion.model.type;

import java.util.ArrayList;

public class BnbParam {
    public long account_number;
    public String chain_id;
    public long sequence;
    public long source;
    public ArrayList<BnbMsg> msgs;

    public class BnbMsg {
        public String id;
        public String refid;
        public long ordertype;
        public long price;
        public long quantity;
        public String sender;
        public long side;
        public String symbol;
        public long timeinforce;
    }
}
