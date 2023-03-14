package com.example.savethepuppy;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosions {
    static Bitmap explosion[]; // Building an array of bitmaps (still not ready because we didn't use the constructor yet)
    static SharedPreferences what_spike; // Kind of spike data
    int explosionFrame = 0; // The anim for the explosions
    int explosionX, explosionY; // The cords of the explosions

    public Explosions(Context context) { // Constructor
        what_spike = context.getSharedPreferences("spikes", MODE_PRIVATE); // Invoke the kind of spike data

        if (what_spike.getInt("ninja_sword", 0) == 2) { // If the spike is ninja sword spike and if its equipped
            explosion = new Bitmap[9]; // Building the explosions bitmap constructor and putting all of the image frame inside it
            explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp0);
            explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp1);
            explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp2);
            explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp3);
            explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp4);
            explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp5);
            explosion[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp6);
            explosion[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp7);
            explosion[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword_exp8);
        } else if (what_spike.getInt("sword", 0) == 2) { // If the spike is ninja sword and if its equipped
            explosion = new Bitmap[6];
            explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_exp0);
            explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_exp1);
            explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_exp2);
            explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_exp3);
            explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_exp4);
            explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword_exp5);
        } else if (what_spike.getInt("steak", 0) == 2) { // If the spike is ninja steak and if its equipped
            explosion = new Bitmap[7];
            explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp0);
            explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp1);
            explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp2);
            explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp3);
            explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp4);
            explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp5);
            explosion[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak_exp6);
        } else if (what_spike.getInt("bomb", 0) == 2) { // If the spike is bomb spike and if its equipped
            explosion = new Bitmap[6];
            explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_exp0);
            explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_exp1);
            explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_exp2);
            explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_exp3);
            explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_exp4);
            explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_exp5);
        } else { // If the spike is the default spike and if its equipped
            explosion = new Bitmap[9];
            explosion[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp0);
            explosion[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp1);
            explosion[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp2);
            explosion[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp3);
            explosion[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp4);
            explosion[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp5);
            explosion[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp6);
            explosion[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp7);
            explosion[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_exp8);
        }
    }

    public Bitmap getExplosion(int explosionFrame) { // Getting the current image frame of the explosions
        return explosion[explosionFrame];
    }
}


