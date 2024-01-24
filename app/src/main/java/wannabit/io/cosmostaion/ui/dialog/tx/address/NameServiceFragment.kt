package wannabit.io.cosmostaion.ui.dialog.tx.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.model.res.NameService
import wannabit.io.cosmostaion.databinding.FragmentNameServiceBinding

interface NameServiceSelectListener {
    fun select(address: String)
}

class NameServiceFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNameServiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var nameServiceAdapter: NameServiceAdapter

    companion object {
        @JvmStatic
        fun newInstance(
            nameServices: MutableList<NameService>, listener: NameServiceSelectListener
        ): NameServiceFragment {
            val args = Bundle().apply {
                putParcelableArrayList("nameServices", ArrayList(nameServices))
            }
            val fragment = NameServiceFragment()
            fragment.arguments = args
            fragment.nameServiceSelectListener = listener
            return fragment
        }
    }

    private var nameServiceSelectListener: NameServiceSelectListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNameServiceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            val nameServices: MutableList<NameService>? =
                arguments?.getParcelableArrayList("nameServices")
            nameServices?.let {
                selectTitle.text = getString(R.string.title_name_service, it[0].name)
            }

            nameServiceAdapter = NameServiceAdapter()
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = nameServiceAdapter
            nameServiceAdapter.submitList(nameServices)

            nameServiceAdapter.setOnItemClickListener {
                nameServiceSelectListener?.select(it)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}