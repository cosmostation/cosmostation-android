package wannabit.io.cosmostaion.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var dashAdapter: DashboardAdapter

    private var baseAccount: BaseAccount? = null

    private var savedState: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initRecyclerView()
        onUpdateLoadData()
    }

    private fun initView() {
        baseAccount = BaseData.baseAccount
        baseAccount?.initAllData()

        binding.apply {
            accountName.text = BaseData.baseAccount?.name
        }
    }

    private fun initRecyclerView() {
        dashAdapter = DashboardAdapter(requireContext())
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = dashAdapter
            dashAdapter.submitList(baseAccount?.allCosmosLineChains as List<Any>?)

            dashAdapter.setOnItemClickListener {
                findNavController().navigate(R.id.action_dashboardFragment_to_cosmosDetailFragment, bundleOf("selectPosition" to it))
                (activity as MainActivity?)?.onNextHideBottomNavi()
            }
        }
    }

    private fun onUpdateLoadData() {
        baseAccount?.let {
            it.allCosmosLineChains.forEach { line ->
                line.setLoadDataCallBack(object : CosmosLine.LoadDataCallback {
                    override fun onDataLoaded(isLoaded: Boolean) {
                        if (isLoaded) {
                            requireActivity().runOnUiThread {
                                dashAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                })
            }
        }
    }
    private fun clickAction() {
        binding.apply {
            btnEdit.setOnClickListener {

            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}