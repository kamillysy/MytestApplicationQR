
/**
 * Created by kamilek on 05.11.16.
 */

package com.example.kamilek.mytestapplicationqr.pair;

import android.app.AlertDialog;
import android.bluetooth.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;


public class BTactic extends AppCompatActivity {
    BluetoothAdapter btAdapter;
    Set<BluetoothDevice> pairedDevices;
    BluetoothSocket socket;
    final UUID myUUID;
    private CharSequence[] pairedDevicesList;

    public BTactic() {
        socket = null;
        myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            Toast.makeText(getApplicationContext(), "No bluetooth detected", Toast.LENGTH_LONG).show();
        } else {
            if (!btAdapter.isEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(intent, 1);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(getApplicationContext(), "Bluetooth must be enabled to continue", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isConnected() {
        if (socket.isConnected()) {
            return true;
        }
        return false;
    }

    private void connect(String deviceadress) {
        BluetoothDevice btDevice = btAdapter.getRemoteDevice(deviceadress);
        try {
            socket = btDevice.createInsecureRfcommSocketToServiceRecord(myUUID);
            socket.connect();
        } catch (IOException socketException) {
            socketException.printStackTrace();
            disconnect();
        }

    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException e) {
            //TODO: obsługa wyjątku

        }
        socket = null;
    }

    private int sendbyte(byte x) {
        if (isConnected()) {
            try {
                socket.getOutputStream().write(x);
            } catch (IOException writeException) {
                writeException.printStackTrace();
                disconnect();
                return 0;
            }
        } else {
            Toast.makeText(getApplicationContext(), "Firstly, connect to the device", Toast.LENGTH_SHORT).show();
            return 0;
        }
        return 1;
    }


    public void btList(View view) {
        ArrayList addresses = new ArrayList();
        generatePairedDevicesList(addresses);
        createAlertDialogList(addresses);
    }

    private void createAlertDialogList(final ArrayList addresses) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("Choose paired BT device");
        builder.setItems(pairedDevicesList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, final int which) {
                connect(addresses.get(which).toString());
            }
        });

        builder.create();
        builder.show();
    }

    private void generatePairedDevicesList(ArrayList addresses) {
        pairedDevices = btAdapter.getBondedDevices();
        final ArrayList list = new ArrayList();
        pairedDevicesList = new CharSequence[pairedDevices.size()];
        if (pairedDevices.size() > 0) {
            int it = 0;
            for (BluetoothDevice bt : pairedDevices) {
                addresses.add(bt.getAddress());
                list.add(bt.getName() + "\n" + bt.getAddress());
                pairedDevicesList[it] = list.get(it).toString();
                it++;
            }
        } else {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices", Toast.LENGTH_SHORT).show();
        }
    }

}


//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fify_master);
//        btIniter();
//        buttonsIniter();
//        pwmControl();
//    }


//
//package com.example.kamillysy.platformcontroller;
//
//        import android.app.AlertDialog;
//        import android.bluetooth.*;
//        import android.content.DialogInterface;
//        import android.content.Intent;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.view.MotionEvent;
//        import android.widget.Button;
//        import android.widget.ImageButton;
//        import android.widget.SeekBar;
//        import android.widget.Toast;
//        import android.view.View.OnTouchListener;
//        import android.view.View;
//        import java.util.Set;
//        import java.util.ArrayList;
//        import android.widget.TextView;
//        import java.io.IOException;
//        import java.util.UUID;
//        import android.widget.SeekBar.OnSeekBarChangeListener;
//
//


//    private void buttonsIniter() {
//        btlist = (Button) findViewById(R.id.ButtonList);
//        final int[] idlist = {R.id.ButtonF, R.id.ButtonLF, R.id.ButtonRF,
//                R.id.ButtonL, R.id.ButtonR,
//                R.id.ButtonB, R.id.ButtonLB, R.id.ButtonRB};
//        final int[] commands = {111, 114, 115, 140, 150, 122, 124, 125};
//
//        for (int it = 0; it < imageButtons.length; it++) {
//            imageButtons[it] = (ImageButton) findViewById(idlist[it]);
//            final int finalIt = it;
//            imageButtons[finalIt].setOnTouchListener(new OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    buttonsAction(event, commands[finalIt]);
//                    return false;
//                }
//            });
//        }
//    }

//    private void buttonsAction(MotionEvent event, int command) {
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            sendComand(command);
//        } else if (event.getAction() == MotionEvent.ACTION_UP) {
//            sendComand(160);
//        }
//    }


//}


