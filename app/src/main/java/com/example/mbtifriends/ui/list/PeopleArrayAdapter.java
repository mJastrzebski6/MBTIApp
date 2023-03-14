package com.example.mbtifriends.ui.list;

import com.example.mbtifriends.Constants;
import com.example.mbtifriends.DatabaseManager;
import com.example.mbtifriends.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mbtifriends.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class PeopleArrayAdapter extends ArrayAdapter {

    private ArrayList<Person> _list;
    private Context _context;
    private int _resource;
    private int _sorting;

    public PeopleArrayAdapter(@NonNull Context context, int resource, ArrayList<Person> objects, Integer sorting) {
        super(context, resource, objects);

        this._list = objects;
        this._context = context;
        this._resource = resource;
        this._sorting = sorting;

        if(sorting == 1){
            Collections.sort(this._list, new Comparator<Person>() {
                public int compare(Person p1, Person p2) {
                    if((p1.getType()).compareTo(p2.getType())>0) return +1;
                    else if((p1.getType()).compareTo(p2.getType())<0) return -1;
                    else if((p1.getFirst_name()).compareTo(p2.getFirst_name())>0) return +1;
                    else return -1;
                }
            });
        }

        if(sorting == 2){
            Collections.sort(this._list, new Comparator<Person>() {
                public int compare(Person p1, Person p2) {
                    if((p1.getLast_name()).compareTo(p2.getLast_name())>0) return +1;
                    else if((p1.getLast_name()).compareTo(p2.getLast_name())<0) return -1;
                    else if((p1.getFirst_name()).compareTo(p2.getFirst_name())>0) return +1;
                    else return -1;
                 }
            });
        }

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(_resource, null);

        TextView data_tv = convertView.findViewById(R.id.data_tv);

        Person person = _list.get(position);
        String data_string = person.getType() + " " + person.getFirst_name() + " " + person.getLast_name();

        if(person.getGender().equals("male")){
            data_tv.setTextColor(Color.parseColor("#303CBE"));
        }
        else data_tv.setTextColor(Color.parseColor("#de89fd"));

        data_tv.setText(data_string);

        ImageView delete_id = convertView.findViewById(R.id.delete_iv);
        ImageView edit_id = convertView.findViewById(R.id.edit_iv);

        delete_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeleteDialog(person, position);
            }
        });
        edit_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(person, position);
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    public Boolean deletePerson(Person person) {
        DatabaseManager db = new DatabaseManager(
                _context,
                "MBTIFriends.db",
                null,
                1
        );
        boolean result = db.deletePerson(person.getId());
        db.close();

        return result;
    }

    public void showDeleteDialog(Person person, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setMessage("Chcesz usunąć " + person.getFirst_name() + " " + person.getLast_name() + "?")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        boolean result = deletePerson(person);
                        if(result){
                            _list.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void showEditDialog(Person person, int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(_context);
        builder.setTitle("Edytowanie");

        LinearLayout lila1= new LinearLayout(_context);
        lila1.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(20, 10, 20, 10);


        final EditText first_name_input = new EditText(_context);
        first_name_input.setInputType(InputType.TYPE_CLASS_TEXT);
        first_name_input.setText(person.getFirst_name());
        first_name_input.setLayoutParams(params);
        lila1.addView(first_name_input);

        final EditText last_name_input = new EditText(_context);
        last_name_input.setInputType(InputType.TYPE_CLASS_TEXT);
        last_name_input.setText(person.getLast_name());
        last_name_input.setLayoutParams(params);
        lila1.addView(last_name_input);

        final Spinner type_input = new Spinner(_context);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(_context, android.R.layout.simple_spinner_item, Constants.types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_input.setAdapter(arrayAdapter);
        type_input.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String tutorialsName = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView <?> parent) {
            }
        });

        type_input.setSelection(Constants.types.indexOf(person.getType()));


        type_input.setLayoutParams(params);

        lila1.addView(type_input);
        builder.setView(lila1);



        builder.setPositiveButton("Zapisz", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String first_name = first_name_input.getText().toString().trim();
                String last_name = last_name_input.getText().toString().trim();
                String type = type_input.getSelectedItem().toString();

                person.setFirst_name(first_name);
                person.setLast_name(last_name);
                person.setType(type);

                boolean result =  updatePerson(person);

                if(result){
                    _list.get(position).setType(type);
                    _list.get(position).setLast_name(last_name);
                    _list.get(position).setFirst_name(first_name);
                    notifyDataSetChanged();
                }

            }
        });
        builder.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    public Boolean updatePerson(Person person) {
        DatabaseManager db = new DatabaseManager(
                _context,
                "MBTIFriends.db",
                null,
                1
        );
        boolean result = db.updatePerson(person);
        db.close();

        return result;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
