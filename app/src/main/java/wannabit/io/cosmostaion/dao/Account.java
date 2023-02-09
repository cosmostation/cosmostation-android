package wannabit.io.cosmostaion.dao;

import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;

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
    public Integer  customPath;
    public Long     mnemonicId;

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
                   int accountNumber, Long fetchTime, int msize, long importTime, String lastTotal,
                   long sortOrder, boolean pushAlarm, boolean newBip, int customPath, Long mnemonicId) {
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
        this.customPath = customPath;
        this.mnemonicId = mnemonicId;
    }

    public ArrayList<Balance> getBalances() {
        return balances;
    }

    public void setBalances(ArrayList<Balance> balances) {
        this.balances = balances;
    }

    public BigDecimal getTokenBalance(String symbol) {
        BigDecimal result = BigDecimal.ZERO;
        if (balances == null || balances.size() == 0)  {
            return result;
        }
        for (Balance balance:balances) {
            if(balance.symbol.equalsIgnoreCase(symbol)) {
                result = balance.balance;
                break;
            }
        }
        return result;
    }

    public SpannableString getLastTotal(BaseChain chain) {
        if (TextUtils.isEmpty(lastTotal)) {
            return SpannableString.valueOf(WDp.getDpAmount2(BigDecimal.ZERO, 6, 6));
        }
        try {
            if (isGRPC(chain)) {
                ChainConfig chainConfig = ChainFactory.getChain(chain);
                return WDp.getDpAmount2(new BigDecimal(lastTotal), chainConfig.decimal(), 6);
            } else {
                return WDp.getDpAmount2(new BigDecimal(lastTotal), 0, 6);
            }

        } catch (Exception e) {
            return SpannableString.valueOf("--");
        }

    }

    public String getName(Context c) {
        if (TextUtils.isEmpty(nickName)) {
            return c.getString(R.string.str_my_wallet) + id;
        }
        return nickName;
    }
}
