package wannabit.io.cosmostaion.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.orogvany.bip32.Network;
import com.github.orogvany.bip32.wallet.CoinType;
import com.github.orogvany.bip32.wallet.HdAddress;
import com.github.orogvany.bip32.wallet.HdKeyGenerator;
import com.github.orogvany.bip39.Language;
import com.github.orogvany.bip39.MnemonicGenerator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.romainpiel.shimmer.ShimmerTextView;

import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSASecurityProvider;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;

import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.crypto.Sha256;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private ImageView       bgImg, bgImgGr;
    private ShimmerTextView logoTitle;
    private LinearLayout    bottomLayer1, bottomLayer2, bottomDetail, btnImportMnemonic, btnWatchAddress;
    private Button          mCreate, mImport;
    private LinearLayout    mNmemonicLayer, mWatchLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        bgImg               = findViewById(R.id.intro_bg);
        bgImgGr             = findViewById(R.id.intro_bg_gr);
        logoTitle           = findViewById(R.id.logo_title);
        bottomLayer1        = findViewById(R.id.bottom_layer1);
        bottomLayer2        = findViewById(R.id.bottom_layer2);
        bottomDetail        = findViewById(R.id.import_detail);
        btnImportMnemonic   = findViewById(R.id.btn_import_mnemonic);
        btnWatchAddress     = findViewById(R.id.btn_watch_address);
        mImport             = findViewById(R.id.btn_import);
        mCreate             = findViewById(R.id.btn_create);
        mNeedLeaveTime = false;

        mNmemonicLayer = findViewById(R.id.import_mnemonic_layer);
        mWatchLayer = findViewById(R.id.import_watch_layer);


        mImport.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        btnImportMnemonic.setOnClickListener(this);
        btnWatchAddress.setOnClickListener(this);

        Bundle pushBundle = getIntent().getExtras();
        if(pushBundle != null) {
            Set<String> keys = pushBundle.keySet();
            for (String key:keys) {
                WLog.w("push " + key + " : " + pushBundle.get(key));
            }
        }

//        WLog.w("UUID  " + new DeviceUuidFactory(this).getDeviceUuidS());
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
//            WLog.w("" + ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).isBackgroundRestricted());
//        }
//        WLog.w("FCM token Already : " + FirebaseInstanceId.getInstance().getInstanceId().getResult().getToken());
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        WLog.w("FCM token new : " + token);
                    }
                });


    }
    private final static int HardenedKeyStart = 0x80000000;

    private static String bytes2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String tmp = null;
        for (byte b : bytes) {
            tmp = Integer.toHexString(0xFF & b);
            if (tmp.length() == 1) {
                tmp = "0" + tmp;
            }
            sb.append(tmp);
        }
        return sb.toString();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getBaseDao().onSelectAccounts().size() == 0) {
                    onInitView();
                } else {
                    if(getBaseApplication().needShowLockScreen()) {
                        Intent intent = new Intent(IntroActivity.this, AppLockActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                    } else {
                        onStartMainActivity();
                    }
                }
            }
        }, 2500);


        String word = "guide worth axis butter craft donkey beef carry mechanic road seven food example ensure tip unit various flight antenna shuffle drill slim eyebrow lava";
        HdKeyGenerator hdKeyGenerator = new HdKeyGenerator();
        MnemonicGenerator mNemonicGenerator = new MnemonicGenerator();
        byte[] seed = mNemonicGenerator.getSeedFromWordlist(word, "", Language.english);
        WLog.w("seed " + WUtil.ByteArrayToHexString(seed));
        HdAddress address = hdKeyGenerator.getAddressFromSeed(seed, Network.mainnet, CoinType.semux);

//        String thisHex = WUtil.ByteArrayToHexString(address.getPublicKey().getPublicKey());
//        WLog.w("thisHex " + thisHex);
//        String thisHex2 = thisHex.substring(2, thisHex.length());
//        WLog.w("thisHex2 " + thisHex2);
//
//        byte[] publickKeyByte = Arrays.copyOfRange(address.getPublicKey().getPublicKey(), 1, address.getPublicKey().getPublicKey().length);
//        WLog.w("publickKeyByte " + WUtil.ByteArrayToHexString(publickKeyByte));


//        EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName(EdDSANamedCurveTable.ED_25519);
//        EdDSAPrivateKey fromHdAddress = new EdDSAPrivateKey(new EdDSAPrivateKeySpec(address.getPrivateKey().getPrivateKey(), spec));
//        Log.w("address PrivateKey ", WUtil.ByteArrayToHexString(address.getPrivateKey().getPrivateKey()));
//
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(word.split(" ")));
        String wseed = WKey.getStringHdSeedFromWords(list);
        WLog.w("wseed " + wseed);
        WLog.w("entropy " + WUtil.ByteArrayToHexString(WKey.toEntropy(list)));
//        DeterministicKey mMasterKey = HDKeyDerivation.createMasterPrivateKey(WUtil.HexStringToByteArray(wseed));
    }

    private void onInitView() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in5 );
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out5 );
        bgImgGr.startAnimation(fadeInAnimation);
        bgImg.startAnimation(fadeOutAnimation);

        final Animation mFadeInAni = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        Animation mFadeOutAni = AnimationUtils.loadAnimation(this, R.anim.fade_out2);
        mFadeOutAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottomLayer2.setVisibility(View.VISIBLE);
                bottomLayer2.startAnimation(mFadeInAni);
            }
        });
        bottomLayer1.startAnimation(mFadeOutAni);


//        logoTitle.setVisibility(View.VISIBLE);
//        Shimmer shimmer = new Shimmer();
//        shimmer.setDuration(1500)
//                .setStartDelay(600)
//                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
//        shimmer.start(logoTitle);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mImport)) {
            mImport.setVisibility(View.GONE);
            bottomDetail.setVisibility(View.VISIBLE);

            Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in4);
            mNmemonicLayer.startAnimation(fadein);
            mWatchLayer.startAnimation(fadein);


        } else if (v.equals(mCreate)) {
            startActivity(new Intent(IntroActivity.this, CreateActivity.class));

        } else if (v.equals(btnImportMnemonic)) {
            startActivity(new Intent(IntroActivity.this, RestoreActivity.class));

        } else if (v.equals(btnWatchAddress)) {
            startActivity(new Intent(IntroActivity.this, WatchingAccountAddActivity.class));
        }

    }


    private void onChangeImageWithFadeInAndOut( Context context, final ImageView imageView, final int resID ){

        final Animation fadeInAnimation = AnimationUtils.loadAnimation( context, R.anim.fade_in );
        Animation fadeOutAnimation = AnimationUtils.loadAnimation( context, R.anim.fade_out );
        fadeOutAnimation.setAnimationListener( new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                imageView.setImageResource( resID );
                imageView.startAnimation( fadeInAnimation );
            }
        });

        imageView.startAnimation( fadeOutAnimation );

    }
}

