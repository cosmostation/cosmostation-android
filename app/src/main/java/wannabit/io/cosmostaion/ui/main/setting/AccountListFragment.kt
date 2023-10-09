package wannabit.io.cosmostaion.ui.main.setting

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.data.repository.account.AccountRepositoryImpl
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.databinding.FragmentAccountListBinding
import wannabit.io.cosmostaion.ui.dialog.account.MnemonicCheckFragment
import wannabit.io.cosmostaion.ui.dialog.account.PrivateCheckFragment
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModel
import wannabit.io.cosmostaion.ui.viewmodel.account.AccountViewModelProviderFactory

class AccountListFragment : Fragment() {

    private var _binding: FragmentAccountListBinding? = null
    private val binding get() = _binding!!

    private lateinit var accountListAdapter: AccountListAdapter

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initRecyclerView()
        clickAction()
        checkAccountData()
    }

    private fun initViewModel() {
        val accountRepository = AccountRepositoryImpl()
        val accountViewModelProviderFactory = AccountViewModelProviderFactory(accountRepository)
        accountViewModel = ViewModelProvider(
            requireActivity(),
            accountViewModelProviderFactory
        )[AccountViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.recycler.apply {
            CoroutineScope(Dispatchers.IO).launch {
                val appDatabase = AppDatabase.getInstance()
                val selectAllAccount = appDatabase.baseAccountDao().selectAll()

                withContext(Dispatchers.Main) {
                    accountListAdapter =
                        AccountListAdapter(requireContext(), popupClickAction)
                    setHasFixedSize(true)
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = accountListAdapter
                    accountListAdapter.submitList(selectAllAccount)
                }
            }
        }
    }

    private fun clickAction() {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                requireActivity().supportFragmentManager.popBackStack()
                (activity as MainActivity?)?.onBackVisibleBottomNavi()
            }
            false
        }

        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
                (activity as MainActivity?)?.onBackVisibleBottomNavi()
            }

            btnAddAccount.setOnClickListener {

            }
        }
    }

    private fun checkAccountData() {
        accountViewModel.baseAccounts.observe(viewLifecycleOwner) { result ->
            accountListAdapter = AccountListAdapter(requireContext(), popupClickAction)
            binding.recycler.setHasFixedSize(true)
            binding.recycler.layoutManager = LinearLayoutManager(requireContext())
            binding.recycler.adapter = accountListAdapter
            accountListAdapter.submitList(result)
        }
    }

    private val popupClickAction = object : AccountListAdapter.ClickListener {

        override fun checkMnemonicAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwMnemonicResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.IO) {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                R.animator.to_right,
                                R.animator.from_right,
                                R.animator.to_left,
                                R.animator.from_left
                            )
                            .add(R.id.fragment_container, MnemonicCheckFragment(account))
                            .hide(this@AccountListFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("AccountList")
                            .commitAllowingStateLoss()
                    }
                }
            }
        }

        override fun checkPrivateAction(account: BaseAccount) {
            ApplicationViewModel.shared.checkPwPrivateResult.observe(viewLifecycleOwner) {
                lifecycleScope.launch {
                    delay(300)
                    withContext(Dispatchers.IO) {
                        requireActivity().supportFragmentManager.beginTransaction()
                            .setCustomAnimations(
                                R.animator.to_right,
                                R.animator.from_right,
                                R.animator.to_left,
                                R.animator.from_left
                            )
                            .add(R.id.fragment_container, PrivateCheckFragment(account))
                            .hide(this@AccountListFragment)
                            .setReorderingAllowed(true)
                            .addToBackStack("AccountList")
                            .commitAllowingStateLoss()
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