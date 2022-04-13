package wannabit.io.cosmostaion.dao;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WDp;

public class Account {
    public Long id;
    public String uuid;
    public String nickName;
    public Boolean isFavo;
    public String address;
    public String baseChain;

    public Boolean hasPrivateKey;
    public String resource;
    public String spec;
    public Boolean fromMnemonic;
    public String path;

    public Boolean isValidator;
    public Integer sequenceNumber;
    public Integer accountNumber;
    public Long fetchTime;
    public int msize;
    public Long importTime;

    public String lastTotal;
    public Long sortOrder;
    public Boolean newBip44;
    public Integer customPath;

    public ArrayList<Balance> balances;


    public static Account getNewInstance() {
        Account result = new Account();
        result.uuid = UUID.randomUUID().toString();
        return result;
    }

    public Account() {
    }

    public Account(
            String uuid,
            String address,
            String baseChain,
            boolean hasPrivateKey,
            String resource,
            String spec,
            boolean fromMnemonic,
            int msize,
            long importTime,
            int path,
            int customPath
    ) {
        this.uuid = uuid;
        this.address = address;
        this.baseChain = baseChain;
        this.hasPrivateKey = hasPrivateKey;
        this.resource = resource;
        this.spec = spec;
        this.fromMnemonic = fromMnemonic;
        this.msize = msize;
        this.importTime = importTime;
        this.path = String.valueOf(path);
        this.customPath = customPath;
    }

    public Account(
            String uuid,
            String address,
            String baseChain,
            long importTime
    ) {
        this.uuid = uuid;
        this.address = address;
        this.baseChain = baseChain;
        this.hasPrivateKey = false;
        this.fromMnemonic = false;
        this.importTime = importTime;
    }

    public Account(Long id, String uuid, String nickName, boolean isFavo, String address,
                   String baseChain, boolean hasPrivateKey, String resource, String spec,
                   boolean fromMnemonic, String path, boolean isValidator, int sequenceNumber,
                   int accountNumber, Long fetchTime, int msize, long importTime, String lastTotal, long sortOrder, boolean newBip, int customPath) {
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
        this.newBip44 = newBip;
        this.customPath = customPath;
    }

    public String getAccountTitle(Context context) {
        String result;
        if (TextUtils.isEmpty(nickName)) {
            result = context.getString(R.string.str_my_wallet) + id;
        } else {
            result = nickName;
        }
        return result;
    }

    public ArrayList<Balance> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<Balance> balances) {
        this.balances = balances;
    }

    public BigDecimal getBnbBalance() {
        BigDecimal result = BigDecimal.ZERO;
        if (balances == null || balances.size() == 0) {
            return result;
        }
        for (Balance balance : balances) {
            if (balance.symbol.equals(BNB_MAIN.getMainDenom())) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getBnbBalanceScale() {
        BigDecimal result = BigDecimal.ZERO;
        if (balances == null || balances.size() == 0) {
            return result;
        }
        for (Balance balance : balances) {
            if (balance.symbol.equals(BNB_MAIN.getMainDenom())) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getBnbTokenBalance(String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if (balances == null || balances.size() == 0) {
            return result;
        }
        for (Balance balance : balances) {
            if (balance.symbol.equals(symbol)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public BigDecimal getTokenBalance(String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if (balances == null || balances.size() == 0) {
            return result;
        }
        for (Balance balance : balances) {
            if (balance.symbol.equalsIgnoreCase(symbol)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public SpannableString getLastTotal(BaseChain chain) {
        SpannableString result = SpannableString.valueOf("--");
        try {
            if (!TextUtils.isEmpty(lastTotal)) {
                switch (chain) {
                    case BNB_MAIN:
                    case OKEX_MAIN:
                        result = WDp.getDpAmount2(new BigDecimal(lastTotal), 0, 6);
                        break;
                    case FETCHAI_MAIN:
                    case SIF_MAIN:
                    case INJ_MAIN:
                    case EVMOS_MAIN:
                    case CUDOS_MAIN:
                        result = WDp.getDpAmount2(new BigDecimal(lastTotal), 18, 6);
                        break;
                    case CRYPTO_MAIN:
                        result = WDp.getDpAmount2(new BigDecimal(lastTotal), 8, 6);
                        break;
                    case PROVENANCE_MAIN:
                        result = WDp.getDpAmount2(new BigDecimal(lastTotal), 9, 6);
                        break;
                    default:
                        result = WDp.getDpAmount2(new BigDecimal(lastTotal), 6, 6);
                }
            }
        } catch (Exception ignore) {
        }
        return result;
    }
}
