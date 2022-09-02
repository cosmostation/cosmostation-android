package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class AccountShowDialog extends DialogFragment {

    private Button btn_nega, btn_posi;
    private TextView mTitle, mAddress;
    private ImageView mQr;
    private Bitmap mBitmap;

    public static AccountShowDialog newInstance(Bundle bundle) {
        AccountShowDialog frag = new AccountShowDialog();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_account_show, null);
        btn_nega = view.findViewById(R.id.btn_nega);
        btn_posi = view.findViewById(R.id.btn_posi);
        mTitle = view.findViewById(R.id.wallet_name);
        mAddress = view.findViewById(R.id.wallet_address_tv);
        mQr = view.findViewById(R.id.wallet_address_qr);
        final String address = getArguments().getString("address");

        mTitle.setText(getArguments().getString("title"));
        mAddress.setText(address);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            mBitmap = toBitmap(qrCodeWriter.encode(address, BarcodeFormat.QR_CODE, 480, 480));
            mQr.setImageBitmap(mBitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        btn_nega.setOnClickListener(v -> {
            ((BaseActivity) getActivity()).onShareType(getArguments().getString("address"));
            getDialog().dismiss();

        });

        btn_posi.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("address", address);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getActivity(), R.string.str_copied, Toast.LENGTH_SHORT).show();

            getDialog().dismiss();
        });

        Dialog dialog = new AlertDialog.Builder(getActivity()).setView(view).setCancelable(true).create();
        dialog.getWindow().setBackgroundDrawableResource(R.color.colorTrans);
        return dialog;
    }

    private static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

}
