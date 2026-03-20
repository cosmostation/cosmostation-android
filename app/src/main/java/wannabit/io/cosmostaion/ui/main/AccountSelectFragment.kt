package wannabit.io.cosmostaion.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentCommonBottomBinding

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
            viewLifecycleOwner.lifecycleScope.launch {
                val appDatabase = AppDatabase.getInstance()
                val selectAllAccounts = withContext(Dispatchers.IO) {
                    appDatabase.baseAccountDao().selectAll()
                }

                val ctx = context ?: return@launch

                accountSelectAdapter = AccountSelectAdapter()
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(ctx)
                adapter = accountSelectAdapter
                accountSelectAdapter.submitList(selectAllAccounts)

                accountSelectAdapter.setOnItemClickListener {
                    val toAccountId = selectAllAccounts[it].id

                    viewLifecycleOwner.lifecycleScope.launch {
                        if (BaseData.baseAccount?.id != toAccountId) {
                            val toAccount = withContext(Dispatchers.IO) {
                                appDatabase.baseAccountDao().selectAccount(toAccountId)
                            }

                            toAccount?.let { account ->
                                Prefs.lastAccountId = account.id
                                BaseData.baseAccount = account
                            }

                            ApplicationViewModel.shared.currentAccount(
                                BaseData.baseAccount,
                                false
                            )
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