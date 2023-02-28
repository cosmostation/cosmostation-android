package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.databinding.DialogTemplateFilledverticalBinding;

public class FilledVerticalButtonAlertDialog extends AlertDialog {
    public DialogTemplateFilledverticalBinding filledVerticalBinding;
    public LinearLayout hiddenView;

    public FilledVerticalButtonAlertDialog(Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        filledVerticalBinding = DialogTemplateFilledverticalBinding.inflate(getLayoutInflater());
        setView(filledVerticalBinding.getRoot());
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
            dialog.filledVerticalBinding.dialogTitle2.setVisibility(View.GONE);
        } else {
            dialog.filledVerticalBinding.dialogTitle2.setText(title);
            dialog.filledVerticalBinding.dialogTitle2.setVisibility(View.VISIBLE);
        }

        if (StringUtils.isEmpty(message)) {
            dialog.filledVerticalBinding.dialogMsg2.setVisibility(View.GONE);
        } else {
            dialog.filledVerticalBinding.dialogMsg2.setText(message);
            dialog.filledVerticalBinding.dialogMsg2.setVisibility(View.VISIBLE);
        }
        dialog.filledVerticalBinding.btnOne.setVisibility(View.GONE);
        dialog.filledVerticalBinding.btnTwo.setVisibility(View.GONE);

        return dialog;
    }

    private static FilledVerticalButtonAlertDialog makeDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                                                    CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage) {
        FilledVerticalButtonAlertDialog dialog = new FilledVerticalButtonAlertDialog(context);
        if (StringUtils.isEmpty(title)) {
            dialog.filledVerticalBinding.dialogTitle.setVisibility(View.GONE);
        } else {
            dialog.filledVerticalBinding.dialogTitle.setText(title);
            dialog.filledVerticalBinding.dialogTitle.setVisibility(View.VISIBLE);
        }

        if (StringUtils.isEmpty(message)) {
            dialog.filledVerticalBinding.dialogMsg.setVisibility(View.GONE);
        } else {
            dialog.filledVerticalBinding.dialogMsg.setText(message);
            dialog.filledVerticalBinding.dialogMsg.setVisibility(View.VISIBLE);
        }

        if (firstButtonImage == null && secondButtonImage == null) {
            dialog.filledVerticalBinding.btnOne.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            dialog.filledVerticalBinding.btnTwo.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            dialog.filledVerticalBinding.btnOne.setCompoundDrawablesWithIntrinsicBounds(firstButtonImage, null, null, null);
            dialog.filledVerticalBinding.btnTwo.setCompoundDrawablesWithIntrinsicBounds(secondButtonImage, null, null, null);
        }

        dialog.filledVerticalBinding.btnOne.setText(firstButtonTitle);
        dialog.filledVerticalBinding.btnOne.setOnClickListener(view -> {
            if (firstButtonListener != null) {
                firstButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        dialog.filledVerticalBinding.btnTwo.setText(secondButtonTitle);
        dialog.filledVerticalBinding.btnTwo.setOnClickListener(view -> {
            if (secondButtonListener != null) {
                secondButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        dialog.filledVerticalBinding.btnLine2.setVisibility(View.VISIBLE);
        dialog.filledVerticalBinding.btnThree.setVisibility(View.GONE);

        return dialog;
    }

    private static FilledVerticalButtonAlertDialog makeTripleButton(Context context, CharSequence title, CharSequence message, CharSequence firstButtonTitle, View.OnClickListener firstButtonListener, Drawable firstButtonImage,
                                                                    CharSequence secondButtonTitle, View.OnClickListener secondButtonListener, Drawable secondButtonImage,
                                                                    CharSequence thirdButtonTitle, View.OnClickListener thirdButtonListener, Drawable thirdButtonImage) {
        FilledVerticalButtonAlertDialog dialog = makeDoubleButton(context, title, message, firstButtonTitle, firstButtonListener, firstButtonImage, secondButtonTitle, secondButtonListener, secondButtonImage);

        if (thirdButtonImage == null) {
            dialog.filledVerticalBinding.btnThree.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            dialog.filledVerticalBinding.btnThree.setCompoundDrawablesWithIntrinsicBounds(thirdButtonImage, null, null, null);
        }

        if (!StringUtils.isEmpty(message))
            dialog.filledVerticalBinding.btnLine.setVisibility(View.VISIBLE);

        dialog.filledVerticalBinding.btnThree.setText(thirdButtonTitle);
        dialog.filledVerticalBinding.btnThree.setVisibility(View.VISIBLE);
        dialog.filledVerticalBinding.btnLine2.setVisibility(View.VISIBLE);
        dialog.filledVerticalBinding.btnLine3.setVisibility(View.VISIBLE);

        dialog.filledVerticalBinding.btnThree.setOnClickListener(view -> {
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
            dialog.filledVerticalBinding.btnFour.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            dialog.filledVerticalBinding.btnFour.setCompoundDrawablesWithIntrinsicBounds(quadrupleButtonImage, null, null, null);
        }

        if (StringUtils.isEmpty(message)) {
            dialog.filledVerticalBinding.dialogMsg.setVisibility(View.GONE);
        } else {
            dialog.filledVerticalBinding.dialogMsg.setText(message);
            dialog.filledVerticalBinding.dialogMsg.setVisibility(View.VISIBLE);
            dialog.filledVerticalBinding.btnLine.setVisibility(View.VISIBLE);
        }

        dialog.filledVerticalBinding.btnLine2.setVisibility(View.VISIBLE);
        dialog.filledVerticalBinding.btnLine3.setVisibility(View.VISIBLE);
        dialog.filledVerticalBinding.btnLine4.setVisibility(View.VISIBLE);

        dialog.filledVerticalBinding.btnFour.setText(quadrupleButtonTitle);
        dialog.filledVerticalBinding.btnFour.setVisibility(View.VISIBLE);

        dialog.filledVerticalBinding.btnFour.setOnClickListener(view -> {
            if (quadrupleButtonListener != null) {
                quadrupleButtonListener.onClick(view);
            }
            dialog.dismiss();
        });

        return dialog;
    }
}
