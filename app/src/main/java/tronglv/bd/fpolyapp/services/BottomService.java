package tronglv.bd.fpolyapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BottomService extends IntentService {

    public static final String BOTTOM_SERVICE_EVENT = "BOTTOM_SERVICE_EVENT";
    public static final String BOTTOM_SERVICE_HOME = "BOTTOM_SERVICE_HOME";
    public static final String BOTTOM_SERVICE_NOTIFICATION = "BOTTOM_SERVICE_NOTIFICATION";
    public static final String BOTTOM_SERVICE_SCHEDULE = "BOTTOM_SERVICE_SCHEDULE";
    public static final String BOTTOM_SERVICE_PROFILE = "BOTTOM_SERVICE_PROFILE";

    public BottomService() {
        super("BottomService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action){
                case BOTTOM_SERVICE_HOME:
                    String home = "home";
                    Intent intentHome = new Intent(BOTTOM_SERVICE_EVENT);
                    intentHome.putExtra("data", home);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentHome);
                    break;
                case BOTTOM_SERVICE_NOTIFICATION:
                    String notification = "notification";
                    Intent intentNotification = new Intent(BOTTOM_SERVICE_EVENT);
                    intentNotification.putExtra("data", notification);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentNotification);
                    break;
                case BOTTOM_SERVICE_SCHEDULE:
                    String schedule = "schedule";
                    Intent intentSchedule = new Intent(BOTTOM_SERVICE_EVENT);
                    intentSchedule.putExtra("data", schedule);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentSchedule);
                    break;
                case BOTTOM_SERVICE_PROFILE:
                    String profile = "profile";
                    Intent intentProfile = new Intent(BOTTOM_SERVICE_EVENT);
                    intentProfile.putExtra("data", profile);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intentProfile);
                    break;
                default:
                    break;
            }
        }
    }
}