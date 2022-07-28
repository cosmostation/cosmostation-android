package wannabit.io.cosmostaion.fragment.txs.nft;

import static wannabit.io.cosmostaion.base.BaseConstant.NFT_INFURA;
import static wannabit.io.cosmostaion.base.BaseConstant.STATION_NFT_DENOM;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.nft.NFTCreateActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WLog;

public class NFTCreateStep0Fragment extends BaseFragment implements View.OnClickListener {
    private File        tempFile;

    private Button      mBtnCancel, mBtnNext;

    private ImageView   mNftImg, mNftImgDel;
    private TextView    mNftImgAdd;
    private EditText    mNftName, mNftContent, mNftDenomId, mNftDenomName;

    private String      mHash;

    public static NFTCreateStep0Fragment newInstance() {
        return new NFTCreateStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_issue_nft_step0, container, false);
        mBtnCancel  = rootView.findViewById(R.id.btn_cancel);
        mBtnNext    = rootView.findViewById(R.id.btn_next);

        mNftImg     = rootView.findViewById(R.id.nft_img);
        mNftImgAdd  = rootView.findViewById(R.id.add_btn);
        mNftImgDel  = rootView.findViewById(R.id.delete_btn);
        mNftName    = rootView.findViewById(R.id.nft_name);
        mNftContent = rootView.findViewById(R.id.nft_content);
        mNftDenomId = rootView.findViewById(R.id.nft_denom_id);
        mNftDenomName = rootView.findViewById(R.id.nft_denom_name);

        mNftImgAdd.setOnClickListener(this);
        mNftImgDel.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);

        onUpdateImgView(null);
        String randomUUID = STATION_NFT_DENOM + UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        mNftDenomId.setText(randomUUID);
        mNftDenomName.setText(randomUUID);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnCancel)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mBtnNext)) {
            String name = mNftName.getText().toString().trim();
            String content = mNftContent.getText().toString().trim();
            String denomID = mNftDenomId.getText().toString().trim();
            String denomName = mNftDenomName.getText().toString().trim();
            if (mHash == null) {
                Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_no_nft_image), Toast.LENGTH_SHORT).show();
                return;
            }
            if (name.isEmpty()) {
                Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_no_nft_title), Toast.LENGTH_SHORT).show();
                return;
            }
            if (content.isEmpty()) {
                Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_no_nft_description), Toast.LENGTH_SHORT).show();
                return;
            }
            getSActivity().mNftName = name;
            getSActivity().mNftDescription = content;
            getSActivity().mNftDenomId = denomID;
            getSActivity().mNftDenomName = denomName;
            getSActivity().mNftHash = mHash;
            getSActivity().onNextStep();

        } else if (v.equals(mNftImgAdd)) {
            tedPermission();

        } else if (v.equals(mNftImgDel)) {
            onUpdateImgView(null);
        }
    }

    private void tedPermission() {
        new TedPermission(getSActivity()).setPermissionListener(new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                goToAlbum();
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getSActivity(), getSActivity().getString(R.string.str_permission_photo_title), Toast.LENGTH_SHORT).show();
            }
        }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).setRationaleMessage(getSActivity().getString(R.string.str_permission_photo_title)).check();
    }

    private void goToAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, 0);
    }

    private void onUploadImgView() {
        getSActivity().onShowWaitDialog();
        Thread upload = new Thread() {
            @Override
            public void run() {
                try {
                    IPFS ipfs = new IPFS("ipfs.infura.io", 5001, "/api/v0/", true);
                    NamedStreamable.InputStreamWrapper file = new NamedStreamable.InputStreamWrapper(new FileInputStream(tempFile));
                    MerkleNode response = ipfs.add(file).get(0);
                    mHash = response.hash.toBase58();
                    if (mHash != null) {
                        getSActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                onUpdateImgView(mHash);
                            }
                        });
                    } else {
                        Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_fail_upload_img), Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException ex) {
                    WLog.w("Error : " + ex.getMessage());
                }
            }
        };
        upload.start();
    }

    private void onUpdateImgView(String hash) {
        getSActivity().onHideWaitDialog();
        if (hash != null) {
            mNftImg.setClipToOutline(true);
            Glide.with(this).load(NFT_INFURA + hash).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(mNftImg);
            mNftImgAdd.setVisibility(View.GONE);
            mNftImgDel.setVisibility(View.VISIBLE);
        } else {
            mNftImg.setImageDrawable(null);
            mNftImgAdd.setVisibility(View.VISIBLE);
            mNftImgDel.setVisibility(View.GONE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            if (tempFile != null) {
                if (tempFile.exists()) {
                    if (tempFile.delete()) {
                        tempFile = null;
                    }
                }
            }
            return;
        }
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Uri photoUri = data.getData();
            Cursor cursor = null;
            try {
                String[] proj = {MediaStore.Images.Media.DATA};
                assert photoUri != null;
                cursor = getSActivity().getContentResolver().query(photoUri, proj, null, null, null);
                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                tempFile = new File(cursor.getString(column_index));
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
            onUploadImgView();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private NFTCreateActivity getSActivity() {
        return (NFTCreateActivity)getBaseActivity();
    }

}
