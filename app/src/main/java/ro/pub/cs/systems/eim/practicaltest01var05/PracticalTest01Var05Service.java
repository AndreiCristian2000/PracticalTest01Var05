package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;


public class PracticalTest01Var05Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra(Constants.TEXT_TO_SEND);

        ProcessingThread processingThread = new ProcessingThread(this, text);
        processingThread.start();

        return Service.START_REDELIVER_INTENT;
    }
}
