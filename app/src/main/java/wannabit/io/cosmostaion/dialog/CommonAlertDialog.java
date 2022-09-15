package wannabit.io.cosmostaion.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.commons.lang3.StringUtils;

import wannabit.io.cosmostaion.R;

public class CommonAlertDialog extends AlertDialog {
    TextView titleTextView;
    TextView messageTextView;
    Button rightButton;
    Button leftButton;
    ImageView headerImageView;
    View buttonBorder;

    public CommonAlertDialog(Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(R.drawable.layout_trans_with_border);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_template_default, null);

        headerImageView = view.findViewById(R.id.dialog_header_image);
        titleTextView = view.findViewById(R.id.dialog_title);
        messageTextView = view.findViewById(R.id.dialog_msg);
        rightButton = view.findViewById(R.id.btn_right);
        leftButton = view.findViewById(R.id.btn_left);
        buttonBorder = view.findViewById(R.id.btn_border);
        setView(view);
    }

    public static void showHeaderImageSingleButton(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener, int imageResourceId) {
        CommonAlertDialog dialog = makeHeaderImageSingleButton(context, title, message, buttonTitle, listener, imageResourceId);
        dialog.create();
        dialog.show();
    }

    public static void showSingleButton(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener, Boolean cancelable) {
        CommonAlertDialog dialog = makeSingleButton(context, title, message, buttonTitle, listener);
        if (!((Activity) context).isFinishing()) {
            dialog.setCancelable(cancelable);
            dialog.create();
            dialog.show();
        }
    }

    public static void showSingleButton(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener) {
        showSingleButton(context, title, message, buttonTitle, listener, true);
    }

    public static void showHeaderImageDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener, int imageResourceId) {
        CommonAlertDialog dialog = makeHeaderImageDoubleButton(context, title, message, leftButtonTitle, leftButtonListener, rightButtonTitle, rightButtonListener, imageResourceId);
        dialog.create();
        dialog.show();
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener, Boolean cancelable) {
        CommonAlertDialog dialog = makeDoubleButton(context, title, message, leftButtonTitle, leftButtonListener, rightButtonTitle, rightButtonListener);
        if (!((Activity) context).isFinishing()) {
            dialog.setCancelable(cancelable);
            dialog.create();
            dialog.show();
        }
    }

    public static void showDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener) {
        showDoubleButton(context, title, message, leftButtonTitle, leftButtonListener, rightButtonTitle, rightButtonListener, false);
    }

    private static CommonAlertDialog makeSingleButton(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener) {
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
            if (listener != null) {
                listener.onClick(view);
            }

            dialog.dismiss();
        });
        dialog.rightButton.setVisibility(View.GONE);
        dialog.buttonBorder.setVisibility(View.GONE);

        return dialog;
    }

    private static CommonAlertDialog makeHeaderImageSingleButton(Context context, CharSequence title, CharSequence message, CharSequence buttonTitle, View.OnClickListener listener, int imageResourceId) {
        CommonAlertDialog dialog = makeSingleButton(context, title, message, buttonTitle, listener);
        dialog.headerImageView.setVisibility(View.VISIBLE);
        dialog.headerImageView.setImageResource(imageResourceId);
        return dialog;
    }

    private static CommonAlertDialog makeDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener) {
        CommonAlertDialog dialog = makeSingleButton(context, title, message, leftButtonTitle, leftButtonListener);
        dialog.buttonBorder.setVisibility(View.VISIBLE);
        dialog.rightButton.setVisibility(View.VISIBLE);
        dialog.rightButton.setText(rightButtonTitle);
        dialog.rightButton.setOnClickListener(view -> {
            if (rightButtonListener != null) {
                rightButtonListener.onClick(view);
            }
            dialog.dismiss();
        });
        return dialog;
    }

    private static CommonAlertDialog makeHeaderImageDoubleButton(Context context, CharSequence title, CharSequence message, CharSequence leftButtonTitle, View.OnClickListener leftButtonListener, CharSequence rightButtonTitle, View.OnClickListener rightButtonListener, int imageResourceId) {
        CommonAlertDialog dialog = makeSingleButton(context, title, message, leftButtonTitle, leftButtonListener);
        dialog.headerImageView.setImageResource(imageResourceId);
        dialog.headerImageView.setVisibility(View.VISIBLE);
        dialog.buttonBorder.setVisibility(View.VISIBLE);
        dialog.rightButton.setVisibility(View.VISIBLE);
        dialog.rightButton.setText(rightButtonTitle);
        dialog.rightButton.setOnClickListener(view -> {
            if (rightButtonListener != null) {
                rightButtonListener.onClick(view);
            }
            dialog.dismiss();
        });
        return dialog;
    }

    public static Spanned highlightingText(String text) {
        return Html.fromHtml("<font color=\"#ff0000\">" + text + "</font>");
    }

}