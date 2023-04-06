package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private EditText editTextSecondary;
    private Button verifyButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        editTextSecondary = (EditText)findViewById(R.id.text_field_secondary);
        verifyButton = (Button)findViewById(R.id.verify_button);
        cancelButton = (Button)findViewById(R.id.cancel_button);

        verifyButton.setOnClickListener(it -> {
            setResult(RESULT_OK, null);
            finish();
        });

        cancelButton.setOnClickListener(it -> {
            setResult(RESULT_CANCELED, null);
            finish();
        });

        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey(Constants.TEXT_TO_SEND)) {
            String textSent = intent.getStringExtra(Constants.TEXT_TO_SEND);
            Log.d("text", textSent);
            editTextSecondary.setText(textSent);
        }
    }
}