package wannabit.io.cosmostaion.ui.wallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.toMoveFragment
import wannabit.io.cosmostaion.databinding.FragmentRestoreMnemonicConfirmBinding

class RestoreMnemonicConfirmFragment(
    val mnemonic: String,
    private val initType: Int
) : Fragment() {

    private var _binding: FragmentRestoreMnemonicConfirmBinding? = null
    private val binding get() = _binding!!

    private lateinit var restoreMnemonicConfirmAdapter: RestoreMnemonicConfirmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestoreMnemonicConfirmBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            val wordList = mnemonic.split(" ")
            cautionView.setBackgroundResource(R.drawable.item_bg)
            recycler.setBackgroundResource(R.drawable.item_bg)

            restoreMnemonicConfirmAdapter = RestoreMnemonicConfirmAdapter(requireContext())
            recycler.setHasFixedSize(true)
            recycler.layoutManager = GridLayoutManager(requireContext(), 3)
            recycler.adapter = restoreMnemonicConfirmAdapter
            restoreMnemonicConfirmAdapter.submitList(wordList)
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnNext.setOnClickListener {
                requireActivity().toMoveFragment(
                    this@RestoreMnemonicConfirmFragment,
                    WalletSelectFragment.newInstance(mnemonic, "", initType),
                    "WalletSelect"
                )
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}