package wannabit.io.cosmostaion.ui.tx.option.general

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.KeyListener
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
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
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
                binding.title.text = if (fromChain is ChainBitCoin86) {
                    "OP_RETURN"
                } else {
                    "Memo"
                }
                binding.memoByte.visibleOrGone(fromChain is ChainBitCoin86)
                binding.memoMaxByte.visibleOrGone(fromChain is ChainBitCoin86)
                binding.bytesTxt.visibleOrGone(fromChain is ChainBitCoin86)

                getString("memo")?.let { memo ->
                    val byteLength = memo.toByteArray(Charsets.UTF_8).size
                    binding.memoByte.text = "$byteLength" + " /"
                    text = Editable.Factory.getInstance().newEditable(memo)
                    setSelection(binding.memoTxt.text.toString().length)
                }
            }
        }
    }

    private fun initTextByte() {
        val originalKeyListener: KeyListener? = binding.memoTxt.keyListener
        var originalText = ""

        binding.apply {
            if (fromChain is ChainBitCoin86) {
                memoTxt.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        originalText = s.toString()
                    }

                    override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        val text = s.toString()
                        val byteLength = text.toByteArray(Charsets.UTF_8).size
                        memoByte.text = "$byteLength" + " /"
                        isMemoConfirmed = byteLength <= 80
                    }

                    override fun afterTextChanged(strEditable: Editable?) {
                        strEditable?.let {
                            val text = it.toString()
                            val byteLength = text.toByteArray(Charsets.UTF_8).size

                            if (byteLength > 80) {
                                memoTxt.keyListener = null
                                it.replace(0, it.length, originalText)
                                memoMaxByte.setTextColor(
                                    ContextCompat.getColorStateList(
                                        requireContext(),
                                        R.color.color_red
                                    )
                                )

                            } else {
                                memoMaxByte.setTextColor(
                                    ContextCompat.getColorStateList(
                                        requireContext(),
                                        R.color.color_base03
                                    )
                                )
                                memoTxt.keyListener = originalKeyListener
                            }
                        }
                    }
                })
            }
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