package in.virussandy.algorithmtask;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Set;

public class NumberAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> numbers;
    private Set<Integer> highlightedNumbers;

    public NumberAdapter(Context context, ArrayList<Integer> numbers, Set<Integer> highlightedNumbers) {
        this.context = context;
        this.numbers = numbers;
        this.highlightedNumbers = highlightedNumbers;
    }

    @Override
    public int getCount() {
        return numbers.size();
    }

    @Override
    public Object getItem(int position) {
        return numbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));
            textView.setGravity(android.view.Gravity.CENTER);
            textView.setTextSize(16);
            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.grid_item_background));
        } else {
            textView = (TextView) convertView;
        }

        int number = numbers.get(position);
        textView.setText(String.valueOf(number));

        // Highlight numbers based on the rule
        if (highlightedNumbers.contains(number)) {
            textView.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200));
            textView.setBackground(AppCompatResources.getDrawable(context,R.drawable.grid_item_background_transparent));
            textView.setTextColor(Color.BLACK);
        } else {
            textView.setBackgroundColor(Color.WHITE);
            textView.setBackground(AppCompatResources.getDrawable(context,R.drawable.grid_item_background));
            textView.setTextColor(Color.BLACK);
        }

        textView.animate().scaleX(1.1f).scaleY(1.1f).setDuration(200).withEndAction(() -> {
            textView.animate().scaleX(1f).scaleY(1f).setDuration(200);
        });

        return textView;
    }

    public void updateHighlightedNumbers(Set<Integer> highlightedNumbers) {
        this.highlightedNumbers = highlightedNumbers;
        notifyDataSetChanged();
    }
}
