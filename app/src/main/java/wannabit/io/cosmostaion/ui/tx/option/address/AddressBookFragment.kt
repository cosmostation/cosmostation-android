package wannabit.io.cosmostaion.ui.tx.option.address

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin86Testnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentAddressBookBinding
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

interface AddressBookSelectListener {
    fun select(address: String, memo: String)
}

class AddressBookFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private lateinit var toChain: BaseChain
    private var senderAddress = ""
    private lateinit var sendAssetType: SendAssetType

    private lateinit var addressBookAdapter: AddressBookAdapter
    private lateinit var evmAddressBookAdapter: EvmAddressBookAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain,
            toChain: BaseChain,
            senderAddress: String?,
            sendAssetType: SendAssetType,
            listener: AddressBookSelectListener
        ): AddressBookFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putParcelable("toChain", toChain)
                putString("senderAddress", senderAddress)
                putSerializable("sendAssetType", sendAssetType)
            }
            val fragment = AddressBookFragment()
            fragment.arguments = args
            fragment.addressBookSelectListener = listener
            return fragment
        }
    }

    private var addressBookSelectListener: AddressBookSelectListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBookBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData() {
        arguments?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                getParcelable(
                    "fromChain", BaseChain::class.java
                )?.let { fromChain = it }
                getParcelable(
                    "toChain", BaseChain::class.java
                )?.let { toChain = it }
                getSerializable(
                    "sendAssetType", SendAssetType::class.java
                )?.let { sendAssetType = it }

            } else {
                (getParcelable("fromChain") as? BaseChain)?.let {
                    fromChain = it
                }
                (getParcelable("toChain") as? BaseChain)?.let {
                    toChain = it
                }
                (getSerializable("sendAssetType") as? SendAssetType)?.let {
                    sendAssetType = it
                }
            }
            getString("senderAddress")?.let { senderAddress = it }
        }

        initView()
    }

    private fun initView() {
        val refMajorAddresses: MutableList<RefAddress> = mutableListOf()
        val refAddresses: MutableList<RefAddress> = mutableListOf()
        val refEvmAddresses: MutableList<RefAddress> = mutableListOf()
        val majorAddressBook: MutableList<AddressBook> = mutableListOf()
        val addressBooks: MutableList<AddressBook> = mutableListOf()
        val evmAddressBooks: MutableList<AddressBook> = mutableListOf()

        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                when (sendAssetType) {
                    SendAssetType.ONLY_EVM_COIN, SendAssetType.ONLY_EVM_ERC20 -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
                                if (fromChain.supportCosmos()) {
                                    if (refAddress.chainTag == toChain.tag && refAddress.evmAddress != ByteUtils.convertBech32ToEvm(
                                            senderAddress
                                        )
                                    ) {
                                        refEvmAddresses.add(refAddress)
                                    }

                                } else {
                                    if (senderAddress.startsWith("0x")) {
                                        if (refAddress.chainTag == toChain.tag && refAddress.evmAddress != senderAddress) {
                                            refEvmAddresses.add(refAddress)
                                        }

                                    } else {
                                        if (refAddress.chainTag == toChain.tag && refAddress.evmAddress != ByteUtils.convertBech32ToEvm(
                                                senderAddress
                                            )
                                        ) {
                                            refEvmAddresses.add(refAddress)
                                        }
                                    }
                                }
                            }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    if (fromChain.supportCosmos()) {
                                        if (addressBook.chainName == toChain.tag && BaseKey.isValidEthAddress(
                                                addressBook.address
                                            ) || addressBook.chainName == "EVM-universal"
                                        ) {
                                            evmAddressBooks.add(addressBook)
                                        }

                                    } else {
                                        if (addressBook.chainName == "EVM-universal" || addressBook.chainName == toChain.tag) {
                                            evmAddressBooks.add(addressBook)
                                        }
                                    }
                                }
                            }
                    }

                    SendAssetType.ONLY_COSMOS_COIN, SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_COSMOS_GRC20 -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .filter { it.dpAddress?.startsWith(toChain.accountPrefix + 1) == true && it.dpAddress?.lowercase() != senderAddress.lowercase() }
                            .forEach { refAddress ->
                                if (refAddresses.none { it.dpAddress?.lowercase() == refAddress.dpAddress?.lowercase() && it.accountId == refAddress.accountId }) {
                                    if (Prefs.displayLegacy) {
                                        refAddresses.add(refAddress)
                                    } else {
                                        allChains().firstOrNull { it.tag == refAddress.chainTag }
                                            ?.let { chain ->
                                                if (chain.isDefault) {
                                                    refAddresses.add(refAddress)
                                                }
                                            }
                                    }
                                }
                            }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    if (fromChain.isTestnet) {
                                        if (addressBook.chainName == toChain.tag) {
                                            addressBooks.add(addressBook)
                                        }

                                    } else {
                                        val chain =
                                            allChains().firstOrNull { it.tag == addressBook.chainName }
                                        if (chain?.isTestnet == false) {
                                            val prefix = addressBook.address.substringBefore('1')
                                            if (toChain.accountPrefix == prefix) {
                                                addressBooks.add(addressBook)
                                            }
                                        }
                                    }
                                }
                            }
                    }

                    SendAssetType.SUI_COIN, SendAssetType.SUI_NFT, SendAssetType.IOTA_COIN, SendAssetType.IOTA_NFT -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
                                if (refAddress.chainTag == toChain.tag && refAddress.dpAddress?.lowercase() != fromChain.mainAddress.lowercase()) {
                                    refMajorAddresses.add(refAddress)
                                }
                            }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.chainName == toChain.tag && addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    majorAddressBook.add(addressBook)
                                }
                            }
                    }

                    SendAssetType.BIT_COIN -> {
                        val dpRefMajorAddresses = if (fromChain.isTestnet) {
                            AppDatabase.getInstance().refAddressDao().selectAll()
                                .filter { it.chainTag.contains("bitcoin") && it.chainTag.contains("_T") && it.dpAddress?.lowercase() != fromChain.mainAddress.lowercase() }
                        } else {
                            AppDatabase.getInstance().refAddressDao().selectAll()
                                .filter { it.chainTag.contains("bitcoin") && !it.chainTag.contains("_T") && it.dpAddress?.lowercase() != fromChain.mainAddress.lowercase() }
                        }

                        dpRefMajorAddresses.forEach { refAddress ->
                            if (refAddresses.none { it.dpAddress?.lowercase() == refAddress.dpAddress?.lowercase() && it.accountId == refAddress.accountId }) {
                                if (Prefs.displayLegacy) {
                                    refMajorAddresses.add(refAddress)
                                } else {
                                    allChains().firstOrNull { it.tag == refAddress.chainTag }
                                        ?.let { chain ->
                                            if (chain.isDefault) {
                                                refMajorAddresses.add(refAddress)
                                            }
                                        }
                                }
                            }
                        }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    val chain =
                                        allChains().firstOrNull { it.tag == addressBook.chainName }
                                    if (fromChain.isTestnet && chain?.isTestnet == true && chain is ChainBitcoin86Testnet) {
                                        majorAddressBook.add(addressBook)

                                    } else if (!fromChain.isTestnet && chain?.isTestnet == false && chain is ChainBitCoin86) {
                                        majorAddressBook.add(addressBook)
                                    }
                                }
                            }
                    }
                }
                sortRefEvmAddresses(refEvmAddresses)
                sortRefAddresses(refAddresses)
                sortRefMajorAddresses(refMajorAddresses)

                withContext(Dispatchers.Main) {
                    when (sendAssetType) {
                        SendAssetType.ONLY_EVM_COIN, SendAssetType.ONLY_EVM_ERC20 -> {
                            if (refEvmAddresses.isEmpty() && evmAddressBooks.isEmpty()) {
                                evmRecycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE

                            } else {
                                evmRecycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                initEvmRecyclerView(
                                    refEvmAddresses, evmAddressBooks
                                )
                            }
                            segmentView.visibility = View.GONE
                            recycler.visibility = View.GONE
                        }

                        SendAssetType.ONLY_COSMOS_COIN, SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_COSMOS_GRC20 -> {
                            if (refAddresses.isEmpty() && addressBooks.isEmpty()) {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE

                            } else {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                initCosmosRecyclerView(
                                    refAddresses, addressBooks
                                )
                            }
                            segmentView.visibility = View.GONE
                            evmRecycler.visibility = View.GONE
                        }

                        SendAssetType.SUI_COIN, SendAssetType.SUI_NFT, SendAssetType.BIT_COIN, SendAssetType.IOTA_COIN, SendAssetType.IOTA_NFT -> {
                            if (refMajorAddresses.isEmpty() && majorAddressBook.isEmpty()) {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE

                            } else {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                initCosmosRecyclerView(
                                    refMajorAddresses, majorAddressBook
                                )
                            }
                            segmentView.visibility = View.GONE
                            evmRecycler.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun initCosmosRecyclerView(
        refAddresses: MutableList<RefAddress>, addressBooks: MutableList<AddressBook>
    ) {
        binding.apply {
            addressBookAdapter = AddressBookAdapter(
                refAddresses, addressBooks
            )
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = addressBookAdapter

            addressBookAdapter.setOnItemClickListener { address, memo ->
                addressBookSelectListener?.select(address, memo)
                dismiss()
            }
        }
    }

    private fun initEvmRecyclerView(
        refEvmAddresses: MutableList<RefAddress>, evmAddressBooks: MutableList<AddressBook>
    ) {
        binding.apply {
            evmAddressBookAdapter = EvmAddressBookAdapter(refEvmAddresses, evmAddressBooks)
            evmRecycler.setHasFixedSize(true)
            evmRecycler.layoutManager = LinearLayoutManager(requireContext())
            evmRecycler.adapter = evmAddressBookAdapter

            evmAddressBookAdapter.setOnItemClickListener { address, memo ->
                addressBookSelectListener?.select(address, memo)
                dismiss()
            }
        }
    }

    private fun sortRefAddresses(refAddresses: MutableList<RefAddress>) {
        val accountMap =
            AppDatabase.getInstance().baseAccountDao().selectAll().associateBy { it.id }
        val chainLineMap = allChains().associateBy { it.tag }
        refAddresses.sortWith(compareBy<RefAddress> { if (BaseData.baseAccount?.id == it.accountId) -1 else accountMap[it.accountId]?.sortOrder?.toInt() }.thenBy { it.accountId }
            .thenByDescending { chainLineMap[it.chainTag]?.isDefault == true })
    }

    private fun sortRefEvmAddresses(refAddresses: MutableList<RefAddress>) {
        val accountMap =
            AppDatabase.getInstance().baseAccountDao().selectAll().associateBy { it.id }
        refAddresses.sortWith(compareBy<RefAddress> { if (BaseData.baseAccount?.id == it.accountId) -1 else accountMap[it.accountId]?.sortOrder?.toInt() }.thenBy { it.accountId })
    }

    private fun sortRefMajorAddresses(refAddresses: MutableList<RefAddress>) {
        val accountMap =
            AppDatabase.getInstance().baseAccountDao().selectAll().associateBy { it.id }
        val chainLineMap = allChains().associateBy { it.tag }
        refAddresses.sortWith(compareBy<RefAddress> { if (BaseData.baseAccount?.id == it.accountId) -1 else accountMap[it.accountId]?.sortOrder?.toInt() }.thenBy { it.accountId }
            .thenByDescending { chainLineMap[it.chainTag]?.isDefault == true })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}