package wannabit.io.cosmostaion.ui.tx.option.address

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
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin86Testnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentAddressBookBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
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

    private lateinit var myAccountAddressAdapter: MyAccountAddressAdapter
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
        initSegmentView()
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

        binding.apply {
            chainImg.setChainLogo(toChain)
            chainTitle.text = toChain.getChainName() + " Address"
        }

        initView()
    }

    private fun initView() {
        val refMajorAddresses: MutableList<RefAddress> = mutableListOf()
        val refAddresses: MutableList<RefAddress> = mutableListOf()
        val refEvmAddresses: MutableList<RefAddress> = mutableListOf()
        val addressBooks: MutableList<AddressBook> = mutableListOf()

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
                                            addressBooks.add(addressBook)
                                        }

                                    } else {
                                        if (addressBook.chainName == "EVM-universal" || addressBook.chainName == toChain.tag) {
                                            addressBooks.add(addressBook)
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

                    SendAssetType.SUI_COIN, SendAssetType.SUI_NFT, SendAssetType.IOTA_COIN, SendAssetType.IOTA_NFT, SendAssetType.SOLANA_COIN, SendAssetType.SOLANA_TOKEN -> {
                        AppDatabase.getInstance().refAddressDao().selectAll()
                            .forEach { refAddress ->
                                if (refAddress.chainTag == toChain.tag && refAddress.dpAddress?.lowercase() != fromChain.mainAddress.lowercase()) {
                                    refMajorAddresses.add(refAddress)
                                }
                            }

                        AppDatabase.getInstance().addressBookDao().selectAll()
                            .forEach { addressBook ->
                                if (addressBook.chainName == toChain.tag && addressBook.address.lowercase() != senderAddress.lowercase()) {
                                    addressBooks.add(addressBook)
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
                                        addressBooks.add(addressBook)

                                    } else if (!fromChain.isTestnet && chain?.isTestnet == false && chain is ChainBitCoin86) {
                                        addressBooks.add(addressBook)
                                    }
                                }
                            }
                    }
                }
                sortRefEvmAddresses(refEvmAddresses)
                sortRefAddresses(refAddresses)
                sortRefMajorAddresses(refMajorAddresses)

                val myAccountMnemonic: MutableList<RefAddress> = mutableListOf()
                val myAccountPrivate: MutableList<RefAddress> = mutableListOf()
                if (refAddresses.isNotEmpty()) {
                    refAddresses.forEach { refAddress ->
                        AppDatabase.getInstance().baseAccountDao()
                            .selectAccount(refAddress.accountId)?.let { account ->
                                if (account.type == BaseAccountType.MNEMONIC) {
                                    myAccountMnemonic.add(refAddress)
                                } else if (account.type == BaseAccountType.PRIVATE_KEY) {
                                    myAccountPrivate.add(refAddress)
                                }
                            }
                    }
                }

                val myEvmAccountMnemonic: MutableList<RefAddress> = mutableListOf()
                val myEvmAccountPrivate: MutableList<RefAddress> = mutableListOf()
                if (refEvmAddresses.isNotEmpty()) {
                    refEvmAddresses.forEach { refAddress ->
                        AppDatabase.getInstance().baseAccountDao()
                            .selectAccount(refAddress.accountId)?.let { account ->
                                if (account.type == BaseAccountType.MNEMONIC) {
                                    myEvmAccountMnemonic.add(refAddress)
                                } else if (account.type == BaseAccountType.PRIVATE_KEY) {
                                    myEvmAccountPrivate.add(refAddress)
                                }
                            }
                    }
                }

                val myMajorAccountMnemonic: MutableList<RefAddress> = mutableListOf()
                val myMajorAccountPrivate: MutableList<RefAddress> = mutableListOf()
                if (refMajorAddresses.isNotEmpty()) {
                    refMajorAddresses.forEach { refAddress ->
                        AppDatabase.getInstance().baseAccountDao()
                            .selectAccount(refAddress.accountId)?.let { account ->
                                if (account.type == BaseAccountType.MNEMONIC) {
                                    myMajorAccountMnemonic.add(refAddress)
                                } else if (account.type == BaseAccountType.PRIVATE_KEY) {
                                    myMajorAccountPrivate.add(refAddress)
                                }
                            }
                    }
                }

                withContext(Dispatchers.Main) {
                    initAddressBookRecyclerView(addressBooks)

                    when (sendAssetType) {
                        SendAssetType.ONLY_EVM_COIN, SendAssetType.ONLY_EVM_ERC20 -> {
                            if (refEvmAddresses.isEmpty()) {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                                noAddress.text = "No My Account"

                            } else {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                initMyAccountRecyclerView(myEvmAccountMnemonic, myEvmAccountPrivate)
                            }

                            initSegmentAction(refEvmAddresses, addressBooks)
                        }

                        SendAssetType.ONLY_COSMOS_COIN, SendAssetType.ONLY_COSMOS_CW20, SendAssetType.ONLY_COSMOS_GRC20 -> {
                            if (refAddresses.isEmpty()) {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                                noAddress.text = "No My Account"

                            } else {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                initMyAccountRecyclerView(myAccountMnemonic, myAccountPrivate)
                            }

                            initSegmentAction(refAddresses, addressBooks)
                        }

                        SendAssetType.SUI_COIN, SendAssetType.SUI_NFT, SendAssetType.BIT_COIN, SendAssetType.IOTA_COIN, SendAssetType.IOTA_NFT, SendAssetType.SOLANA_COIN, SendAssetType.SOLANA_TOKEN -> {
                            if (refMajorAddresses.isEmpty()) {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                                noAddress.text = "No My Account"

                            } else {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                                initMyAccountRecyclerView(
                                    myMajorAccountMnemonic, myMajorAccountPrivate
                                )
                            }

                            initSegmentAction(refMajorAddresses, addressBooks)
                        }
                    }
                }
            }
        }
    }

    private fun initMyAccountRecyclerView(
        myAccountMnemonic: MutableList<RefAddress>,
        myAccountPrivate: MutableList<RefAddress>,
    ) {
        binding.apply {
            myAccountAddressAdapter =
                MyAccountAddressAdapter(myAccountMnemonic, myAccountPrivate, sendAssetType)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = myAccountAddressAdapter

            myAccountAddressAdapter.setOnItemClickListener { address, memo ->
                addressBookSelectListener?.select(address, memo)
                dismiss()
            }
        }
    }

    private fun initAddressBookRecyclerView(addressBooks: MutableList<AddressBook>) {
        binding.apply {
            addressBookAdapter = AddressBookAdapter(addressBooks)
            addressBookRecycler.setHasFixedSize(true)
            addressBookRecycler.layoutManager = LinearLayoutManager(requireContext())
            addressBookRecycler.adapter = addressBookAdapter

            addressBookAdapter.setOnItemClickListener { address, memo ->
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
                        segmentView.btnTitle.text = "My Account"
                    }

                    else -> {
                        segmentView.btnTitle.text = "Address Book"
                    }
                }
            }
        }
    }

    private fun initSegmentAction(
        refAddresses: MutableList<RefAddress>, addressBooks: MutableList<AddressBook>
    ) {
        binding.apply {
            styleSegment.setOnPositionChangedListener { position ->
                if (isAdded) {
                    when (position) {
                        0 -> {
                            addressBookRecycler.visibility = View.GONE
                            if (refAddresses.isEmpty()) {
                                recycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                                noAddress.text = "No My Account"

                            } else {
                                recycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                            }
                        }

                        else -> {
                            recycler.visibility = View.GONE
                            if (addressBooks.isEmpty()) {
                                addressBookRecycler.visibility = View.GONE
                                emptyLayout.visibility = View.VISIBLE
                                noAddress.text = "No Address Book"

                            } else {
                                addressBookRecycler.visibility = View.VISIBLE
                                emptyLayout.visibility = View.GONE
                            }
                        }
                    }
                }
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