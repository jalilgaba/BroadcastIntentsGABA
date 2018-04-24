package broadcastintents.gaba.diiage.org;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView contentTxt;
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent intent) {
            // TODO Auto-generated method stub
            int level = intent.getIntExtra("level", 0);

            if (level < 20) // si le niveau de la batterie est < 20 on affiche le toast
            {
                //contentTxt.setText("Battery level low : ("+String.valueOf(level) + "%)");
                Toast.makeText(arg0, "Battery level low : ("+String.valueOf(level) + "%)",
                        Toast.LENGTH_LONG).show();

            }
        }
    };

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        contentTxt = (TextView) this.findViewById(R.id.txtBatteryLevel);
        this.registerReceiver(this.mBatInfoReceiver,
                new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    @Override
    protected void onStart() {
        // evenement levé lorsqu'on reçoit un sms
        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.RECEIVE_SMS},10);
        super.onStart();
    }
}
