package com.example.androidarchitecturecomponents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_ID ="com.example.androidarchitecturecomponents.EXTRA_ID";
    public static final String EXTRA_TITLE ="com.example.androidarchitecturecomponents.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION ="com.example.androidarchitecturecomponents.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY ="com.example.androidarchitecturecomponents.EXTRA_PRIORITY";

    private EditText edittexttitle;
    private EditText edittextdescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        edittexttitle=findViewById(R.id.edit_text_title);
        edittextdescription=findViewById(R.id.edit_text_description);
        numberPickerPriority=findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(100);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            edittexttitle.setText(intent.getStringExtra(EXTRA_TITLE));
            edittextdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }
        else {
            setTitle("Add Note");
        }
    }
    private void saveNote(){
        String title = edittexttitle.getText().toString();
        String description = edittextdescription.getText().toString();
        int priority=numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please add Title and Description.", Toast.LENGTH_SHORT).show();
        }
        Intent data= new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);

        int id=getIntent().getIntExtra(EXTRA_ID,-1);

        if(id != -1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
              return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
