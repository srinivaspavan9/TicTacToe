package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    //0-x
    //1-0
    int activeplayer=0;
    int [] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void gameReset(){
        gameActive=true;
        activeplayer=0;
        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        TextView status= findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }

    public void playerTap(View view){
        ImageView img=(ImageView)view;
        int tappedImage=Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset();
        }
        if(gameState[tappedImage]==2 && gameActive) {
            gameState[tappedImage] = activeplayer;
            img.setTranslationY(-1000f);
            if (activeplayer == 0) {
                img.setImageResource(R.drawable.x);
                activeplayer = 1;
                TextView status= findViewById(R.id.status);
                status.setText("O's Turn - Tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                activeplayer = 0;
                TextView status= findViewById(R.id.status);
                status.setText("X's Turn - Tap to play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winPosition:winPositions){
            if(gameState[winPosition[0]]==gameState[winPosition[1]] &&
                    gameState[winPosition[1]]==gameState[winPosition[2]] &&
            gameState[winPosition[0]]!=2){
                String winnerStr;
                if(gameState[winPosition[0]]==0){
                    winnerStr="X has won";
                    gameReset();
                }
                else{
                    winnerStr= "O has won";
                    gameReset();
                }
                TextView status=findViewById(R.id.status);
                status.setText(winnerStr);
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}