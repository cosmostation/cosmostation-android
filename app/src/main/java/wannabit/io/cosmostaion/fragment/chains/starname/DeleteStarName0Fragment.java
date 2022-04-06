package wannabit.io.cosmostaion.fragment.chains.starname;

import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_DOMAIN;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.DeleteStarNameActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class DeleteStarName0Fragment extends BaseFragment implements View.OnClickListener {

    private Button mCancelBtn, mConfirmBtn;
    private TextView mStarName, mExpireTime;

    public static DeleteStarName0Fragment newInstance(Bundle bundle) {
        DeleteStarName0Fragment fragment = new DeleteStarName0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delete_starname_0, container, false);
        mCancelBtn = rootView.findViewById(R.id.btn_cancel);
        mConfirmBtn = rootView.findViewById(R.id.nextButton);
        mStarName = rootView.findViewById(R.id.to_delete_starname);
        mExpireTime = rootView.findViewById(R.id.expire_time);
        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        if (getSActivity().mStarNameDomainType.equals(IOV_MSG_TYPE_DELETE_DOMAIN)) {
            mStarName.setText("*" + getSActivity().mStarNameDomain);
        } else {
            mStarName.setText(getSActivity().mStarNameAccount + "*" + getSActivity().mStarNameDomain);
        }
        mExpireTime.setText(WDp.getDpTime(getContext(), getSActivity().mValidTime * 1000));


        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onNextStep();
        }

    }


    private DeleteStarNameActivity getSActivity() {
        return (DeleteStarNameActivity) getBaseActivity();
    }
}
