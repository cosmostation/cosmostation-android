package wannabit.io.cosmostaion.ui.main.chain.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentMajorNftBinding
import wannabit.io.cosmostaion.ui.tx.genTx.major.iota.IotaNftTransferFragment
import wannabit.io.cosmostaion.ui.tx.genTx.major.sui.SuiNftTransferFragment

class MajorNftFragment : Fragment() {

    private var _binding: FragmentMajorNftBinding? = null
    private val binding get() = _binding!!

    private lateinit var majorNftAdapter: MajorNftAdapter

    private lateinit var walletViewModel: WalletViewModel

    private lateinit var selectedChain: BaseChain
    private var moveAllNfts: MutableList<JsonObject> = mutableListOf()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorNftFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorNftFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMajorNftBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        refreshData()
        setUpObserve()
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

        if (selectedChain is ChainSui) {
            (selectedChain as ChainSui).suiFetcher?.let { fetcher ->
                moveAllNfts.addAll(fetcher.suiAllNfts())
            }

        } else {
            (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                moveAllNfts.addAll(fetcher.iotaAllNfts())
            }
        }

        updateView()
    }

    private fun updateView() {
        binding.apply {
            refresher.isRefreshing = false
            loading.visibility = View.GONE
            binding.recycler.suppressLayout(false)

            if (moveAllNfts.isEmpty()) {
                emptyLayout.visibility = View.VISIBLE
                recycler.visibility = View.GONE

            } else {
                emptyLayout.visibility = View.GONE
                recycler.visibility = View.VISIBLE

                majorNftAdapter = MajorNftAdapter(selectedChain)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = GridLayoutManager(requireContext(), 2)
                recycler.adapter = majorNftAdapter
                majorNftAdapter.submitList(moveAllNfts)

                if (::majorNftAdapter.isInitialized) {
                    majorNftAdapter.setOnItemClickListener { chain, info ->
                        if (isClickable) {
                            isClickable = false

                            if (selectedChain is ChainSui) {
                                SuiNftTransferFragment(chain, info).show(
                                    requireActivity().supportFragmentManager,
                                    SuiNftTransferFragment::class.java.name
                                )

                            } else {
                                IotaNftTransferFragment(chain, info).show(
                                    requireActivity().supportFragmentManager,
                                    IotaNftTransferFragment::class.java.name
                                )
                            }

                            Handler(Looper.getMainLooper()).postDelayed({
                                isClickable = true
                            }, 300)
                        }
                    }
                }
            }
        }
    }

    private fun refreshData() {
        binding.refresher.setOnRefreshListener {
            if (binding.refresher.isRefreshing) {
                binding.recycler.suppressLayout(true)
            }

            BaseData.baseAccount?.let { account ->
                selectedChain.fetchState = FetchState.IDLE

                if (selectedChain is ChainSui) {
                    ApplicationViewModel.shared.loadSuiData(account.id, selectedChain)
                } else {
                    ApplicationViewModel.shared.loadIotaData(account.id, selectedChain)
                }
            }
        }
    }

    private fun setUpObserve() {
        ApplicationViewModel.shared.notifyTxResult.observe(viewLifecycleOwner) {
            moveAllNfts.clear()
            if (selectedChain is ChainSui) {
                (selectedChain as ChainSui).suiFetcher?.let { fetcher ->
                    moveAllNfts.addAll(fetcher.suiAllNfts())
                }

            } else {
                (selectedChain as ChainIota).iotaFetcher?.let { fetcher ->
                    moveAllNfts.addAll(fetcher.iotaAllNfts())
                }
            }

            updateView()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}