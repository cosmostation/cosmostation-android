package wannabit.io.cosmostaion.crypto;

import android.util.Base64;

public class EncResult {
    byte[] encData;
    byte[] ivData;

    public EncResult(byte[] encData, byte[] ivData) {
        this.encData = encData;
        this.ivData = ivData;
    }

    public byte[] getEncData() {
        return encData;
    }

    public void setEncData(byte[] encData) {
        this.encData = encData;
    }

    public byte[] getIvData() {
        return ivData;
    }

    public void setIvData(byte[] ivData) {
        this.ivData = ivData;
    }


    public String getEncDataString() {
        String result = null;
        try {
            if(getEncData() != null) {
                result = Base64.encodeToString(getEncData(), 0);
            }
        } catch (Exception e) {
            result = null;
        } finally {
            return result;
        }
    }

    public String getIvDataString() {
        String result = null;
        try {
            if(getIvData() != null) {
                result = Base64.encodeToString(getIvData(), 0);
            }
        } catch (Exception e) {
            result = null;
        } finally {
            return result;
        }
    }
}
