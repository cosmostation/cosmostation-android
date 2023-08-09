package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.activities.txs.authz.AuthzRevokeActivity
import wannabit.io.cosmostaion.activities.txs.neutron.dao.DaoProposalActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentRevokeStep0Binding

class RevokeStep0Fragment : BaseFragment() {

    private var _binding: FragmentRevokeStep0Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevokeStep0Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onUpdateView()
        onClick()
    }

    private fun onUpdateView() {

    }

    private fun onClick() {
        getSActivity()?.let { activity ->
            binding.apply {
                btnCancel.setOnClickListener {
                    activity.onBeforeStep()
                }

                btnNext.setOnClickListener {
                    activity.onNextStep()
                }
            }
        }
    }

    private fun getSActivity(): AuthzRevokeActivity? {
        return baseActivity as? AuthzRevokeActivity
    }
}