package wannabit.io.cosmostaion.ui.option.tx.address

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
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentAddressBookBinding
import wannabit.io.cosmostaion.ui.tx.step.SendAssetType

class AddressBookFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private lateinit var toChain: BaseChain
    private var senderAddress = ""
    private lateinit var sendAssetType: SendAssetType

    private lateinit var addressBookAdapter: AddressBookAdapter

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
                                    if (addressBook.chainName == toChain.name && addressBook.address.startsWith(
                                            "0x"
                                        ) && addressBook.address != ByteUtils.convertBech32ToEvm(
                                            senderAddress
                                        )
                                    ) {
                                        evmAddressBooks.add(addressBook)
                                    }
                                }
                        }
                    }

                    SendAssetType.ONLY_COSMOS_COIN -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
                                if (refAddress.dpAddress?.startsWith((toChain as CosmosLine).accountPrefix + 1) == true && refAddress.dpAddress?.lowercase() != senderAddress.lowercase()) {
                                    refAddresses.add(refAddress)
                                }
                            }
                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.chainName == toChain.name && addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    addressBooks.add(addressBook)
                                }
                            }
                    }

                    else -> {

                    }
                }


//                AppDatabase.getInstance().refAddressDao().selectAll().forEach { refAddress ->
//                    if (addressType == AddressType.EVM_TRANSFER) {
//                        if (refAddress.chainTag == targetChain?.tag && refAddress.dpAddress != sendAddress) {
//                            if (Prefs.displayLegacy) {
//                                refAddresses.add(refAddress)
//                            } else {
//                                allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }
//                                    ?.let { line ->
//                                        if (line.isDefault) {
//                                            refAddresses.add(refAddress)
//                                        }
//                                    }
//                            }
//                        }
//
//                    } else {
//                        if (refAddress.dpAddress?.startsWith(targetChain?.accountPrefix!! + 1) == true && refAddress.dpAddress != sendAddress) {
//                            if (Prefs.displayLegacy) {
//                                refAddresses.add(refAddress)
//                            } else {
//                                allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }
//                                    ?.let { line ->
//                                        if (line.isDefault) {
//                                            refAddresses.add(refAddress)
//                                        }
//                                    }
//                            }
//                        }
//                    }
//                }
                sortRefAddresses(refAddresses)
                sortRefAddresses(refEvmAddresses)

                withContext(Dispatchers.Main) {
                    if (refAddresses.size == 0 && addressBooks.size == 0) {
                        recycler.visibility = View.GONE
                        emptyLayout.visibility = View.VISIBLE

                    } else {
                        recycler.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE

                        addressBookAdapter = AddressBookAdapter(
                            toChain, refEvmAddresses, refAddresses, evmAddressBooks, addressBooks
                        )
                        recycler.setHasFixedSize(true)
                        recycler.layoutManager = LinearLayoutManager(requireContext())
                        recycler.adapter = addressBookAdapter

                        addressBookAdapter.setOnItemClickListener { refAddress, addressBook ->
                            addressBookSelectListener?.select(refAddress, addressBook)
                            dismiss()
                        }
                    }
                }
            }
        }
    }

    private fun sortRefAddresses(refAddresses: MutableList<RefAddress>) {
        refAddresses.sortWith { o1, o2 ->
            val o1Account = AppDatabase.getInstance().baseAccountDao().selectAccount(o1.accountId)
            val o2Account = AppDatabase.getInstance().baseAccountDao().selectAccount(o2.accountId)
            when {
                BaseData.baseAccount?.name == o1Account?.name -> -1
                BaseData.baseAccount?.name == o2Account?.name -> 1
                o1Account?.sortOrder!! < o2Account?.sortOrder!! -> -1
                o1Account.sortOrder > o2Account.sortOrder -> 1
                else -> 0
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface AddressBookSelectListener {
    fun select(refAddress: RefAddress?, addressBook: AddressBook?)
}