package wannabit.io.cosmostaion.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;

import wannabit.io.cosmostaion.R;

/**
 * Created by yongjoojung on 2017. 7. 2..
 */

public class DeviceUuidFactory {

    private static final String PREFS_FILE = "device_id.xml";
    private static final String PREFS_DEVICE_ID = "device_id";
    private volatile static UUID uuid;

    public DeviceUuidFactory(final Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
                    final String id = prefs.getString(PREFS_DEVICE_ID, null);
                    if (id != null) {
                        uuid = UUID.fromString(id);
                    } else {
                        final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                        if (!"9774d56d682e549c".equals(androidId)) {
                            try {
                                uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                            } catch (UnsupportedEncodingException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            new TedPermission(context)
                                    .setPermissionListener(new PermissionListener() {
                                        @Override
                                        public void onPermissionGranted() {
                                            try {
                                                @SuppressLint("MissingPermission") final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                                                uuid = TextUtils.isEmpty(deviceId) ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.randomUUID();
                                            } catch (UnsupportedEncodingException e) {
                                                throw new RuntimeException(e);
                                            }
                                        }

                                        @Override
                                        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                                            Toast.makeText(context, R.string.error_permission, Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .setPermissions(android.Manifest.permission.READ_PHONE_STATE)
                                    .setRationaleMessage(context.getString(R.string.str_permission_push))
                                    .check();
                        }
                        // Write the value out to the prefs file
                        prefs.edit()
                                .putString(PREFS_DEVICE_ID, uuid.toString())
                                .commit();
                    }
                }
            }
        }
    }

    public UUID getDeviceUuid() {
        return uuid;
    }

    public String getDeviceUuidS() {
        return uuid.toString();
    }
}
