package wannabit.io.cosmostaion.fragment.chains.ibc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.ibc.IBCSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dialog.Dialog_IBC_Receive_Chain;
import wannabit.io.cosmostaion.dialog.Dialog_IBC_Relayer_Channel;
import wannabit.io.cosmostaion.dialog.Dialog_IBC_Unknown_Relayer;
import wannabit.io.cosmostaion.utils.WDp;

public class IBCSendStep0Fragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_POPUP_IBC_CHAIN = 1000;
    public final static int SELECT_POPUP_IBC_RELAYER = 2000;
    public final static int SELECT_POPUP_IBC_UNKNOWN_RELAYER = 3000;

    private Button mBtnCancel, mBtnNext;

    private ImageView mFromChainImg;
    private TextView mFromChainTv;

    private LinearLayout mToChainLayer;
    private ImageView mToChainImg;
    private TextView mToChainTv;
    private ImageView mDialogImg;

    private RelativeLayout mToRelayer;
    private TextView mRelayerTxt;
    private ImageView mRelayerImg;

    private ArrayList<IbcPath> mIbcSendableRelayers = new ArrayList<>();
    private IbcPath mIbcSelectedRelayer;
    private ArrayList<IbcPath.Path> mIbcSendablePaths = new ArrayList<>();
    private IbcPath.Path mIbcSelectedPath;

    public static IBCSendStep0Fragment newInstance(Bundle bundle) {
        IBCSendStep0Fragment fragment = new IBCSendStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ibc_send_step0, container, false);
        mBtnCancel = rootView.findViewById(R.id.btn_cancel);
        mBtnNext = rootView.findViewById(R.id.btn_next);
        mFromChainImg = rootView.findViewById(R.id.img_from_chain);
        mFromChainTv = rootView.findViewById(R.id.txt_from_chain);

        mToChainLayer = rootView.findViewById(R.id.to_chain_layer);
        mToChainImg = rootView.findViewById(R.id.img_to_chain);
        mToChainTv = rootView.findViewById(R.id.txt_to_chain);
        mDialogImg = rootView.findViewById(R.id.dialog_img);

        mToRelayer = rootView.findViewById(R.id.btn_channel_relayer);
        mRelayerTxt = rootView.findViewById(R.id.txt_to_relayer_channel);
        mRelayerImg = rootView.findViewById(R.id.relayer_img);

        mToChainLayer.setOnClickListener(this);
        mToRelayer.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getSActivity().mToIbcDenom.startsWith("ibc/")) {
            mIbcSendableRelayers = getBaseDao().getIbcRollbackRelayer(getSActivity().mToIbcDenom);
        } else {
            mIbcSendableRelayers = getBaseDao().getIbcSendableRelayers();
        }

        if (mIbcSendableRelayers.size() <= 0) {
            onForceBack();
            return;
        }
        onSortRelayer(mIbcSendableRelayers);
        mIbcSelectedRelayer = mIbcSendableRelayers.get(0);

        if (getSActivity().mToIbcDenom.startsWith("ibc/")) {
            for (IbcPath.Path path : mIbcSelectedRelayer.paths) {
                if (path.auth != null && path.auth) {
                    mIbcSendablePaths.add(path);
                }
            }
        } else {
            mIbcSendablePaths = mIbcSelectedRelayer.paths;
        }
        if (mIbcSendablePaths.size() <= 0) {
            onForceBack();
            return;
        }
        onSortPath(mIbcSendablePaths);
        mIbcSelectedPath = mIbcSendablePaths.get(0);

        onUpdateView();
    }

    private void onUpdateView() {
        if (getSActivity().mToIbcDenom.startsWith("ibc/")) {
            mToChainLayer.setClickable(false);
            mToChainLayer.setBackgroundResource(R.drawable.box_gray);
            mDialogImg.setVisibility(View.GONE);
        } else {
            mToChainLayer.setClickable(true);
            mToChainLayer.setBackgroundResource(R.drawable.btn_trans_with_border);
            mDialogImg.setVisibility(View.VISIBLE);
        }
        WDp.getChainImg(getSActivity(), getSActivity().mBaseChain, mFromChainImg);
        WDp.getChainTitle2(getSActivity(), getSActivity().mBaseChain, mFromChainTv);

        BaseChain toChain = WDp.getChainTypeByChainId(mIbcSelectedRelayer.chain_id);
        WDp.getChainImg(getSActivity(), toChain, mToChainImg);
        WDp.getChainTitle2(getSActivity(), toChain, mToChainTv);

        mRelayerTxt.setText(mIbcSelectedPath.channel_id);
        if (mIbcSelectedPath.auth == null) {
            mRelayerImg.setImageDrawable(getSActivity().getDrawable(R.drawable.unknown));
        } else if (mIbcSelectedPath.auth) {
            mRelayerImg.setImageDrawable(getSActivity().getDrawable(R.drawable.wellknown));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            if (mIbcSelectedPath.auth == null) {
                Dialog_IBC_Unknown_Relayer warning = Dialog_IBC_Unknown_Relayer.newInstance();
                warning.setCancelable(true);
                warning.setTargetFragment(this, SELECT_POPUP_IBC_UNKNOWN_RELAYER);
                getFragmentManager().beginTransaction().add(warning, "dialog").commitNowAllowingStateLoss();
            } else if (mIbcSelectedPath.auth) {
                getSActivity().mIbcSelectedRelayer = mIbcSelectedRelayer;
                getSActivity().mPath = mIbcSelectedPath;
                getSActivity().onNextStep();
            }

        } else if (v.equals(mToChainLayer)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("chain", mIbcSendableRelayers);
            Dialog_IBC_Receive_Chain dialog = Dialog_IBC_Receive_Chain.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_POPUP_IBC_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mToRelayer)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("channel", mIbcSendablePaths);
            Dialog_IBC_Relayer_Channel dialog = Dialog_IBC_Relayer_Channel.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_POPUP_IBC_RELAYER);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
        }
    }

    private void onSortRelayer(ArrayList<IbcPath> ibcPaths) {
        Collections.sort(ibcPaths, new Comparator<IbcPath>() {
            @Override
            public int compare(IbcPath o1, IbcPath o2) {
                if (o1.chain_id.contains("cosmoshub-")) return -1;
                if (o2.chain_id.contains("cosmoshub-")) return 1;
                if (o1.chain_id.contains("osmosis-")) return -1;
                if (o2.chain_id.contains("osmosis-")) return 1;
                return o1.chain_id.compareTo(o2.chain_id);
            }
        });
    }

    private void onSortPath(ArrayList<IbcPath.Path> paths) {
        Collections.sort(paths, new Comparator<IbcPath.Path>() {
            @Override
            public int compare(IbcPath.Path o1, IbcPath.Path o2) {
                IbcToken ibcToken = getBaseDao().getIbcToken(getSActivity().mToIbcDenom);
                if (getSActivity().mToIbcDenom.startsWith("ibc/")) {
                    if (o1.channel_id.equalsIgnoreCase(ibcToken.channel_id)) return -1;
                    if (o2.channel_id.equalsIgnoreCase(ibcToken.channel_id)) return 1;
                }
                if (o1.auth != null) return -1;
                if (o2.auth != null) return 1;
                else return 0;
            }
        });
    }

    private void onForceBack() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getSActivity(), R.string.error_no_relayer_channel, Toast.LENGTH_SHORT).show();
                getSActivity().onBeforeStep();
            }
        }, 610);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_POPUP_IBC_CHAIN && resultCode == Activity.RESULT_OK) {
            mIbcSelectedRelayer = mIbcSendableRelayers.get(data.getIntExtra("position", 0));
            mIbcSendablePaths = mIbcSelectedRelayer.paths;
            onSortPath(mIbcSendablePaths);
            mIbcSelectedPath = mIbcSendablePaths.get(0);
            onUpdateView();

        } else if (requestCode == SELECT_POPUP_IBC_RELAYER && resultCode == Activity.RESULT_OK) {
            mIbcSelectedPath = mIbcSendablePaths.get(data.getIntExtra("position", 0));
            onUpdateView();

        } else if (requestCode == SELECT_POPUP_IBC_UNKNOWN_RELAYER && resultCode == Activity.RESULT_OK) {
            if (data.getIntExtra("continue", -1) == 0) {
                getSActivity().mIbcSelectedRelayer = mIbcSelectedRelayer;
                getSActivity().mPath = mIbcSelectedPath;
                getSActivity().onNextStep();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private IBCSendActivity getSActivity() {
        return (IBCSendActivity) getBaseActivity();
    }
}
