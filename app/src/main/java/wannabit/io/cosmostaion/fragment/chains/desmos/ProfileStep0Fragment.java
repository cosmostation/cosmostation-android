package wannabit.io.cosmostaion.fragment.chains.desmos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.desmos.ProfileActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class ProfileStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button      mCancelBtn, mNextBtn;

    private EditText    mProfileDtag, mProfileNickname, mProfileBio;
    private ImageView   mProfileCoverImg, mProfileImg;

    public static ProfileStep0Fragment newInstance(Bundle bundle) {
        ProfileStep0Fragment fragment = new ProfileStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_profile_step0, container, false);
        mCancelBtn                  = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                    = rootView.findViewById(R.id.btn_next);

        mProfileDtag                = rootView.findViewById(R.id.profile_dtag);
        mProfileNickname            = rootView.findViewById(R.id.profile_nickname);
        mProfileBio                 = rootView.findViewById(R.id.profile_bio);
        mProfileCoverImg            = rootView.findViewById(R.id.profile_cover_img);
        mProfileImg                 = rootView.findViewById(R.id.profile_img);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private ProfileActivity getSActivity() {
        return (ProfileActivity)getBaseActivity();
    }
}
