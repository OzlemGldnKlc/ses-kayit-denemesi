package com.example.loginactivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class SesKayit extends AppCompatActivity {
    MediaRecorder mRecorder;
    MediaPlayer mplayer;
    String cikisDosyasi = null;
    Context context = this;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ses_kayit);
        cikisDosyasi= Environment.getExternalStorageDirectory().getAbsolutePath()+"/kayit.3gpp";
        mRecorder = new MediaRecorder();
        //setAudioSource-kaydedilecek sesin kaynağı- MIC (mikrofon)
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //setOutputFormat-kaydedilecek sesin hangi formatta kaydedileceğini belirtiyor
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //AudioEncoder - Codec değerlerini gösteriyor
        mRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        //set.OutputFile - Sesin nereye kaydedileceğini belirliyor.
        mRecorder.setOutputFile(cikisDosyasi);
    }

    public void btnKayitBaslat(View view) {
        try {

            //parametreler kayır işlemi için hazırlanıyor
            mRecorder.prepare();
            mRecorder.start();
            Toast.makeText(context, "Kayıt Başlıyor...", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnKayitDurdur(View view) {
        if(mplayer != null){
            mplayer.stop();
            mplayer.release();
            mplayer=null;
            Toast.makeText(context, "Kayıt Oynatılma Durduruluyor...", Toast.LENGTH_SHORT).show();
        }

    }

    public void btnOynat(View view) {

        mplayer = new MediaPlayer();
        try {


            mplayer.setDataSource(cikisDosyasi);
            mplayer.prepare();
            mplayer.start();
            Toast.makeText(context, "Kayıt Oynatılıyor...", Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnDurdur(View view) {
        mRecorder.stop();
        //release işlem bittikten sonra içeriği temizliyor tekrar kullanmayı sağlıyor
        mRecorder.release();
        mRecorder=null;
        Toast.makeText(context, "Kayıt Durduruluyor...", Toast.LENGTH_SHORT).show();

    }
}



