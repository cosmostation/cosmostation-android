package wannabit.io.cosmostaion.fragment.txs.kava;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_Htlc_Receivable_Accounts;
import wannabit.io.cosmostaion.utils.WDp;

public class HtlcSendStep1Fragment extends BaseFragment implements View.OnClickListener {
    public final static int SELECT_ACCOUNT = 9101;

    private Button mBeforeBtn, mNextBtn;
    private RelativeLayout mReceiverBtn;
    private ImageView mKeyStatusImg;
    private TextView mRecipientAddressTv;
    private TextView mWarnMSg;

    private ArrayList<Account> mToAccountList;
    private Account mToAccount;

    public static HtlcSendStep1Fragment newInstance(Bundle bundle) {
        HtlcSendStep1Fragment fragment = new HtlcSendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_send_step1, container, false);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mReceiverBtn = rootView.findViewById(R.id.receive_layer);
        mKeyStatusImg = rootView.findViewById(R.id.key_status);
        mRecipientAddressTv = rootView.findViewById(R.id.key_address);
        mWarnMSg = rootView.findViewById(R.id.warn_msg);

        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mReceiverBtn.setOnClickListener(this);

        return rootView;
    }

    private void onUpdateView() {
        if (mToAccount == null) {
            getSActivity().onBeforeStep();
        }
        mKeyStatusImg.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            mKeyStatusImg.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_bnb), android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            mKeyStatusImg.setColorFilter(ContextCompat.getColor(getContext(), R.color.color_kava), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mKeyStatusImg.setVisibility(View.VISIBLE);
        mRecipientAddressTv.setText(mToAccount.address);

    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        mToAccountList = getSActivity().getBaseDao().onSelectAccountsByHtlcClaim(getSActivity().mRecipientChain);
        if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            mWarnMSg.setText(String.format(getString(R.string.error_can_not_bep3_account_msg), WDp.getDpChainName(getContext(), getSActivity().mRecipientChain)));

        } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            mWarnMSg.setText(String.format(getString(R.string.error_can_not_bep3_account_msg2), WDp.getDpChainName(getContext(), getSActivity().mRecipientChain)));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (mToAccount == null) {
                Toast.makeText(getContext(), R.string.error_select_account_to_recipient, Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().mRecipientAccount = mToAccount;
            getSActivity().onNextStep();

        } else if (v.equals(mReceiverBtn)) {
            if (mToAccountList.size() > 0) {
                Bundle bundle = new Bundle();
                bundle.putString("chainName", getSActivity().mRecipientChain.getChain());
                Dialog_Htlc_Receivable_Accounts dialog = Dialog_Htlc_Receivable_Accounts.newInstance(bundle);
                dialog.setCancelable(true);
                dialog.setTargetFragment(this, SELECT_ACCOUNT);
                getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

            } else {
                String title = String.format(getString(R.string.error_can_not_bep3_account_title), WDp.getDpChainName(getContext(), getSActivity().mRecipientChain));
                String msg = "";
                if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN)) {
                    msg = String.format(getString(R.string.error_can_not_bep3_account_msg), WDp.getDpChainName(getContext(), getSActivity().mRecipientChain));
                } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
                    msg = String.format(getString(R.string.error_can_not_bep3_account_msg2), WDp.getDpChainName(getContext(), getSActivity().mRecipientChain));
                }
                AlertDialogUtils.showSingleButtonDialog(getSActivity(), title, msg, getContext().getString(R.string.str_ok), null);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_ACCOUNT && resultCode == Activity.RESULT_OK) {
            mToAccount = mToAccountList.get(data.getIntExtra("position", 0));
            onUpdateView();
        }
    }

    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity) getBaseActivity();
    }


}
