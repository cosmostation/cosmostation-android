package com.fulldive.wallet.presentation.main.history;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import wannabit.io.cosmostaion.R;

public class HistoryViewHeader extends RecyclerView.ItemDecoration {
    private final int topPadding;

    private View headerView;
    private TextView mItemCnt;

    public HistoryViewHeader(Context context) {
        topPadding = dpToPx(context, 26);
    }

    // dp -> pixel 단위로 변경
    private int dpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        if (headerView == null) {
            headerView = inflateHeaderView(parent);
            mItemCnt = headerView.findViewById(R.id.recycler_cnt);
            fixLayoutSize(headerView, parent);
        }
        mItemCnt.setText(String.format(Locale.ENGLISH, "%d", state.getItemCount()));

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            drawHeader(c, child, headerView);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.top = topPadding;
        }
    }

    private View inflateHeaderView(RecyclerView parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_header, parent, false);
    }

    private void drawHeader(Canvas c, View child, View headerView) {
        c.save();
        headerView.draw(c);
        c.restore();
    }

    private void fixLayoutSize(View view, ViewGroup parent) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                View.MeasureSpec.UNSPECIFIED);

        int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(),
                view.getLayoutParams().width);
        int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(),
                view.getLayoutParams().height);

        view.measure(childWidth, childHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }
}
