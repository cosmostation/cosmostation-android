package wannabit.io.cosmostaion.ui.dialog.qr

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentQrCodeBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentChainBinding


class QrCodeFragment(
    private val selectedChain: CosmosLine
) : BottomSheetDialogFragment() {

    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQrCodeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setupClickAction()
    }

    private fun initView() {
        BaseData.baseAccount?.let { account ->
            binding.apply {
                chainSegment.apply {
                    setSelectedBackground(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_accent_purple
                        )
                    )
                    setRipple(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_accent_purple
                        )
                    )
                }

                for (i in 0 until 2) {
                    val segmentView = ItemSegmentChainBinding.inflate(layoutInflater)
                    chainSegment.addView(
                        segmentView.root,
                        i,
                        LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                    )
                    when (i) {
                        0 -> segmentView.btnChain.text = selectedChain.apiName.uppercase()
                        else -> segmentView.btnChain.text = "ETHEREUM"
                    }
                }

                addressView.setBackgroundResource(R.drawable.cell_bg)
                chainName.text = selectedChain.name
                accountPath.text = selectedChain.getHDPath(account.lastHDPath)
                chainImg.setImageResource(selectedChain.logo)

                qrView.radius = resources.getDimension(R.dimen.space_8)
                qrImg.clipToOutline = true
                setQrAddress(selectedChain.address)
            }
        }
    }

    private fun setQrAddress(selectAddress: String?) {
        binding.apply {
            chainSegment.visibleOrGone(selectedChain.evmCompatible)
            val hints = mutableMapOf<EncodeHintType, Int>()
            hints[EncodeHintType.MARGIN] = 0

            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(
                selectAddress,
                BarcodeFormat.QR_CODE,
                540,
                540,
                hints
            )
            address.text = selectAddress
            qrImg.setImageBitmap(bitmap)
        }
    }

    private fun setupClickAction() {
        binding.apply {
            chainSegment.setOnPositionChangedListener { position ->
                when (position) {
                    0 -> {
                        setQrAddress(selectedChain.address)
                        addressTitle.text = getString(R.string.str_address)
                    }
                    else -> {
                        setQrAddress(ByteUtils.convertBech32ToEvm(selectedChain.address))
                        addressTitle.text = getString(R.string.str_ethereum_address)
                    }
                }
            }

            addressView.setOnClickListener {
                val clipboard =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", selectedChain.address)
                clipboard.setPrimaryClip(clip)
                requireActivity().makeToast(R.string.str_msg_address_copied)
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, selectedChain.address)
                intent.type = "text/plain"

                startActivity(Intent.createChooser(intent, "share"))
            }
        }
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 9 / 10
    }

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}