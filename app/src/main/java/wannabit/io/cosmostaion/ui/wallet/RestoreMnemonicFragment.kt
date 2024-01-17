package wannabit.io.cosmostaion.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.databinding.FragmentRestoreMnemonicBinding

class RestoreMnemonicFragment : Fragment() {

    private var _binding: FragmentRestoreMnemonicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestoreMnemonicBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickAction()
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnNext.setOnClickListener {
                mnemonicPhrase.text.toString().trim().let { mnemonic ->
                    if (mnemonic.isEmpty()) {
                        requireActivity().makeToast(R.string.error_invalid_menmonic)
                        return@setOnClickListener
                    }

                    val wordList = mnemonic.split(" ")
                    if (!isValidWords(wordList)) {
                        requireActivity().makeToast(R.string.error_invalid_menmonic)
                        return@setOnClickListener
                    }

                    requireActivity().toMoveFragment(
                        this@RestoreMnemonicFragment,
                        RestoreMnemonicConfirmFragment(mnemonic),
                        "RestorePath"
                    )
                }
            }
        }
    }

    private fun isValidWords(wordList: List<String>): Boolean {
        return (wordList.size == 12 || wordList.size == 18 || wordList.size == 24) && BaseKey.isMnemonicWords(
            wordList
        ) && BaseKey.isValidStringHdSeedFromWords(wordList)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}