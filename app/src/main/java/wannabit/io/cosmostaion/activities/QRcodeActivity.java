package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import wannabit.io.cosmostaion.R;

public class QRcodeActivity extends CaptureActivity {

    DecoratedBarcodeView barcodeView;
    CaptureManager capture;
    Button disconnectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        disconnectBtn = findViewById(R.id.btn_disconnect);
        barcodeView = findViewById(R.id.viewBarcode);

        capture = new CaptureManager(this, barcodeView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        disconnectBtn.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }


}