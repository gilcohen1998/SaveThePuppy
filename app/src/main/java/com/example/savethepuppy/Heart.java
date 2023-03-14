package com.example.savethepuppy;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

public class Heart {
    private Bitmap bitmap; // New bit map
    private int x, y; // The heart cords
    private int velocity; // The heart speed

    public Heart(Bitmap bitmap, int x, int y) { // The constructor
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        Random random = new Random(System.currentTimeMillis());
        this.velocity = random.nextInt(10) + 10;
    }

    public int getX() {
        return x;
    } // Getting method for the x cord

    public int getY() {
        return y;
    }  // Getting method for the y cord

    public int getVelocity() {
        return velocity;
    }  // Getting method for the speed


    public void update() {
        y += velocity;
    } // Update the y cord according to the speed

    public int GetH() { // Getting method for the height size of the heart
        return bitmap.getHeight();
    }

    public int GetW() { // Getting method for the width size of the heart
        return bitmap.getWidth();
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, x, y, null);
    }
}