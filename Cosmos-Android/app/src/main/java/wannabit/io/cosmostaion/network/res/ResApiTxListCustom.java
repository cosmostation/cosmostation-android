package wannabit.io.cosmostaion.network.res;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.WLog;

public class ResApiTxListCustom {

    @SerializedName("id")
    public int id;

    @SerializedName("chain_id")
    public String chain_id;

    @SerializedName("height")
    public int height;

    @SerializedName("code")
    public int code;

    @SerializedName("tx_hash")
    public String tx_hash;

    @SerializedName("messages")
    public String messages;

    @SerializedName("timestamp")
    public String timestamp;


    public JSONArray getMsgs() {
        try {
            return new JSONArray(messages);
        } catch (JSONException err){ WLog.e("getMsgs Error" + err.toString()); }
        return null;
    }

    public int getMsgCnt() {
        if (getMsgs() != null) {
            return getMsgs().length();
        }
        return 0;
    }

    public boolean isSuccess() {
        if (code > 0) {
            return false;
        }
        return true;
    }

    public String getMsgType(Context c, String address) {
        if (getMsgCnt() == 0) {
            return "Known";

        } else {
            try {
                String result = "";
                if (getMsgCnt() == 2) {
                    String msgType0 = "";
                    String msgType1 = "";
                    try {
                        msgType0 = getMsgs().getJSONObject(0).getString("@type");
                    } catch (Exception e) { }
                    try {
                        msgType0 = getMsgs().getJSONObject(0).getString("type");
                    } catch (Exception e) { }
                    try {
                        msgType1 = getMsgs().getJSONObject(1).getString("@type");
                    } catch (Exception e) { }
                    try {
                        msgType1 = getMsgs().getJSONObject(1).getString("type");
                    } catch (Exception e) { }

                    if (msgType0.contains("MsgWithdrawDelegatorReward") && msgType1.contains("MsgDelegate")) {
                        return c.getString(R.string.tx_reinvest);
                    }
                }

                String msgType = "";
                try {
                    msgType = getMsgs().getJSONObject(0).getString("@type");
                } catch (Exception e) { }
                try {
                    msgType = getMsgs().getJSONObject(0).getString("type");
                } catch (Exception e) { }

                if (msgType.contains("MsgDelegate")) {
                    result = c.getString(R.string.tx_delegate);
                } else if (msgType.contains("MsgUndelegate")) {
                    result = c.getString(R.string.tx_undelegate);

                } else if (msgType.contains("MsgWithdrawDelegatorReward") || msgType.contains("MsgWithdrawDelegationReward")) {
                    result = c.getString(R.string.tx_get_reward);

                } else if (msgType.contains("MsgSend")) {
                    try {
                        if (getMsgs().getJSONObject(0).getString("to_address").equals(address)) {
                            result = c.getString(R.string.tx_receive);
                        } else if (getMsgs().getJSONObject(0).getString("from_address").equals(address)) {
                            result = c.getString(R.string.tx_send);
                        } else {
                            result = c.getString(R.string.tx_transfer);
                        }

                    } catch (Exception e) {
                        result = c.getString(R.string.tx_transfer);
                    }

                } else if (msgType.contains("MsgMultiSend")) {
                    result = c.getString(R.string.tx_transfer);

                } else if (msgType.contains("MsgBeginRedelegate")) {
                    result = c.getString(R.string.tx_redelegate);

                } else if (msgType.contains("MsgSetWithdrawAddress") || msgType.contains("MsgModifyWithdrawAddress")) {
                    result = c.getString(R.string.tx_change_reward_address);

                } else if (msgType.contains("MsgCreateValidator")) {
                    result = c.getString(R.string.tx_create_validator);

                } else if (msgType.contains("MsgEditValidator")) {
                    result = c.getString(R.string.tx_edit_validator);

                } else if (msgType.contains("MsgUnjail")) {
                    result = c.getString(R.string.tx_unjail);

                } else if (msgType.contains("MsgSubmitProposal")) {
                    result = c.getString(R.string.tx_submit_proposal);

                } else if (msgType.contains("MsgVote")) {
                    result = c.getString(R.string.tx_vote);

                } else if (msgType.contains("MsgDeposit")) {
                    result = c.getString(R.string.tx_deposit);

                } else if (msgType.contains("MsgWithdrawValidatorCommission")) {
                    result = c.getString(R.string.tx_get_commission);

                } else if (msgType.contains("MsgCreateBid")) {
                    result = c.getString(R.string.tx_create_bid);

                } else if (msgType.contains("MsgCreateLease")) {
                    result = c.getString(R.string.tx_create_lease);

                } else if (msgType.contains("MsgWithdrawLease")) {
                    result = c.getString(R.string.tx_withdraw_lease);

                } else if (msgType.contains("MsgCreateDeployment")) {
                    result = c.getString(R.string.tx_create_deployment);

                } else if (msgType.contains("MsgUpdateDeployment")) {
                    result = c.getString(R.string.tx_update_deployment);

                } else if (msgType.contains("MsgCloseDeployment")) {
                    result = c.getString(R.string.tx_close_deployment);

                } else if (msgType.contains("MsgCreateCertificate")) {
                    result = c.getString(R.string.tx_create_certificate);

                } else if (msgType.contains("MsgRevokeCertificate")) {
                    result = c.getString(R.string.tx_revoke_certificate);

                } else if (msgType.contains("MsgUpdateClient")) {
                    result = c.getString(R.string.tx_ibc_update_client);

                } else if (msgType.contains("MsgTransfer")) {
                    result = c.getString(R.string.tx_ibc_transfer);

                }

                else if (msgType.contains("MsgMintNFT")) {
                    result = "NFT Mint";

                } else if (msgType.contains("MsgTransferNFT")) {
                    result = "NFT Transfer";

                } else if (msgType.contains("MsgEditNFT")) {
                    result = "NFT Edit";

                } else if (msgType.contains("MsgIssueDenom")) {
                    result = "NFT Issue";

                } else if (msgType.contains("MsgRequestRandom")) {
                    result = "Random Request";
                }

                if (getMsgCnt() > 1) {
                    result = result +  "\n+ " + (getMsgCnt() - 1);
                }
                return result;
            } catch (Exception e) {
                WLog.w("Exception " + e.getMessage());
            }
            return "Known";
        }
    }

}
