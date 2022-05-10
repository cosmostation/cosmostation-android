package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ASSETMANTLE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITCANNA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITSONG_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERBERUS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CHIHUAHUA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COMDEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRESCENT_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CUDOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KONSTELL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.LUM_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OMNIFLIX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PROVENANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.STARGAZE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class MnemonicCheckActivity extends BaseActivity {

    private Toolbar             mToolbar;
    private CardView            mMnemonicLayer;
    private LinearLayout[]      mWordsLayer = new LinearLayout[24];
    private TextView[]          mTvWords = new TextView[24];
    private Button              mCopy, mOk;

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
        mOk             = findViewById(R.id.btn_ok);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i = 0; i < mWordsLayer.length; i++) {
            mWordsLayer[i] = findViewById(getResources().getIdentifier("layer_mnemonic_" + i , "id", this.getPackageName()));
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }

        mEntropy = getIntent().getStringExtra("entropy");
        Account toCheck = getBaseDao().onSelectAccount(""+getIntent().getLongExtra("checkid", -1));
        mMnemonicLayer.setCardBackgroundColor(WDp.getChainBgColor(getBaseContext(), getChain(toCheck.baseChain)));
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
            } else if (getChain(toCheck.baseChain).equals(SECRET_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_secret));
            } else if (getChain(toCheck.baseChain).equals(AKASH_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_akash));
            } else if (getChain(toCheck.baseChain).equals(OKEX_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_okex));
            } else if (getChain(toCheck.baseChain).equals(PERSIS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_persis));
            } else if (getChain(toCheck.baseChain).equals(SENTINEL_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_sentinel));
            } else if (getChain(toCheck.baseChain).equals(FETCHAI_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_fetch));
            } else if (getChain(toCheck.baseChain).equals(CRYPTO_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_cryto));
            } else if (getChain(toCheck.baseChain).equals(SIF_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_sif));
            } else if (getChain(toCheck.baseChain).equals(KI_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_ki));
            } else if (getChain(toCheck.baseChain).equals(OSMOSIS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_osmosis));
            } else if (getChain(toCheck.baseChain).equals(MEDI_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_medi));
            } else if (getChain(toCheck.baseChain).equals(EMONEY_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_emoney));
            } else if (getChain(toCheck.baseChain).equals(RIZON_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_rizon));
            } else if (getChain(toCheck.baseChain).equals(JUNO_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_juno));
            } else if (getChain(toCheck.baseChain).equals(REGEN_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_regen));
            } else if (getChain(toCheck.baseChain).equals(BITCANNA_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_bitcanna));
            } else if (getChain(toCheck.baseChain).equals(ALTHEA_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_althea));
            } else if (getChain(toCheck.baseChain).equals(STARGAZE_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_stargaze));
            } else if (getChain(toCheck.baseChain).equals(GRABRIDGE_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_grabridge));
            } else if (getChain(toCheck.baseChain).equals(COMDEX_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_comdex));
            } else if (getChain(toCheck.baseChain).equals(INJ_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_inj));
            } else if (getChain(toCheck.baseChain).equals(BITSONG_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_bitsong));
            } else if (getChain(toCheck.baseChain).equals(DESMOS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_desmos));
            } else if (getChain(toCheck.baseChain).equals(LUM_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_lum));
            } else if (getChain(toCheck.baseChain).equals(CHIHUAHUA_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_chihuahua));
            } else if (getChain(toCheck.baseChain).equals(UMEE_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_umee));
            } else if (getChain(toCheck.baseChain).equals(AXELAR_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_axelar));
            } else if (getChain(toCheck.baseChain).equals(KONSTELL_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_konstellattion));
            } else if (getChain(toCheck.baseChain).equals(EVMOS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_evmos));
            } else if (getChain(toCheck.baseChain).equals(CUDOS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_cudos));
            } else if (getChain(toCheck.baseChain).equals(PROVENANCE_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_provenance));
            } else if (getChain(toCheck.baseChain).equals(CERBERUS_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_cerberus));
            } else if (getChain(toCheck.baseChain).equals(OMNIFLIX_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_omniflix));
            } else if (getChain(toCheck.baseChain).equals(CRESCENT_MAIN) || getChain(toCheck.baseChain).equals(CRESCENT_TEST)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_crescent));
            } else if (getChain(toCheck.baseChain).equals(ASSETMANTLE_MAIN)) {
                mWordsLayer[i].setBackground(getDrawable(R.drawable.box_round_mantle));
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
                AlertDialogUtils.showDoubleButtonDialog(MnemonicCheckActivity.this, getString(R.string.str_safe_copy_title), getString(R.string.str_safe_copy_msg),
                        getString(R.string.str_raw_copy), view -> onRawCopy(),
                        getString(R.string.str_safe_copy), view -> onSafeCopy());
            }
        });

        mOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartMainActivity(3);
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
