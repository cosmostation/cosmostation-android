package wannabit.io.cosmostaion.ui.password

import androidx.fragment.app.Fragment

open class KeyboardFragment : Fragment() {

    var listener: KeyboardListener? = null

    fun setKeyboardListener(keyboardListener: KeyboardListener) {
        listener = keyboardListener
    }
}