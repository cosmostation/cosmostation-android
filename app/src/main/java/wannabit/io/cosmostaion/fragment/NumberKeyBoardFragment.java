package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import wannabit.io.cosmostaion.R;

public class NumberKeyBoardFragment extends KeyboardFragment implements View.OnClickListener {

    private View rootView;
    private final Button[] numberButtons = new Button[10];
    private ArrayList<String> numberArray = new ArrayList<>();

    public static NumberKeyBoardFragment newInstance() {
        return new NumberKeyBoardFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_keyboard_number, container, false);
        numberArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.password_number)));
        Collections.shuffle(numberArray, new Random(System.nanoTime()));
        final String packageName = rootView.getContext().getPackageName();
        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = rootView.findViewById(getResources().getIdentifier("password_number" + i, "id", packageName));
            numberButtons[i].setText(numberArray.get(i));
            numberButtons[i].setOnClickListener(this);
        }
        rootView.findViewById(R.id.password_back).setOnClickListener(this);
        return rootView;
    }

    @Override
    public void shuffleKeyboard() {
        final Animation fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
        fadeInAnimation.reset();

        Animation fadeOutAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_out);
        fadeOutAnimation.reset();
        fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Collections.shuffle(numberArray, new Random(System.nanoTime()));
                for (int i = 0; i < numberButtons.length; i++) {
                    numberButtons[i].setText(numberArray.get(i));
                }
                rootView.startAnimation(fadeInAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        rootView.startAnimation(fadeOutAnimation);

    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            if (keyboardListener != null) {
                keyboardListener.userInsertKey(((Button) view).getText().toString().trim().toCharArray()[0]);
            }

        } else if (view instanceof ImageButton) {
            if (keyboardListener != null) {
                keyboardListener.userDeleteKey();
            }
        }
    }
}
