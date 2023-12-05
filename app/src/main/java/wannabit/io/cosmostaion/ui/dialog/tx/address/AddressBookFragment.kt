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
                        if (address.startsWith(targetChain?.accountPrefix!!) && address != sendAddress) {
                            refAddresses.add(refAddress)

                            withContext(Dispatchers.Main) {
                                myAccountCnt.text = refAddresses.size.toString()
                                addressBookAdapter = AddressBookAdapter(targetChain, addressType)
                                recycler.setHasFixedSize(true)
                                recycler.layoutManager = LinearLayoutManager(requireContext())
                                recycler.adapter = addressBookAdapter
                                addressBookAdapter.submitList(refAddresses)

                                addressBookAdapter.setOnItemClickListener { address ->
                                    address?.let {
                                        listener.select(it)
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
    fun select(address: String)
}