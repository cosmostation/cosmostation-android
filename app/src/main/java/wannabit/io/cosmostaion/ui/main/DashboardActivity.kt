package wannabit.io.cosmostaion.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.database.model.Chain
import wannabit.io.cosmostaion.databinding.ActivityDashboardBinding
import wannabit.io.cosmostaion.ui.chain.ChainSwitchFragment

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var adapter: DashboardAdapter
    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        setupRecyclerView()
        setupViewModels()
        loadData()
    }

    private fun loadData() {
        ApplicationViewModel.shared.loadPrices()
        ApplicationViewModel.shared.loadBalances()
    }

    private fun setupViews() {
        binding.manage.setOnClickListener {
            ChainSwitchFragment().show(supportFragmentManager, ChainSwitchFragment::class.java.name)
        }
    }

    private fun setupViewModels() {
        ApplicationViewModel.shared.currentWalletLiveData.observe(this) {
            binding.account.text = it.nickname
        }

        ApplicationViewModel.shared.pricesLiveData.observe(this) {
            calculateTotalBalance()
            adapter.notifyDataSetChanged()
        }

        ApplicationViewModel.shared.balancesLiveData.observe(this) {
            calculateTotalBalance()
            adapter.notifyDataSetChanged()
        }
    }

    private fun calculateTotalBalance() {
        ApplicationViewModel.shared.pricesLiveData.value?.let { prices ->
            ApplicationViewModel.shared.balancesLiveData.value?.let { balances ->
                //TODO calculate total balance
                binding.total.text = "$ 10,000.00"
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = DashboardAdapter(this)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter
        adapter.chains.addAll(Chain.allChains())
        adapter.notifyDataSetChanged()
    }
}