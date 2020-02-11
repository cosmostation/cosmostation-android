package wannabit.io.cosmostaion.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import wannabit.io.cosmostaion.R;

public class Dialog_Kava_Testnet extends DialogFragment {
    public static Dialog_Kava_Testnet newInstance(Bundle bundle) {
        Dialog_Kava_Testnet frag = new Dialog_Kava_Testnet();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view  = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_kava_testnet, null);
        Button btn_posi = view.findViewById(R.id.btn_posi);

        btn_posi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}