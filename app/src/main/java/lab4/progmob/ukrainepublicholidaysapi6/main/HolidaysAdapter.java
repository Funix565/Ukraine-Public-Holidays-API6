package lab4.progmob.ukrainepublicholidaysapi6.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import lab4.progmob.ukrainepublicholidaysapi6.R;
import lab4.progmob.ukrainepublicholidaysapi6.model.Holiday;

public class HolidaysAdapter
        extends RecyclerView.Adapter<HolidaysAdapter.HolidayViewHolder>
        implements View.OnClickListener {

    private List<Holiday> holidays = Collections.emptyList();
    private HolidayListener listener;

    public HolidaysAdapter(HolidayListener listener) {
        this.listener = listener;
    }

    public void setHolidays(List<Holiday> holidays) {
        this.holidays = holidays;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        Holiday holiday = (Holiday) view.getTag();
        listener.onHolidayChosen(holiday);
    }

    @NonNull
    @Override
    public HolidayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View root = inflater.inflate(R.layout.item_holiday, parent, false);
        return new HolidayViewHolder(root, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HolidayViewHolder holder, int position) {
        Holiday holiday = holidays.get(position);
        holder.itemLocalNameTv.setText(holiday.getLocalName());
        holder.itemDate.setText(holiday.getDate().toString());
        holder.itemView.setTag(holiday);
    }

    @Override
    public int getItemCount() {
        return holidays.size();
    }

    static class HolidayViewHolder extends RecyclerView.ViewHolder {
        private TextView itemDate;
        private TextView itemLocalNameTv;

        public HolidayViewHolder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            itemDate = itemView.findViewById(R.id.itemDate);
            itemLocalNameTv = itemView.findViewById(R.id.itemLocalNameTv);
            itemView.setOnClickListener(listener);
        }
    }

    public interface HolidayListener {
        void onHolidayChosen(Holiday holiday);
    }
}
