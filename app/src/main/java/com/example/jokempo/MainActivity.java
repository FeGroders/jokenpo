package com.example.jokempo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtVitorias;
    TextView txtRecorde;
    ImageView jogador1;
    ImageView jogador2;
    ImageView escolha1;
    ImageView escolha2;
    ImageButton btnPedra;
    ImageButton btnTesoura;
    ImageButton btnPapel;
    Animation some;
    Animation aparece;
    int jogada1=0;
    int jogada2=0;
    int vitorias=0;
    int recorde=0;
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        media = MediaPlayer.create(MainActivity.this, R.raw.alex_play);
        txtVitorias = findViewById(R.id.txtVitorias);
        txtRecorde = findViewById(R.id.txtRecorde);
        escolha1 = findViewById(R.id.escolha1);
        escolha2 = findViewById(R.id.escolha2);
        jogador1 = findViewById(R.id.imgJogador1);
        jogador2 = findViewById(R.id.imgJogador2);
        btnPapel = findViewById(R.id.btnPapel);
        btnPedra = findViewById(R.id.btnPedra);
        btnTesoura = findViewById(R.id.btnTesoura);
;
        some = new AlphaAnimation(1,0);
        some.setDuration(1500);
        aparece = new AlphaAnimation(0,1);
        aparece.setDuration(100);


        some.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                escolha2.setVisibility(View.INVISIBLE);
                escolha2.startAnimation(aparece);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        aparece.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                sorteiaJogadaInimigo();

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                verificaJogada();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    public void tocouBotao(View view){
        tocaSom();
        escolha2.startAnimation(some);
        //escolha1.setVisibility(View.VISIBLE);
        switch(view.getId()){
            case(R.id.btnPapel):
                jogada1=1;
                escolha1.setImageResource(R.drawable.papelpng);
                break;
            case(R.id.btnPedra):
                jogada1=2;
                escolha1.setImageResource(R.drawable.pedrapng);
                break;
            case(R.id.btnTesoura):
                jogada1=3;
                escolha1.setImageResource(R.drawable.tesourapng);
                break;
        }
        jogador2.setImageResource(R.drawable.box);

    }

    public void sorteiaJogadaInimigo(){
        Random r= new Random();
        int numRandom = r.nextInt(3);
        escolha2.setVisibility(View.VISIBLE);
        escolha1.setVisibility(View.VISIBLE);
        switch (numRandom){
            case 0:
                jogada2=1;
                escolha2.setImageResource(R.drawable.papelpng);
                break;
            case 1:
                jogada2=2;
                escolha2.setImageResource(R.drawable.pedrapng);
                break;
            case 2:
                jogada2=3;
                escolha2.setImageResource(R.drawable.tesourapng);
                break;
        }
    }

    public void verificaJogada(){
        if(jogada1==jogada2){
            Toast.makeText(this, "Empate!", Toast.LENGTH_SHORT).show();
        }
        else if(jogada1==1 && jogada2==2 || jogada1==2 && jogada2==3 || jogada1==3 && jogada2==1){
            Toast.makeText(this, "Ganhou!", Toast.LENGTH_SHORT).show();
            vitorias++;
        }
        else{
            Toast.makeText(this, "Perdeu!", Toast.LENGTH_SHORT).show();
            vitorias=0;
        }
        if(vitorias>recorde)
        {
            recorde=vitorias;
        }
        txtVitorias.setText("Vitórias consecutivas: "+vitorias);
        txtRecorde.setText("Recorde: "+recorde);
    }

    public void tocaSom(){
        if(media!=null){
            media.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        media.release();
        media.stop();
    }
}