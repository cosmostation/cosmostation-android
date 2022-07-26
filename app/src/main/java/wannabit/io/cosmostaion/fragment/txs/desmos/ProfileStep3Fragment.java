package wannabit.io.cosmostaion.fragment.txs.desmos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.desmos.ProfileActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class ProfileStep3Fragment extends BaseFragment implements View.OnClickListener {

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mProfileDtag;
    private TextView        mProfileNick;
    private TextView        mProfileBio;
    private TextView        mProfileUri;
    private TextView        mProfileCoverUri;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static ProfileStep3Fragment newInstance() {
        return new ProfileStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_profile_step3, container, false);
        mFeeAmount              = rootView.findViewById(R.id.tx_fee_amount);
        mFeeAmountSymbol        = rootView.findViewById(R.id.tx_fee_symbol);
        mProfileDtag            = rootView.findViewById(R.id.profile_dtag);
        mProfileNick            = rootView.findViewById(R.id.profile_nickname);
        mProfileBio             = rootView.findViewById(R.id.profile_bio);
        mProfileUri             = rootView.findViewById(R.id.profile_uri);
        mProfileCoverUri        = rootView.findViewById(R.id.profile_cover_uri);
        mMemo                   = rootView.findViewById(R.id.memo);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);

        mProfileDtag.setText(getSActivity().mDtag);
        mProfileNick.setText(getSActivity().mNickname);
        mProfileBio.setText(getSActivity().mBio);
        if (getSActivity().mProfileImg != null) {
            mProfileUri.setText("https://ipfs.infura.io/ipfs/" + getSActivity().mProfileImg);
        } else {
            mProfileUri.setText("");
        }
        if (getSActivity().mCoverImg != null) {
            mProfileCoverUri.setText("https://ipfs.infura.io/ipfs/" + getSActivity().mCoverImg);
        } else {
            mProfileCoverUri.setText("");
        }
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onSaveProfile();
        }
    }

    private ProfileActivity getSActivity() {
        return (ProfileActivity)getBaseActivity();
    }
}
