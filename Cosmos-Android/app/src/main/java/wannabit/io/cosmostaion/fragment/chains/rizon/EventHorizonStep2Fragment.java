package wannabit.io.cosmostaion.fragment.chains.rizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.rizon.EventHorizonActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class EventHorizonStep2Fragment extends BaseFragment implements View.OnClickListener{

    private boolean                         mIsGen;
    private TextView                        mRizonToAddress;

    private RelativeLayout                  mBtnDone;

    public static EventHorizonStep2Fragment newInstance(Bundle bundle) {
        EventHorizonStep2Fragment fragment = new EventHorizonStep2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_horizon_step2, container, false);
        mIsGen                      = true;
        mRizonToAddress             = rootView.findViewById(R.id.rizon_to_address);
        mBtnDone                    = rootView.findViewById(R.id.btn_done);

        mRizonToAddress.setText(getSActivity().mAccount.address);

        mBtnDone.setOnClickListener(this);
        return rootView;
    }


    private EventHorizonActivity getSActivity() { return (EventHorizonActivity)getBaseActivity(); }

    @Override
    public void onRefreshTab() {

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDone)) {
            if (!mIsGen) {
                getSActivity().onBackPressed();
            } else {
                getSActivity().onStartMainActivity(0);
            }
            return;
        }
    }
}
