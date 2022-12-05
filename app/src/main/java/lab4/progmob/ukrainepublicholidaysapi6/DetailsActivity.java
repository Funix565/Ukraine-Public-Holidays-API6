package lab4.progmob.ukrainepublicholidaysapi6;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lab4.progmob.ukrainepublicholidaysapi6.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}