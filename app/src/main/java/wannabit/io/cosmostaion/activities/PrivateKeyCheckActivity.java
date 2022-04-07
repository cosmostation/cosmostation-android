package wannabit.io.cosmostaion.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import org.bitcoinj.crypto.DeterministicKey;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Copy_pKey;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;

public class PrivateKeyCheckActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private CardView mCardView;
    private TextView mPKey;
    private Button mCopy, mOk;

    private String mEntropy;
    private DeterministicKey deterministicKey;

    private String mKeyString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_key_check);

        mToolbar = findViewById(R.id.toolbar);
        mCardView = findViewById(R.id.card_root);
        mPKey = findViewById(R.id.private_key);
        mCopy = findViewById(R.id.btn_copy);
        mOk = findViewById(R.id.btn_ok);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCopy.setOnClickListener(this);
        mOk.setOnClickListener(this);

        account = getBaseDao().onSelectAccount("" + getIntent().getLongExtra("checkid", -1));
        mCardView.setCardBackgroundColor(WDp.getChainBgColor(getBaseContext(), BaseChain.getChain(account.baseChain)));
        onUpdateView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void onUpdateView() {
        if (account.fromMnemonic) {
            mEntropy = getIntent().getStringExtra("entropy");
            deterministicKey = WKey.getKeyWithPathfromEntropy(account, mEntropy);
            if (deterministicKey != null) {
                mKeyString = "0x" + deterministicKey.getPrivateKeyAsHex();
            }
        } else {
            String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + account.uuid, account.resource, account.spec);
            if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
                mKeyString = privateKey;
            } else {
                mKeyString = "0x" + privateKey;
            }
        }
        mCopy.setVisibility(View.VISIBLE);
        mPKey.setText(mKeyString);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCopy)) {
            Dialog_Safe_Copy_pKey delete = Dialog_Safe_Copy_pKey.newInstance();
            showDialog(delete);

        } else if (v.equals(mOk)) {
            startMainActivity(3);
        }
    }

    public void onRawCopy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("pKey", mKeyString);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getBaseContext(), R.string.str_copied, Toast.LENGTH_SHORT).show();
    }

    public void onSafeCopy() {
        getBaseDao().mCopyEncResult = CryptoHelper.doEncryptData(getBaseDao().mCopySalt, mKeyString, false);
        Toast.makeText(getBaseContext(), R.string.str_safe_copied, Toast.LENGTH_SHORT).show();
    }
}

