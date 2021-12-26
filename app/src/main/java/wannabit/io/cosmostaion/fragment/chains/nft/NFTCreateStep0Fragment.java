package wannabit.io.cosmostaion.fragment.chains.nft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTCreateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class NFTCreateStep0Fragment extends BaseFragment implements View.OnClickListener{

    private Button      mBtnCancel, mBtnNext;

    private ImageView   mNftImg;
    private EditText    mNftName, mNftContent;

    public static NFTCreateStep0Fragment newInstance(Bundle bundle) {
        NFTCreateStep0Fragment fragment = new NFTCreateStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_nft_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mNftImg = rootView.findViewById(R.id.nft_img);
        mNftName = rootView.findViewById(R.id.nft_name);
        mNftContent = rootView.findViewById(R.id.nft_content);

        mNftImg.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            String name = mNftName.getText().toString().trim();
            String content = mNftContent.getText().toString().trim();
            if (name.isEmpty() || content.isEmpty()) {

            }
            getSActivity().onNextStep();

        } else if (v.equals(mNftImg)) {

        }
    }

    private NFTCreateActivity getSActivity() {
        return (NFTCreateActivity)getBaseActivity();
    }
}
