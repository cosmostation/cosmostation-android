package wannabit.io.cosmostaion.ui.main.setting.wallet.importQR

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentImportCheckKeyBinding

interface QrImportConfirmListener {
    fun qrImportConfirm(mnemonic: String)
}

class ImportCheckKeyFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentImportCheckKeyBinding? = null
    private val binding get() = _binding!!

    private lateinit var decryptString: String

    private lateinit var cryptoJS: CryptoJS

    companion object {
        @JvmStatic
        fun newInstance(
            decryptString: String,
            listener: QrImportConfirmListener
        ): ImportCheckKeyFragment {
            val args = Bundle().apply {
                putString("decryptString", decryptString)
            }
            val fragment = ImportCheckKeyFragment()
            fragment.qrImportConfirmListener = listener
            fragment.arguments = args
            return fragment
        }
    }

    private var qrImportConfirmListener: QrImportConfirmListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImportCheckKeyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("decryptString")?.let { decryptString = it }
        cryptoJS = CryptoJS(requireContext())
        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            btnConfirm.setOnClickListener {
                keyTxt.text.toString().trim().apply {
                    val userInput = this
                    if (userInput.isEmpty()) {
                        return@setOnClickListener
                    }

                    loading.visibility = View.VISIBLE
                    lifecycleScope.launch(Dispatchers.IO) {
                        try {
                            val decrypted = cryptoJS.decryptWithJS(decryptString, userInput)
                            Gson().fromJson(decrypted, JsonObject::class.java)?.let { json ->
                                withContext(Dispatchers.Main) {
                                    loading.visibility = View.GONE
                                    json.asJsonObject["mnemonic"].asString?.let { mnemonic ->
                                        delay(1000)
                                        dismiss()
                                        qrImportConfirmListener?.qrImportConfirm(mnemonic)

                                    } ?: run {
                                        requireActivity().makeToast(R.string.error_decrytion)
                                        return@run
                                    }
                                }
                            }

                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                loading.visibility = View.GONE
                                requireActivity().makeToast(R.string.error_decrytion)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}