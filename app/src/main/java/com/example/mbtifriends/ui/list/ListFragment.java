package com.example.mbtifriends.ui.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mbtifriends.DatabaseManager;
import com.example.mbtifriends.Person;
import com.example.mbtifriends.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ListViewModel listViewModel;
    private View root;
    private ListView people_lv;
    private TextView sort_by_type;
    private TextView sort_by_surname;
    private ArrayList<Person> people;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        root = inflater.inflate(R.layout.fragment_list, container, false);
        people_lv = root.findViewById(R.id.people_lv);

        sort_by_surname = root.findViewById(R.id.sort_by_surname);
        sort_by_type = root.findViewById(R.id.sort_by_type);

        addListeners();
        return root;
    }

    void addListeners(){
        sort_by_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createList(1);
            }
        });

        sort_by_surname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createList(2);
            }
        });
        getPeople();
    }

    void getPeople(){
        DatabaseManager db = new DatabaseManager (
                getActivity(),
                "MBTIFriends.db",
                null,
                1
        );

        people = db.getPeople();
        db.close();
        createList(1);
    }

    void createList(Integer sorting){
        PeopleArrayAdapter adapter = new PeopleArrayAdapter (
                getActivity(),
                R.layout.person_row_layout,
                people,
                sorting
        );
        people_lv.setAdapter(adapter);
    }
}