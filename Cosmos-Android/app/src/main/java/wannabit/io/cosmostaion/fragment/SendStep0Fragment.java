package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class SendStep0Fragment extends BaseFragment implements View.OnClickListener {

    private EditText    mAddressInput;
    private ImageView   mQrBtn;
    private Button      mNextBtn;

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
        mQrBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mNextBtn)) {
//            String targetAddress = mAddressInput.getText().toString();
//            if(!TextUtils.isEmpty(targetAddress) && WKey.isValidBech32(targetAddress)) {
//                getSActivity().mTagetAddress = targetAddress;
//                getSActivity().onNextStep();
//
//            } else if(getSActivity().mAccount.address.equals(targetAddress)) {
//                Toast.makeText(getContext(), R.string.error_self_sending, Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getContext(), R.string.error_invalid_address, Toast.LENGTH_SHORT).show();
//            }

            //TODO TEST
            getSActivity().mTagetAddress = "cosmos17j405ekvw5cmfvc0g4e7urfutg08fu7jsy6gj4";
            getSActivity().onNextStep();

        } else if (v.equals(mQrBtn)) {

        }
    }

    private SendActivity getSActivity() {
        return (SendActivity)getBaseActivity();
    }
}