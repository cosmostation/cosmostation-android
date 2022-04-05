package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.ITimelessActivity;
import wannabit.io.cosmostaion.fragment.AlphabetKeyBoardFragment;
import wannabit.io.cosmostaion.fragment.KeyboardFragment;
import wannabit.io.cosmostaion.fragment.NumberKeyBoardFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.InitPasswordTask;
import wannabit.io.cosmostaion.utils.KeyboardListener;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.LockedViewPager;

public class PasswordSetActivity extends BaseActivity implements ITimelessActivity, KeyboardListener, TaskListener {

    private LinearLayout layerContents;
    private TextView passwordTextView;
    private final ImageView[] circleImageView = new ImageView[5];

    private LockedViewPager viewPager;
    private KeyboardPagerAdapter adapter;

    private String userInput = "";
    private String confirmInput = "";
    private boolean isConfirmSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        layerContents = findViewById(R.id.layer_contents);
        passwordTextView = findViewById(R.id.subtitleTextView);
        viewPager = findViewById(R.id.keyboardPager);

        for (int i = 0; i < circleImageView.length; i++) {
            circleImageView[i] = findViewById(getResources().getIdentifier("img_circle" + i, "id", getPackageName()));
        }

        viewPager.setOffscreenPageLimit(2);
        adapter = new KeyboardPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        onInitView();
    }

    private void onInitView() {
        isConfirmSequence = false;
        userInput = "";
        confirmInput = "";

        for (ImageView imageView : circleImageView) {
            imageView.setBackgroundResource(R.drawable.ic_pass_gr);
        }
        viewPager.setCurrentItem(0, true);
    }

    private void onUpdateCnt() {
        if (userInput == null)
            userInput = "";

        final int inputLength = userInput.length();
        for (int i = 0; i < circleImageView.length; i++) {
            circleImageView[i].setBackgroundResource(
                    i < inputLength ? R.drawable.ic_pass_pu : R.drawable.ic_pass_gr
            );
        }
    }

    private void onShakeView() {
        layerContents.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        animation.reset();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onInitView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        layerContents.startAnimation(animation);
    }

    private void onFinishInput() {
        if (isConfirmSequence) {
            passwordTextView.setVisibility(View.INVISIBLE);
            if (confirmInput.equals(userInput)) {
                onShowWaitDialog();
                new InitPasswordTask(getBaseApplication(), this).execute(confirmInput);

            } else {
                onShakeView();
                Toast.makeText(getBaseContext(), R.string.error_msg_password_not_same, Toast.LENGTH_SHORT).show();
            }

        } else {
            passwordTextView.setVisibility(View.VISIBLE);
            if (adapter != null && adapter.getFragments() != null) {
                for (KeyboardFragment frag : adapter.getFragments()) {
                    if (frag != null)
                        frag.onShuffleKeyboard();
                }
            }
            isConfirmSequence = true;
            confirmInput = userInput;
            userInput = "";
            for (ImageView imageView : circleImageView) {
                imageView.setBackgroundResource(R.drawable.ic_pass_gr);
            }
            viewPager.setCurrentItem(0, true);
        }
    }


    @Override
    public void userInsertKey(char input) {
        if (userInput == null || userInput.length() == 0) {
            userInput = String.valueOf(input);

        } else if (userInput.length() < 5) {
            userInput = userInput + input;
        }

        if (userInput.length() == 4) {
            viewPager.setCurrentItem(1, true);

        } else if (userInput.length() == 5 && WUtil.checkPasscodePattern(userInput)) {
            onFinishInput();

        } else if (userInput.length() == 5 && !WUtil.checkPasscodePattern(userInput)) {
            onInitView();
            return;
        }
        onUpdateCnt();

    }

    @Override
    public void userDeleteKey() {
        if (userInput == null || userInput.length() <= 0) {
            onBackPressed();
        } else if (userInput.length() == 4) {
            userInput = userInput.substring(0, userInput.length() - 1);
            viewPager.setCurrentItem(0, true);
        } else {
            userInput = userInput.substring(0, userInput.length() - 1);
        }
        onUpdateCnt();
    }

    @Override
    public void onBackPressed() {
        if (userInput != null && userInput.length() > 0) {
            userDeleteKey();
        } else if (isConfirmSequence) {
            isConfirmSequence = false;
            confirmInput = "";
            onInitView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        onHideWaitDialog();
        onNextPage();
    }

    public void onNextPage() {
        Intent result = new Intent();
        setResult(Activity.RESULT_OK, result);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
    }


    public class KeyboardPagerAdapter extends FragmentPagerAdapter {

        private final ArrayList<KeyboardFragment> fragments = new ArrayList<>();

        public KeyboardPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.clear();
            NumberKeyBoardFragment number = NumberKeyBoardFragment.newInstance();
            number.setListener(PasswordSetActivity.this);
            fragments.add(number);

            AlphabetKeyBoardFragment alphabet = AlphabetKeyBoardFragment.newInstance();
            alphabet.setListener(PasswordSetActivity.this);
            fragments.add(alphabet);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public ArrayList<KeyboardFragment> getFragments() {
            return fragments;
        }
    }
}
