package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.task.GenerateAccountTask;
import wannabit.io.cosmostaion.task.InsertMnemonicTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class RecoverMnemonicFragment extends BaseFragment implements View.OnClickListener, TaskListener{

    private EditText            mEtUserMnemonic;
    private Button              mBtnNext;

    private ArrayList<String>   mWords = new ArrayList<>();

    public static RecoverMnemonicFragment newInstance(Bundle bundle) {
        RecoverMnemonicFragment fragment = new RecoverMnemonicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recover_mnemonic, container, false);
        mEtUserMnemonic     = rootView.findViewById(R.id.et_user_mnemonics);
        mBtnNext            = rootView.findViewById(R.id.btn_next);

        mBtnNext.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        if(v.equals(mBtnNext)) {
            String userInput = mEtUserMnemonic.getText().toString().trim();
            if(TextUtils.isEmpty(userInput)) return;
            ArrayList<String> insertWords = new ArrayList<>(Arrays.asList(userInput.split("\\s+")));
            if(insertWords.size() != 24) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_mnemonic_count, Toast.LENGTH_SHORT).show();
                return;
            }

            for(String word:insertWords) {
                word = word.replace(",","").replace(" ", "");
                mWords.add(word);
            }

            if(!WKey.isMnemonicWords(mWords)) {
                Toast.makeText(getBaseActivity(), R.string.error_invalid_mnemonic_count, Toast.LENGTH_SHORT).show();
                return;
            }
            getBaseActivity().onShowWaitDialog();
            new InsertMnemonicTask(getBaseApplication(), this).execute(WKey.getStringHdSeedFromWords(mWords));
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(getActivity().isFinishing() && !isAdded()) return;
        getBaseActivity().onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_ADD_MN && result.isSuccess) {
            getBaseActivity().onShowWaitDialog();
            new GenerateAccountTask(getBaseApplication(), this).execute(BaseChain.WANNABIT_NET.getChain(), "0", result.resultData.toString());
        } else if (result.taskType == BaseConstant.TASK_INIT_ACCOUNT && result.isSuccess) {
            getBaseActivity().onStartMainActivity();
        }
    }
}
