package wannabit.io.cosmostaion.activities;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.OutputStream;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_ChangeNickName;
import wannabit.io.cosmostaion.dialog.Dialog_DeleteConfirm;
import wannabit.io.cosmostaion.dialog.Dialog_ShareType;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

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
        onInitView();
    }

    public void onStartDeleteUser() {
        if(mAccount.hasPrivateKey) {
            Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_DELETE_ACCOUNT);
            intent.putExtra("id", mAccount.id);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
        } else {
            onDeleteAccount(mAccount.id);
        }
    }


    private void onInitView() {
        if(getIntent() == null || TextUtils.isEmpty(getIntent().getStringExtra("id"))) {
            onBackPressed();
        }
        mAccount = getBaseDao().onSelectAccount(getIntent().getStringExtra("id"));
        if(mAccount == null)  onBackPressed();

        if(TextUtils.isEmpty(mAccount.nickName)) {
            mToolbarTitle.setText(getString(R.string.str_my_wallet) + mAccount.id);
            mAccountName.setText(getString(R.string.str_my_wallet) + mAccount.id);
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
    public void onShareType() {
        super.onShareType();
        Dialog_ShareType show = Dialog_ShareType.newInstance(null);
        show.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();
    }


    @Override
    public void onShare(boolean isText) {
        if(isText) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, mAccount.address);
            shareIntent.setType("text/plain");
            startActivity(Intent.createChooser(shareIntent, "send"));

        } else {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            try {
                final Bitmap mBitmap = WUtil.toBitmap(qrCodeWriter.encode(mAccount.address, BarcodeFormat.QR_CODE, 480, 480));
                new TedPermission(this)
                        .setPermissionListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted() {
                                try {
                                    ContentValues values = new ContentValues();
                                    values.put(MediaStore.Images.Media.TITLE, mAccount.address);
                                    values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                                    Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                    OutputStream outstream = getContentResolver().openOutputStream(uri);
                                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream);
                                    outstream.close();

                                    Intent shareIntent = new Intent();
                                    shareIntent.setAction(Intent.ACTION_SEND);
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, mAccount.address);
                                    shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                                    shareIntent.setType("image/jpeg");
                                    shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    startActivity(Intent.createChooser(shareIntent, "send"));

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                Toast.makeText(getBaseContext(), R.string.error_permission, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .setRationaleMessage(getString(R.string.str_permission_qr))
                        .check();

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }


    public void onChangeNickName(String name) {
        mAccount.nickName = name;
        if(getBaseDao().onUpdateAccount(mAccount) > 0) {
            onInitView();
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCheck)) {
            if(mAccount.hasPrivateKey) {
                Intent intent = new Intent(AccountDetailActivity.this, PasswordCheckActivity.class);
                intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_CHECK_MNEMONIC);
                intent.putExtra("checkid", mAccount.id);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

            } else {
                startActivity(new Intent(AccountDetailActivity.this, RestoreActivity.class));
            }

        } else if (v.equals(mBtnDelete)) {
            Bundle bundle = new Bundle();
            bundle.putLong("id", mAccount.id);
            Dialog_DeleteConfirm delete = Dialog_DeleteConfirm.newInstance(bundle);
            delete.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(delete, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mNameEditImg)) {
            Bundle bundle = new Bundle();
            bundle.putLong("id", mAccount.id);
            bundle.putString("name", mAccount.nickName);
            Dialog_ChangeNickName delete = Dialog_ChangeNickName.newInstance(bundle);
            delete.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(delete, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnCopy)) {
            ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("address", mAccount.address);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, R.string.str_copied, Toast.LENGTH_SHORT).show();

        } else if (v.equals(mBtnQr)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            bundle.putString("title", mToolbarTitle.getText().toString());
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        }

    }


}
