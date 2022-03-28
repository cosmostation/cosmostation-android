package wannabit.io.cosmostaion.activities.chains.starname;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.squareup.picasso.Picasso;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_Wallet_for_Starname;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class StarNameResourceAddActivity extends BaseActivity implements View.OnClickListener {

    private Button mCancel, mConfirm;
    private LinearLayout mWallet, mScan, mPaste;
    private EditText mUserInput;
    private ImageView mChainImg;
    private TextView mChainName;

    private StarnameAssets mStarNameAsset;
    private BaseChain mTochain;
    private Types.Resource mStarNameResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_resource_add);

        mCancel = findViewById(R.id.btn_cancel);
        mConfirm = findViewById(R.id.btn_confirm);
        mWallet = findViewById(R.id.btn_wallet);
        mScan = findViewById(R.id.btn_qr);
        mPaste = findViewById(R.id.btn_paste);

        mUserInput = findViewById(R.id.user_inputs);
        mChainImg = findViewById(R.id.chainImg);
        mChainName = findViewById(R.id.chainName);

        try {
            mStarNameResource = Types.Resource.parseFrom(getIntent().getByteArrayExtra("resource"));
        } catch (Exception e) {
            mStarNameAsset = getIntent().getParcelableExtra("asset");
        }

        if (mStarNameResource != null) {
            Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(mStarNameResource.getUri())).fit().into(mChainImg);
            mChainName.setText(StarnameAssets.getStarNameChainName(mStarNameResource.getUri()));
            if (!TextUtils.isEmpty(mStarNameResource.getResource())) {
                mUserInput.setText(mStarNameResource.getResource());
            }
        } else if (mStarNameAsset != null) {
            if (mStarNameAsset.chainName != null) {
                mTochain = BaseChain.getChain(mStarNameAsset.chainName);
            }
            Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(mStarNameAsset.url)).fit().into(mChainImg);
            mChainName.setText(StarnameAssets.getStarNameChainName(mStarNameAsset.url));
        }

        mCancel.setOnClickListener(this);
        mConfirm.setOnClickListener(this);
        mWallet.setOnClickListener(this);
        mScan.setOnClickListener(this);
        mPaste.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onHideKeyboard();
        Types.Resource temp;
        if (v.equals(mCancel)) {
            finish();

        } else if (v.equals(mConfirm)) {
            final String userinput = mUserInput.getText().toString().trim();

            if (TextUtils.isEmpty(userinput)) {
                Toast.makeText(getBaseContext(), R.string.error_invalid_address_pubkey, Toast.LENGTH_SHORT).show();
                return;

            } else if (mStarNameAsset != null && mStarNameAsset.url != null) {
                if (mTochain != null) {
                    if (!WDp.isValidChainAddress(mTochain, userinput)) {
                        Toast.makeText(getBaseContext(), R.string.error_invalid_address_pubkey, Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                temp = Types.Resource.newBuilder().setUri(mStarNameAsset.url).setResource(userinput).build();
            } else {
                temp = Types.Resource.newBuilder().setUri(mStarNameResource.getUri()).setResource(userinput).build();
            }

            mStarNameResource = temp;
            Intent result = getIntent();
            result.putExtra("resource", mStarNameResource.toByteArray());
            setResult(Activity.RESULT_OK, result);
            finish();

        } else if (v.equals(mWallet)) {
            if (mStarNameAsset != null) {
                if (mStarNameAsset.chainName == null) {
                    Toast.makeText(getBaseContext(), R.string.error_not_support_cosmostation, Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getBaseDao().onSelectAccountsByChain(mTochain).size() <= 0) {
                    Toast.makeText(this, getString(R.string.error_no_wallet_this_chain), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Bundle bundle = new Bundle();
            if (mStarNameAsset != null) {
                bundle.putParcelable("asset", mStarNameAsset);
            } else if (mStarNameResource != null) {
                bundle.putString("chainUri", mStarNameResource.getUri());
            }
            Dialog_Wallet_for_Starname dialog = Dialog_Wallet_for_Starname.newInstance(bundle);
            dialog.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mScan)) {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setOrientationLocked(true);
            integrator.initiateScan();

        } else if (v.equals(mPaste)) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard.getPrimaryClip() != null && clipboard.getPrimaryClip().getItemCount() > 0) {
                String userPaste = clipboard.getPrimaryClip().getItemAt(0).coerceToText(this).toString().trim();
                if (TextUtils.isEmpty(userPaste)) {
                    Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
                    return;
                }
                mUserInput.setText(userPaste);

            } else {
                Toast.makeText(this, R.string.error_clipboard_no_data, Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onChoiceStarnameResourceAddress(String address) {
        WLog.w("address " + address);
        mUserInput.setText(address);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                mUserInput.setText(result.getContents().trim());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
