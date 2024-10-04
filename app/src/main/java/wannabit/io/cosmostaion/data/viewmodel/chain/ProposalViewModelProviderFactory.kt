package wannabit.io.cosmostaion.data.viewmodel.chain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.data.repository.chain.ProposalRepository

class ProposalViewModelProviderFactory(
    private val proposalRepository: ProposalRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProposalViewModel::class.java)) {
            return ProposalViewModel(proposalRepository) as T
        }
        throw java.lang.IllegalArgumentException("ViewModel class not found")
    }
}