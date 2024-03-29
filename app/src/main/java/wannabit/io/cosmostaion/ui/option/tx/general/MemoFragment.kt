package wannabit.io.cosmostaion.ui.option.tx.general

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.databinding.FragmentMemoBinding
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity


interface MemoListener {
    fun memo(memo: String)
}

class MemoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMemoBinding? = null
    private val binding get() = _binding!!

    companion object {
        @JvmStatic
        fun newInstance(
            memo: String, listener: MemoListener
        ): MemoFragment {
            val args = Bundle().apply {
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
        clickAction()
    }

    private fun initView() {
        binding.memoTxt.apply {
            arguments?.getString("memo")?.let { memo ->
                text = Editable.Factory.getInstance().newEditable(memo)
                setSelection(binding.memoTxt.text.toString().length)
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