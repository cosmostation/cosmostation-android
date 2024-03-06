package wannabit.io.cosmostaion.ui.qr

import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
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
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentQrCodeBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentChainBinding

class QrCodeEvmFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine): QrCodeEvmFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = QrCodeEvmFragment()
            fragment.arguments = args
            return fragment
        }
    }

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

        initData()
        initView()
        setupClickAction()
    }

    private fun initData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedEvmChain", EthereumLine::class.java)
                ?.let { selectedEvmChain = it }
        } else {
            (arguments?.getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                selectedEvmChain = it
            }
        }
    }

    private fun initView() {
        BaseData.baseAccount?.let { account ->
            binding.apply {
                chainBadge.visibility = View.GONE
                chainTypeBadge.visibility = View.GONE
                chainSegment.visibleOrGone(selectedEvmChain.supportCosmos)

                if (!selectedEvmChain.supportCosmos) {
                    setQrAddress(selectedEvmChain.address)

                } else {
                    segmentView.setBackgroundResource(R.drawable.cell_search_bg)
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
                            0 -> {
                                segmentView.btnChain.text = "ETHEREUM"
                                segmentView.btnChain.drawable = ContextCompat.getDrawable(
                                    requireContext(), R.drawable.icon_ethereum_address
                                )
                                setQrAddress(ByteUtils.convertBech32ToEvm(selectedEvmChain.address))
                                addressTitle.text = getString(R.string.str_ethereum_address)
                            }

                            else -> {
                                segmentView.btnChain.drawable = ContextCompat.getDrawable(
                                    requireContext(), selectedEvmChain.addressLogo
                                )
                                segmentView.btnChain.text = selectedEvmChain.apiName.uppercase()
                                addressTitle.text = getString(R.string.str_address)
                            }
                        }
                    }
                }

                addressView.setBackgroundResource(R.drawable.cell_bg)
                chainName.text = selectedEvmChain.name
                accountPath.text = selectedEvmChain.getHDPath(account.lastHDPath)
                chainImg.setImageResource(selectedEvmChain.logo)
                chainBadge.visibility = View.GONE

                qrView.radius = resources.getDimension(R.dimen.space_8)
                qrImg.clipToOutline = true
            }
        }
    }

    private fun setQrAddress(selectAddress: String?) {
        binding.apply {
            val hints = mutableMapOf<EncodeHintType, Int>()
            hints[EncodeHintType.MARGIN] = 0

            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(
                selectAddress, BarcodeFormat.QR_CODE, 540, 540, hints
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
                        setQrAddress(ByteUtils.convertBech32ToEvm(selectedEvmChain.address))
                        addressTitle.text = getString(R.string.str_ethereum_address)
                    }

                    else -> {
                        setQrAddress(selectedEvmChain.address)
                        addressTitle.text = getString(R.string.str_address)
                    }
                }
            }

            addressView.setOnClickListener {
                val clipboard =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", selectedEvmChain.address)
                clipboard.setPrimaryClip(clip)
                requireActivity().makeToast(R.string.str_msg_address_copied)
            }

            btnShare.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, selectedEvmChain.address)
                intent.type = "text/plain"

                startActivity(Intent.createChooser(intent, "share"))
            }
        }
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun getBottomSheetDialogDefaultHeight(): Int {
        return getWindowHeight() * 13 / 14
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