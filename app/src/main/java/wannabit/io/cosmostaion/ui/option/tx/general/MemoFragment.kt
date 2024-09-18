package wannabit.io.cosmostaion.ui.option.tx.general

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentMemoBinding
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity


interface MemoListener {
    fun memo(memo: String)
}

class MemoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMemoBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private var isMemoConfirmed = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain, memo: String, listener: MemoListener
        ): MemoFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putString("memo", memo)
            }
            val fragment = MemoFragment()
            fragment.arguments = args
            fragment.memoListener = listener
            return fragment
        }
    }

    private var memoListener: MemoListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMemoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initTextByte()
        clickAction()
    }

    private fun initView() {
        binding.memoTxt.apply {
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                }
                binding.memoByte.visibleOrGone(fromChain is ChainBitCoin84)
                binding.memoMaxByte.visibleOrGone(fromChain is ChainBitCoin84)

                getString("memo")?.let { memo ->
                    val byteLength = memo.toByteArray(Charsets.UTF_8).size
                    binding.memoByte.text = "$byteLength"
                    text = Editable.Factory.getInstance().newEditable(memo)
                    setSelection(binding.memoTxt.text.toString().length)
                }
            }
        }
    }

    private fun initTextByte() {
        binding.apply {
            memoTxt.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (fromChain is ChainBitCoin84) {
                        val text = s.toString()
                        val byteLength = text.toByteArray(Charsets.UTF_8).size
                        memoByte.text = "$byteLength"
                        isMemoConfirmed = byteLength < 80
                        if (byteLength > 80) {
                            memoByte.setTextColor(
                                ContextCompat.getColorStateList(
                                    requireContext(),
                                    R.color.color_accent_red
                                )
                            )
                        } else {
                            memoByte.setTextColor(
                                ContextCompat.getColorStateList(
                                    requireContext(),
                                    R.color.color_base03
                                )
                            )
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun clickAction() {
        binding.apply {
            btnQr.setOnClickListener {
                val integrator = IntentIntegrator.forSupportFragment(this@MemoFragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QrCodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

            btnConfirm.setOnClickListener {
                if (!isMemoConfirmed) {
                    return@setOnClickListener
                }
                memoListener?.memo(memoTxt.text.toString().trim())
                dismiss()
            }
        }
    }

    private val qrCodeResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra(Intents.Scan.RESULT)?.trim()?.let { qrMemo ->
                    binding.memoTxt.text = Editable.Factory.getInstance().newEditable(qrMemo)
                    binding.memoTxt.setSelection(qrMemo.length)
                }
            }
        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}