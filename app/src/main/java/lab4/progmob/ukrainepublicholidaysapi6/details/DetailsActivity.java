package lab4.progmob.ukrainepublicholidaysapi6.details;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import lab4.progmob.ukrainepublicholidaysapi6.App;
import lab4.progmob.ukrainepublicholidaysapi6.R;
import lab4.progmob.ukrainepublicholidaysapi6.databinding.ActivityDetailsBinding;
import lab4.progmob.ukrainepublicholidaysapi6.main.MainActivity;
import lab4.progmob.ukrainepublicholidaysapi6.model.Holiday;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_HOLIDAY = "EXTRA_HOLIDAY";

    private ActivityDetailsBinding binding;

    private DetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String holidayDate = getIntent().getStringExtra(EXTRA_HOLIDAY);
        if (holidayDate == null || holidayDate.trim().isEmpty()) {
            throw new RuntimeException("There is no holiday");
        }

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        viewModel = viewModelProvider.get(DetailsViewModel.class);

        viewModel.loadHolidayByDate(holidayDate);

        viewModel.getResults().observe(this, result -> {
            switch (result.getStatus()) {
                case SUCCESS:
                    // TODO: Null checks
                    Holiday holiday = result.getData();
                    if (holiday.getLocalName() != null) {
                        binding.localNameTv.setText(holiday.getLocalName());
                    }
                    else {
                        binding.localNameTv.setText(R.string.unknown);
                    }

                    binding.dateTv.setText(holiday.getDate());

                    if (holiday.getName() != null) {
                        binding.name.setText(holiday.getName());
                    }
                    else {
                        binding.name.setText(R.string.unknown);
                    }

                    if (holiday.getCountryCode() != null) {
                        binding.countryCode.setText(getString(R.string.country_code, holiday.getCountryCode()));
                    }
                    else {
                        binding.countryCode.setText(R.string.unknown);
                    }


                    binding.fixed.setText(getString(R.string.same_year_date, String.valueOf(holiday.isFixed())));
                    binding.global.setText(getString(R.string.every_county, String.valueOf(holiday.isGlobal())));

                    if (holiday.getLaunchYear() != null) {
                        binding.launchYear.setText(getString(R.string.launch_year, holiday.getLaunchYear()));
                    }
                    else {
                        binding.launchYear.setText(R.string.unknown);
                    }

                    binding.progress.setVisibility(View.GONE);

                    break;
                case EMPTY:
                    binding.localNameTv.setText("");
                    binding.dateTv.setText("");
                    binding.name.setText("");
                    binding.countryCode.setText("");
                    binding.fixed.setText("");
                    binding.global.setText("");
                    binding.launchYear.setText("");
                    binding.progress.setVisibility(View.GONE);
                    break;
                case LOADING:
                    binding.localNameTv.setText("");
                    binding.dateTv.setText("");
                    binding.name.setText("");
                    binding.countryCode.setText("");
                    binding.fixed.setText("");
                    binding.global.setText("");
                    binding.launchYear.setText("");
                    binding.progress.setVisibility(View.VISIBLE);
            }
        });
    }
}