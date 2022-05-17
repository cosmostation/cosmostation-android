package wannabit.io.cosmostaion.fragment.chains.desmos;

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

import java.math.BigDecimal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.desmos.LinkAccountActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.DialogFragment_LinkAccounts;
import wannabit.io.cosmostaion.dialog.DialogFragment_LinkChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResAirdropClaimCheck;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class LinkAccountStep0Fragment extends BaseFragment implements View.OnClickListener {

    public final static int             SELECT_POPUP_LINK_CHAIN              = 1000;
    public final static int             SELECT_POPUP_LINK_ACCOUNT            = 2000;

    private Button          mCancelBtn, mNextBtn;

    private RelativeLayout  mBtnLinkChain, mBtnLinkAccount;
    private ImageView       mLinkChain;
    private TextView        mLinkChainTxt;
    private TextView        mLinkAccountName, mLinkAccountAddress;
    private TextView        mAirdropAmount;

    private BaseChain       mSelectedChain;
    private Account         mSelectedAccount;

    private ResAirdropClaimCheck mCheckClaim;

    public static LinkAccountStep0Fragment newInstance(Bundle bundle) {
        LinkAccountStep0Fragment fragment = new LinkAccountStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_link_account_step0, container, false);
        mCancelBtn          = rootView.findViewById(R.id.btn_cancel);
        mNextBtn            = rootView.findViewById(R.id.btn_next);

        mBtnLinkChain       = rootView.findViewById(R.id.btn_link_chain);
        mLinkChain          = rootView.findViewById(R.id.img_link_chain);
        mLinkChainTxt       = rootView.findViewById(R.id.txt_link_chain);

        mBtnLinkAccount     = rootView.findViewById(R.id.btn_link_account);
        mLinkAccountName    = rootView.findViewById(R.id.wallet_name);
        mLinkAccountAddress = rootView.findViewById(R.id.wallet_address);

        mAirdropAmount      = rootView.findViewById(R.id.airdrop_amount);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mBtnLinkChain.setOnClickListener(this);
        mBtnLinkAccount.setOnClickListener(this);

        mSelectedChain = WUtil.getDesmosAirDropChains().get(0);
        return rootView;
    }

    public void onUpdateView() {
        WDp.getChainImg(getSActivity(), mSelectedChain, mLinkChain);
        WDp.getChainTitle2(getSActivity(), mSelectedChain, mLinkChainTxt);

        if ((mSelectedAccount != null)) {
            mLinkAccountName.setText(WUtil.getWalletName(getContext(), mSelectedAccount));
            mLinkAccountAddress.setText(mSelectedAccount.address);
            ApiClient.getAirDrop(getContext()).getClaimable(mSelectedAccount.address).enqueue(new Callback<ResAirdropClaimCheck>() {
                @Override
                public void onResponse(Call<ResAirdropClaimCheck> call, Response<ResAirdropClaimCheck> response) {
                    if (response.isSuccessful()) {
                        mCheckClaim = new ResAirdropClaimCheck(response.body());
                        mAirdropAmount.setText(mCheckClaim.getUnclaimedAirdropAmount().toPlainString());
                    }
                }

                @Override
                public void onFailure(Call<ResAirdropClaimCheck> call, Throwable t) {
                    WLog.w("error : " + t.getMessage());
                }
            });

        } else {
            mLinkAccountName.setText("");
            mLinkAccountAddress.setText("");
            mAirdropAmount.setText("0");
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            if (mSelectedAccount == null) {
                Toast.makeText(getSActivity(), R.string.error_select_account_link, Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().mDesmosToLinkChain = mSelectedChain;
            getSActivity().mDesmosToLinkAccountId = mSelectedAccount.id;
            getSActivity().mDesmosAirDropAmount = new BigDecimal(mAirdropAmount.getText().toString().trim());
            getSActivity().onNextStep();

        } else if (v.equals(mBtnLinkChain)) {
            DialogFragment_LinkChain dialog = DialogFragment_LinkChain.newInstance(null);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_POPUP_LINK_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnLinkAccount)) {
            if (getBaseDao().onSelectAllAccountsByChainWithKey(mSelectedChain).size() <= 0) {
                Toast.makeText(getSActivity(), R.string.error_no_account_this_chain, Toast.LENGTH_SHORT).show();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("chainName", mSelectedChain.getChain());
            DialogFragment_LinkAccounts dialog = DialogFragment_LinkAccounts.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_POPUP_LINK_ACCOUNT);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_POPUP_LINK_CHAIN && resultCode == Activity.RESULT_OK) {
            mSelectedChain = WUtil.getDesmosAirDropChains().get(data.getIntExtra("position", 0));
            mSelectedAccount = null;
            onUpdateView();

        } else if (requestCode == SELECT_POPUP_LINK_ACCOUNT && resultCode == Activity.RESULT_OK) {
            mSelectedAccount = getBaseDao().onSelectAllAccountsByChainWithKey(mSelectedChain).get(data.getIntExtra("position", 0));
            onUpdateView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private LinkAccountActivity getSActivity() {
        return (LinkAccountActivity)getBaseActivity();
    }

}
