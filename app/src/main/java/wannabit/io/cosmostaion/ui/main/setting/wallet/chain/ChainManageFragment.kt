package wannabit.io.cosmostaion.ui.main.setting.wallet.chain

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentChainManageBinding
import wannabit.io.cosmostaion.ui.main.SettingType
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment
import wannabit.io.cosmostaion.ui.option.notice.NoticeInfoFragment

class ChainManageFragment : Fragment() {

    private var _binding: FragmentChainManageBinding? = null
    private val binding get() = _binding!!

    private lateinit var chainManageAdapter: ChainManageAdapter

    private var mainnetChains = mutableListOf<BaseChain>()
    private var searchMainnetChains = mutableListOf<BaseChain>()
    private var testnetChains = mutableListOf<BaseChain>()
    private var searchTestnetChains = mutableListOf<BaseChain>()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChainManageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSearchView()
        setUpClickAction()
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                allChains().filter { !it.isTestnet && it.isDefault }.forEach { chain ->
                    if (!mainnetChains.any { it.name == chain.name }) {
                        mainnetChains.add(chain)
                    }
                }

                allChains().filter { it.isTestnet && it.isDefault }.forEach { chain ->
                    if (!testnetChains.any { it.name == chain.name }) {
                        testnetChains.add(chain)
                    }
                }

                searchMainnetChains.addAll(mainnetChains)
                searchTestnetChains.addAll(testnetChains)

                withContext(Dispatchers.Main) {
                    chainManageAdapter =
                        ChainManageAdapter(searchMainnetChains, searchTestnetChains)
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = chainManageAdapter

                    chainManageAdapter.setOnItemClickListener { chain ->
                        selectEndPoint(chain)
                    }
                }
            }
        }
    }

    private fun selectEndPoint(chain: BaseChain) {
        if (isClickable) {
            isClickable = false

            if (chain is ChainOktEvm) {
                isClickable = true
                return
            }
            val settingType = if (chain.isEvmCosmos()) {
                SettingType.END_POINT_COSMOS
            } else if (chain.supportCosmos()) {
                SettingType.END_POINT_COSMOS
            } else {
                SettingType.END_POINT_EVM
            }

            SettingBottomFragment.newInstance(chain, settingType).show(
                parentFragmentManager, SettingBottomFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)

            parentFragmentManager.setFragmentResultListener(
                "endpoint", this@ChainManageFragment
            ) { _, _ ->
                chainManageAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initSearchView() {
        binding.apply {
            searchView.setQuery("", false)
            searchView.clearFocus()
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    searchMainnetChains.clear()
                    searchTestnetChains.clear()

                    if (StringUtils.isEmpty(newText)) {
                        searchMainnetChains.addAll(mainnetChains)
                        searchTestnetChains.addAll(testnetChains)

                    } else {
                        newText?.let { searchTxt ->
                            searchMainnetChains.addAll(allChains().filter { chain ->
                                chain.name.contains(
                                    searchTxt, ignoreCase = true
                                ) && !chain.isTestnet && chain.isDefault
                            })

                            searchTestnetChains.addAll(allChains().filter { chain ->
                                chain.name.contains(searchTxt, ignoreCase = true) && chain.isTestnet
                            })
                        }
                    }
                    if (searchMainnetChains.isEmpty() && searchTestnetChains.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        chainManageAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    private fun setUpClickAction() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.btnReset.setOnClickListener {
            if (isClickable) {
                isClickable = false

                ChainEndpointResetFragment.newInstance(object : ResetListener {
                    override fun reset() {
                        lifecycleScope.launch(Dispatchers.IO) {
                            mainnetChains.forEach { chain ->
                                Prefs.removeEndpointType(chain)
                                Prefs.removeGrpcEndpoint(chain)
                                Prefs.removeLcdEndpoint(chain)
                                Prefs.removeEvmRpcEndpoint(chain)
                            }

                            testnetChains.forEach { chain ->
                                Prefs.removeEndpointType(chain)
                                Prefs.removeGrpcEndpoint(chain)
                                Prefs.removeLcdEndpoint(chain)
                                Prefs.removeEvmRpcEndpoint(chain)
                            }
                        }
                        chainManageAdapter.notifyDataSetChanged()
                    }
                }).show(
                    requireActivity().supportFragmentManager, ChainEndpointResetFragment::class.java.name)

                Handler(Looper.getMainLooper()).postDelayed({
                    isClickable = true
                }, 300)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}