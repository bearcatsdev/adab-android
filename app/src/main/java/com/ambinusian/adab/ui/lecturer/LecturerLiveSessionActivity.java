package com.ambinusian.adab.ui.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ambinusian.adab.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.button.MaterialButton;

import java.net.URISyntaxException;

public class LecturerLiveSessionActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private TextView hasil;
    private MaterialButton sendBtn;
    private MaterialButton disconnectBtn;
    private Integer classId;
    private Socket mSocket;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_live_session);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            classId = bundle.getInt("class_id");
        } else {
            finish();
        }

        editTextMessage = findViewById(R.id.editText_message);
        hasil = findViewById(R.id.textView);
        sendBtn = findViewById(R.id.button_send);
        disconnectBtn = findViewById(R.id.button_disconnect);
        toolbar = findViewById(R.id.toolbar_lecturerLiveSession);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connectSocket();

        hasil.setMovementMethod(new ScrollingMovementMethod());

        sendBtn.setOnClickListener(v -> {
            String textToSend = editTextMessage.getText().toString();
            emitToSocket(textToSend);
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void connectSocket() {
        try {
            mSocket = IO.socket("https://adabapi.bancet.cf");  // kalo gbs pake "http://adab.bancet.cf:3000"
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.connect();

        while(!mSocket.connected()) {
            Log.d("Socket.io", "connecting...");
        }

        mSocket.emit("join room", "7");

        mSocket.on("message", args -> {
            String msg = (String) args[0]; // msg itu dari dosen, coba tes tampilin aja kalo mau
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String text = hasil.getText().toString().replace("Listening...","");
                    String listening = "<font color='#EE0000'>Listening...</font>";
                    hasil.setText(Html.fromHtml(text + " " + msg + " " + listening));
                }
            });
        });
    }

    private void emitToSocket(String text) {
        mSocket.emit("message", text); // ini nnt server socket bakalan terima variabel `text`, trus di emit lagi ke semua client yang konek ke room socket.io yang sama
    }
}
