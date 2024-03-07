package wannabit.io.cosmostaion.ui.main.setting.wallet.account

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.trustwallet.walletconnect.extensions.toHex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.FragmentPrivateCheckBinding

class PrivateCheckFragment : Fragment() {

    private var _binding: FragmentPrivateCheckBinding? = null
    private val binding get() = _binding!!

    private lateinit var account: BaseAccount

    private lateinit var privateAdapter: PrivateAdapter

    private val allEvmLines: MutableList<EthereumLine> = mutableListOf()
    private val allCosmosLines: MutableList<CosmosLine> = mutableListOf()

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

            if (account.lastHDPath != "0") {
                lastHdPath.visibility = View.VISIBLE
                lastHdPath.text = "Last HD Path : " + account.lastHDPath
            } else {
                lastHdPath.visibility = View.GONE
            }
            accountName.text = account.name
        }
        initAllKeyData()
    }

    private fun initAllKeyData() {
        account.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                if (type == BaseAccountType.MNEMONIC) {
                    allEvmLines.addAll(allEvmLines())
                    allCosmosLines.addAll(allCosmosLines())

                    allEvmLines.forEach { evmLine ->
                        if (evmLine.address?.isEmpty() == true) {
                            evmLine.setInfoWithSeed(seed, evmLine.setParentPath, lastHDPath)
                        }
                    }
                    allCosmosLines.forEach { line ->
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithSeed(seed, line.setParentPath, lastHDPath)
                        }
                    }

                } else if (type == BaseAccountType.PRIVATE_KEY) {
                    allEvmLines.addAll(allEvmLines())
                    allCosmosLines().filter { it.isDefault }.forEach { line ->
                        allCosmosLines.add(line)
                    }

                    for (evmLine in allEvmLines) {
                        if (evmLine.address?.isEmpty() == true) {
                            evmLine.setInfoWithPrivateKey(privateKey)
                        }
                    }
                    for (line in allCosmosLines) {
                        if (line.address?.isEmpty() == true) {
                            line.setInfoWithPrivateKey(privateKey)
                        }
                    }
                }

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
                privateCheckTitle.text = getString(R.string.title_check_private_each_chain)
                privateKeyLayout.visibility = View.GONE
                tapMsg.visibility = View.GONE
                recycler.visibility = View.VISIBLE

                privateAdapter = PrivateAdapter(account, allEvmLines, allCosmosLines)
                recycler.setHasFixedSize(true)
                recycler.layoutManager = LinearLayoutManager(requireContext())
                recycler.adapter = privateAdapter

            } else {
                privateCheckTitle.text = getString(R.string.str_only_private)
                privateKeyLayout.visibility = View.VISIBLE
                tapMsg.visibility = View.VISIBLE
                recycler.visibility = View.GONE
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}