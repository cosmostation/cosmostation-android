package wannabit.io.cosmostaion.ui.tx.info.major

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.tabs.TabLayoutMediator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.databinding.FragmentBtcStakeInfoBinding
import wannabit.io.cosmostaion.ui.tx.genTx.major.BtcStakingFragment

class BtcStakeInfoFragment : Fragment() {

    private var _binding: FragmentBtcStakeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var btcStakePagerAdapter: BtcStakePagerAdapter

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): BtcStakeInfoFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = BtcStakeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtcStakeInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            val denom = if (selectedChain.isTestnet) "sBTC" else "BTC"
            titleManageStake.text = getString(R.string.title_manage_stake, denom)

            btcStakePagerAdapter = BtcStakePagerAdapter(
                this@BtcStakeInfoFragment, selectedChain
            )
            viewPager.adapter = btcStakePagerAdapter
            viewPager.offscreenPageLimit = 1
            viewPager.isUserInputEnabled = false
            tabLayout.bringToFront()

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = when (position) {
                    0 -> "Staking"
                    else -> "Unstaking"
                }
            }.attach()
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            btnBack.setOnClickListener {
                requireActivity().supportFragmentManager.popBackStack()
            }

            btnStake.setOnClickListener {
                handleOneClickWithDelay(BtcStakingFragment.newInstance(selectedChain))
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    class BtcStakePagerAdapter(
        fragment: Fragment, selectedChain: BaseChain
    ) : FragmentStateAdapter(fragment) {
        private val fragments = mutableListOf<Fragment>()

        init {
            fragments.add(BtcActiveFragment.newInstance(selectedChain))
//            fragments.add(SuiPendingFragment.newInstance(selectedChain))
        }

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