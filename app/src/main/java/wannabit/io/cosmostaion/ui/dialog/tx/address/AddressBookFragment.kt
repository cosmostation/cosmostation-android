package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.database.AppDatabase
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
        binding.apply {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance().refAddressDao().selectAll().forEach { refAddress ->
                    refAddress.dpAddress?.let { address ->
                        if (addressType == AddressType.EVM_TRANSFER) {
                            if (refAddress.chainTag == targetChain?.tag && address != sendAddress) {
                                refAddresses.add(refAddress)
                            }
                        } else {
                            if (address.startsWith(targetChain?.accountPrefix!!) && address != sendAddress) {
                                refAddresses.add(refAddress)
                            }
                        }

                        val addressBooks: MutableList<AddressBook> = mutableListOf()
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

                                    addressBookAdapter = AddressBookAdapter(targetLine, refAddresses, addressBooks, addressType)
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