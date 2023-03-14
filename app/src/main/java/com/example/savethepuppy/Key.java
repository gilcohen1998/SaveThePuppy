package com.example.savethepuppy;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Random;

public class Key {
    private Bitmap bitmap; // New bit map
    private int x, y; // The key cords
    private int velocity; // The key speed
    static Random random; // Random var

    public Key(Bitmap bitmap, int x, int y) { // The constructor
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        random = new Random(System.currentTimeMillis());
        this.velocity = random.nextInt(10) + 40;
    }

    public int getX() {
        return x;
    } // Getting method for the x cord

    public int getY() {
        return y;
    } // Getting method for the y cord

    public int getVelocity() {
        return velocity;
    } // Getting method for the speed


    public void update() {
        y += velocity;
    } // Update the y cord according to the speed

    public int GetH() { // Getting method for the height size of the key
        return bitmap.getHeight();
    }

    public int GetW() { // Getting method for the width size of the key
        return bitmap.getWidth();
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }
}