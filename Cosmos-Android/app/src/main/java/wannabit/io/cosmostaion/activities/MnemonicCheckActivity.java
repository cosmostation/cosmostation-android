package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class MnemonicCheckActivity extends BaseActivity {

    private Toolbar             mToolbar;
    private TextView[]          mTvWords = new TextView[24];

    private ArrayList<String>   mWords = new ArrayList<>();
    private byte[]              mEntropy;
    private String              mHDSeed;
    private Account             mAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnemonic_check);
        mToolbar  = findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        for(int i = 0; i < mTvWords.length; i++) {
            mTvWords[i] = findViewById(getResources().getIdentifier("tv_mnemonic_" + i , "id", this.getPackageName()));
        }

        mHDSeed = getIntent().getStringExtra("seed");
        long id = getIntent().getLongExtra("checkid", -1);



        WLog.w("HDSeed : "  + mHDSeed);
        WLog.w("id : "  + id);

        mHDSeed = "7ee3c3adf3d3593f893b7b464d3ac9a6c5ef74e68adbb0b5819a5531a37d663502fcff28a2470d6592525db0bc491e4bf32fe180bdaf95ce52652e31f154d9d6";
        ArrayList<String> mWords = new ArrayList<String>(WKey.getRandomMnemonic(WUtil.HexStringToByteArray(mHDSeed)));
        WLog.w("mWords : " + mWords.size());
        for(String word : mWords) {
            WLog.w("word : " + word);
        }
    }
}
