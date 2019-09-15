package com.ambinusian.adab.ui.lecturer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ambinusian.adab.R;
import com.ambinusian.adab.manager.APIManager;
import com.ambinusian.adab.manager.NetworkHelper;
import com.ambinusian.adab.preferences.UserPreferences;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.button.MaterialButton;

import org.json.HTTP;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

public class ActivityLive extends AppCompatActivity implements RecognitionListener {

    private TextView hasil;
    private MaterialButton endSession,pauseSession;
    private Integer classId;
    private Socket mSocket;
    private Toolbar toolbar;
    private RelativeLayout loadingLayout;
    private RelativeLayout contentLoadingLayout;
    private TextView className;
    private TextView classSession;
    private TextView courseTitle;
    private UserPreferences userPreferences;
    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private SpeechRecognizer speechRecognizer;
    private String kalimat ="";
    private String kalimatSementara;
    private Intent intent;
    private TextView toolbarTitle;
    private ScrollView scrollViewMain;


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

        hasil = findViewById(R.id.textView);
        endSession = findViewById(R.id.button_end_session);
        pauseSession = findViewById(R.id.button_pause_session);
        toolbar = findViewById(R.id.toolbar_lecturerLiveSession);
        loadingLayout = findViewById(R.id.layout_loading);
        contentLoadingLayout = findViewById(R.id.layout_loading_content);
        className = findViewById(R.id.tv_class_name);
        classSession = findViewById(R.id.tv_class_session);
        courseTitle = findViewById(R.id.tv_course_title);
        scrollViewMain = findViewById(R.id.scrollview_main);
        toolbarTitle = findViewById(R.id.toolbar_title);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbarTitle.setVisibility(View.GONE);
        scrollViewMain.setVisibility(View.GONE);

        hasil.setMovementMethod(new ScrollingMovementMethod());

        connectSocket();

        APIManager apiManager = new APIManager(this);
        userPreferences = new UserPreferences(this);
        apiManager.getClassDetails(userPreferences.getUserToken(), classId, new NetworkHelper.getClassDetails() {
            @Override
            public void onResponse(Boolean success, Map<String, Object> classDetails) {
                if (success) {
                    String courseTitleText = (String) classDetails.get("topic");
                    String classNameText = (String) classDetails.get("course_name");
                    String sessionText = getString(R.string.class_session) + " " + classDetails.get("session");

                    courseTitle.setText(courseTitleText);
                    className.setText(classNameText);
                    classSession.setText(sessionText);

                    //connect to the socket
                    connectSocket();

                    loadingLayout.setVisibility(View.GONE);
                    toolbarTitle.setVisibility(View.VISIBLE);
                    scrollViewMain.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(int errorCode, String errorReason) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        pauseSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage(R.string.dialog_pause_class_question)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                resetSpeechRecognizer();
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.create();
            }
        });

        endSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setMessage(R.string.dialog_end_class_question)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.create();
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

        mSocket.emit("join room",classId);

        if (mSocket.connected()) {
            Log.d("Socket.io", "oke bang sudah konek");
            contentLoadingLayout.setVisibility(View.GONE);

            // start speech recognizer
            requestAudioPermissions();
            resetSpeechRecognizer();
            speechRecognizer.startListening(intent);

        } else {
            Log.d("Socket.io", "error");
        }

        boolean theFirstTime = true;

        mSocket.on("message", args -> {
            String msg = (String) args[0]; // msg itu dari dosen, coba tes tampilin aja kalo mau
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(hasil.getText().toString().equals("")){
                        Log.d("message", msg);
                        String listening = "<font color='#EE0000'>Listening...</font>";
                        kalimat = msg;
                        hasil.setText(Html.fromHtml(  kalimat+ " " + listening));

                        scrollViewMain.fullScroll(View.FOCUS_DOWN);
                    }
                }
            });
        });
    }

    private void emitToSocket(String text) {
        Log.d("emit",text);
        mSocket.emit("message", text); // ini nnt server socket bakalan terima variabel `text`, trus di emit lagi ke semua client yang konek ke room socket.io yang sama
    }
    private void setIntentandAudio(){
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"in_ID");
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);

        AudioManager amanager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);
    }

    private void resetSpeechRecognizer() {

        if(speechRecognizer != null)
            speechRecognizer.destroy();
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        if(SpeechRecognizer.isRecognitionAvailable(this))
            speechRecognizer.setRecognitionListener(this);
        else
            finish();
    }

    private String Validasi(String kalimat){
        String hasil = kalimat;
        if(kalimat.contains("Apa") || kalimat.contains("apa") || kalimat.contains("Bagaimana") || kalimat.contains("bagaimana") || kalimat.contains("Kenapa") || kalimat.contains("kenapa") || kalimat.contains("Kapan") || kalimat.contains("kapan") || kalimat.contains("Mengapa") || kalimat.contains("mengapa")|| kalimat.contains("Berapa") || kalimat.contains("berapa") || kalimat.contains("Kah") || kalimat.contains("kah")){
            hasil = "?";
        }
        else
        {
            hasil = "";
        }
        return hasil;
    }

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
            setIntentandAudio();
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
                    setIntentandAudio();
                } else {
                    Toast.makeText(this, "Permissions Denied to record audio", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


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
        speechRecognizer.stopListening();
    }

    @Override
    public void onError(int i) {
        resetSpeechRecognizer();
        speechRecognizer.startListening(intent);
    }

    @Override
    public void onResults(Bundle bundle) {
        String questionMark = Validasi( kalimatSementara);
        kalimat = kalimat + kalimatSementara + questionMark + " / ";
        String listening = "<font color='#EE0000'>Listening...</font>";
        hasil.setText(Html.fromHtml(kalimat+" "+listening));
        speechRecognizer.startListening(intent);
    }

    @Override
    public void onPartialResults(Bundle bundle) {
        ArrayList<String> matches =  bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        emitToSocket(matches.get(0).replace(kalimatSementara,""));
        kalimatSementara = matches.get(0);

        scrollViewMain.fullScroll(View.FOCUS_DOWN);

        if(matches != null){
            String listening = "<font color='#EE0000'>Listening...</font>";
            hasil.setText(Html.fromHtml(kalimat + " " +kalimatSementara + " " + listening));
        }

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
