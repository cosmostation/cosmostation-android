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

public class AlphabetKeyBoardFragment extends KeyboardFragment implements View.OnClickListener{

    private View                mRootView;
    private Button[]            mAlphabetBtns = new Button[26];
    private ImageButton         mBackBtn;
    private ArrayList<String>   mAlphabetArray = new ArrayList<>();

    public static AlphabetKeyBoardFragment newInstance() {
        AlphabetKeyBoardFragment fragment = new AlphabetKeyBoardFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_keyboard_alphabet, container, false);
        mAlphabetArray = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.password_alphabet)));
        Collections.shuffle(mAlphabetArray, new Random(System.nanoTime()));
        for(int i = 0; i < mAlphabetBtns.length; i++) {
            mAlphabetBtns[i] = mRootView.findViewById(getResources().getIdentifier("password_char" + i , "id", getBaseActivity().getPackageName()));
            mAlphabetBtns[i].setText(mAlphabetArray.get(i));
            mAlphabetBtns[i].setOnClickListener(this);
        }
        mBackBtn = mRootView.findViewById(R.id.password_back);
        mBackBtn.setOnClickListener(this);
        return mRootView;
    }

    @Override
    public void onShuffleKeyboard() {
        final Animation mFadeInAni = AnimationUtils.loadAnimation(getBaseActivity(), R.anim.fade_in);
        mFadeInAni.reset();

        Animation mFadeOutAni = AnimationUtils.loadAnimation(getBaseActivity(), R.anim.fade_out);
        mFadeOutAni.reset();
        mFadeOutAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                Collections.shuffle(mAlphabetArray, new Random(System.nanoTime()));
                for(int i = 0; i < mAlphabetBtns.length; i++) {
                    mAlphabetBtns[i].setText(mAlphabetArray.get(i));
                }
                mRootView.startAnimation(mFadeInAni);
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        mRootView.startAnimation(mFadeOutAni);

    }

    @Override
    public void onClick(View view) {
        if(view instanceof Button) {
            if(mListner != null) {
                mListner.userInsertKey(((Button)view).getText().toString().trim().toCharArray()[0]);
            }

        } else if (view instanceof ImageButton) {
            if(mListner != null) {
                mListner.userDeleteKey();
            }
        }
    }
}
