package wannabit.io.cosmostaion.ui.init

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.databinding.FragmentRestorePrivateBinding
import java.util.regex.Pattern

class RestorePrivateFragment(private val initType: Int) : Fragment() {

    private var _binding: FragmentRestorePrivateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestorePrivateBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                if (initType == 0) {
                    requireActivity().onBackPressed()
                } else {
                    requireActivity().supportFragmentManager.popBackStack()
                }
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
                        requireActivity().makeToast(R.string.error_invalid_private_key)
                        return@setOnClickListener
                    }

                    requireActivity().toMoveFragment(
                        this@RestorePrivateFragment,
                        WalletSelectFragment.newInstance("", userInput, initType),
                        "WalletSelect"
                    )
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