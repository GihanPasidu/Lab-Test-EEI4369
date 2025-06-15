package com.s23010306;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private VideoView mainbackgroundVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the background video
        mainbackgroundVideo = findViewById(R.id.backgroundVideo);
        setupVideoBackground();

        // Initialize the GET STARTED button
        Button getStartedButton = findViewById(R.id.getStartedButton);
        getStartedButton.setOnClickListener(v -> {
            // Create an Intent to start LoginActivity
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void setupVideoBackground() {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.main_background;
        Uri uri = Uri.parse(videoPath);
        mainbackgroundVideo.setVideoURI(uri);
        mainbackgroundVideo.setOnPreparedListener(mp -> {
            mp.setLooping(true);
            mp.setVolume(0f, 0f); // Mute
        });
        mainbackgroundVideo.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mainbackgroundVideo != null) {
            mainbackgroundVideo.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mainbackgroundVideo != null && !mainbackgroundVideo.isPlaying()) {
            mainbackgroundVideo.start();
        }
    }
}
