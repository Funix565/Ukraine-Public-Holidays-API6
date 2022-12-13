package lab4.progmob.ukrainepublicholidaysapi6.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.time.Year;

import lab4.progmob.ukrainepublicholidaysapi6.databinding.ActivityMainBinding;
import lab4.progmob.ukrainepublicholidaysapi6.details.DetailsActivity;

public class MainActivity extends AppCompatActivity {

    // TODO: We can enter it in date spinner with years (from -- to)
    private static final String CURRENT_YEAR = Year.now().toString();

    private ActivityMainBinding binding;

    private MainViewModel viewModel;

    private HolidaysAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(MainViewModel.class);

        viewModel.getViewState().observe(this, state -> {
            binding.holidaysList.setVisibility(toVisibility(state.isShowList()));
            binding.progress.setVisibility(toVisibility(state.isShowProgress()));
            binding.emptyTv.setVisibility(toVisibility(state.isShowEmptyHint()));
            binding.errorTv.setVisibility(toVisibility(state.isShowError()));

            adapter.setHolidays(state.getHolidays());
        });

        // TODO: Get year from user
        viewModel.getHolidays(CURRENT_YEAR);

        initHolidaysList();
    }

    private void initHolidaysList() {
        LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.holidaysList.setLayoutManager(layoutManager);

        adapter = new HolidaysAdapter(holiday -> {
            Intent intent = new Intent(this, DetailsActivity.class);

            // TODO: Check localName?
            intent.putExtra(DetailsActivity.EXTRA_HOLIDAY, holiday.getLocalName());
            startActivity(intent);
        });
        binding.holidaysList.setAdapter(adapter);
    }

    static int toVisibility(boolean show) {
        return show ? View.VISIBLE : View.GONE;
    }
}