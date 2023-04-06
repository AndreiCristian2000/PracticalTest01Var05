package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ProcessingThread extends Thread {

    private Context context = null;
    private boolean isRunning = true;

    private String text;

    public ProcessingThread(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String[] parts = text.split(",");

        for (String part : parts) {
            Intent intent = new Intent();
            intent.setAction(Constants.ACTION_MESSAGE);
            intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA, part);
            context.sendBroadcast(intent);
            sleep();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
