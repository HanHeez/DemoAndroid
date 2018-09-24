package com.gtv.hanhee.mediaappmusic;

import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton btnPrev, btnNext, btnPlay, btnStop;
    TextView txtTitle, txtTimeSong, txtTimeTotal;
    SeekBar sbSong;
    MediaPlayer mediaPlayer;

    ArrayList<Song> arraySong;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddControls();
        AddSong();
        AddEvents();


    }

    private void khoitaoMediaPlayer() {
        mediaPlayer = MediaPlayer.create(MainActivity.this,arraySong.get(position).getFile());

        txtTitle.setText(arraySong.get(position).getTitle());
    }

    private void AddEvents() {

        khoitaoMediaPlayer();




        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                setTimeTotal();
                UpdateTimeSong();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                khoitaoMediaPlayer();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position > arraySong.size() - 1) {
                    position = 0;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                UpdateTimeSong();
            }

        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position--;
                if (position < 0) {
                    position = arraySong.size()-1;
                }
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                khoitaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimeTotal();
                UpdateTimeSong();
            }

        });

        sbSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(sbSong.getProgress());
            }
        });
    }

    private  void UpdateTimeSong() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhDangGio = new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(dinhDangGio.format(mediaPlayer.getCurrentPosition()));
                sbSong.setProgress(mediaPlayer.getCurrentPosition());

                // kiem tra thoi gian bai hat > neu ket thuc thi next

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if (position > arraySong.size() - 1) {
                            position = 0;
                        }
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.stop();
                        }
                        khoitaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        setTimeTotal();
                        UpdateTimeSong();
                    }
                });

                handler.postDelayed(this,500);
            }
        }, 100);
    }
    private void setTimeTotal() {
        SimpleDateFormat DinhDangGio = new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(DinhDangGio.format(mediaPlayer.getDuration()));
        // gan' max cua sbSong = mediaPlay.getDuration();
        sbSong.setMax(mediaPlayer.getDuration());

    }
    private void AddSong() {
        arraySong = new ArrayList<>();
        arraySong.add(new Song("Anh Muon", R.raw.anh_muon));
        arraySong.add(new Song("Anh Xin Loi", R.raw.anh_xin_loi));
        arraySong.add(new Song("Co Anh O Day", R.raw.co_anh_o_day));

    }

    private void AddControls() {
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnStop = (ImageButton) findViewById(R.id.btnStop);
        sbSong = (SeekBar) findViewById(R.id.sbSong);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTimeTotal = (TextView) findViewById(R.id.txtTimeTotal);
        txtTimeSong = (TextView) findViewById(R.id.txtTimeSong);
    }
}
