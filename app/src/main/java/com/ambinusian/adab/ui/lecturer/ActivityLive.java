package com.ambinusian.adab.ui.lecturer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ambinusian.adab.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.button.MaterialButton;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class ActivityLive extends AppCompatActivity {

    private EditText editTextMessage;
    private TextView hasil;
    private MaterialButton sendBtn;
    private MaterialButton disconnectBtn;
    private Integer classId;
    private Socket mSocket;
    private Toolbar toolbar;

    //semetara aja
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private SpeechRecognizer speechRecognizer;
    private String kalimat ="";
    private String kalimatSementara;
    private Intent intent;

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
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        connectSocket();


        //invisibility editTextMessage and sendBtn for a while
        editTextMessage.setVisibility(View.GONE);
        sendBtn.setVisibility(View.GONE);

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

        requestAudioPermissions();
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
//                    hasil.setText(Html.fromHtml(text + " " + msg + " " + listening));
                }
            });
        });
    }

    private void emitToSocket(String text) {
        mSocket.emit("message", text); // ini nnt server socket bakalan terima variabel `text`, trus di emit lagi ke semua client yang konek ke room socket.io yang sama
    }
    private void speechToText(){
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"in_ID");
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION, true);
        amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        amanager.setStreamMute(AudioManager.STREAM_RING, true);
        amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);

        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {
                kalimatSementara = "";
            }

            @Override
            public void onRmsChanged(float v) {
            }

            @Override
            public void onBufferReceived(byte[] bytes) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int i) {
                speechRecognizer.startListening(intent);;

            }

            @Override
            public void onResults(Bundle bundle) {
                kalimat = Validasi( kalimat + " "+kalimatSementara) + " / ";
                speechRecognizer.startListening(intent);;

            }

            @Override
            public void onPartialResults(Bundle bundle) {
                ArrayList<String> matches =  bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                kalimatSementara = matches.get(0);

                if(matches != null){
                    String listening = "<font color='#EE0000'>Listening...</font>";
                    hasil.setText(Html.fromHtml(kalimat + " " +kalimatSementara + " " + listening));
                }
            }

            @Override
            public void onEvent(int i, Bundle bundle) {
            }
        });
        startVoiceInput();
    }

    private String Validasi(String kalimat){
        String hasil = kalimat;
        if(kalimat.contains("Apa") || kalimat.contains("apa") || kalimat.contains("Bagaimana") || kalimat.contains("bagaimana") || kalimat.contains("Kenapa") || kalimat.contains("kenapa") || kalimat.contains("Kapan") || kalimat.contains("kapan") || kalimat.contains("Mengapa") || kalimat.contains("mengapa")|| kalimat.contains("Berapa") || kalimat.contains("berapa") || kalimat.contains("Kah") || kalimat.contains("kah")){
            hasil = hasil + " ?";
        }
        else
        {
            hasil = hasil + "";
        }
        return hasil;
    }

    private void startVoiceInput() {
        speechRecognizer.startListening(intent);



    }

    //Requesting run-time permissions

    //Create placeholder for user's consent to record_audio permission.
    //This will be used in handling callback
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    private void requestAudioPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            //When permission is not granted by user, show them message why this permission is needed.
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {

                //Give user option to still opt-in the permissions
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);

            } else {
                // Show user dialog to grant permission to record audio
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        MY_PERMISSIONS_RECORD_AUDIO);
            }
        }
        //If permission is granted, then go ahead recording audio
        else if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED) {

            //Go ahead with recording audio now
            speechToText();
        }
    }

    //Handling callback
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_RECORD_AUDIO: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay!
                    speechToText();
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permissions Denied to record audio", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


}
