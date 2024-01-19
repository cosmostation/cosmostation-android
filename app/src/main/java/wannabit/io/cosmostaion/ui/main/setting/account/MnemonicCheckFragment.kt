package wannabit.io.cosmostaion.ui.main.setting.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import net.i2p.crypto.eddsa.Utils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.database.CryptoHelper
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentMnemonicCheckBinding
import wannabit.io.cosmostaion.ui.wallet.WalletSelectFragment

class MnemonicCheckFragment(val account: BaseAccount) : Fragment() {

    private var _binding: FragmentMnemonicCheckBinding? = null
    private val binding get() = _binding!!

    private lateinit var mnemonicAdapter: MnemonicAdapter

    private var wordList: List<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMnemonicCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModels()
        setUpClickAction()
    }


    private fun setUpViewModels() {
        binding.apply {
            accountNameView.setBackgroundResource(R.drawable.item_bg)
            recycler.setBackgroundResource(R.drawable.item_bg)

            account.apply {
                accountName.text = name
                CryptoHelper.doDecryptData(
                    CosmostationConstants.ENCRYPT_MNEMONIC_KEY + uuid, resource, spec
                )?.let {
                    wordList = BaseKey.getMnemonicWords(Utils.hexToBytes(it))
                    phraseCnt.text = getString(R.string.str_phrase_cnt, wordList.size.toString())

                    mnemonicAdapter = MnemonicAdapter(requireContext())
                    recycler.setHasFixedSize(true)
                    recycler.layoutManager = GridLayoutManager(requireContext(), 3)
                    recycler.adapter = mnemonicAdapter
                    mnemonicAdapter.submitList(wordList)
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnSelect.setOnClickListener {
                requireActivity().toMoveFragment(
                    this@MnemonicCheckFragment,
                    WalletSelectFragment(wordList.joinToString(" "), ""),
                    "WalletSelect"
                )
            }

            btnConfirm.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}