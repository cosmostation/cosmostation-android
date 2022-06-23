package wannabit.io.cosmostaion.dao;

import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class MWords {
    public Long     id;
    public String   uuid;
    public String   resource;
    public String   spec;
    public String   nickName;
    public Integer  wordsCnt;
    public Boolean  isFavo;
    public Long     importTime;

    public static MWords getNewInstance() {
        MWords result = new MWords();
        result.uuid = UUID.randomUUID().toString();
        result.importTime = Calendar.getInstance().getTime().getTime();
        return result;
    }

    public MWords() {
    }

    public MWords(Long id, String uuid, String resource, String spec, String nickName, Integer wordsCnt, Boolean isFavo, Long importTime) {
        this.id = id;
        this.uuid = uuid;
        this.resource = resource;
        this.spec = spec;
        this.nickName = nickName;
        this.wordsCnt = wordsCnt;
        this.isFavo = isFavo;
        this.importTime = importTime;
    }

    public String getWords(Context c) {
        String entropy = CryptoHelper.doDecryptData(c.getString(R.string.key_mnemonic) + uuid, resource, spec);
        if (entropy != null) {
            return String.join(" ", new ArrayList<>(WKey.getRandomMnemonic(WUtil.HexStringToByteArray(entropy)))).trim();
        }
        return "";
    }

    public ArrayList<String> getMnemonicWords(Context c) {
        String entropy = CryptoHelper.doDecryptData(c.getString(R.string.key_mnemonic) + uuid, resource, spec);
        if (entropy != null) {
            return new ArrayList<>(WKey.getRandomMnemonic(WUtil.HexStringToByteArray(entropy)));
        }
        return null;
    }

    public String getName() {
        if (nickName == null) {
            return "Mnemonic " + id;
        }
        return nickName;
    }

    public int getLinkedWalletCnt(BaseData baseData) {
        return baseData.onSelectAccountsByMnemonic(id).size();
    }

    public String getImportDate(Context c) {
        return WDp.getDpTime(c, importTime);
    }
}
