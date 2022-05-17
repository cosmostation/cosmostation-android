package wannabit.io.cosmostaion.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;


public class Dialog_Wait extends DialogFragment {

    public static Dialog_Wait newInstance() {
        Dialog_Wait frag = new Dialog_Wait();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_wait, container);
    }

}