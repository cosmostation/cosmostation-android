package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;

public class CustomAlertDialog extends AlertDialog {
    TextView titleTextView, messageTextView;
    Button Button1, Button2, Button3;

    public CustomAlertDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_template_paddedvertical, null);

        titleTextView = view.findViewById(R.id.dialog_title);
        messageTextView = view.findViewById(R.id.dialog_msg);
        Button1 = view.findViewById(R.id.btn_one);
        Button2 = view.findViewById(R.id.btn_two);
        Button3 = view.findViewById(R.id.btn_three);
        setView(view);

    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence Button1Title, View.OnClickListener Button1Listener, CharSequence Button2Title, View.OnClickListener Button2Listener, Boolean cancelable) {
        CustomAlertDialog dialog = makeDoubleButton(context, title, message, Button1Title, Button1Listener, Button2Title, Button2Listener);
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence Button1Title, View.OnClickListener Button1Listener, CharSequence Button2Title, View.OnClickListener Button2Listener) {
        showDoubleButton(context, title, message, Button1Title, Button1Listener, Button2Title, Button2Listener, true);
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence Button1Title, View.OnClickListener Button1Listener,
                                        CharSequence Button2Title, View.OnClickListener Button2Listener, CharSequence Button3Title, View.OnClickListener Button3Listener, Boolean cancelable) {
        CustomAlertDialog dialog = makeTripleButton(context, title, message, Button1Title, Button1Listener, Button2Title, Button2Listener, Button3Title, Button3Listener);
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence Button1Title, View.OnClickListener Button1Listener,
                                        CharSequence Button2Title, View.OnClickListener Button2Listener, CharSequence Button3Title, View.OnClickListener Button3Listener) {
        showTripleButton(context, title, message, Button1Title, Button1Listener, Button2Title, Button2Listener, Button3Title, Button3Listener, true);
    }

    private static CustomAlertDialog makeDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence Button1Title, View.OnClickListener Button1Listener, CharSequence Button2Title, View.OnClickListener Button2Listener) {
        CustomAlertDialog dialog = new CustomAlertDialog(context);
        if (StringUtils.isEmpty(title)) {
            dialog.titleTextView.setVisibility(View.GONE);
        } else {
            dialog.titleTextView.setText(title);
            dialog.titleTextView.setVisibility(View.VISIBLE);
        }

        if (StringUtils.isEmpty(message)) {
            dialog.messageTextView.setVisibility(View.GONE);
        } else {
            dialog.messageTextView.setText(message);
            dialog.messageTextView.setVisibility(View.VISIBLE);
        }

        dialog.Button1.setText(Button1Title);
        dialog.Button1.setOnClickListener(view -> {
            if (Button1Listener != null) {
                Button1Listener.onClick(view);
            }
            dialog.dismiss();
        });

        dialog.Button2.setText(Button2Title);
        dialog.Button2.setOnClickListener(view -> {
            if (Button2Listener != null) {
                Button2Listener.onClick(view);
            }
            dialog.dismiss();
        });

        dialog.Button3.setVisibility(View.GONE);

        return dialog;
    }

    private static CustomAlertDialog makeTripleButton(Context context, CharSequence title, CharSequence message, CharSequence Button1Title, View.OnClickListener Button1Listener,
                                                      CharSequence Button2Title, View.OnClickListener Button2Listener, CharSequence Button3Title, View.OnClickListener Button3Listener) {
        CustomAlertDialog dialog = makeDoubleButton(context, title, message, Button1Title, Button1Listener, Button2Title, Button2Listener);
        dialog.Button3.setText(Button3Title);
        dialog.Button3.setVisibility(View.VISIBLE);
        dialog.Button3.setOnClickListener(view -> {
            if (Button3Listener != null) {
                Button3Listener.onClick(view);
            }
            dialog.dismiss();
        });

        return dialog;
    }
}
