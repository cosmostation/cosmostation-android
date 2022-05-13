package wannabit.io.cosmostaion.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class Dialog_AccountShow extends DialogFragment {

    private Button      btn_nega, btn_posi;
    private TextView    mTitle, mAddress;
    private ImageView   mQr;


    private Bitmap          mBitmap;

    public static Dialog_AccountShow newInstance(Bundle bundle) {
        Dialog_AccountShow frag = new Dialog_AccountShow();
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return inflater.inflate(R.layout.dialog_account_show, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        btn_nega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)getActivity()).onShareType(getArguments().getString("address"));
                getDialog().dismiss();

            }
        });

        btn_posi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager)getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("address", address);
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getActivity(), R.string.str_copied, Toast.LENGTH_SHORT).show();

                getDialog().dismiss();
            }
        });
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
