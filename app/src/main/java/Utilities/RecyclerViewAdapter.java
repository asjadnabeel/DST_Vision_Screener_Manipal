package Utilities;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.optometry.plymouth.mrda.R;

import org.w3c.dom.Text;

import java.util.Map;

/**
 * Created by broly on 18/03/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Activity activity;
    private Map<Integer, Double> percentages;

    public RecyclerViewAdapter(Map<Integer, Double> percentages, Activity activity) {
        this.percentages = percentages;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.level, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.level.setText("test");
        //holder.percentage.setText(percentages.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView level;
        private TextView percentage;

        public ViewHolder(View itemView) {
            super(itemView);
            level = (TextView) itemView.findViewById(R.id.level);
            percentage = (TextView) itemView.findViewById(R.id.percentage);

        }
    }
}
