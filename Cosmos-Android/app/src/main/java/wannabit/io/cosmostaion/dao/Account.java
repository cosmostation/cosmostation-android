package wannabit.io.cosmostaion.dao;

import org.bitcoinj.crypto.DeterministicKey;

import java.util.ArrayList;
import java.util.UUID;

public class Account {
    public Long     id;
    public String   uuid;
    public String   nickName;
    public boolean  isFavo;
    public String   address;
    public String   baseChain;

    public boolean  hasPrivateKey;
    public String   resource;
    public String   spec;
    public boolean  fromMnemonic;
    public String   path;

    public boolean  isValidator;
    public int      sequenceNumber;
    public int      accountNumber;
    public Long     fetchTime;

    public ArrayList<Balance>   balances;
//    public DeterministicKey     deterministicKey;


    public static Account getNewInstance() {
        Account result = new Account();
        result.uuid = UUID.randomUUID().toString();
        return result;
    }

    public Account() {
    }

    public Account(Long id, String uuid, String nickName, boolean isFavo, String address, String baseChain, boolean hasPrivateKey, String resource, String spec, boolean fromMnemonic, String path, boolean isValidator, int sequenceNumber, int accountNumber, Long fetchTime) {
        this.id = id;
        this.uuid = uuid;
        this.nickName = nickName;
        this.isFavo = isFavo;
        this.address = address;
        this.baseChain = baseChain;
        this.hasPrivateKey = hasPrivateKey;
        this.resource = resource;
        this.spec = spec;
        this.fromMnemonic = fromMnemonic;
        this.path = path;
        this.isValidator = isValidator;
        this.sequenceNumber = sequenceNumber;
        this.accountNumber = accountNumber;
        this.fetchTime = fetchTime;
    }

    public ArrayList<Balance> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<Balance> balances) {
        this.balances = balances;
    }

//    public DeterministicKey getDeterministicKey() {
//        return deterministicKey;
//    }
//
//    public void setDeterministicKey(DeterministicKey deterministicKey) {
//        this.deterministicKey = deterministicKey;
//    }
}
