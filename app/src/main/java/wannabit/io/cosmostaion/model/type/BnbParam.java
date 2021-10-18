package wannabit.io.cosmostaion.model.type;

import android.text.TextUtils;

import com.binance.dex.api.client.encoding.message.InputOutput;

import java.util.ArrayList;
import java.util.List;

public class BnbParam {
    public static final int TYPE_NOT_SUPPORT = 0;
    public static final int TYPE_NEW_ORDER = 1;
    public static final int TYPE_CANCEL_ORDER = 2;
    public static final int TYPE_TRANSFER_ORDER = 3;

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
        public List<InputOutput> inputs;
        public List<InputOutput> outputs;
    }

    public int getMsgType() {
        if (msgs == null && msgs.size() == 0) return TYPE_NOT_SUPPORT;

        if (!TextUtils.isEmpty(msgs.get(0).id)) {
            return TYPE_NEW_ORDER;

        } else if (!TextUtils.isEmpty(msgs.get(0).refid)) {
            return TYPE_CANCEL_ORDER;

        } else if (msgs.get(0).inputs != null) {
            return TYPE_TRANSFER_ORDER;

        }
        return TYPE_NOT_SUPPORT;
    }
}
