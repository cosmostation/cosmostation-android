package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentBep3AddressBinding

class Bep3AddressFragment(
    private val selectedRecipientChains: MutableList<CosmosLine>?,
    val listener: Bep3AddressListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentBep3AddressBinding? = null
    private val binding get() = _binding!!

    private lateinit var bep3AddressAdapter: Bep3AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBep3AddressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            bep3AddressAdapter = Bep3AddressAdapter()
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = bep3AddressAdapter
            bep3AddressAdapter.submitList(selectedRecipientChains)

            bep3AddressAdapter.setOnItemClickListener { address ->
                address?.let {
                    listener.address(address)
                    dismiss()
                }
            }
        }
    }
}

interface Bep3AddressListener {
    fun address(address: String)
}