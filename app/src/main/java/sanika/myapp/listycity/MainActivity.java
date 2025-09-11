package sanika.myapp.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button; //new
import android.widget.EditText; //new
//import androidx.appcompat.app.AlertDialog; //new
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
//import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    String chosenCity= null;

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

        cityList= findViewById(R.id.city_list);
        EditText inputCity= findViewById(R.id.city_input);
        Button AddButton = findViewById(R.id.add_button);
        Button ConfirmButton = findViewById(R.id.confirm_button);
        Button DeleteButton = findViewById(R.id.delete_button);

        inputCity.setVisibility(View.GONE);
        ConfirmButton.setVisibility(View.GONE);

        dataList= new ArrayList<>();
        cityAdapter= new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        //cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        //cityList.setOnItemClickListener((parent, view, position, id) -> chosenCity = dataList.get(position));

        cityList.setOnItemClickListener((parent, view, position, id) ->{
        chosenCity=dataList.get(position);
        });

        // Add city button -> show input and confirm
        AddButton.setOnClickListener(v -> {
            inputCity.setVisibility(View.VISIBLE);
            ConfirmButton.setVisibility(View.VISIBLE);

        });

        // Confirm button -> add city
        ConfirmButton.setOnClickListener(v -> {
            String cityName = inputCity.getText().toString().trim();
            if (!cityName.isEmpty()) {
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                inputCity.setText("");
               inputCity.setVisibility(View.GONE);
               ConfirmButton.setVisibility(View.GONE);
            }
        });
        //String []cities= {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        //dataList.addAll(Arrays.asList(cities));


        // Delete city button
        DeleteButton.setOnClickListener(v -> {
            if (chosenCity != null) {
                dataList.remove(chosenCity);
                cityAdapter.notifyDataSetChanged();
                chosenCity = null;
            }
        });
    }
}
