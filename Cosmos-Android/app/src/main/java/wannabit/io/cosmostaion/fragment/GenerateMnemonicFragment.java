package wannabit.io.cosmostaion.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class GenerateMnemonicFragment extends BaseFragment implements View.OnClickListener, TaskListener{

    private TextView[]          mTvWords = new TextView[24];
    private Button              mRefresh, mNext;

    private ArrayList<String>   mWords = new ArrayList<>();
    private byte[]              mSeed;

    public static GenerateMnemonicFragment newInstance(Bundle bundle) {
        GenerateMnemonicFragment fragment = new GenerateMnemonicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_generate_mnemonic, container, false);
        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = rootView.findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", getBaseActivity().getPackageName()));
        }
        mRefresh = rootView.findViewById(R.id.btn_refresh);
        mNext = rootView.findViewById(R.id.btn_next);

        mRefresh.setOnClickListener(this);
        mNext.setOnClickListener(this);

        onRefreshSeeds();
        return rootView;
    }



    private void onRefreshSeeds() {
        mSeed = WKey.getEntropy();
        mWords = new ArrayList<String>(WKey.getRandomMnemonic(mSeed));
        WLog.w("mWords : " + mWords.size());

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i].setText(mWords.get(i));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mRefresh)) {
            onRefreshSeeds();

        } else if (v.equals(mNext)) {
//            ClipboardManager clipboard = (ClipboardManager) getBaseActivity().getSystemService(Context.CLIPBOARD_SERVICE);
//            ClipData clip = ClipData.newPlainText("mnemonic", getClipboard());
//            clipboard.setPrimaryClip(clip);
//            Toast.makeText(getBaseActivity(), R.string.str_mnemonic_copied, Toast.LENGTH_SHORT).show();
//            getBaseActivity().onShowWaitDialog();
//            new InsertMnemonicTask(getBaseApplication(), this).execute(WUtil.ByteArrayToHexString(mSeed));

        }
    }


    private String getClipboard() {
        StringBuilder builder = new StringBuilder();
        for(String s : mWords) {
            if(builder.length() != 0)
                builder.append(" ");
            builder.append(s);
        }
        return builder.toString();
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(getActivity().isFinishing() && !isAdded()) return;
        getBaseActivity().onHideWaitDialog();
//        if (result.taskType == BaseConstant.TASK_ADD_MN && result.isSuccess) {
//            getBaseActivity().onShowWaitDialog();
//            new GenerateAccountTask(getBaseApplication(), this).execute(BaseChain.WANNABIT_NET.getChain(), "0", result.resultData.toString());
//        } else if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT && result.isSuccess) {
//            getBaseActivity().onStartMainActivity();
//        }
    }
}
