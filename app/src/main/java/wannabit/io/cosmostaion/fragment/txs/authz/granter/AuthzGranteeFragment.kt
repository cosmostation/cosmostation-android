package wannabit.io.cosmostaion.fragment.txs.authz.granter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.activities.txs.authz.AuthzDetailActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentAuthzGranteeBinding
import wannabit.io.cosmostaion.model.viewModel.authz.AuthzViewModel
import wannabit.io.cosmostaion.utils.makeToast

class AuthzGranteeFragment : BaseFragment() {

    private var _binding: FragmentAuthzGranteeBinding? = null
    private val binding get() = _binding!!

    private val authzViewModel: AuthzViewModel by activityViewModels()
    private lateinit var authzGranteeAdapter: AuthzGranteeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthzGranteeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emptyRoot.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), baseActivity.mChainConfig.chainBgColor()))
        initRecyclerView()

        authzViewModel.granteeData(baseActivity.mChainConfig, baseActivity.mAccount.address)
        loadDataObserve()
    }

    private fun initRecyclerView() {
        authzGranteeAdapter = AuthzGranteeAdapter(requireContext(), baseDao, baseActivity.mChainConfig)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = authzGranteeAdapter
        }

        authzGranteeAdapter.setOnItemClickListener {
            Intent(requireContext(), AuthzDetailActivity::class.java).apply {
                putExtra("granter", it.first.granter)
                startActivity(this)
            }
        }
    }

    private fun loadDataObserve() {
        authzViewModel.authzGranteeData.observe(viewLifecycleOwner) { response ->
            if (response.size > 0) {
                binding.recycler.visibility = View.VISIBLE
                binding.emptyRoot.visibility = View.GONE
            } else {
                binding.recycler.visibility = View.GONE
                binding.emptyRoot.visibility = View.VISIBLE
            }
            authzGranteeAdapter.submitList(response)
        }

        authzViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            requireContext().makeToast(errorMessage)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}