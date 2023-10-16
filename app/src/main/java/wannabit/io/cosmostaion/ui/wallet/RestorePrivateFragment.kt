package wannabit.io.cosmostaion.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.databinding.FragmentRestorePrivateBinding
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import java.util.regex.Pattern

class RestorePrivateFragment(val name: String) : Fragment() {

    private var _binding: FragmentRestorePrivateBinding? = null
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestorePrivateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backdropLayout.visibility = View.GONE
        checkRestore()
        clickAction()
    }

    private fun clickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnCreateAccount.setOnClickListener {
                privateKey.text.toString().trim().let { privateKey ->
                    if (privateKey.isEmpty()) {
                        requireActivity().makeToast(R.string.error_account_name)
                        return@setOnClickListener
                    }

                    var userInput = privateKey
                    if (userInput.lowercase().startsWith("0x")) {
                        userInput = userInput.substring(2)
                    }

                    if (!isValidStringPrivateKey(userInput)) {
                        requireActivity().makeToast(R.string.error_invalid_private_Key)
                        return@setOnClickListener
                    }

                    requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.color_background_dialog)
                    backdropLayout.visibility = View.VISIBLE
                    accountViewModel.createByPrivate(name, userInput)
                }
            }
        }
    }

    private fun checkRestore() {
        accountViewModel.create.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                delay(1000)
                withContext(Dispatchers.Main) {
                    binding.backdropLayout.visibility = View.GONE
                    requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.color_transparent)
                    ApplicationViewModel.shared.currentAccount()

                    requireActivity().finish()
                    requireActivity().overridePendingTransition(0, 0)
                }
            }
        }
    }

    private fun isValidStringPrivateKey(input: String): Boolean {
        var result = false
        val regex = "^(0x|0X)?[a-fA-F0-9]{64}"
        val p = Pattern.compile(regex)
        val m = p.matcher(input)
        if (m.matches()) {
            result = true
        }
        return result
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}