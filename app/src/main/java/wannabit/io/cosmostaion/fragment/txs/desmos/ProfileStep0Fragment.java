package wannabit.io.cosmostaion.fragment.txs.desmos;

import static wannabit.io.cosmostaion.base.BaseConstant.NFT_INFURA;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
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
import java.util.regex.Pattern;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.desmos.ProfileActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.DesmosProfileGrpcTask;
import wannabit.io.cosmostaion.utils.WLog;

public class ProfileStep0Fragment extends BaseFragment implements View.OnClickListener {
    private File        tempFile;

    private Button      mCancelBtn, mNextBtn;

    private ImageView   mProfileCoverImg, mProfileImg, mImgDel;
    private TextView    mImgAdd;

    private EditText    mProfileDtag, mProfileNickname, mProfileBio;

    private boolean     misProfile = true;
    private String      mCoverHash;
    private String      mProfileHash;

    public static ProfileStep0Fragment newInstance() {
        return new ProfileStep0Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_profile_step0, container, false);
        mCancelBtn                  = rootView.findViewById(R.id.btn_cancel);
        mNextBtn                    = rootView.findViewById(R.id.btn_next);

        mProfileCoverImg            = rootView.findViewById(R.id.profile_cover_img);
        mImgAdd                     = rootView.findViewById(R.id.add_btn);
        mImgDel                     = rootView.findViewById(R.id.delete_btn);
        mProfileImg                 = rootView.findViewById(R.id.profile_img);
        mProfileDtag                = rootView.findViewById(R.id.profile_dtag);
        mProfileNickname            = rootView.findViewById(R.id.profile_nickname);
        mProfileBio                 = rootView.findViewById(R.id.profile_bio);

        mCancelBtn.setOnClickListener(this);
        mNextBtn.setOnClickListener(this);
        mImgAdd.setOnClickListener(this);
        mImgDel.setOnClickListener(this);
        mProfileImg.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mCancelBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mNextBtn)) {
            String dtag = mProfileDtag.getText().toString().trim();
            String nickname = mProfileNickname.getText().toString().trim();
            String bio = mProfileBio.getText().toString().trim();
            if (dtag.isEmpty()) {
                Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_no_profile_dtag), Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Pattern.matches("^[A-Za-z0-9_]+$", dtag) || dtag.length() < 6 || dtag.length() > 30) {
                Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_profile_dtag_count), Toast.LENGTH_SHORT).show();
                return;
            }

            new DesmosProfileGrpcTask(getBaseApplication(), new TaskListener() {
                @Override
                public void onTaskResponse(TaskResult result) {
                    if (!result.isSuccess) {
                        getSActivity().mDtag = dtag;
                        getSActivity().mNickname = nickname;
                        getSActivity().mBio = bio;
                        getSActivity().mCoverImg = mCoverHash;
                        getSActivity().mProfileImg = mProfileHash;
                        getSActivity().onNextStep();

                    } else {
                        Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_already_registered_dtag), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }, getSActivity().mBaseChain, dtag).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (v.equals(mImgAdd)) {
            misProfile = true;
            tedPermission();

        } else if (v.equals(mProfileImg)) {
            misProfile = false;
            tedPermission();

        } else if (v.equals(mImgDel)) {
            onUpdateImgView(true, null);
            mCoverHash = null;
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
        if (misProfile) {
            startActivityForResult(intent, 0);
        } else {
            startActivityForResult(intent, 1);
        }
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
                    if (misProfile) {
                        mCoverHash = response.hash.toBase58();
                        if (mCoverHash != null) {
                            getSActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onUpdateImgView(true, mCoverHash);
                                }
                            });
                        } else {
                            Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_fail_upload_img), Toast.LENGTH_SHORT).show();
                        }

                    } else if (!misProfile) {
                        mProfileHash = response.hash.toBase58();
                        if (mProfileHash != null) {
                            getSActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onUpdateImgView(false, mProfileHash);
                                }
                            });
                        } else {
                            Toast.makeText(getSActivity(), getSActivity().getString(R.string.error_fail_upload_img), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (IOException ex) {
                    WLog.w("Error : " + ex.getMessage());
                }
            }
        };
        upload.start();
    }

    private void onUpdateImgView(boolean isProfile, String hash) {
        getSActivity().onHideWaitDialog();
        if (isProfile) {
            if (hash != null) {
                mProfileCoverImg.setClipToOutline(true);
                Glide.with(this).load(NFT_INFURA + hash).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(mProfileCoverImg);
                mImgAdd.setVisibility(View.GONE);
                mImgDel.setVisibility(View.VISIBLE);
            } else {
                mProfileCoverImg.setImageDrawable(null);
                mImgAdd.setVisibility(View.VISIBLE);
                mImgDel.setVisibility(View.GONE);
            }

        } else {
            if (hash != null) {
                mProfileImg.setClipToOutline(true);
                Glide.with(this).load(NFT_INFURA + hash).diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter().into(mProfileImg);
            } else {
                mProfileImg.setImageDrawable(null);
            }
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

        } else if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
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

    private ProfileActivity getSActivity() {
        return (ProfileActivity)getBaseActivity();
    }

}
