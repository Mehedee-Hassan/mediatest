package transition.activity.ui.liquiddark.com.mediaplayer;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    VideoView videoView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new MyTask().execute();
//        setupMediaPlayer();


        videoView = findViewById(R.id.testvv);
        setupMediaPlayer();
//vv();
    }


    public void playWav(){
        int minBufferSize = AudioTrack.getMinBufferSize(8000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT);
        int bufferSize = 1024;
        AudioTrack at = new AudioTrack(AudioManager.STREAM_MUSIC, 8000, AudioFormat.CHANNEL_CONFIGURATION_MONO, AudioFormat.ENCODING_PCM_16BIT, minBufferSize, AudioTrack.MODE_STREAM);
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath();

        int i = 0;
        byte[] s = new byte[bufferSize];
        try {

            InputStream inputStream =  getResources().openRawResource(R.raw.cc);
            DataInputStream dis = new DataInputStream(inputStream);

            at.play();
            while((i = dis.read(s, 0, bufferSize)) > -1){
                at.write(s, 0, i);

            }
            at.stop();
            at.release();
            dis.close();
            inputStream.close();


//            MediaPlayer mp = new MediaPlayer();
//
//            FileInputStream fis = new FileInputStream(inputStream);
//            mp.setDataSource(dis);
//
//            Toast.makeText(this, "Success, Path has been set", Toast.LENGTH_SHORT).show();
//
//            mp.prepare();
//            mp.start();

        } catch (FileNotFoundException e) {
            // TODO
            e.printStackTrace();
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }

    }


    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            playWav();
            return null;
        }
    }


    public void test() throws IOException {
        URL imageUrl = new URL("http://aamracloud.com/apps/remote.php/webdav/Music/tt/2.mp4");
        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
        conn.setRequestProperty("Authorization", "basic " +
                Base64.encode("mehedee:mehedee123".getBytes() ,Base64.DEFAULT));
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setInstanceFollowRedirects(true);
        InputStream is = conn.getInputStream();

        MediaPlayer mediaPlayer = new MediaPlayer();

//        mediaPlayer.setDataSource();


    }

    public void setupMediaPlayer(){
        Log.e("asdfas","1 ");

        // Setup Media Player and Prepare to Play
        MediaPlayer media = new MediaPlayer();
        media.setAudioStreamType(AudioManager.STREAM_MUSIC);

        // Authdata
        String username = "mehedee";
        String password = "mehedee123";

        // encrypt Authdata
        byte[] toEncrypt = (username + ":" + password).getBytes();
        String encoded = Base64.encodeToString(toEncrypt, Base64.DEFAULT);

        // create header
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization","Basic "+encoded);

        Uri uri = Uri.parse("http://aamracloud.com/apps/remote.php/webdav/Music/tt/2.mp4");
//        Uri uri = Uri.parse("https://github.com/Mehedee-Hassan/tt/blob/master/bb.mp3");
        Log.e("asdfas","Exception ");

        try {
            media.setDataSource(this,uri,headers);
//            media.setDataSource(this,uri);
        } catch (IOException e) {
            Log.e("asdfas","Exception "+e.getMessage());
        }
        Log.e("asdfas","Exception ");


        media.start();

//        media.prepareAsync();
//
//        media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//            }
//        });
    }

    public void vv(){


        // Authdata
        String username = "mehedee";
        String password = "mehedee123";

        // encrypt Authdata
        byte[] toEncrypt = (username + ":" + password).getBytes();
        String encoded = Base64.encodeToString(toEncrypt, Base64.DEFAULT);

        // create header
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization","Basic "+encoded);

        Uri uri = Uri.parse("https://github.com/Mehedee-Hassan/tt/blob/master/bb.mp3");
        Log.e("asdfas","Exception ");


        videoView.setVideoURI(uri);


    }
}
