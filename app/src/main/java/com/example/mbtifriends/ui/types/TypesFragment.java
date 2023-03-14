package com.example.mbtifriends.ui.types;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mbtifriends.Constants;
import com.example.mbtifriends.CustomAdapter;
import com.example.mbtifriends.R;
import com.example.mbtifriends.TypeActivity;

public class TypesFragment extends Fragment {

    private TypesViewModel typesViewModel;
    private View root;
    private GridView gridView;



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        typesViewModel = new ViewModelProvider(this).get(TypesViewModel.class);
        root = inflater.inflate(R.layout.fragment_types, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        typesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        gridView = root.findViewById(R.id.gv);


        CustomAdapter customAdapter = new CustomAdapter(getContext(), Constants.types);
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Log.i("xxx", Constants.types.get(i));

                Intent intent = new Intent(getContext(), TypeActivity.class);
                intent.putExtra("type", Constants.types.get(i));
                startActivity(intent);
            }
        });
        return root;
    }
}
