package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentEcoSystemBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.EcoSystemAdapter
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory

class MajorEcosystemFragment : Fragment() {

    private var _binding: FragmentEcoSystemBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var walletViewModel: WalletViewModel

    private lateinit var ecoSystemAdapter: EcoSystemAdapter

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorEcosystemFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorEcosystemFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEcoSystemBinding.inflate(layoutInflater, container, false)
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpEcoSystemInfo()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("selectedChain", BaseChain::class.java)
                ?.let { selectedChain = it }
        } else {
            (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                selectedChain = it
            }
        }

        walletViewModel.ecoSystemList(selectedChain.apiName)
    }

    private fun updateView(infos: MutableList<JsonObject>) {
        binding.apply {
            loading.visibility = View.GONE
            recycler.visibility = View.VISIBLE

            ecoSystemAdapter = EcoSystemAdapter(requireContext(), selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = GridLayoutManager(requireContext(), 2)
            recycler.adapter = ecoSystemAdapter
            ecoSystemAdapter.submitList(infos)

            ecoSystemAdapter.setOnItemClickListener {
                Intent(requireActivity(), DappActivity::class.java).apply {
                    putExtra("dapp", it)
                    putExtra("selectedChain", selectedChain as Parcelable)
                    startActivity(this)
                }
            }
        }
    }

    private fun setUpEcoSystemInfo() {
        walletViewModel.ecoSystemListResult.observe(viewLifecycleOwner) { infos ->
            runCatching {
                if (infos?.isNotEmpty() == true) {
                    updateView(infos)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}