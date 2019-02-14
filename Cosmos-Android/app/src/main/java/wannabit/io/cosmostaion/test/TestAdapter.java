package wannabit.io.cosmostaion.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestItemHolder> {
    Context context;

    public TestAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public TestItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View v = inflater.inflate(R.layout.item_reward_promotion, viewGroup, false);
        return new TestItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestItemHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class TestItemHolder extends RecyclerView.ViewHolder {
        CardView itemCard;

        public TestItemHolder(View v) {
            super(v);
            itemCard           = itemView.findViewById(R.id.card_test);
        }
    }
}
