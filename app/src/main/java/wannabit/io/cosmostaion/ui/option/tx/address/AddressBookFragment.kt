package wannabit.io.cosmostaion.ui.option.tx.address

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentAddressBookBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType

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
                putSerializable("fromChain", fromChain)
                putSerializable("toChain", toChain)
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
                getSerializable(
                    "fromChain", BaseChain::class.java
                )?.let { fromChain = it }
                getSerializable(
                    "toChain", BaseChain::class.java
                )?.let { toChain = it }
                getSerializable(
                    "sendAssetType", SendAssetType::class.java
                )?.let { sendAssetType = it }

            } else {
                (getSerializable("fromChain") as? BaseChain)?.let {
                    fromChain = it
                }
                (getSerializable("toChain") as? BaseChain)?.let {
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
        val refAddresses: MutableList<RefAddress> = mutableListOf()
        val refEvmAddresses: MutableList<RefAddress> = mutableListOf()
        val addressBooks: MutableList<AddressBook> = mutableListOf()
        val evmAddressBooks: MutableList<AddressBook> = mutableListOf()

        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                when (sendAssetType) {
                    SendAssetType.ONLY_EVM_COIN, SendAssetType.ONLY_EVM_ERC20 -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
//                                if (fromChain is ChainOkt996Keccak) {
//                                    if (refAddress.dpAddress?.startsWith((toChain as ChainOkt996Keccak).accountPrefix + 1) == true && refAddress.dpAddress?.lowercase() != senderAddress.lowercase()) {
//                                        if (Prefs.displayLegacy) {
//                                            refAddresses.add(refAddress)
//                                        } else {
//                                            allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }
//                                                ?.let { chain ->
//                                                    if (chain.isDefault) {
//                                                        refAddresses.add(refAddress)
//                                                    }
//
//                                                } ?: run {
//                                                allEvmLines().firstOrNull { it.tag == refAddress.chainTag }
//                                                    ?.let { evmChain ->
//                                                        if (evmChain.isDefault) {
//                                                            refAddresses.add(refAddress)
//                                                        }
//                                                    }
//                                            }
//                                        }
//                                    }
//
//                                } else if ((fromChain as EthereumLine).supportCosmos) {
//                                    if (refAddress.chainTag == toChain.tag && refAddress.evmAddress != ByteUtils.convertBech32ToEvm(
//                                            senderAddress
//                                        )
//                                    ) {
//                                        refEvmAddresses.add(refAddress)
//                                    }
//
//                                } else {
//                                    if (refAddress.chainTag == toChain.tag && refAddress.evmAddress != senderAddress) {
//                                        refEvmAddresses.add(refAddress)
//                                    }
//                                }
                            }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
//                                if (fromChain is ChainOkt996Keccak) {
//                                    if (addressBook.chainName == toChain.name && addressBook.address.lowercase() != senderAddress.lowercase()) {
//                                        addressBooks.add(addressBook)
//                                    }
//
//                                } else if ((fromChain as EthereumLine).supportCosmos) {
//                                    if (addressBook.address.startsWith("0x") && addressBook.address != ByteUtils.convertBech32ToEvm(
//                                            senderAddress
//                                        )
//                                    ) {
//                                        evmAddressBooks.add(addressBook)
//                                    }
//
//                                } else {
//                                    if (addressBook.address.startsWith("0x") && addressBook.address != senderAddress) {
//                                        evmAddressBooks.add(addressBook)
//                                    }
//                                }
                            }
                    }

                    SendAssetType.COSMOS_EVM_COIN -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
                                if (refAddress.dpAddress?.startsWith((toChain as CosmosLine).accountPrefix + 1) == true && refAddress.dpAddress?.lowercase() != senderAddress.lowercase()) {
                                    if (Prefs.displayLegacy) {
                                        refAddresses.add(refAddress)
                                    } else {
                                        allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }
                                            ?.let { chain ->
                                                if (chain.isDefault) {
                                                    refAddresses.add(refAddress)
                                                }

                                            } ?: run {
                                            allEvmLines().firstOrNull { it.tag == refAddress.chainTag }
                                                ?.let { evmChain ->
                                                    if (evmChain.isDefault) {
                                                        refAddresses.add(refAddress)
                                                    }
                                                }
                                        }
                                    }
                                }
                            }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.chainName == toChain.name && addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    addressBooks.add(addressBook)
                                }
                            }

                        if (fromChain.tag == toChain.tag) {
                            AppDatabase.getInstance().refAddressDao().selectAll()
                                .forEach { refAddress ->
                                    if (refAddress.chainTag == toChain.tag && refAddress.evmAddress != ByteUtils.convertBech32ToEvm(
                                            senderAddress
                                        )
                                    ) {
                                        refEvmAddresses.add(refAddress)
                                    }
                                }

                            AppDatabase.getInstance().addressBookDao().selectAll()
                                .forEach { addressBook ->
                                    if (addressBook.address.startsWith("0x") && addressBook.address != ByteUtils.convertBech32ToEvm(
                                            senderAddress
                                        )
                                    ) {
                                        evmAddressBooks.add(addressBook)
                                    }
                                }
                        }
                    }

                    SendAssetType.ONLY_COSMOS_COIN, SendAssetType.ONLY_COSMOS_CW20 -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
                                if (refAddress.dpAddress?.startsWith((toChain as CosmosLine).accountPrefix + 1) == true && refAddress.dpAddress?.lowercase() != senderAddress.lowercase()) {
                                    if (Prefs.displayLegacy) {
                                        refAddresses.add(refAddress)
                                    } else {
                                        allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }
                                            ?.let { chain ->
                                                if (chain.isDefault) {
                                                    refAddresses.add(refAddress)
                                                }

                                            } ?: run {
                                            allEvmLines().firstOrNull { it.tag == refAddress.chainTag }
                                                ?.let { evmChain ->
                                                    if (evmChain.isDefault) {
                                                        refAddresses.add(refAddress)
                                                    }
                                                }
                                        }
                                    }
                                }
                            }
                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.chainName == toChain.name && addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    addressBooks.add(addressBook)
                                }
                            }
                    }
                }
                sortRefEvmAddresses(refEvmAddresses)
                sortRefAddresses(refAddresses)

                withContext(Dispatchers.Main) {
                    when (sendAssetType) {
                        SendAssetType.ONLY_EVM_COIN, SendAssetType.ONLY_EVM_ERC20 -> {
//                            if (fromChain is ChainOkt996Keccak) {
//                                if (refAddresses.isEmpty() && addressBooks.isEmpty()) {
//                                    recycler.visibility = View.GONE
//                                    emptyLayout.visibility = View.VISIBLE
//
//                                } else {
//                                    recycler.visibility = View.VISIBLE
//                                    emptyLayout.visibility = View.GONE
//                                    initCosmosRecyclerView(
//                                        refAddresses, addressBooks
//                                    )
//                                }
//                                segmentView.visibility = View.GONE
//                                evmRecycler.visibility = View.GONE
//
//                            } else {
//                                if (refEvmAddresses.isEmpty() && evmAddressBooks.isEmpty()) {
//                                    evmRecycler.visibility = View.GONE
//                                    emptyLayout.visibility = View.VISIBLE
//
//                                } else {
//                                    evmRecycler.visibility = View.VISIBLE
//                                    emptyLayout.visibility = View.GONE
//                                    initEvmRecyclerView(
//                                        refEvmAddresses, evmAddressBooks
//                                    )
//                                }
//                                segmentView.visibility = View.GONE
//                                recycler.visibility = View.GONE
//                            }
                        }

                        SendAssetType.COSMOS_EVM_COIN -> {
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

                            if (refEvmAddresses.isEmpty() && evmAddressBooks.isEmpty()) {
                                return@withContext
                            } else {
                                initEvmRecyclerView(refEvmAddresses, evmAddressBooks)
                            }

                            segmentView.visibility = View.VISIBLE
                            evmRecycler.visibility = View.GONE
                            initSegmentView()
                            segmentAction(
                                refEvmAddresses, refAddresses, evmAddressBooks, addressBooks
                            )
                        }

                        SendAssetType.ONLY_COSMOS_COIN, SendAssetType.ONLY_COSMOS_CW20 -> {
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

    private fun initSegmentView() {
        binding.apply {
            segmentView.setBackgroundResource(R.drawable.cell_search_bg)
            styleSegment.apply {
                setSelectedBackground(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
                setRipple(
                    ContextCompat.getColor(
                        requireContext(), R.color.color_accent_purple
                    )
                )
            }

            for (i in 0 until 2) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                styleSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )

                when (i) {
                    0 -> {
                        segmentView.btnTitle.text = "Cosmos Style"
                    }

                    else -> {
                        segmentView.btnTitle.text = "EVM Style"
                    }
                }
            }
        }
    }

    private fun segmentAction(
        refEvmAddresses: MutableList<RefAddress>,
        refAddresses: MutableList<RefAddress>,
        evmAddressBooks: MutableList<AddressBook>,
        addressBooks: MutableList<AddressBook>
    ) {
        binding.apply {
            styleSegment.setOnPositionChangedListener { position ->
                when (position) {
                    0 -> {
                        if (refAddresses.isEmpty() && addressBooks.isEmpty()) {
                            recycler.visibility = View.GONE
                            emptyLayout.visibility = View.VISIBLE

                        } else {
                            recycler.visibility = View.VISIBLE
                            emptyLayout.visibility = View.GONE
                        }
                        evmRecycler.visibility = View.GONE
                    }

                    else -> {
                        if (refEvmAddresses.isEmpty() && evmAddressBooks.isEmpty()) {
                            evmRecycler.visibility = View.GONE
                            emptyLayout.visibility = View.VISIBLE

                        } else {
                            evmRecycler.visibility = View.VISIBLE
                            emptyLayout.visibility = View.GONE
                        }
                        recycler.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun sortRefAddresses(refAddresses: MutableList<RefAddress>) {
        val accountMap =
            AppDatabase.getInstance().baseAccountDao().selectAll().associateBy { it.id }
        val cosmosLineMap = allCosmosLines().associateBy { it.tag }
        val evmLineMap = allEvmLines().associateBy { it.tag }
        refAddresses.sortWith(compareBy<RefAddress> { if (BaseData.baseAccount?.id == it.accountId) -1 else accountMap[it.accountId]?.sortOrder?.toInt() }.thenBy { it.accountId }
            .thenByDescending { evmLineMap[it.chainTag]?.isDefault == true }
            .thenByDescending { cosmosLineMap[it.chainTag]?.isDefault == true })
    }

    private fun sortRefEvmAddresses(refAddresses: MutableList<RefAddress>) {
        val accountMap =
            AppDatabase.getInstance().baseAccountDao().selectAll().associateBy { it.id }
        refAddresses.sortWith(compareBy<RefAddress> { if (BaseData.baseAccount?.id == it.accountId) -1 else accountMap[it.accountId]?.sortOrder?.toInt() }.thenBy { it.accountId })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}