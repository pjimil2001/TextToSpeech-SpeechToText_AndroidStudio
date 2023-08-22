package com.maahi.it.ts_st;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import static android.Manifest.permission.RECORD_AUDIO;


public class MainActivity extends AppCompatActivity {
    private TextToSpeech mTts;
    private Button ts, st, stop;
    private TextView speech, text;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }

    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        FishBun.with(this).setImageAdapter(new GlideAdapter()).startAlbum();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        speech = findViewById(R.id.speech);
        ts = findViewById(R.id.ts);
        st = findViewById(R.id.st);
        stop = findViewById(R.id.stop);

        requestRecordAudioPermission();
        Spechtotext();


        st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSpeechRecognition();
                Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSpeechRecognition();
                Toast.makeText(MainActivity.this, "stop", Toast.LENGTH_SHORT).show();

            }
        });

        ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTextToSpeech(text.getText().toString());
            }
        });

        mTts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {

                    // TTS is initialized successfully

                }
            }
        });
    }


    // Convert text to speech
    private void convertTextToSpeech(String text) {
        mTts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTts.shutdown();
        mSpeechRecognizer.destroy();
    }


    private void requestRecordAudioPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String requiredPermission = android.Manifest.permission.RECORD_AUDIO;
            if (checkCallingOrSelfPermission(requiredPermission) == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{requiredPermission}, 101);
            }
        }
    }

    private void Spechtotext() {
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
            }

            @Override
            public void onBeginningOfSpeech() {
            }

            @Override
            public void onRmsChanged(float rmsdB) {
            }

            @Override
            public void onBufferReceived(byte[] buffer) {
            }

            @Override
            public void onEndOfSpeech() {
            }

            @Override
            public void onError(int error) {

                Log.d(TAG, "error " + error);
//                speech.setText("error " + error);
                Toast.makeText(MainActivity.this, "No, speech found.", Toast.LENGTH_SHORT).show();
            }

            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                if (matches != null && matches.size() > 0) {
                    String spokenText = matches.get(0);
                    speech.setText(spokenText.toString());
                }
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
            }

            @Override
            public void onEvent(int eventType, Bundle params) {
            }
        });

        Log.d("SpeechRecognizer", "created");
    }

    private void startSpeechRecognition() {
        Log.d("SpeechRecognizer", "startSpeechRecognition");
        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
    }

    // Stop listening for speech input
    private void stopSpeechRecognition() {
        Log.d("SpeechRecognizer", "stopSpeechRecognition");
        mSpeechRecognizer.stopListening();
    }
}