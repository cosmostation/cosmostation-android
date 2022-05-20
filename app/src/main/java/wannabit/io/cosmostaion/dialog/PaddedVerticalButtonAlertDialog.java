package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;

public class PaddedVerticalButtonAlertDialog extends AlertDialog {
    TextView titleTextView, messageTextView;
    Button firstButton, secondButton, thirdButton;

    public PaddedVerticalButtonAlertDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_template_paddedvertical, null);

        titleTextView = view.findViewById(R.id.dialog_title);
        messageTextView = view.findViewById(R.id.dialog_msg);
        firstButton = view.findViewById(R.id.btn_one);
        secondButton = view.findViewById(R.id.btn_two);
        thirdButton = view.findViewById(R.id.btn_three);
        setView(view);

    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Boolean cancelable) {
        PaddedVerticalButtonAlertDialog dialog = makeDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, secondButtonTitle, secondButtonListener);
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, CharSequence secondButtonTitle, View.OnClickListener secondButtonListener) {
        showDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, secondButtonTitle, secondButtonListener, true);
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Boolean cancelable) {
        PaddedVerticalButtonAlertDialog dialog = makeTripleButton(context, title, message, firstButtonTitle, firstButtonListener, secondButtonTitle, secondButtonListener, thirdButtonTitle, thirdButtonListener);
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener) {
        showTripleButton(context, title, message, firstButtonTitle, firstButtonListener, secondButtonTitle, secondButtonListener, thirdButtonTitle, thirdButtonListener, true);
    }

    private static PaddedVerticalButtonAlertDialog makeDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, CharSequence secondButtonTitle, View.OnClickListener secondButtonListener) {
        PaddedVerticalButtonAlertDialog dialog = new PaddedVerticalButtonAlertDialog(context);
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

        dialog.firstButton.setText(firstButtonTitle);
        dialog.firstButton.setOnClickListener(view -> {
            if (firstButtonListener != null) {
                firstButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        dialog.secondButton.setText(secondButtonTitle);
        dialog.secondButton.setOnClickListener(view -> {
            if (secondButtonListener != null) {
                secondButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        dialog.thirdButton.setVisibility(View.GONE);

        return dialog;
    }

    private static PaddedVerticalButtonAlertDialog makeTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener,
                                                                    CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener) {
        PaddedVerticalButtonAlertDialog dialog = makeDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, secondButtonTitle, secondButtonListener);
        dialog.thirdButton.setText(thirdButtonTitle);
        dialog.thirdButton.setVisibility(View.VISIBLE);
        dialog.thirdButton.setOnClickListener(view -> {
            if (thirdButtonListener != null) {
                thirdButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        return dialog;
    }
}
