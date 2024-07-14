package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
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
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toHex
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentPrivateCheckBinding

class PrivateCheckFragment : Fragment() {

    private var _binding: FragmentPrivateCheckBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: BaseAccount

    private lateinit var privateAdapter: PrivateAdapter

    private var mainnetChains = mutableListOf<BaseChain>()
    private var searchMainnetChains = mutableListOf<BaseChain>()
    private var testnetChains = mutableListOf<BaseChain>()
    private var searchTestnetChains = mutableListOf<BaseChain>()

    companion object {
        @JvmStatic
        fun newInstance(baseAccount: BaseAccount): PrivateCheckFragment {
            val args = Bundle().apply {
                putParcelable("baseAccount", baseAccount)
            }
            val fragment = PrivateCheckFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrivateCheckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initSearchView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("baseAccount", BaseAccount::class.java)
                    ?.let { account = it }

            } else {
                (arguments?.getParcelable("baseAccount") as? BaseAccount)?.let {
                    account = it
                }
            }
            listOf(accountNameView, privateKeyLayout).forEach {
                it.setBackgroundResource(
                    R.drawable.item_bg
                )
            }

            if (account.type == BaseAccountType.MNEMONIC) {
                accountNameTitle.visibility = View.GONE
                accountNameView.visibility = View.GONE
                searchBar.visibility = View.VISIBLE
                privateCheckTitle.text = account.name
                privateKeyLayout.visibility = View.GONE
                tapMsg.visibility = View.GONE

            } else {
                accountNameTitle.visibility = View.VISIBLE
                accountNameView.visibility = View.VISIBLE
                searchBar.visibility = View.GONE
                privateCheckTitle.text = getString(R.string.str_only_private)
                recycler.visibility = View.GONE
            }
            accountName.text = account.name
        }
        initAllKeyData()
    }

    private fun initAllKeyData() {
        account.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                val allChains = allChains()
                if (type == BaseAccountType.MNEMONIC) {
                    for (chain in allChains) {
                        if (chain.publicKey == null) {
                            chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    for (chain in allChains) {
                        if (chain.publicKey == null) {
                            chain.setInfoWithPrivateKey(privateKey)
                        }
                    }
                }

                mainnetChains =
                    allChains.filter { !it.isTestnet && it.tag != "okt996_Secp" }.toMutableList()
                testnetChains = allChains.filter { it.isTestnet }.toMutableList()
                searchMainnetChains = mainnetChains
                searchTestnetChains = testnetChains

                withContext(Dispatchers.Main) {
                    updateView()
                }
            }
        }
    }

    private fun updateView() {
        binding.apply {
            btnConfirm.visibleOrGone(account.type == BaseAccountType.PRIVATE_KEY)

            if (account.type == BaseAccountType.MNEMONIC) {
                recycler.visibility = View.VISIBLE
                privateAdapter = PrivateAdapter(account, searchMainnetChains, searchTestnetChains)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = privateAdapter

            } else {
                privateKeyLayout.visibility = View.VISIBLE
                tapMsg.visibility = View.VISIBLE
                requireActivity().runOnUiThread {
                    accountPrivateKey.text = "0x" + account.privateKey?.toHex()
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnConfirm.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            privateKeyLayout.setOnClickListener {
                val clipboard =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("address", "0x" + account.privateKey?.toHex())
                clipboard.setPrimaryClip(clip)
                requireActivity().makeToast(R.string.str_msg_private_copy)
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
                        searchMainnetChains.addAll(allChains().filter { !it.isTestnet })
                        searchTestnetChains.addAll(allChains().filter { it.isTestnet })

                    } else {
                        newText?.let { searchTxt ->
                            searchMainnetChains.addAll(allChains().filter { chain ->
                                chain.name.contains(
                                    searchTxt,
                                    ignoreCase = true
                                ) && !chain.isTestnet
                            })

                            searchTestnetChains.addAll(allChains().filter { chain ->
                                chain.name.contains(
                                    searchTxt,
                                    ignoreCase = true
                                ) && !chain.isTestnet
                            })
                        }
                    }

                    if (searchMainnetChains.isEmpty() && searchTestnetChains.isEmpty()) {
                        emptyLayout.visibility = View.VISIBLE
                        recycler.visibility = View.GONE
                    } else {
                        emptyLayout.visibility = View.GONE
                        recycler.visibility = View.VISIBLE
                        privateAdapter.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}