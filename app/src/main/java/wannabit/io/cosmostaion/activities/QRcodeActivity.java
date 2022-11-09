package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import wannabit.io.cosmostaion.R;

public class QRcodeActivity extends CaptureActivity {

    Button disconnectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        disconnectBtn = findViewById(R.id.btn_disconnect);
        disconnectBtn.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_qrcode);
        return findViewById(R.id.viewBarcode);
    }
}