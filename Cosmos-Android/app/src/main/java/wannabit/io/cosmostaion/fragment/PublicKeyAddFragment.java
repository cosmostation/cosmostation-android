package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.task.GenerateAccountTask;
import wannabit.io.cosmostaion.task.GenerateEmptyAccountTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class PublicKeyAddFragment extends BaseFragment implements View.OnClickListener, TaskListener {

    private EditText    mEtUserAddress;
    private Button      mBtnNext;

    public static PublicKeyAddFragment newInstance(Bundle bundle) {
        PublicKeyAddFragment fragment = new PublicKeyAddFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_publickey, container, false);
        mEtUserAddress      = rootView.findViewById(R.id.et_user_address);
        mBtnNext            = rootView.findViewById(R.id.btn_next);

        mBtnNext.setOnClickListener(this);
        return rootView;
    }


    @Override
    public void onClick(View v) {
        if(v.equals(mBtnNext)) {
            String userInput = mEtUserAddress.getText().toString().trim();

            if (userInput.startsWith("cosmospub") && WKey.isValidBech32(userInput)) {
                // this is valid pubkey
                getBaseActivity().onShowWaitDialog();
                new GenerateEmptyAccountTask(getBaseApplication(), this).execute(BaseChain.WANNABIT_NET.getChain(),
                        WKey.getCosmosDpPubToDpAddress(userInput));


            } else if (userInput.startsWith("cosmos") && WKey.isValidBech32(userInput)) {
                // this is valid address
                getBaseActivity().onShowWaitDialog();
                new GenerateEmptyAccountTask(getBaseApplication(), this).execute(BaseChain.WANNABIT_NET.getChain(),
                        userInput);

            } else {
                // this is wtf
                Toast.makeText(getBaseActivity(), R.string.error_invalid_address_pubkey, Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(getActivity().isFinishing() && !isAdded()) return;
        getBaseActivity().onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_INIT_EMPTY_ACCOUNT) {
            if(result.isSuccess) {
                getBaseActivity().onShowWaitDialog();
                getBaseActivity().onStartMainActivity();

            } else {
                Toast.makeText(getBaseActivity(), R.string.error_already_exist_account, Toast.LENGTH_SHORT).show();
            }

        }

    }
}

