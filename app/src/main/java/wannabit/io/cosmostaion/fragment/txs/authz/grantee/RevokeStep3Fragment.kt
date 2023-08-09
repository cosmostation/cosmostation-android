package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentRevokeStep3Binding

class RevokeStep3Fragment : BaseFragment() {

    private var _binding: FragmentRevokeStep3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevokeStep3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }
}