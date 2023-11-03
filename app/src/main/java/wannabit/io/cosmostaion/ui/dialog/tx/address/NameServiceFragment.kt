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

class NameServiceFragment(
    private val nameServiceList: MutableList<NameService>,
    val listener: NameServiceSelectListener
) : BottomSheetDialogFragment() {

    private var _binding: FragmentNameServiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var nameServiceAdapter: NameServiceAdapter

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
            selectTitle.text = getString(R.string.title_name_service, nameServiceList[0].name)

            nameServiceAdapter = NameServiceAdapter()
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = nameServiceAdapter
            nameServiceAdapter.submitList(nameServiceList)

            nameServiceAdapter.setOnItemClickListener {
                listener.select(it)
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

interface NameServiceSelectListener {
    fun select(address: String)
}