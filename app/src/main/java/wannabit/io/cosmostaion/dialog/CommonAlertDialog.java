package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;

public class CommonAlertDialog extends AlertDialog {

    private Context mContext;
    private static CommonAlertDialog commonAlertDialog;

    protected CommonAlertDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public static CommonAlertDialog getInstance(Context context) {
        commonAlertDialog = new CommonAlertDialog(context);
        return commonAlertDialog;
    }

    public void showDefaultDialog(String title, String message, String title2, String message2) {

        if (title2!=null || message2!=null) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_common_alert, null);
            TextView msg1 = view.findViewById(R.id.dialog_msg1);
            TextView msg2 = view.findViewById(R.id.dialog_msg2);
            TextView msg3 = view.findViewById(R.id.dialog_msg3);
            TextView msg4 = view.findViewById(R.id.dialog_msg4);
            msg1.setText(title);
            msg2.setText(message);
            msg3.setText(title2);
            msg4.setText(message2);

            AlertDialog ad = new Builder(getContext()).setView(view).create();

            Button btn_negative = view.findViewById(R.id.btn_nega);
            btn_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ad.dismiss();
                }
            });

            ad.show();
        }
        else{
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_common_alert, null);
            TextView msg1 = view.findViewById(R.id.dialog_msg1);
            TextView msg2 = view.findViewById(R.id.dialog_msg2);
            TextView msg3 = view.findViewById(R.id.dialog_msg3);
            TextView msg4 = view.findViewById(R.id.dialog_msg4);
            msg1.setText(title);
            msg2.setText(message);
            msg3.setVisibility(View.GONE);
            msg4.setVisibility(View.GONE);

            AlertDialog ad = new Builder(getContext()).setView(view).create();

            Button btn_negative = view.findViewById(R.id.btn_nega);
            btn_negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ad.dismiss();
                }
            });

            ad.show();
        }
    }
}
