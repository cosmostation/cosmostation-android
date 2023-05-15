package wannabit.io.cosmostaion.fragment.txs.neutron.defi.liquidity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronLiquidityBinding

class NeutronLiquidityFragment : BaseFragment() {

    private var _binding: FragmentNeutronLiquidityBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_neutron_liquidity, container, false)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}