package wannabit.io.cosmostaion.network.res;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.Param;
import wannabit.io.cosmostaion.model.type.Coin;

public class ResProposal {
    @SerializedName("id")
    public int id;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("voting_end_time")
    public String voting_end_time;

    @SerializedName("moniker")
    public String moniker;

    @SerializedName("is_expedited")
    public boolean is_expedited;

    @SerializedName("voteMeta")
    public VoteMeta voteMeta;

    @SerializedName("messages")
    public ArrayList<Message> messages;


    public class Message {
        @SerializedName("@type")
        public String type;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("amount")
        public ArrayList<Coin> amount;

        @SerializedName("content")
        public Content content;
    }

    public class Content {
        @SerializedName("@type")
        public String type;

        @SerializedName("title")
        public String title;

        @SerializedName("description")
        public String description;

        @SerializedName("amount")
        public ArrayList<Coin> amount;
    }

    public class VoteMeta {
        @SerializedName("yes_amount")
        public String yes_amount;

        @SerializedName("no_amount")
        public String no_amount;

        @SerializedName("no_with_veto_amount")
        public String no_with_veto_amount;

        @SerializedName("abstain_amount")
        public String abstain_amount;
    }

    public BigDecimal getValidAmount() {
        if (voteMeta != null) {
            return new BigDecimal(voteMeta.yes_amount).
                    add(new BigDecimal(voteMeta.no_amount)).
                    add(new BigDecimal(voteMeta.abstain_amount));
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getSumAmount() {
        if (voteMeta != null) {
            return new BigDecimal(voteMeta.yes_amount).
                    add(new BigDecimal(voteMeta.no_amount)).
                    add(new BigDecimal(voteMeta.abstain_amount)).
                    add(new BigDecimal(voteMeta.no_with_veto_amount));
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getYesAmount() {
        if (voteMeta != null) {
            return new BigDecimal(voteMeta.yes_amount);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal getVetoAmount() {
        if (voteMeta != null) {
            return new BigDecimal(voteMeta.no_with_veto_amount);
        } else {
            return BigDecimal.ZERO;
        }
    }

    public String getVoteStatus(Context c, BaseData baseData, ChainConfig chainConfig, ResProposal resProposal) {
        Param param = baseData.mParam;
        if (param != null) {
            BigDecimal quorum = param.getQuorum(chainConfig, resProposal);
            BigDecimal threshold = param.getThreshold(chainConfig);
            BigDecimal vetoThreshold = param.getVetoThreshold(chainConfig);
            BigDecimal bondedAmount = param.getBondedAmount();

            if (bondedAmount.multiply(quorum).compareTo(getSumAmount()) > 0) {
                return c.getString(R.string.str_vote_reject_by_quorum);
            }
            if (getSumAmount().multiply(vetoThreshold).compareTo(getVetoAmount()) < 0) {
                return c.getString(R.string.str_vote_reject_by_veto);
            }
            if (getValidAmount().multiply(threshold).compareTo(getYesAmount()) > 0) {
                return c.getString(R.string.str_vote_reject_by_no);
            }
            return c.getString(R.string.str_vote_pass_by_yes);
        } else {
            return "";
        }
    }

    public Boolean getVoteStatus(BaseData baseData, ChainConfig chainConfig, ResProposal resProposal) {
        Param param = baseData.mParam;
        if (param != null) {
            BigDecimal quorum = param.getQuorum(chainConfig, resProposal);
            BigDecimal threshold = param.getThreshold(chainConfig);
            BigDecimal vetoThreshold = param.getVetoThreshold(chainConfig);
            BigDecimal bondedAmount = param.getBondedAmount();

            if (bondedAmount.multiply(quorum).compareTo(getSumAmount()) > 0) {
                return false;
            }
            if (getSumAmount().multiply(vetoThreshold).compareTo(getVetoAmount()) < 0) {
                return false;
            }
            if (getValidAmount().multiply(threshold).compareTo(getYesAmount()) > 0) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    public String getProposalType(int position) {
        if (messages.size() > 0) {
            Message message = messages.get(position);
            if (message.type.contains("MsgExecLegacyContent") && message.content != null) {
                return message.content.type;
            } else {
                return message.type;
            }
        }
        return "";
    }

    public String getProposalTitle(int position) {
        if (messages.size() > 0) {
            Message message = messages.get(position);
            if (message.type.contains("MsgExecLegacyContent") && message.content != null) {
                return message.content.title;
            } else {
                return message.title;
            }
        }
        return "";
    }

    public String getProposalDescription(int position) {
        if (messages.size() > 0) {
            Message message = messages.get(position);
            if (message.type.contains("MsgExecLegacyContent") && message.content != null) {
                return message.content.description;
            } else {
                return message.description;
            }
        }
        return "";
    }

    public Coin getProposalAmount(int position) {
        if (messages.size() > 0) {
            Message message = messages.get(position);
            if (message.type.contains("MsgExecLegacyContent") && message.content != null && message.content.amount != null) {
                return message.content.amount.get(0);
            } else if (message.amount != null && !message.amount.isEmpty()) {
                return message.amount.get(0);
            } else {
                return null;
            }
        }
        return null;
    }
}
