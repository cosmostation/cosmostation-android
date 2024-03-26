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
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentQrCodeBinding

class QrCodeEvmFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine

    private var isShowEvmAddress = true

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
                btnFilter.visibleOrGone(selectedEvmChain.supportCosmos)

                if (!selectedEvmChain.supportCosmos) {
                    setQrAddress(selectedEvmChain.address)

                } else {
                    updateAddress()
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

    private fun updateAddress() {
        if (isShowEvmAddress) {
            setQrAddress(ByteUtils.convertBech32ToEvm(selectedEvmChain.address))
            binding.addressTitle.text = getString(R.string.str_eth_style_address)
        } else {
            setQrAddress(selectedEvmChain.address)
            binding.addressTitle.text = getString(R.string.str_cosmos_style_address)
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
            btnFilter.setOnClickListener {
                isShowEvmAddress = !isShowEvmAddress
                updateAddress()
            }

            addressView.setOnClickListener {
                val copyAddress = if (isShowEvmAddress) {
                    if (selectedEvmChain.supportCosmos) {
                        ByteUtils.convertBech32ToEvm(selectedEvmChain.address)
                    } else {
                        selectedEvmChain.address
                    }
                } else {
                    selectedEvmChain.address
                }
                val clipboard =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", copyAddress)
                clipboard.setPrimaryClip(clip)
                requireActivity().makeToast(R.string.str_msg_address_copied)
            }

            btnShare.setOnClickListener {
                val shareAddress = if (isShowEvmAddress) {
                    if (selectedEvmChain.supportCosmos) {
                        ByteUtils.convertBech32ToEvm(selectedEvmChain.address)
                    } else {
                        selectedEvmChain.address
                    }
                } else {
                    selectedEvmChain.address
                }

                val intent = Intent()
                intent.action = Intent.ACTION_SEND
                intent.putExtra(Intent.EXTRA_TEXT, shareAddress)
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