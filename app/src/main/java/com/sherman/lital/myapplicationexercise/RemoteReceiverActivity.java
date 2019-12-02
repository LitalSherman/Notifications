package com.sherman.lital.myapplicationexercise;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RemoteReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_receiver);

        TextView textView = findViewById(R.id.receiver);

        Bundle bundle = RemoteInput.getResultsFromIntent(getIntent());

        if(bundle != null){
            textView.setText(bundle.getCharSequence(MainActivity.TXT_REPLAY).toString());
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.cancel(MainActivity.NOTIFICATION_ID);
    }
}
