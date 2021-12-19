package dev.farjana.fiverrorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    ConstraintLayout frametop, frameRight, frameBottom, frameleft, vidup, viddown, vidleft, vidright, vidcons;
    BitmapDrawable bd;
    int height;
    int width;
    int frameWidth = 24;
    int frameHeight = 24;
    int vidHeight = 0;
    int vidWidth = 0;
    int vidW, vidH, u = 40, v = 40;
    VideoView videoView;
    ConstraintLayout videoLayout;


    @SuppressLint("UseCompatLoadingForDrawables")
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmldesign);

        initializeAll();


        Display display = getWindowManager().getDefaultDisplay();
        int displayWidth = display.getWidth(); // ((display.getWidth()*20)/100)
        int displayHeight = display.getHeight();// ((display.getHeight()*30)/100)
        // ImageView


        bd = (BitmapDrawable) this.getResources().getDrawable(R.drawable.im1);
        height = bd.getBitmap().getHeight();
        width = bd.getBitmap().getWidth();

        if (height < displayHeight / 3 || width < displayWidth / 2) {
            frameHeight = 12;
            frameWidth = 12;
        }

        if (width > height) {
            width = displayWidth * 3 / 4;
            height = displayHeight / 4;
        } else {
            width = displayWidth * 9 / 20;
            height = displayHeight / 3;
        }



        // Video Contents .........
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.convert;

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);


        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(this, uri);
        vidW = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
        vidH = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
        retriever.release();

        MediaController media = new MediaController(this);

        videoView.setMediaController(media);
        media.setAnchorView(videoView);

        if (vidW > vidH) {
            if (vidW > displayWidth * 3 / 4) {

                vidWidth = displayWidth * 3 / 4;
                vidHeight = displayHeight / 4;
                u = 20;
                v = 24;
            } else {

                vidWidth = displayWidth / 3;
                vidHeight = displayHeight / 4;
                u = 28;
                v = 34;
            }

        } else {
            if (vidW > displayWidth * 3 / 4) {
                vidWidth = displayWidth * 3 / 5;
                vidHeight = displayHeight * 2 / 5;
                u = 38;
                v = 50;
            } else {

                vidWidth = vidW;
                vidHeight = displayHeight * 3 / 5;
                u = 30;
                v = 48;
            }
        }
        videoView.getLayoutParams().height = vidHeight;
        videoView.getLayoutParams().width = vidWidth;
        videoView.requestLayout();


        int h = vidHeight;
        int w = vidWidth;
        videoLayout.getLayoutParams().height = h;
        videoLayout.getLayoutParams().width = w;
        videoLayout.requestLayout();


        resizeFrame();

    }

    private void resizeFrame() {

        frameLayout.setBackground(bd);
        frameLayout.getLayoutParams().height = height;
        frameLayout.getLayoutParams().width = width;
        frameLayout.requestLayout();

        frameleft.getLayoutParams().height = height;
        frametop.getLayoutParams().width = width;
        frameRight.getLayoutParams().height = height;
        frameBottom.getLayoutParams().width = width;


        frameleft.getLayoutParams().width = frameWidth;
        frametop.getLayoutParams().height = frameHeight;
        frameRight.getLayoutParams().width = frameWidth;
        frameBottom.getLayoutParams().height = frameHeight;

        vidup.getLayoutParams().width = vidWidth;
        viddown.getLayoutParams().width = vidWidth;
        vidleft.getLayoutParams().height = vidHeight;
        vidright.getLayoutParams().height = vidHeight;

        vidup.getLayoutParams().height = v;
        viddown.getLayoutParams().height = v;
        vidleft.getLayoutParams().width = u;
        vidright.getLayoutParams().width = u;

        frameleft.requestLayout();
        frametop.requestLayout();
        frameRight.requestLayout();
        frameBottom.requestLayout();

        vidup.requestLayout();
        viddown.requestLayout();
        vidleft.requestLayout();
        vidright.requestLayout();

    }

    private void initializeAll() {
        videoLayout = findViewById(R.id.videoConstraint);
        videoView = findViewById(R.id.videoView);
        frameLayout = findViewById(R.id.framelayout);
        frameleft = findViewById(R.id.frameleft);
        frametop = findViewById(R.id.frametop);
        frameRight = findViewById(R.id.frameRight);
        frameBottom = findViewById(R.id.frameBottom);

        vidup = findViewById(R.id.up);
        viddown = findViewById(R.id.down);
        vidleft = findViewById(R.id.left);
        vidright = findViewById(R.id.right);

        vidcons = findViewById(R.id.vidcons);

    }
}