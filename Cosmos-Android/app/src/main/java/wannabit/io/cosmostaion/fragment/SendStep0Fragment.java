package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class SendStep0Fragment extends BaseFragment implements View.OnClickListener {

    private EditText    mAddressInput;
    private ImageView   mQrBtn;
    private Button      mCancel, mNextBtn;
    private Button      mTest;

    public static SendStep0Fragment newInstance(Bundle bundle) {
        SendStep0Fragment fragment = new SendStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step0, container, false);
        mAddressInput = rootView.findViewById(R.id.receiver_account);
        mQrBtn = rootView.findViewById(R.id.receiver_camera);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mCancel = rootView.findViewById(R.id.btn_cancel);
        mTest = rootView.findViewById(R.id.btn_test);
        mQrBtn.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mTest.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
            String targetAddress = mAddressInput.getText().toString();
            if(getSActivity().mAccount.address.equals(targetAddress)) {
                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
                return;

            } else if(!TextUtils.isEmpty(targetAddress) && WKey.isValidBech32(targetAddress)) {
                getSActivity().mTagetAddress = targetAddress;
                getSActivity().onNextStep();
                return;
            }else {
                Toast.makeText(getContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
                return;
            }

        } else if (v.equals(mQrBtn)) {

        } else if (v.equals(mTest)) {
            if(!getSActivity().mAccount.address.equals("cosmos1pzllggpn22094j3mwq79u4ql63cwac6enqkjck"))
                mAddressInput.setText("cosmos1pzllggpn22094j3mwq79u4ql63cwac6enqkjck");
            else
                mAddressInput.setText("cosmos12tzwn2ej48g9rvmhm3t6ryfjgrkf0a0hlday2z");

        } else if (v.equals(mCancel)) {
            getSActivity().onBeforeStep();
        }
    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }
}