package com.sherman.lital.myapplicationexercise;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick {

    public static final String CHANNEL_ID = "channel_id";
    public static final String CHANNEL_NAME = "Notification Channel";
    public static final int NOTIFICATION_ID = 001;
    public static final String TXT_REPLAY = "text replay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createCells();
        RecyclerView rc = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rc.setLayoutManager(layoutManager);
        CellAdapter adapter = new CellAdapter(createCells(), this);
        rc.setAdapter(adapter);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rc.addItemDecoration(decoration);
    }

    private List<Cell> createCells() {

        Cell cell11 = new Cell("Lital", "052-555555555", "Haronnovich3, Rehovot", 35, "lital@lital.com");
        Cell cell12 = new Cell("Moshe", "052-555555555", "Haronnovich3, Rehovot", 21, "Moshe@lital.com");
        Cell cell13 = new Cell("Yaniv", "052-555555555", "Haronnovich3, Rehovot", 44, "Yaniv@lital.com");
        Cell cell14 = new Cell("Moran", "052-555555555", "Haronnovich3, Rehovot", 87, "Moran@lital.com");
        Cell cell15 = new Cell("Vladi", "052-555555555", "Haronnovich3, Rehovot", 98, "Vladi@lital.com");
        Cell cell16 = new Cell("Michale", "052-555555555", "Haronnovich3, Rehovot", 11, "Michale@lital.com");
        Cell cell17 = new Cell("Gadi", "052-555555555", "Haronnovich3, Rehovot", 18, "Gadi@lital.com");
        Cell cell18 = new Cell("Sharon", "052-555555555", "Haronnovich3, Rehovot", 56, "Sharon@lital.com");
        Cell cell19 = new Cell("Gil", "052-555555555", "Haronnovich3, Rehovot", 22, "Gil@lital.com");

        List<Cell> cells = new ArrayList<>();
        cells.add(cell11);
        cells.add(cell12);
        cells.add(cell13);
        cells.add(cell14);
        cells.add(cell15);
        cells.add(cell16);
        cells.add(cell17);
        cells.add(cell18);
        cells.add(cell19);

        return cells;
    }

    @Override
    public void onNameClick(String name) {
        createNotificationChannelIfNeeded();
        addNotification(name);
    }

    private void createNotificationChannelIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void addNotification(String name) {
        Intent landingIntent = new Intent(this, LandingActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent landingPanding = PendingIntent.getActivity(this, 0, landingIntent, PendingIntent.FLAG_ONE_SHOT);

        //Adding action button to notification
        Intent yesIntent = new Intent(this, YesActivity.class);
        yesIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent yesPanding = PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_ONE_SHOT);

        Intent noIntent = new Intent(this, NoActivity.class);
        noIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent noPanding = PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_ONE_SHOT);

        // The notification will work but to open new activity or doing someting we need to add setContentIntent
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(name)
                .setContentText("You click on " + name + "!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                //To open activity
                .setContentIntent(landingPanding)
                //To add action buttons
                .addAction(R.drawable.ic_launcher_background, "YES", yesPanding)
                .addAction(R.drawable.ic_launcher_background, "NO", noPanding);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            RemoteInput remoteInput = new RemoteInput.Builder(TXT_REPLAY).setLabel("Replay").build();

            Intent replayIntent = new Intent(this, RemoteReceiverActivity.class);
            replayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent replayPanding = PendingIntent.getActivity(this, 0, replayIntent, PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_launcher_background, "Replay",
                    replayPanding).addRemoteInput(remoteInput).build();

            builder.addAction(action);
        }
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}
