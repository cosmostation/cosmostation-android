package wannabit.io.cosmostaion.dao;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;

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
    public Long     importTime;

    public String   lastTotal;
    public Long     sortOrder;
    public Boolean  pushAlarm;
    public Boolean  newBip44;
    public Integer  fetchNewBip;

    public ArrayList<Balance>   balances;


    public static Account getNewInstance() {
        Account result = new Account();
        result.uuid = UUID.randomUUID().toString();
        return result;
    }

    public Account() {
    }

    public Account(Long id, String uuid, String nickName, boolean isFavo, String address,
                   String baseChain, boolean hasPrivateKey, String resource, String spec,
                   boolean fromMnemonic, String path, boolean isValidator, int sequenceNumber,
                   int accountNumber, Long fetchTime, int msize, long importTime, String lastTotal, long sortOrder, boolean pushAlarm, boolean newBip, int fetchNewBip) {
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
        this.importTime = importTime;
        this.lastTotal = lastTotal;
        this.sortOrder = sortOrder;
        this.pushAlarm = pushAlarm;
        this.newBip44 = newBip;
        this.fetchNewBip = fetchNewBip;
    }

    public ArrayList<Balance> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<Balance> balances) {
        this.balances = balances;
    }

    public BigDecimal getBnbBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.TOKEN_BNB)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getBnbBalanceScale() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.TOKEN_BNB)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getBnbTokenBalance(String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(symbol)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getKavaBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.TOKEN_KAVA)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getKavaDelegable() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.TOKEN_KAVA)) {
                result = balance.balance.add(balance.locked);
                break;
            }
        }
        return result;
    }

    public BigDecimal getTokenBalance(String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equalsIgnoreCase(symbol)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getTokenDelegable(String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if (balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equalsIgnoreCase(symbol)) {
                result = balance.balance.add(balance.locked);
                break;
            }
        }
        return result;
    }

    public BigDecimal getIovBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(TOKEN_IOV) || balance.symbol.equals(TOKEN_IOV_TEST)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getBandBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if(balances == null || balances.size() == 0)  {
            return result;
        }
        for(Balance balance:balances) {
            if(balance.symbol.equals(BaseConstant.TOKEN_BAND)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }


    public SpannableString getLastTotal(Context c, BaseChain chain) {
        if (TextUtils.isEmpty(lastTotal)) {
            return SpannableString.valueOf("--");
        }
        try {
            if (chain.equals(BaseChain.BNB_MAIN) || chain.equals(BaseChain.BNB_TEST)) {
                return WDp.getDpAmount2(c, new BigDecimal(lastTotal), 0, 6);

            } else if (chain.equals(BaseChain.OKEX_MAIN) || chain.equals(BaseChain.OK_TEST)) {
                return WDp.getDpAmount2(c, new BigDecimal(lastTotal), 0, 6);

            } else if (chain.equals(FETCHAI_MAIN) || chain.equals(SIF_MAIN)) {
                return WDp.getDpAmount2(c, new BigDecimal(lastTotal), 18, 6);

            } else if (chain.equals(CRYPTO_MAIN)) {
                return WDp.getDpAmount2(c, new BigDecimal(lastTotal), 8, 6);

            } else {
                return WDp.getDpAmount2(c, new BigDecimal(lastTotal), 6, 6);
            }

        } catch (Exception e) {
            return SpannableString.valueOf("--");
        }

    }
}
