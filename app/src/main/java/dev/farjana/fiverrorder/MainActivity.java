package dev.farjana.fiverrorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.appdesign, null));
        VideoView videoView = findViewById(R.id.videoView);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.convert;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController media = new MediaController(this);
        videoView.setMediaController(media);
        media.setAnchorView(videoView);



    }
}