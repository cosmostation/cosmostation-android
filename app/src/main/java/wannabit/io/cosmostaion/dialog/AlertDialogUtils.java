package wannabit.io.cosmostaion.dialog;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

import org.apache.commons.lang3.StringUtils;

public class AlertDialogUtils {
    public static void showSingleButtonDialog(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener) {
        CommonAlertDialog dialog = makeSingleButtonDialog(context, title, message, buttonTitle, listener);
        dialog.create();
        dialog.show();
    }

    public static void showHeaderImageSingleButtonDialog(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener, int imageResourceId) {
        CommonAlertDialog dialog = makeHeaderImageSingleButtonDialog(context, title, message, buttonTitle, listener, imageResourceId);
        dialog.create();
        dialog.show();
    }

    public static void showDoubleButtonDialog(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener) {
        CommonAlertDialog dialog = makeDoubleButtonDialog(context, title, message, leftButtonTitle, leftButtonListener, rightButtonTitle, rightButtonListener);
        dialog.create();
        dialog.show();
    }

    private static CommonAlertDialog makeSingleButtonDialog(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener) {
        CommonAlertDialog dialog = new CommonAlertDialog(context);
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

        dialog.leftButton.setText(buttonTitle);
        dialog.leftButton.setOnClickListener(view -> {
            listener.onClick(view);
            dialog.dismiss();
        });
        dialog.rightButton.setVisibility(View.GONE);

        return dialog;
    }

    private static CommonAlertDialog makeHeaderImageSingleButtonDialog(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener, int imageResourceId) {
        CommonAlertDialog dialog = makeSingleButtonDialog(context, title, message, buttonTitle, listener);
        dialog.headerImageView.setVisibility(View.VISIBLE);
        dialog.headerImageView.setImageResource(imageResourceId);
        return dialog;
    }

    private static CommonAlertDialog makeDoubleButtonDialog(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener) {
        CommonAlertDialog dialog = makeSingleButtonDialog(context, title, message, leftButtonTitle, leftButtonListener);
        dialog.rightButton.setVisibility(View.VISIBLE);
        dialog.rightButton.setText(rightButtonTitle);
        dialog.rightButton.setOnClickListener(view -> {
            rightButtonListener.onClick(view);
            dialog.dismiss();
        });
        return dialog;
    }

    public static Spanned highlightingText(String text) {
        return Html.fromHtml("<font color=\"#f31963\">" + text + "</font>");
    }
}