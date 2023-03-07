package wannabit.io.cosmostaion.dao;

public class NameService {
    public NameServiceType type;
    public String name;
    public String address;

    public NameService(NameServiceType type, String name, String address) {
        this.type = type;
        this.name = name;
        this.address = address;
    }

    public enum NameServiceType{
        STARNAME, ICNS, STARGAZE, ICNS_STARGAZE;
    }
}
