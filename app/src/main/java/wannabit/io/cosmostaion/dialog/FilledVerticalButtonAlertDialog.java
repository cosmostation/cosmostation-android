package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;

public class FilledVerticalButtonAlertDialog extends AlertDialog {
    TextView titleTextView, messageTextView;
    Button firstButton, secondButton, thirdButton, quadrupleButton;

    public FilledVerticalButtonAlertDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_template_filledvertical, null);

        titleTextView = view.findViewById(R.id.dialog_title);
        messageTextView = view.findViewById(R.id.dialog_msg);
        firstButton = view.findViewById(R.id.btn_one);
        secondButton = view.findViewById(R.id.btn_two);
        thirdButton = view.findViewById(R.id.btn_three);
        quadrupleButton = view.findViewById(R.id.btn_four);
        setView(view);

    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage, Boolean cancelable) {
        FilledVerticalButtonAlertDialog dialog = makeDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage);
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage) {
        showDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, true);
    }

    public static void showTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                        CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                        CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage, Boolean cancelable) {
        FilledVerticalButtonAlertDialog dialog = makeTripleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage);
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
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
        dialog.setCancelable(cancelable);
        dialog.create();
        dialog.show();
    }

    public static void showQuadrupleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                           CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                           CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage,
                                           CharSequence quadrupleButtonTitle, View.OnClickListener quadrupleButtonListener, Drawable quadrupleButtonImage) {
        showQuadrupleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage, thirdButtonTitle, thirdButtonListener, thirdButtonImage, quadrupleButtonTitle, quadrupleButtonListener, quadrupleButtonImage, true);
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
