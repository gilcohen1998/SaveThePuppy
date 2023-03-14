package com.example.savethepuppy;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Spikes {
    static SharedPreferences what_spike;
    static Bitmap spike[] = new Bitmap[8]; // Building the constructor of the spikes (array of bitmaps)
    int spikeFrame = 0;
    int spikeX, spikeY, spikeVelocity; // The location of the spike in the map
    static Random random; // Create a random number

    // Setting the anim for the spikes

    public Spikes(Context context) { // Create the anim for the spikes
        what_spike = context.getSharedPreferences("spikes", MODE_PRIVATE);

        if (what_spike.getInt("ninja_sword", 0) == 2) {
            spike[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword0);
            spike[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword1);
            spike[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword2);
            spike[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword3);
            spike[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword4);
            spike[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword5);
            spike[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword6);
            spike[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.ninja_sword7);
        } else if (what_spike.getInt("sword", 0) == 2) {
            spike[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword0);
            spike[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword1);
            spike[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword2);
            spike[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword3);
            spike[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword4);
            spike[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword5);
            spike[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword6);
            spike[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.sword7);
        } else if (what_spike.getInt("steak", 0) == 2) {
            spike[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak0);
            spike[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak1);
            spike[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak2);
            spike[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak3);
            spike[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak4);
            spike[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak5);
            spike[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak6);
            spike[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.steak7);
        } else if (what_spike.getInt("bomb", 0) == 2) {
            spike[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb0);
            spike[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb1);
            spike[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb2);
            spike[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb3);
            spike[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb4);
            spike[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb5);
            spike[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb6);
            spike[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb7);
        } else {
            spike[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja0);
            spike[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja1);
            spike[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja2);
            spike[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja3);
            spike[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja4);
            spike[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja5);
            spike[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja6);
            spike[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.shuriken_ninja7);
        }


        random = new Random(System.currentTimeMillis()); // Create a constructor for the random class
        resetPosition(); // A func that generate new x,y,speed for the spike
    }

    // Getting method for using the anim later

    public Bitmap getSpike(int spikeFrame) {
        return spike[spikeFrame];

    }

    // Getting the cords of the spikes

    public int getSpikeWidth() {
        return spike[0].getWidth();
    }

    public int getSpikeHeight() {
        return spike[0].getHeight();
    }

    public void resetPosition() {
        spikeX = random.nextInt(GamePlay.dWidth - getSpikeWidth()); // Generate a new x place (num between the x size of the map - the x size of the spike)
        spikeY = -200 + random.nextInt(600) * -1; // Generate y place for the spike
        spikeVelocity = 15 + random.nextInt(25); // Generate the speed of the spike
    }

}
