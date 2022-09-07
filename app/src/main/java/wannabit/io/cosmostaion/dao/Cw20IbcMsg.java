package wannabit.io.cosmostaion.dao;

public class Cw20IbcMsg {
    public String channel;
    public String remote_address;
    public long timeout;

    public Cw20IbcMsg(String channel, String remote_address) {
        this.channel = channel;
        this.remote_address = remote_address;
        this.timeout = 900;
    }
}
