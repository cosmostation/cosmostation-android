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
import wannabit.io.cosmostaion.databinding.FragmentNumberKeyBoardBinding
import java.util.Random

class NumberKeyBoardFragment : KeyboardFragment(), View.OnClickListener {

    private var _binding: FragmentNumberKeyBoardBinding? = null
    private val binding get() = _binding!!

    private val numbers = mutableListOf<String>()
    private val numberBtns = arrayOfNulls<Button>(10)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNumberKeyBoardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            val passwordList = resources.getStringArray(R.array.password_number)
            numbers.addAll(passwordList)
            numbers.shuffle(Random(System.nanoTime()))

            for (i in numberBtns.indices) {
                numberBtns[i] = requireView().findViewById(
                    resources.getIdentifier(
                        "password_number$i", "id", requireActivity().packageName
                    )
                )
                numberBtns[i]?.text = numbers[i]
                numberBtns[i]?.setOnClickListener(this@NumberKeyBoardFragment)
            }
            passwordBack.setOnClickListener(this@NumberKeyBoardFragment)
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