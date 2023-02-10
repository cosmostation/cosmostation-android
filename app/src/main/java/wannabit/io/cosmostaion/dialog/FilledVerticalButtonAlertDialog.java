package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;

public class FilledVerticalButtonAlertDialog extends AlertDialog {
    public TextView titleTextView, title2TextView, messageTextView, message2TextView;
    public Button firstButton, secondButton, thirdButton, quadrupleButton;
    View btnLine, btnLine2, btnLine3, btnLine4;
    public LinearLayout hiddenView;

    public FilledVerticalButtonAlertDialog(Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_template_filledvertical, null);

        titleTextView = view.findViewById(R.id.dialog_title);
        title2TextView = view.findViewById(R.id.dialog_title2);
        messageTextView = view.findViewById(R.id.dialog_msg);
        message2TextView = view.findViewById(R.id.dialog_msg2);
        firstButton = view.findViewById(R.id.btn_one);
        secondButton = view.findViewById(R.id.btn_two);
        thirdButton = view.findViewById(R.id.btn_three);
        quadrupleButton = view.findViewById(R.id.btn_four);
        btnLine = view.findViewById(R.id.btn_line);
        btnLine2 = view.findViewById(R.id.btn_line2);
        btnLine3 = view.findViewById(R.id.btn_line3);
        btnLine4 = view.findViewById(R.id.btn_line4);
        hiddenView = view.findViewById(R.id.hidden_view);

        setView(view);

    }

    public static void showNoButton(Context context, CharSequence title, CharSequence message, Boolean cancelable) {
        FilledVerticalButtonAlertDialog dialog = makeNoButton(context, title, message);
        if (!((Activity) context).isFinishing()) {
            dialog.setCancelable(cancelable);
            dialog.create();
            dialog.show();
        }
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage, Boolean cancelable) {
        FilledVerticalButtonAlertDialog dialog = makeDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage);
        if (!((Activity) context).isFinishing()) {
            dialog.setCancelable(cancelable);
            dialog.create();
            dialog.show();
        }
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage) {
        showDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, true);
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                        CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage, Boolean cancelable) {
        FilledVerticalButtonAlertDialog dialog = makeTripleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage);
        if (!((Activity) context).isFinishing()) {
            dialog.setCancelable(cancelable);
            dialog.create();
            dialog.show();
        }
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage, CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage) {
        showTripleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage, true);
    }

    public static void showQuadrupleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                           CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                           CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage,
                                           CharSequence quadrupleButtonTitle, View.OnClickListener quadrupleButtonListener, Drawable quadrupleButtonImage, Boolean cancelable) {
        FilledVerticalButtonAlertDialog dialog = makeQuadrupleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage, quadrupleButtonTitle, quadrupleButtonListener, quadrupleButtonImage);
        if (!((Activity) context).isFinishing()) {
            dialog.setCancelable(cancelable);
            dialog.create();
            dialog.show();
        }
    }

    public static void showQuadrupleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                           CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                           CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage,
                                           CharSequence quadrupleButtonTitle, View.OnClickListener quadrupleButtonListener, Drawable quadrupleButtonImage) {
        showQuadrupleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage, quadrupleButtonTitle, quadrupleButtonListener, quadrupleButtonImage, true);
    }

    private static FilledVerticalButtonAlertDialog makeNoButton(Context context, CharSequence title, CharSequence message) {
        FilledVerticalButtonAlertDialog dialog = new FilledVerticalButtonAlertDialog(context);
        if (StringUtils.isEmpty(title)) {
            dialog.title2TextView.setVisibility(View.GONE);
        } else {
            dialog.title2TextView.setText(title);
            dialog.title2TextView.setVisibility(View.VISIBLE);
        }

        if (StringUtils.isEmpty(message)) {
            dialog.message2TextView.setVisibility(View.GONE);
        } else {
            dialog.message2TextView.setText(message);
            dialog.message2TextView.setVisibility(View.VISIBLE);
        }
        dialog.firstButton.setVisibility(View.GONE);
        dialog.secondButton.setVisibility(View.GONE);

        return dialog;
    }

    private static FilledVerticalButtonAlertDialog makeDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                                                    CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage) {
        FilledVerticalButtonAlertDialog dialog = new FilledVerticalButtonAlertDialog(context);
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

        if (firstButtonImage == null && secondButtonImage == null) {
            dialog.firstButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            dialog.secondButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            dialog.firstButton.setCompoundDrawablesWithIntrinsicBounds(firstButtonImage, null, null, null);
            dialog.secondButton.setCompoundDrawablesWithIntrinsicBounds(secondButtonImage, null, null, null);
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

        dialog.btnLine2.setVisibility(View.VISIBLE);
        dialog.thirdButton.setVisibility(View.GONE);

        return dialog;
    }

    private static FilledVerticalButtonAlertDialog makeTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                                                    CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                                                    CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage) {
        FilledVerticalButtonAlertDialog dialog = makeDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage);

        if (thirdButtonImage == null) {
            dialog.thirdButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            dialog.thirdButton.setCompoundDrawablesWithIntrinsicBounds(thirdButtonImage, null, null, null);
        }

        if (!StringUtils.isEmpty(message)) dialog.btnLine.setVisibility(View.VISIBLE);

        dialog.thirdButton.setText(thirdButtonTitle);
        dialog.thirdButton.setVisibility(View.VISIBLE);
        dialog.btnLine2.setVisibility(View.VISIBLE);
        dialog.btnLine3.setVisibility(View.VISIBLE);

        dialog.thirdButton.setOnClickListener(view -> {
            if (thirdButtonListener != null) {
                thirdButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        return dialog;
    }

    private static FilledVerticalButtonAlertDialog makeQuadrupleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                                                       CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                                                       CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage,
                                                                       CharSequence quadrupleButtonTitle, View.OnClickListener quadrupleButtonListener, Drawable quadrupleButtonImage) {
        FilledVerticalButtonAlertDialog dialog = makeTripleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage);

        if (quadrupleButtonImage == null) {
            dialog.quadrupleButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            dialog.quadrupleButton.setCompoundDrawablesWithIntrinsicBounds(quadrupleButtonImage, null, null, null);
        }

        if (StringUtils.isEmpty(message)) {
            dialog.messageTextView.setVisibility(View.GONE);
        } else {
            dialog.messageTextView.setText(message);
            dialog.messageTextView.setVisibility(View.VISIBLE);
            dialog.btnLine.setVisibility(View.VISIBLE);
        }

        dialog.btnLine2.setVisibility(View.VISIBLE);
        dialog.btnLine3.setVisibility(View.VISIBLE);
        dialog.btnLine4.setVisibility(View.VISIBLE);

        dialog.quadrupleButton.setText(quadrupleButtonTitle);
        dialog.quadrupleButton.setVisibility(View.VISIBLE);

        dialog.quadrupleButton.setOnClickListener(view -> {
            if (quadrupleButtonListener != null) {
                quadrupleButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        return dialog;
    }
}
