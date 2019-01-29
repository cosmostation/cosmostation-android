package wannabit.io.cosmostaion.dao;

import java.util.UUID;

public class Mnemonic {
    public Long id;
    public String uuid;
    public String resource;
    public String spec;
    public String dpMasterKey;

    public static Mnemonic getNewInstance() {
        Mnemonic result = new Mnemonic();
        result.uuid = UUID.randomUUID().toString();
        return result;
    }

    public Mnemonic() {
    }

    public Mnemonic(Long id, String uuid, String resource, String spec, String dpMasterKey) {
        this.id = id;
        this.uuid = uuid;
        this.resource = resource;
        this.spec = spec;
        this.dpMasterKey = dpMasterKey;
    }
}
