package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.WDp;

public class AccountDetailActivity extends BaseActivity implements View.OnClickListener {


    private Toolbar         mToolbar;
    private TextView        mToolbarTitle;
    private Button          mBtnCheck, mBtnDelete;

    private ImageView       mKeyTypeImg, mNameEditImg;
    private TextView        mAccountName;

    private ImageView       mBtnCopy, mBtnQr;
    private TextView        mAccountAddress, mAccountGenTime;
    private TextView        mAccountChain, mAccountState, mAccountPath, mImportMsg;
    private RelativeLayout  mPathLayer;


    private Account         mAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);
        mToolbar                = findViewById(R.id.tool_bar);
        mToolbarTitle           = findViewById(R.id.toolbar_title);
        mBtnCheck               = findViewById(R.id.btn_check);
        mBtnDelete              = findViewById(R.id.btn_delete);
        mKeyTypeImg             = findViewById(R.id.account_img);
        mNameEditImg            = findViewById(R.id.account_edit);
        mAccountName            = findViewById(R.id.account_name);
        mBtnCopy                = findViewById(R.id.account_copy);
        mBtnQr                  = findViewById(R.id.account_qr);
        mAccountAddress         = findViewById(R.id.account_address);
        mAccountGenTime         = findViewById(R.id.account_import_time);
        mAccountChain           = findViewById(R.id.account_chain);
        mAccountState           = findViewById(R.id.account_import_state);
        mAccountPath            = findViewById(R.id.account_path);
        mImportMsg              = findViewById(R.id.import_msg);
        mPathLayer              = findViewById(R.id.account_path_layer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnCheck.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mKeyTypeImg.setOnClickListener(this);
        mNameEditImg.setOnClickListener(this);
        mBtnCopy.setOnClickListener(this);
        mBtnQr.setOnClickListener(this);

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

    @Override
    protected void onResume() {
        super.onResume();
        mAccount = getBaseDao().onSelectAccount(getIntent().getStringExtra("id"));
        if(mAccount == null)  onBackPressed();

        onInitView();
    }


    private void onInitView() {
        if(TextUtils.isEmpty(mAccount.nickName)) {
            mToolbarTitle.setText("Wallet " + mAccount.id);
            mAccountName.setText("Wallet " + mAccount.id);
        } else {
            mToolbarTitle.setText(mAccount.nickName);
            mAccountName.setText(mAccount.nickName);
        }

        mAccountAddress.setText(mAccount.address);
        mAccountGenTime.setText(WDp.getDpTime(getBaseContext(), mAccount.importTime));
        mAccountChain.setText(mAccount.baseChain);

        if(mAccount.hasPrivateKey) {
            mKeyTypeImg.setImageDrawable(getResources().getDrawable(R.drawable.key_on));
            mAccountState.setText(getString(R.string.str_with_mnemonic));
            mAccountPath.setText(BaseConstant.KEY_PATH + mAccount.path);
            mPathLayer.setVisibility(View.VISIBLE);
            mImportMsg.setVisibility(View.GONE);
            mBtnCheck.setText(getString(R.string.str_check_mnemonic));

        } else {
            mKeyTypeImg.setImageDrawable(getResources().getDrawable(R.drawable.key_off));
            mAccountState.setText(getString(R.string.str_only_address));
            mPathLayer.setVisibility(View.GONE);
            mImportMsg.setVisibility(View.VISIBLE);
            mBtnCheck.setText(getString(R.string.str_import_mnemonic));

        }


    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCheck)) {

        } else if (v.equals(mBtnDelete)) {

        } else if (v.equals(mKeyTypeImg)) {

        } else if (v.equals(mNameEditImg)) {

        } else if (v.equals(mBtnCopy)) {

        } else if (v.equals(mBtnQr)) {

        }

    }
}
