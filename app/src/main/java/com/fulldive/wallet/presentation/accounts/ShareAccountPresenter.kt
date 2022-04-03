package com.fulldive.wallet.presentation.accounts

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.fulldive.wallet.di.modules.DefaultPresentersModule
import com.fulldive.wallet.extensions.safeSingle
import com.fulldive.wallet.extensions.withDefaults
import com.fulldive.wallet.interactors.QRCodeInteractor
import com.fulldive.wallet.presentation.base.BaseMoxyPresenter
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wannabit.io.cosmostaion.R
import javax.inject.Inject

@ProvidedBy(DefaultPresentersModule::class)
class ShareAccountPresenter @Inject constructor(
    private val qrCodeInteractor: QRCodeInteractor
) : BaseMoxyPresenter<ShareAccountMoxyView>() {

    lateinit var address: String

    fun onShareTextClicked(context: Context) {
        Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, address)
            type = "text/plain"
        }
            .let { intent -> Intent.createChooser(intent, context.getString(R.string.str_share)) }
            .let(viewState::startActivity)
    }

    fun onShareQRClicked(context: Context) {
        qrCodeInteractor
            .generate(address)
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { bitmap ->
                checkPermissions(context)
                    .toSingleDefault(bitmap)
            }
            .observeOn(Schedulers.io())
            .flatMap { bitmap ->
                saveBitmap(context, bitmap)
                    .onErrorResumeNext {
                        Single.error(Exception("Can't save QR code to storage."))
                    }
            }
            .map { uri ->
                Intent.createChooser(
                    Intent(Intent.ACTION_SEND).apply {
                        type = "image/jpeg"
                        putExtra(Intent.EXTRA_TEXT, address)
                        putExtra(Intent.EXTRA_STREAM, uri)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    },
                    context.getString(R.string.str_share)
                )
            }
            .withDefaults()
            .compositeSubscribe(
                onSuccess = { intent ->
                    viewState.startActivity(intent)
                    viewState.dismiss()
                }
            )
    }

    private fun saveBitmap(context: Context, bitmap: Bitmap): Single<Uri> {
        return safeSingle {
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, address)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }
            val uri = context.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
            if (uri != null) {
                context.contentResolver.openOutputStream(uri).use { outstream ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outstream)
                }
            }
            uri
        }
    }

    private fun checkPermissions(context: Context): Completable {
        return Completable.create { emitter ->
            TedPermission(context)
                .setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        emitter.onComplete()
                    }

                    override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                        emitter.tryOnError(Exception(context.getString(R.string.error_permission)))
                    }
                })
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .setRationaleMessage(context.getString(R.string.str_permission_qr))
                .check()
        }
    }
}