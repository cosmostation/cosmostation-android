package wannabit.io.cosmostaion.fragment.txs.kava;

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

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.HtlcReceivableAccountsDialog;

public class HtlcSendStep1Fragment extends BaseFragment implements View.OnClickListener {

    private Button mBeforeBtn, mNextBtn;
    private RelativeLayout mReceiverBtn;
    private ImageView mKeyStatusImg;
    private TextView mRecipientAddressTv;
    private TextView mWarnMSg;

    private ArrayList<Account> mToAccountList;
    private Account mToAccount;
    private ChainConfig mToChainConfig;

    public static HtlcSendStep1Fragment newInstance() {
        return new HtlcSendStep1Fragment();
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
        mKeyStatusImg.setColorFilter(ContextCompat.getColor(getContext(), mToChainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
        mKeyStatusImg.setVisibility(View.VISIBLE);
        mRecipientAddressTv.setText(mToAccount.address);

    }

    @Override
    public void onRefreshTab() {
        super.onRefreshTab();
        mToChainConfig = ChainFactory.getChain(getSActivity().mRecipientChain);
        mToAccountList = getSActivity().getBaseDao().onSelectAccountsByHtlcClaim(getSActivity().mRecipientChain);
        mWarnMSg.setText(setWarnMsg());
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
            if (mToAccountList.size() > 0 && !getSActivity().isFinishing()) {
                Bundle bundleData = new Bundle();
                bundleData.putString("chainName", getSActivity().mRecipientChain.getChain());
                HtlcReceivableAccountsDialog dialog = HtlcReceivableAccountsDialog.newInstance(bundleData);
                dialog.show(getSActivity().getSupportFragmentManager(), HtlcReceivableAccountsDialog.class.getName());
                getParentFragmentManager().setFragmentResultListener(HtlcReceivableAccountsDialog.HTLC_ACCOUNT_BUNDLE_KEY, this, (((requestKey, bundle) -> {
                    mToAccount = mToAccountList.get(bundle.getInt(BaseConstant.POSITION));
                    onUpdateView();
                })));

            } else {
                String title = String.format(getString(R.string.error_can_not_bep3_account_title), StringUtils.capitalize(mToChainConfig.chainName()));
                CommonAlertDialog.showSingleButton(getSActivity(), title, setWarnMsg(), getContext().getString(R.string.str_ok), null);
            }
        }
    }

    private String setWarnMsg() {
        String msg = "";
        if (getSActivity().mRecipientChain.equals(BaseChain.BNB_MAIN)) {
            msg = String.format(getString(R.string.error_can_not_bep3_account_msg), StringUtils.capitalize(mToChainConfig.chainName()));
        } else if (getSActivity().mRecipientChain.equals(BaseChain.KAVA_MAIN)) {
            msg = String.format(getString(R.string.error_can_not_bep3_account_msg2), StringUtils.capitalize(mToChainConfig.chainName()));
        }
        return msg;
    }

    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity) getBaseActivity();
    }
}
