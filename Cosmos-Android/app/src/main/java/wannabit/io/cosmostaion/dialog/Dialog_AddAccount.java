package wannabit.io.cosmostaion.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.CreateActivity;
import wannabit.io.cosmostaion.activities.RestoreActivity;
import wannabit.io.cosmostaion.activities.WatchingAccountAddActivity;

public class Dialog_AddAccount extends DialogFragment {

    private LinearLayout    btn_import_mnemonic, btn_watch_address;
    private Button btn_create;

    public static Dialog_AddAccount newInstance(Bundle bundle) {
        Dialog_AddAccount frag = new Dialog_AddAccount();
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_account, null);

        btn_import_mnemonic = view.findViewById(R.id.btn_import_mnemonic);
        btn_watch_address = view.findViewById(R.id.btn_watch_address);
        btn_create = view.findViewById(R.id.btn_create);

        btn_import_mnemonic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RestoreActivity.class));
                getDialog().dismiss();
            }
        });

        btn_watch_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WatchingAccountAddActivity.class));
                getDialog().dismiss();
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
                getDialog().dismiss();
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        return builder.create();
    }
}
