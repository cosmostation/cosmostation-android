package wannabit.io.cosmostaion.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.setting.MnemonicCreateActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicRestoreActivity;
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity;
import wannabit.io.cosmostaion.activities.setting.WatchingWalletAddActivity;

public class Dialog_AddAccount extends DialogFragment {

    private LinearLayout btn_import_key, btn_import_mnemonic, btn_watch_address;
    private Button btn_create;

    public static Dialog_AddAccount newInstance(Bundle bundle) {
        Dialog_AddAccount frag = new Dialog_AddAccount();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_account, null);

        btn_import_key = view.findViewById(R.id.btn_import_key);
        btn_import_mnemonic = view.findViewById(R.id.btn_import_mnemonic);
        btn_watch_address = view.findViewById(R.id.btn_watch_address);
        btn_create = view.findViewById(R.id.btn_create);

        btn_import_key.setOnClickListener(v -> {
            Intent restoreIntent = new Intent(getActivity(), PrivateKeyRestoreActivity.class);
            startActivity(restoreIntent);
            getDialog().dismiss();
        });

        btn_import_mnemonic.setOnClickListener(v -> {
            Intent restoreIntent = new Intent(getActivity(), MnemonicRestoreActivity.class);
            startActivity(restoreIntent);
            getDialog().dismiss();
        });

        btn_watch_address.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), WatchingWalletAddActivity.class));
            getDialog().dismiss();
        });

        btn_create.setOnClickListener(v -> {
            Intent createIntent = new Intent(getActivity(), MnemonicCreateActivity.class);
            startActivity(createIntent);
            getDialog().dismiss();
        });

        return view;
    }
}
