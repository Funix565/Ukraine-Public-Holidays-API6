package lab4.progmob.ukrainepublicholidaysapi6.details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import lab4.progmob.ukrainepublicholidaysapi6.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_HOLIDAY = "EXTRA_HOLIDAY";

    private ActivityDetailsBinding binding;

    private DetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // TODO: Better use date instead of name
        String holiday = getIntent().getStringExtra(EXTRA_HOLIDAY);
        if (holiday == null || holiday.trim().isEmpty()) {
            throw new RuntimeException("There is no holiday");
        }

        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(DetailsViewModel.class);
    }
}