package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;


public class WaitDialog extends DialogFragment {

    public static WaitDialog newInstance() {
        WaitDialog frag = new WaitDialog();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        getDialog().getWindow().setDimAmount(0.2f);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_wait, null);

        return view;
    }
}