package wannabit.io.cosmostaion.dao;

public class Password {
    public String resource;
    public String spec;

    public Password() {
    }

    public Password(String resource) {
        this.resource = resource;
    }

    public Password(String resource, String spec) {
        this.resource = resource;
        this.spec = spec;
    }
}
