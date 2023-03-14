package com.example.savethepuppy;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Coin {
    static Bitmap coin[] = new Bitmap[6]; // Building with the constructor of the bitmap (array of bitmaps)
    int coinFrame = 0; // The coin frame position
    int coinX, coinY, coinVelocity; // The location of the spike in the map
    Random random = new Random(System.currentTimeMillis()); // Create a random number

    // Setting the anim for the coins

    public Coin(Context context) { // Create the anim for the coins (constructor)
        coin[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins0);
        coin[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins1);
        coin[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins2);
        coin[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins3);
        coin[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins4);
        coin[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.coins5);
        resetPosition();
    }

    // Getting frame method for using the anim later (in the draw method)

    public Bitmap getCoin(int frame) {
        return coin[frame];

    }

    // Getting the size of the coins

    public int getCoinWidth() {
        return coin[0].getWidth();
    }

    public int getCoinHeight() {
        return coin[0].getHeight();
    }

    public void resetPosition() { // Giving the coins a random position and speed every time a coin spawn
        coinX = random.nextInt(GamePlay.dWidth - getCoinWidth()); // Generate a new x place (num between the x size of the map - the x size of the coin)
        coinY = -200 + random.nextInt(600) * -1; // Generate y place for the coin
        coinVelocity = random.nextInt(10) + 10; // Generate the speed of the coin
    }
}