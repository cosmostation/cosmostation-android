package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.concurrentForEach
import wannabit.io.cosmostaion.data.model.req.Cw721Model
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.databinding.FragmentNftBinding
import wannabit.io.cosmostaion.ui.main.chain.cosmos.NftAdapter.Companion.VIEW_TYPE_NFT_HEADER
import wannabit.io.cosmostaion.ui.main.chain.cosmos.NftAdapter.Companion.VIEW_TYPE_NFT_ITEM
import wannabit.io.cosmostaion.ui.tx.genTx.NftTransferFragment

class NftFragment : Fragment() {

    private var _binding: FragmentNftBinding? = null
    private val binding get() = _binding!!

    private lateinit var nftAdapter: NftAdapter

    private lateinit var walletViewModel: WalletViewModel

    private lateinit var selectedChain: BaseChain

    private var isBusy = false
    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): NftFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = NftFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNftBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        refreshData()
        setUpObserve()

        if (selectedChain.cosmosFetcher?.cw721Fetched == false) {
            fetchData()
        } else {
            updateView(selectedChain.cosmosFetcher?.cw721Models)
        }
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
    }

    override fun onResume() {
        super.onResume()
        if (selectedChain.cosmosFetcher?.cw721Fetched == false) {
            fetchData()
        } else {
            updateView(selectedChain.cosmosFetcher?.cw721Models)
        }
    }

    private fun updateView(nftGroup: MutableList<Cw721Model>?) {
        binding.apply {
            refresher.isRefreshing = false
            loading.visibility = View.GONE

            if (nftGroup?.isEmpty() == true) {
                emptyLayout.visibility = View.VISIBLE
                recycler.visibility = View.GONE

            } else {
                emptyLayout.visibility = View.GONE
                recycler.visibility = View.VISIBLE

                nftAdapter = NftAdapter(selectedChain)
                recycler.setHasFixedSize(true)
                val gridLayoutManager = GridLayoutManager(requireContext(), 2)
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (recycler.adapter?.getItemViewType(position)) {
                            VIEW_TYPE_NFT_HEADER -> 2
                            VIEW_TYPE_NFT_ITEM -> 1
                            else -> throw IllegalArgumentException("Unsupported view type")
                        }
                    }
                }
                recycler.layoutManager = gridLayoutManager
                recycler.adapter = nftAdapter
                nftAdapter.submitList(nftGroup)

                if (::nftAdapter.isInitialized) {
                    nftAdapter.setOnItemClickListener { line, info, token ->
                        if (isClickable) {
                            isClickable = false

                            NftTransferFragment(line, info, token).show(
                                requireActivity().supportFragmentManager,
                                NftTransferFragment::class.java.name
                            )

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
            fetchData()
        }
    }

    private fun fetchData() {
        if (isBusy) {
            return
        }
        isBusy = true
        selectedChain.cosmosFetcher?.cw721Fetched = false
        selectedChain.cosmosFetcher?.cw721Models?.clear()
        selectedChain.cosmosFetcher?.cw721Tokens?.asSequence()?.concurrentForEach { list ->

            walletViewModel.cw721AllTokens(selectedChain, list)
        }
    }

    private fun setUpObserve() {
        walletViewModel.cw721ModelResult.observe(viewLifecycleOwner) { tag ->
            if (selectedChain.tag == tag) {
                selectedChain.cosmosFetcher?.cw721Fetched = true
                selectedChain.cosmosFetcher?.cw721Models?.sortWith(compareBy { it.info.id })
                selectedChain.cosmosFetcher?.cw721Models?.forEach { cw721Model ->
                    cw721Model.sortId()
                }
                isBusy = false
                updateView(selectedChain.cosmosFetcher?.cw721Models)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}