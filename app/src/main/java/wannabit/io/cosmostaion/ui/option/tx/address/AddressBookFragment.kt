package wannabit.io.cosmostaion.ui.option.tx.address

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentAddressBookBinding

class AddressBookFragment(
    private val sendAddress: String?,
    private val targetChain: CosmosLine?,
    private val addressType: AddressType?,
    val listener: AddressBookSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentAddressBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var addressBookAdapter: AddressBookAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddressBookBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val refAddresses: MutableList<RefAddress> = mutableListOf()
        val addressBooks: MutableList<AddressBook> = mutableListOf()

        binding.apply {
            lifecycleScope.launch(Dispatchers.IO) {
                AppDatabase.getInstance().refAddressDao().selectAll().forEach { refAddress ->
                    if (addressType == AddressType.EVM_TRANSFER) {
                        if (refAddress.chainTag == targetChain?.tag && refAddress.dpAddress != sendAddress) {
                            if (Prefs.displayLegacy) {
                                refAddresses.add(refAddress)
                            } else {
                                allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }?.let { line ->
                                    if (line.isDefault) {
                                        refAddresses.add(refAddress)
                                    }
                                }
                            }
                        }
                    } else {
                        if (refAddress.dpAddress?.startsWith(targetChain?.accountPrefix!!) == true && refAddress.dpAddress != sendAddress) {
                            if (Prefs.displayLegacy) {
                                refAddresses.add(refAddress)
                            } else {
                                allCosmosLines().firstOrNull { it.tag == refAddress.chainTag }?.let { line ->
                                    if (line.isDefault) {
                                        refAddresses.add(refAddress)
                                    }
                                }
                            }
                        }
                    }
                }

                refAddresses.sortWith { o1, o2 ->
                    when {
                        o1.accountId == BaseData.baseAccount?.id -> -1
                        o2.accountId == BaseData.baseAccount?.id -> 1
                        else -> 0
                    }
                }

                AppDatabase.getInstance().addressBookDao().selectAll().forEach { book ->
                    if (book.chainName == targetChain?.name && book.address != sendAddress) {
                        addressBooks.add(book)
                    }
                }

                targetChain?.let { targetLine ->
                    withContext(Dispatchers.Main) {
                        if (refAddresses.size == 0 && addressBooks.size == 0) {
                            recycler.visibility = View.GONE
                            emptyLayout.visibility = View.VISIBLE

                        } else {
                            recycler.visibility = View.VISIBLE
                            emptyLayout.visibility = View.GONE

                            addressBookAdapter = AddressBookAdapter(
                                targetLine, refAddresses, addressBooks, addressType
                            )
                            recycler.setHasFixedSize(true)
                            recycler.layoutManager = LinearLayoutManager(requireContext())
                            recycler.adapter = addressBookAdapter

                            addressBookAdapter.setOnItemClickListener { refAddress, addressBook ->
                                listener.select(refAddress, addressBook)
                                dismiss()
                            }
                        }
                    }
                }
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