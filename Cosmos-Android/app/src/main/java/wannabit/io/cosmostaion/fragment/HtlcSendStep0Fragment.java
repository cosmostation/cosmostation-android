package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class HtlcSendStep0Fragment extends BaseFragment implements View.OnClickListener {

    private Button          mBtnCancel, mBtnNext;
    private ImageView       mFromChainImg;
    private TextView        mFromChainTv;
    private RelativeLayout  mBtnToChain;
    private ImageView       mToChainImg;
    private TextView        mToChainTv;

    private ArrayList<BaseChain> mToChainList;
    private BaseChain       mToChain;

    public static HtlcSendStep0Fragment newInstance(Bundle bundle) {
        HtlcSendStep0Fragment fragment = new HtlcSendStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_send_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);

        mFromChainImg = rootView.findViewById(R.id.img_from_chain);
        mFromChainTv = rootView.findViewById(R.id.txt_from_chain);
        mBtnToChain = rootView.findViewById(R.id.btn_to_chain);
        mToChainImg = rootView.findViewById(R.id.img_to_chain);
        mToChainTv = rootView.findViewById(R.id.txt_to_chain);

        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
        mBtnToChain.setOnClickListener(this);

        mToChainList = BaseChain.getHtlcSendable(getSActivity().mBaseChain);
        if (mToChainList.size() <= 0) { getSActivity().onBeforeStep(); }
        mToChain = mToChainList.get(0);
        onUpdateView();
        return rootView;
    }

    private void onUpdateView() {
        if (mToChain == null) { getSActivity().onBeforeStep(); }
        WDp.onDpChain(getContext(), getSActivity().mBaseChain, mFromChainImg, mFromChainTv);
        WDp.onDpChain(getContext(), mToChain, mToChainImg, mToChainTv);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnToChain)) {

        } else if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            getSActivity().mRecipientChain = mToChain;
            getSActivity().onNextStep();

        }
    }



    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity)getBaseActivity();
    }


}
