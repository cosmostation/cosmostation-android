package wannabit.io.cosmostaion.fragment.txs.liquidstaking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentPersisLusBinding

class PersisLUSFragment : BaseFragment() {

    private var _binding: FragmentPersisLusBinding? = null
    private val binding get() = _binding!!

    private lateinit var persisLUSViewModel: PersisViewModel

    companion object {
        fun newInstance(): PersisLUSFragment = PersisLUSFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersisLusBinding.inflate(layoutInflater, container, false)
//        val respository = PersisLUSRepositoryImpl()
//        persisLUSViewModel = PersisLUSViewModel(respository) // ViewModelFactory를 사용해야함

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // api call 하는 부분
//        persisLUSViewModel.callAPI()
//
//        persisLUSViewModel.currentEpoch.observe(viewLifecycleOwner, Observer {currentEpoch ->
//            binding.textviewResponse.text = currentEpoch
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}