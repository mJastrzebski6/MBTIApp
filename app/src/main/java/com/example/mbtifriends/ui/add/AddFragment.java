package com.example.mbtifriends.ui.add;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mbtifriends.DatabaseManager;
import com.example.mbtifriends.Person;
import com.example.mbtifriends.R;
import com.example.mbtifriends.Constants;

public class AddFragment extends Fragment {

    private AddViewModel addViewModel;
    private View root;

    private EditText first_name_input;
    private EditText last_name_input;
    private Switch gender_input;
    private Spinner type_input;

    @SuppressLint("CutPasteId")
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        addViewModel = new ViewModelProvider(this).get(AddViewModel.class);
        root = inflater.inflate(R.layout.fragment_add, container, false);
        first_name_input = root.findViewById(R.id.firstname_input);
        last_name_input= root.findViewById(R.id.surname_input);
        gender_input= root.findViewById(R.id.gender_input);
        type_input = root.findViewById(R.id.type_input);



//        final TextView textView = root.findViewById(R.id.text_notifications);
//        addViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        final Button addButton = root.findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPerson();
            }
        });
        final Spinner spinner = root.findViewById(R.id.type_input);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Constants.types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
                //Toast.makeText(parent.getContext(), "Selected: " + tutorialsName,          Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        return root;
    }

    public void addPerson(){
        DatabaseManager db = new DatabaseManager (
                getActivity(),
                "MBTIFriends.db",
                null,
                1
        );



        String first_name = first_name_input.getText().toString().trim();
        String last_name = last_name_input.getText().toString().trim();

        boolean result = db.checkIfPersonExists(first_name, last_name);

        if (result) Toast.makeText(getActivity(), "Osoba już istnieje", Toast.LENGTH_LONG).show();
        else{
            String gender = gender_input.isChecked() ? "female" : "male";
            String type = type_input.getSelectedItem().toString();

            Person person = new Person(first_name, last_name, type, gender, "0");
            boolean success = db.insertPerson(person);

            if(success){
                Toast.makeText(getActivity(), "Dodano", Toast.LENGTH_SHORT).show();
                resetForm();
            }
            else Toast.makeText(getActivity(), "Błąd podczas dodawania", Toast.LENGTH_LONG).show();

        }


        db.close();
    }
    void resetForm(){
        first_name_input.setText("");
        last_name_input.setText("");

    }
}