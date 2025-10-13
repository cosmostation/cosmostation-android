package wannabit.io.cosmostaion.ui.main

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
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel

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
                val selectAllAccounts = appDatabase.baseAccountDao().selectAll()

                withContext(Dispatchers.Main) {
                    accountSelectAdapter = AccountSelectAdapter()
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = accountSelectAdapter
                    accountSelectAdapter.submitList(selectAllAccounts)

                    accountSelectAdapter.setOnItemClickListener {
                        val toAccountId = selectAllAccounts[it].id
                        if (BaseData.baseAccount?.id != toAccountId) {
                            CoroutineScope(Dispatchers.IO).launch {
                                val toAccount =
                                    appDatabase.baseAccountDao().selectAccount(toAccountId)
                                withContext(Dispatchers.Main) {
                                    toAccount?.let { account ->
                                        Prefs.lastAccountId = account.id
                                        BaseData.baseAccount = account
                                    }
                                }
                                ApplicationViewModel.shared.currentAccount(BaseData.baseAccount, false)
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