package wannabit.io.cosmostaion.ui.main.chain

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
import wannabit.io.cosmostaion.databinding.FragmentCosmosDetailBinding
import wannabit.io.cosmostaion.ui.main.MainActivity


class CosmosDetailFragment : Fragment() {

    private var _binding: FragmentCosmosDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagerAdapter: AccountPageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCosmosDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTab()
        onClick()
    }

    private fun initTab() {
        binding.apply {
            pagerAdapter = AccountPageAdapter(requireActivity())
            viewPager.adapter = pagerAdapter
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Coin"
                    else -> tab.text = "History"
                }
            }.attach()
        }
    }

    private fun onClick() {
        // bottom bar
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                findNavController().popBackStack()
                (activity as MainActivity?)?.onBackVisibleBottomNavi()
                return@setOnKeyListener true
            }
            false
        }

        binding.apply {
            btnBack.setOnClickListener {
                findNavController().popBackStack()
                (activity as MainActivity?)?.onBackVisibleBottomNavi()
            }

            btnAccount.setOnClickListener {

            }
        }
    }

    class AccountPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        private val fragments = listOf(CoinFragment(), HistoryFragment())

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