package in.virussandy.algorithmtask;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private GridView numberGridView;
    private Spinner ruleSpinner;
    private ArrayList<Integer> numbers;
    private NumberAdapter numberAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numberGridView = findViewById(R.id.numberGridView);
        ruleSpinner = findViewById(R.id.ruleSpinner);


        numbers = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            numbers.add(i);
        }


        String[] rules = {"Select Algorithm","Odd Numbers", "Even Numbers", "Prime Numbers", "Fibonacci Numbers"};
        ArrayAdapter<String> spinnerAdapter = new CustomSpinnerAdapter(this,rules);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ruleSpinner.setAdapter(spinnerAdapter);



        numberAdapter = new NumberAdapter(this, numbers, getHighlightedNumbers("Select Algorithm"));
        numberGridView.setAdapter(numberAdapter);



        ruleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRule = rules[position];
                Set<Integer> highlightedNumbers = getHighlightedNumbers(selectedRule);
                numberAdapter.updateHighlightedNumbers(highlightedNumbers);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Set<Integer> highlightedNumbers = new HashSet<>(); // Empty set
                numberAdapter.updateHighlightedNumbers(highlightedNumbers);
            }
        });
    }
    private Set<Integer> getHighlightedNumbers(String rule) {
        Set<Integer> highlightedNumbers = new HashSet<>();
        switch (rule) {
            case "Odd Numbers":
                for (int num : numbers) {
                    if (num % 2 != 0) highlightedNumbers.add(num);
                }
                break;

            case "Even Numbers":
                for (int num : numbers) {
                    if (num % 2 == 0) highlightedNumbers.add(num);
                }
                break;

            case "Prime Numbers":
                for (int num : numbers) {
                    if (isPrime(num)) highlightedNumbers.add(num);
                }
                break;

            case "Fibonacci Numbers":
                for (int num : numbers) {
                    if (isFibonacci(num)) highlightedNumbers.add(num);
                }
                break;
        }
        return highlightedNumbers;
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private boolean isFibonacci(int num) {
        int a = 0, b = 1;
        while (a <= num) {
            if (a == num) return true;
            int temp = a + b;
            a = b;
            b = temp;
        }
        return false;
    }
}