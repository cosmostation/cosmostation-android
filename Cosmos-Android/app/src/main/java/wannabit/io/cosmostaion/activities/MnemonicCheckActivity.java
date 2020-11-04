package wannabit.io.cosmostaion.activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Copy;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

public class MnemonicCheckActivity extends BaseActivity {

    private Toolbar             mToolbar;
    private CardView            mMnemonicLayer;
    private LinearLayout[]      mWordsLayer = new LinearLayout[24];
    private TextView[]          mTvWords = new TextView[24];
    private Button              mCopy;

    private String              mEntropy;
    private ArrayList<String>   mWords = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_mnemonic_check);
        mToolbar        = findViewById(R.id.tool_bar);
        mMnemonicLayer  = findViewById(R.id.card_mnemonic_layer);
        mCopy           = findViewById(R.id.btn_copy);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i = 0; i < mWordsLayer.length; i++) {
            mWordsLayer[i] = findViewById(getResources().getIdentifier("layer_mnemonic_" + i , "id", this.getPackageName()));
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }

        mEntropy = getIntent().getStringExtra("entropy");
        Account toCheck = getBaseDao().onSelectAccount(""+getIntent().getLongExtra("checkid", -1));
        if (getChain(toCheck.baseChain).equals(COSMOS_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
        } else if (getChain(toCheck.baseChain).equals(IRIS_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
        } else if (getChain(toCheck.baseChain).equals(BNB_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
        } else if (getChain(toCheck.baseChain).equals(KAVA_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
        } else if (getChain(toCheck.baseChain).equals(IOV_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg6));
        } else if (getChain(toCheck.baseChain).equals(BAND_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg8));
        } else if (getChain(toCheck.baseChain).equals(CERTIK_MAIN)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg10));
        } else if (getChain(toCheck.baseChain).equals(BNB_TEST) || getChain(toCheck.baseChain).equals(KAVA_TEST) || getChain(toCheck.baseChain).equals(IOV_TEST) ||
                getChain(toCheck.baseChain).equals(OK_TEST) || getChain(toCheck.baseChain).equals(CERTIK_TEST)) {
            mMnemonicLayer.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));

        }
        mWords = new ArrayList<String>(WKey.getRandomMnemonic(WUtil.HexStringToByteArray(mEntropy)));

        for(int i = 0; i < mWordsLayer.length; i++) {
            if (getChain(toCheck.baseChain).equals(COSMOS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_atom));
            } else if (getChain(toCheck.baseChain).equals(IRIS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_iris));
            } else if (getChain(toCheck.baseChain).equals(BNB_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_bnb));
            } else if (getChain(toCheck.baseChain).equals(KAVA_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_kava));
            } else if (getChain(toCheck.baseChain).equals(IOV_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_iov));
            } else if (getChain(toCheck.baseChain).equals(BAND_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_band));
            } else if (getChain(toCheck.baseChain).equals(CERTIK_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_certik));
            } else if (getChain(toCheck.baseChain).equals(BNB_TEST) || getChain(toCheck.baseChain).equals(KAVA_TEST) || getChain(toCheck.baseChain).equals(IOV_TEST) ||
                    getChain(toCheck.baseChain).equals(OK_TEST) || getChain(toCheck.baseChain).equals(CERTIK_TEST)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_darkgray));
            }
            if(i >= mWords.size()) mWordsLayer[i].setVisibility(View.INVISIBLE);
            else mWordsLayer[i].setVisibility(View.VISIBLE);
        }

        for(int i = 0; i < mWords.size(); i++) {
            mTvWords[i].setText(mWords.get(i));
        }

        mCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Safe_Copy delete = Dialog_Safe_Copy.newInstance();
                delete.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(delete, "dialog").commitNowAllowingStateLoss();
            }
        });
    }

    public void onRawCopy() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        StringBuilder builder = new StringBuilder();
        for(String s : mWords) {
            if(builder.length() != 0)
                builder.append(" ");
            builder.append(s);
        }
        String data = builder.toString();
        ClipData clip = ClipData.newPlainText("my data", data);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getBaseContext(), R.string.str_copied, Toast.LENGTH_SHORT).show();

    }

    public void onSafeCopy() {
        StringBuilder builder = new StringBuilder();
        for(String s : mWords) {
            if(builder.length() != 0)
                builder.append(" ");
            builder.append(s);
        }
        String data = builder.toString();
        getBaseDao().mCopyEncResult = CryptoHelper.doEncryptData(getBaseDao().mCopySalt, data, false);
        Toast.makeText(getBaseContext(), R.string.str_safe_copied, Toast.LENGTH_SHORT).show();

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
}
