package wannabit.io.cosmostaion.fragment.txs.neutron.defi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentNeutronPoolBinding
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapBinding

class NeutronPoolFragment : BaseFragment() {

    private var _binding: FragmentNeutronPoolBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_neutron_pool, container, false)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}