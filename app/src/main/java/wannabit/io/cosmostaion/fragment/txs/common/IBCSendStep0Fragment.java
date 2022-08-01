package wannabit.io.cosmostaion.fragment.txs.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
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
import java.util.HashSet;
import java.util.Set;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.common.IBCSendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.IBCReceiveChainDialog;
import wannabit.io.cosmostaion.dialog.IBCRelayerChannelDialog;
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
            mIbcSendableRelayers = getIbcRollbackRelayer(getSActivity().mToIbcDenom);
        } else {
            mIbcSendableRelayers = getIbcSendableRelayers();
        }

        if (mIbcSendableRelayers.size() <= 0) {
            onForceBack();
            return;
        }
        onSortRelayer(mIbcSendableRelayers);
        mIbcSelectedRelayer = mIbcSendableRelayers.get(0);

        if (getSActivity().mToIbcDenom.startsWith("ibc/")) {
            mIbcSendablePaths = getIbcRollbackChannel(getSActivity().mToIbcDenom, mIbcSelectedRelayer.paths);
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
        ChainConfig fromChainConfig = getSActivity().mChainConfig;
        mFromChainImg.setImageResource(fromChainConfig.chainImg());
        mFromChainTv.setText(fromChainConfig.chainTitleToUp());

        BaseChain toChain = WDp.getChainTypeByChainId(mIbcSelectedRelayer.chain_id);
        ChainConfig toChainConfig = ChainFactory.getChain(toChain);
        mToChainImg.setImageResource(toChainConfig.chainImg());
        mToChainTv.setText(toChainConfig.chainTitleToUp());

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
                AlertDialogUtils.showDoubleButtonDialog(getSActivity(), getString(R.string.str_ibc_warning_c), getString(R.string.str_ibc_unknown_relayer_msg),
                        Html.fromHtml("<font color=\"#007AFF\">" + getString(R.string.str_cancel) + "</font>"), null,
                        Html.fromHtml("<font color=\"#007AFF\">" + getString(R.string.str_continue) + "</font>"), view -> {
                            Intent resultIntent = new Intent();
                            resultIntent.putExtra("continue", 0);
                            onActivityResult(SELECT_POPUP_IBC_UNKNOWN_RELAYER, Activity.RESULT_OK, resultIntent);
                        });
            } else if (mIbcSelectedPath.auth) {
                getSActivity().mIbcSelectedRelayer = mIbcSelectedRelayer;
                getSActivity().mPath = mIbcSelectedPath;
                getSActivity().onNextStep();
            }

        } else if (v.equals(mToChainLayer)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("chain", mIbcSendableRelayers);
            IBCReceiveChainDialog dialog = IBCReceiveChainDialog.newInstance(bundle);
            dialog.setCancelable(true);
            dialog.setTargetFragment(this, SELECT_POPUP_IBC_CHAIN);
            getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mToRelayer)) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("channel", mIbcSendablePaths);
            IBCRelayerChannelDialog dialog = IBCRelayerChannelDialog.newInstance(bundle);
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
                if (getSActivity().mToIbcDenom.equalsIgnoreCase(WDp.mainDenom(getSActivity().mBaseChain))) {
                    if (o1.auth != null && o1.port_id.equalsIgnoreCase(o1.counter_party.port_id))
                        return -1;
                    if (o2.auth != null && o2.port_id.equalsIgnoreCase(o2.counter_party.port_id))
                        return 1;
                    if (o1.auth != null && !o1.port_id.equalsIgnoreCase(o1.counter_party.port_id))
                        return -1;
                    if (o2.auth != null && !o2.port_id.equalsIgnoreCase(o2.counter_party.port_id))
                        return 1;
                } else {
                    if (o1.auth != null && !o1.port_id.equalsIgnoreCase(o1.counter_party.port_id))
                        return -1;
                    if (o2.auth != null && !o2.port_id.equalsIgnoreCase(o2.counter_party.port_id))
                        return 1;
                    if (o1.auth != null && o1.port_id.equalsIgnoreCase(o1.counter_party.port_id))
                        return -1;
                    if (o2.auth != null && o2.port_id.equalsIgnoreCase(o2.counter_party.port_id))
                        return 1;
                }
                return 0;
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

    public ArrayList<IbcPath> getIbcSendableRelayers() {
        ArrayList<IbcPath> result = new ArrayList<>();
        for (IbcPath ibcPath: getBaseDao().mIbcPaths) {
            for (IbcPath.Path path: ibcPath.paths) {
                if (path.auth != null && path.auth) {
                    result.add(ibcPath);
                }
            }
        }
        Set<IbcPath> arr2 = new HashSet<>(result);
        ArrayList<IbcPath> resArr2 = new ArrayList<>(arr2);
        return resArr2;
    }

    public ArrayList<IbcPath> getIbcRollbackRelayer(String denom) {
        ArrayList<IbcPath> result = new ArrayList<>();
        IbcToken ibcToken = getBaseDao().getIbcToken(denom.replaceAll("ibc/", ""));
        if (getBaseDao().mIbcPaths != null && getBaseDao().mIbcPaths.size() > 0) {
            for (IbcPath ibcPath: getBaseDao().mIbcPaths) {
                for (IbcPath.Path path: ibcPath.paths) {
                    if (path.channel_id != null && path.channel_id.equalsIgnoreCase(ibcToken.channel_id)) {
                        result.add(ibcPath);
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<IbcPath.Path> getIbcRollbackChannel(String denom, ArrayList<IbcPath.Path> paths) {
        ArrayList<IbcPath.Path> result = new ArrayList<>();
        IbcToken ibcToken = getBaseDao().getIbcToken(denom);
        if (paths != null && paths.size() > 0) {
            for (IbcPath.Path path: paths) {
                if (path.auth != null && path.auth && path.channel_id.equalsIgnoreCase(ibcToken.channel_id)) {
                    result.add(path);
                }
            }
        }
        return result;
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
