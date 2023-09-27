package wannabit.io.cosmostaion.ui.main.setting

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
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.ui.main.DashboardFragment

class AccountSelectFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommonBottomBinding? = null
    private val binding get() = _binding!!

    private lateinit var accountSelectAdapter: AccountSelectAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommonBottomBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.recycler.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val appDatabase = AppDatabase.getInstance()
                val selectAllAccount = appDatabase.baseAccountDao().selectAll()

                withContext(Dispatchers.Main) {
                    accountSelectAdapter = AccountSelectAdapter(requireContext())
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = accountSelectAdapter
                    accountSelectAdapter.submitList(selectAllAccount)

                    accountSelectAdapter.setOnItemClickListener {
                        val toAccountId = selectAllAccount[it].id
                        if (BaseData.baseAccount?.id != toAccountId) {
                            CoroutineScope(Dispatchers.IO).launch {
                                val toAccount = appDatabase.baseAccountDao().selectAccount(toAccountId)
                                withContext(Dispatchers.Main) {
                                    BaseData.setLastAccount(toAccount!!)
                                    BaseData.baseAccount = toAccount
                                }
                                val fragment = parentFragmentManager.fragments.firstOrNull { dashboardFragment ->
                                    dashboardFragment is DashboardFragment
                                } as? DashboardFragment
                                fragment?.onResume()
                            }
                        }
                        dismiss()
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