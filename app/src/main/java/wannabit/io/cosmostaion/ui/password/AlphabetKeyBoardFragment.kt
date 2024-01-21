package wannabit.io.cosmostaion.ui.password

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentAlphabetKeyBoardBinding
import java.util.Random

class AlphabetKeyBoardFragment : KeyboardFragment(), View.OnClickListener {

    private var _binding: FragmentAlphabetKeyBoardBinding? = null
    private val binding get() = _binding!!

    private val alphabets = mutableListOf<String>()
    private val alphabetBtns = arrayOfNulls<Button>(26)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlphabetKeyBoardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            val passwordList = resources.getStringArray(R.array.password_alphabet)
            alphabets.addAll(passwordList)
            alphabets.shuffle(Random(System.nanoTime()))

            for (i in alphabetBtns.indices) {
                alphabetBtns[i] = requireView().findViewById(
                    resources.getIdentifier(
                        "password_char$i", "id", requireActivity().packageName
                    )
                )
                alphabetBtns[i]?.text = alphabets[i]
                alphabetBtns[i]?.setOnClickListener(this@AlphabetKeyBoardFragment)
            }
            passwordBack.setOnClickListener(this@AlphabetKeyBoardFragment)
        }
    }

    override fun onClick(view: View?) {
        if (view is Button) {
            if (listener != null) {
                val input = view.text.toString().trim().toCharArray()[0]
                initVibrate()
                listener?.userInsertPassword(input)
            }

        } else if (view is ImageButton) {
            if (listener != null) {
                initVibrate()
                listener?.userDeletePassword()
            }
        }
    }

    private fun initVibrate() {
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, 50))
        } else {
            vibrator.vibrate(100)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}