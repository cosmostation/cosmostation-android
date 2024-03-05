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
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentQrCodeBinding


class QrCodeFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): QrCodeFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = QrCodeFragment()
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
            arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                selectedChain = it
            }
        }
    }

    private fun initView() {
        BaseData.baseAccount?.let { account ->
            binding.apply {
                setQrAddress(selectedChain.address)
                addressView.setBackgroundResource(R.drawable.cell_bg)
                chainName.text = selectedChain.name
                accountPath.text = selectedChain.getHDPath(account.lastHDPath)
                chainImg.setImageResource(selectedChain.logo)

                if (!selectedChain.isDefault) {
                    chainBadge.visibility = View.VISIBLE
                    chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                    chainBadge.setTextColor(
                        ContextCompat.getColor(
                            requireContext(), R.color.color_base02
                        )
                    )
                    chainBadge.text = requireActivity().getString(R.string.str_legacy)
                    when (selectedChain.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.text =
                                requireActivity().getString(R.string.str_ethsecp256k1)
                        }

                        "okt996_Secp" -> {
                            chainTypeBadge.text =
                                requireActivity().getString(R.string.str_secp256k1)
                        }

                        else -> {
                            chainTypeBadge.visibility = View.GONE
                        }
                    }

                } else {
                    chainBadge.visibility = View.GONE
                    chainTypeBadge.visibility = View.GONE
                }

                qrView.radius = resources.getDimension(R.dimen.space_8)
                qrImg.clipToOutline = true
            }
        }
    }

    private fun setQrAddress(selectAddress: String?) {
        binding.apply {
            chainSegment.visibility = View.GONE
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