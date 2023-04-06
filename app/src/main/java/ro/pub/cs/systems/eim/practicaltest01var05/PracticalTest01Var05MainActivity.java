package ro.pub.cs.systems.eim.practicaltest01var05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button topLeftButton, topRightButton, centerButton, bottomLeftButton, bottomRightButton;
    private Button navigateToSecondaryActivityButton;
    int numberOfClicks = 0;
    String text;
    private int serviceStatus = Constants.SERVICE_STOPPED;

    private IntentFilter intentFilter = new IntentFilter();

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();


    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(Constants.BROADCAST_RECEIVER_EXTRA, intent.getStringExtra(Constants.BROADCAST_RECEIVER_EXTRA));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);


        editText = (EditText)findViewById(R.id.text_field);
        topLeftButton = (Button)findViewById(R.id.top_left_button);
        topRightButton = (Button)findViewById(R.id.top_right_button);
        centerButton = (Button)findViewById(R.id.center_button);
        bottomLeftButton = (Button)findViewById(R.id.bottom_left_button);
        bottomRightButton = (Button)findViewById(R.id.bottom_right_button);
        navigateToSecondaryActivityButton = (Button)findViewById(R.id.navigate_to_secondary_activity_button);

        topLeftButton.setOnClickListener(it -> {
            numberOfClicks++;
            text = editText.getText().toString();
            if (text.length() == 0) {
                editText.setText(text + topLeftButton.getText().toString());
            } else {
                editText.setText(text + ", " + topLeftButton.getText().toString());
            }
            startPracticalService();
        });

        topRightButton.setOnClickListener(it -> {
            numberOfClicks++;
            text = editText.getText().toString();
            if (text.length() == 0) {
                editText.setText(text + topRightButton.getText().toString());
            } else {
                editText.setText(text + ", " + topRightButton.getText().toString());
            }
            startPracticalService();
        });

        centerButton.setOnClickListener(it -> {
            numberOfClicks++;
            text = editText.getText().toString();
            if (text.length() == 0) {
                editText.setText(text + centerButton.getText().toString());
            } else {
                editText.setText(text + ", " + centerButton.getText().toString());
            }
            startPracticalService();
        });

        bottomLeftButton.setOnClickListener(it -> {
            numberOfClicks++;
            text = editText.getText().toString();
            if (text.length() == 0) {
                editText.setText(text + bottomLeftButton.getText().toString());
            } else {
                editText.setText(text + ", " + bottomLeftButton.getText().toString());
            }
            startPracticalService();
        });

        bottomRightButton.setOnClickListener(it -> {
            numberOfClicks++;
            text = editText.getText().toString();
            if (text.length() == 0) {
                editText.setText(text + bottomRightButton.getText().toString());
            } else {
                editText.setText(text + ", " + bottomRightButton.getText().toString());
            }
            startPracticalService();
        });

        navigateToSecondaryActivityButton.setOnClickListener(it -> {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);
            String textToSend = editText.getText().toString();
            intent.putExtra(Constants.TEXT_TO_SEND, textToSend);
            startActivityForResult(intent, Constants.SECONDARY_ACTIVITY_REQUEST_CODE);
        });

        intentFilter.addAction(Constants.ACTION_MESSAGE);
    }

    private void startPracticalService() {
        if (numberOfClicks > Constants.NUMBER_OF_CLICKS_THRESHOLD && serviceStatus == Constants.SERVICE_STOPPED) {
            Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
            intent.putExtra(Constants.TEXT_TO_SEND, editText.getText().toString());
            getApplicationContext().startService(intent);
            serviceStatus = Constants.SERVICE_STARTED;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.NUMBER_OF_CLICKS, numberOfClicks);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState.containsKey(Constants.NUMBER_OF_CLICKS)) {
            numberOfClicks = savedInstanceState.getInt(Constants.NUMBER_OF_CLICKS);
            Toast.makeText(this, "Number of clicks are " + numberOfClicks, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == Constants.SECONDARY_ACTIVITY_REQUEST_CODE) {
            numberOfClicks = 0;
            editText.setText("");
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
}