package wannabit.io.cosmostaion.ui.main.chain.major

import android.os.Bundle
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.chain.BaseChain

class MajorNftFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorNftFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorNftFragment()
            fragment.arguments = args
            return fragment
        }
    }
}