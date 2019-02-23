package wannabit.io.cosmostaion.dao;

import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import wannabit.io.cosmostaion.base.BaseConstant;

public class Account {
    public Long     id;
    public String   uuid;
    public String   nickName;
    public Boolean  isFavo;
    public String   address;
    public String   baseChain;

    public Boolean  hasPrivateKey;
    public String   resource;
    public String   spec;
    public Boolean  fromMnemonic;
    public String   path;

    public Boolean  isValidator;
    public Integer  sequenceNumber;
    public Integer  accountNumber;
    public Long     fetchTime;
    public int      msize;

    public ArrayList<Balance>   balances;
//    public DeterministicKey     deterministicKey;


    public static Account getNewInstance() {
        Account result = new Account();
        result.uuid = UUID.randomUUID().toString();
        return result;
    }

    public Account() {
    }

    public Account(Long id, String uuid, String nickName, boolean isFavo, String address, String baseChain, boolean hasPrivateKey, String resource, String spec, boolean fromMnemonic, String path, boolean isValidator, int sequenceNumber, int accountNumber, Long fetchTime, int msize) {
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
        this.msize = msize;
    }

    public ArrayList<Balance> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<Balance> balances) {
        this.balances = balances;
    }


    public BigDecimal getAtomBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_ATOM)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getPhotonBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0) {
            return  result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.COSMOS_PHOTON)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

//    public DeterministicKey getDeterministicKey() {
//        return deterministicKey;
//    }
//
//    public void setDeterministicKey(DeterministicKey deterministicKey) {
//        this.deterministicKey = deterministicKey;
//    }
}
