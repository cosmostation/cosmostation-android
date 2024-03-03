package wannabit.io.cosmostaion.ui.option.tx.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.databinding.FragmentBep3AddressBinding

interface Bep3AddressListener {
    fun address(address: String)
}

class Bep3AddressFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentBep3AddressBinding? = null
    private val binding get() = _binding!!

    private var recipientAbleChains: MutableList<CosmosLine>? = mutableListOf()

    private lateinit var bep3AddressAdapter: Bep3AddressAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            recipientAbleChains: MutableList<CosmosLine>?, listener: Bep3AddressListener
        ): Bep3AddressFragment {
            val args = Bundle().apply {
                putParcelableArrayList("recipientAbleChains",
                    recipientAbleChains?.let { ArrayList(it) })
            }
            val fragment = Bep3AddressFragment()
            fragment.arguments = args
            fragment.bep3AddressListener = listener
            return fragment
        }
    }

    private var bep3AddressListener: Bep3AddressListener? = null

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
            recipientAbleChains = arguments?.getParcelableArrayList("recipientAbleChains")

            bep3AddressAdapter = Bep3AddressAdapter()
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = bep3AddressAdapter
            bep3AddressAdapter.submitList(recipientAbleChains)

            bep3AddressAdapter.setOnItemClickListener { address ->
                address?.let {
                    bep3AddressListener?.address(address)
                    dismiss()
                }
            }
        }
    }
}