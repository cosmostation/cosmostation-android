package wannabit.io.cosmostaion.ui.main.chain

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant.EXPLORER_BASE_URL
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.databinding.FragmentCosmosDetailBinding
import wannabit.io.cosmostaion.ui.main.MainActivity


class CosmosDetailFragment : Fragment() {

    private var _binding: FragmentCosmosDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagerAdapter: AccountPageAdapter

    private var selectedPosition: Int = -1
    private lateinit var selectedChain: CosmosLine

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCosmosDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initTab()
        onClickAction()
    }

    private fun initTab() {
        binding.apply {
            pagerAdapter = AccountPageAdapter(requireActivity(), selectedPosition)
            viewPager.adapter = pagerAdapter
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.str_coin)
                    else -> tab.text = getString(R.string.str_history)
                }
            }.attach()
        }
    }

    private fun initData() {
        val baseAccount = BaseData.baseAccount
        val args = arguments
        if (args != null) {
            selectedPosition = arguments?.getInt("selectPosition") ?: -1
        }
        baseAccount?.let {
            selectedChain = baseAccount.allCosmosLineChains[selectedPosition]
            binding.accountAddress.text = selectedChain.address
            binding.accountValue.text = formatAssetValue(selectedChain.allAssetValue())
        }
    }

    private fun onClickAction() {
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                findNavController().navigateUp()
                (activity as MainActivity?)?.onBackVisibleBottomNavi()
                return@setOnKeyListener true
            }
            false
        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
                (activity as MainActivity?)?.onBackVisibleBottomNavi()
            }

            btnAccount.setOnClickListener {
                val accountUrl = EXPLORER_BASE_URL + "/" + selectedChain.apiName + "/" + selectedChain.address
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(accountUrl)))
            }
        }
    }

    class AccountPageAdapter(fragmentActivity: FragmentActivity, selectedPosition: Int) : FragmentStateAdapter(fragmentActivity) {
        private val fragments = listOf(CoinFragment(selectedPosition), HistoryFragment(selectedPosition))

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}