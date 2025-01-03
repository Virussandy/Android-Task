package in.virussandy.algorithmtask;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(Context context, String[] items) {
        super(context, android.R.layout.simple_spinner_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setTextSize(16);
        textView.setTextColor(getContext().getResources().getColor(android.R.color.black));
        return textView;
    }
}
