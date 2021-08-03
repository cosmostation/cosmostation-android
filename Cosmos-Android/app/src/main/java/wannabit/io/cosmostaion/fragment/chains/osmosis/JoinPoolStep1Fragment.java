package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.JoinPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class JoinPoolStep1Fragment extends BaseFragment implements View.OnClickListener{

    private EditText mMemo;
    private TextView mMemoCnt;

    private Button mBeforeBtn, mNextBtn;

    public static JoinPoolStep1Fragment newInstance(Bundle bundle) {
        JoinPoolStep1Fragment fragment = new JoinPoolStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_memo, container, false);
        mMemo       = rootView.findViewById(R.id.et_memo);
        mMemoCnt    = rootView.findViewById(R.id.tv_memoCnt);
        mBeforeBtn = rootView.findViewById(R.id.btn_before);
        mNextBtn = rootView.findViewById(R.id.btn_next);
        mBeforeBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            getSActivity().onNextStep();
        }
    }

    private JoinPoolActivity getSActivity() { return (JoinPoolActivity)getBaseActivity(); }
}
