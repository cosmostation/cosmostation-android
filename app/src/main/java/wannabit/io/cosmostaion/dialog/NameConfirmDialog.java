package wannabit.io.cosmostaion.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import wannabit.io.cosmostaion.R;

public class NameConfirmDialog extends DialogFragment {

    public static int STAR_NAME_BUNDLE_VALUE = 8500;
    public static int ICNS_BUNDLE_VALUE = 8501;

    public final static String NAME_SERVICE_BUNDLE_KEY = "nameservice";
    public final static String NAME_BUNDLE_KEY = "name";
    public final static String MATCH_ADDRESS_BUNDLE_KEY = "matchAddress";
    public final static String CONFIRM_BUNDLE_KEY = "confirm";

    public static NameConfirmDialog newInstance(Bundle bundle) {
        NameConfirmDialog frag = new NameConfirmDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_name_service_confirm, null);
        TextView nameServiceTitle = view.findViewById(R.id.name_service_title);
        TextView nameServiceInfo = view.findViewById(R.id.name_service_info);
        Button btn_negative = view.findViewById(R.id.btn_nega);
        Button btn_posi = view.findViewById(R.id.btn_posi);

        int nameServiceType = getArguments().getInt(NAME_SERVICE_BUNDLE_KEY, -1);
        if (nameServiceType == STAR_NAME_BUNDLE_VALUE) {
            nameServiceTitle.setText(getString(R.string.str_starname_confirm_title));
        } else {
            nameServiceTitle.setText(getString(R.string.str_icns_confirm_title));
        }
        nameServiceInfo.setText(String.format(getString(R.string.str_starname_confirm_msg), getArguments().getString(NameConfirmDialog.NAME_BUNDLE_KEY), getArguments().getString(NameConfirmDialog.MATCH_ADDRESS_BUNDLE_KEY)));

        btn_negative.setOnClickListener(v -> {
            dismiss();
        });

        btn_posi.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putString(NameConfirmDialog.MATCH_ADDRESS_BUNDLE_KEY, getArguments().getString(NameConfirmDialog.MATCH_ADDRESS_BUNDLE_KEY));
            getParentFragmentManager().setFragmentResult(NameConfirmDialog.CONFIRM_BUNDLE_KEY, result);
            dismiss();
        });

        return view;
    }
}