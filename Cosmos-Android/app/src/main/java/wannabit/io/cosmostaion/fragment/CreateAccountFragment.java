package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class CreateAccountFragment extends BaseFragment implements View.OnClickListener{

    private Button mBtnCreateMnemonic, mBtnRecoverMnemonic, mBtnAddAddress;

    public static CreateAccountFragment newInstance(Bundle bundle) {
        CreateAccountFragment fragment = new CreateAccountFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgament_create_account, container, false);
        mBtnCreateMnemonic = rootView.findViewById(R.id.btn_create_mnemonic);
        mBtnRecoverMnemonic = rootView.findViewById(R.id.btn_recover_mnemonic);
        mBtnAddAddress = rootView.findViewById(R.id.btn_add_pubkey);

        mBtnCreateMnemonic.setOnClickListener(this);
        mBtnRecoverMnemonic.setOnClickListener(this);
        mBtnAddAddress.setOnClickListener(this);

        return rootView;
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCreateMnemonic)) {
            FragmentTransaction transaction = getTransaction();
            transaction.setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit, R.animator.fragment_slide_right_enter, R.animator.fragment_slide_right_exit);
            transaction.replace(R.id.main_container, GenerateMnemonicFragment.newInstance(null));
            transaction.addToBackStack("type");
            transaction.commit();

        } else if (v.equals(mBtnRecoverMnemonic)) {
            FragmentTransaction transaction = getTransaction();
            transaction.setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit, R.animator.fragment_slide_right_enter, R.animator.fragment_slide_right_exit);
            transaction.replace(R.id.main_container, RecoverMnemonicFragment.newInstance(null));
            transaction.addToBackStack("type");
            transaction.commit();

        } else if (v.equals(mBtnAddAddress)) {
            FragmentTransaction transaction = getTransaction();
            transaction.setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit, R.animator.fragment_slide_right_enter, R.animator.fragment_slide_right_exit);
            transaction.replace(R.id.main_container, PublicKeyAddFragment.newInstance(null));
            transaction.addToBackStack("type");
            transaction.commit();
        }

    }
}
