package com.example.savethepuppy;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// The Gameplay class

public class GamePlay extends View {
    Bitmap background, ground, animal, heart_bar; // New pixel maps
    Rect rectBackground, rectGround; // Create a new rectangle
    Context context;
    Handler handler;
    SharedPreferences what_mode, coins, keys, what_spike, skins, achievements;// The mode type (int)
    SharedPreferences.Editor editor_coins, editor_keys, editor_achievements, editor_skins, editor_spikes; // All the game data (editing)
    final long UPDATE_MILLIS = 30; // FPS
    Runnable runnable;
    Paint textPaint = new Paint(); // Create new paint for the text
    float TEXT_SIZE = 90; // Size of the text
    int points = 0; // Points var
    int life = 3; // Life var
    int max_life = 3; // Max life var
    int drop_heart = 0; // The rate of the hearts perk
    int drop_coin = 0; // The rate of the coins perk
    int drop_key = 0; // The rate of the keys perk
    int AmountOfSpikes = 0; // How many spikes will be var
    int AmountOfSpeed = 0; // The speed of the spike var
    static int dWidth, dHeight; // Size of the all screen (the map)
    Random random;
    float animalX, animalY; // The player cords
    float oldX;
    float oldAnimalX;

    // Building the arrays of all the dropping

    private final List<Heart> Hearts = new ArrayList<>(); // Array of hearts
    private final List<Key> Keys = new ArrayList<>(); // Array of keys
    public final ArrayList<Spikes> spikes = new ArrayList<>();  // Array of spikes
    public final ArrayList<Explosions> explosions = new ArrayList<>(); // Array of explosions
    public final ArrayList<Coin> Coins = new ArrayList<>(); // Array of coins
    public final List<Coin> coinsToRemove = new ArrayList<>();
    public final List<Heart> heartsToRemove = new ArrayList<>();
    public final List<Key> keysToRemove = new ArrayList<>();
    public final List<Explosions> expToRemove = new ArrayList<>();
    View view;

    Toast toast;


    public TextView toast_text;
    public ImageView toast_icon;

    public GamePlay(Context context) { // Setting the view
        super(context);
        this.context = context;

        // Setting all the data

        editor_coins = context.getSharedPreferences("coins", MODE_PRIVATE).edit();
        editor_keys = context.getSharedPreferences("keys", MODE_PRIVATE).edit();
        editor_achievements = context.getSharedPreferences("achievements", MODE_PRIVATE).edit();
        editor_spikes = context.getSharedPreferences("spikes", MODE_PRIVATE).edit();
        editor_skins = context.getSharedPreferences("skins", MODE_PRIVATE).edit();
        what_mode = context.getSharedPreferences("mode", MODE_PRIVATE);
        coins = context.getSharedPreferences("coins", MODE_PRIVATE);
        skins = context.getSharedPreferences("skins", MODE_PRIVATE);
        keys = context.getSharedPreferences("keys", MODE_PRIVATE);
        what_spike = context.getSharedPreferences("spikes", MODE_PRIVATE);
        achievements = context.getSharedPreferences("achievements", MODE_PRIVATE);

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.new_toast));

        // Setting the custom toast

        toast = new Toast(context);
        toast.setView(view);
        toast_text = view.findViewById(R.id.text_toast);
        toast_icon = view.findViewById(R.id.icon_toast);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setGravity(Gravity.TOP | Gravity.START, 50, 50);

        // Checking what mode the player choose and building the rates,map and other in the mode

        int Mode = what_mode.getInt("mode", 0);
        if (Mode == 0) {
            drop_coin = 200;
            drop_heart = 1000;
            drop_key = 4000;
            life = 5;
            max_life = 5;
            AmountOfSpikes = 3;
            AmountOfSpeed = 5;
            heart_bar = BitmapFactory.decodeResource(getResources(), R.drawable.five_hearts);
            background = BitmapFactory.decodeResource(getResources(), R.drawable.easy_mode_background);
        } else if (Mode == 1) {
            drop_coin = 150;
            drop_heart = 800;
            drop_key = 3700;
            life = 3;
            max_life = 3;
            AmountOfSpikes = 4;
            AmountOfSpeed = 15;
            heart_bar = BitmapFactory.decodeResource(getResources(), R.drawable.three_hearts);
            background = BitmapFactory.decodeResource(getResources(), R.drawable.medium_mode_background);
        } else if (Mode == 2) {
            drop_coin = 100;
            drop_heart = 600;
            drop_key = 3500;
            life = 2;
            max_life = 2;
            AmountOfSpikes = 5;
            AmountOfSpeed = 25;
            heart_bar = BitmapFactory.decodeResource(getResources(), R.drawable.two_hearts);
            background = BitmapFactory.decodeResource(getResources(), R.drawable.hard_mode_background);
        } else if (Mode == 3) {
            drop_coin = 50;
            drop_heart = 400;
            drop_key = 3300;
            life = 2;
            max_life = 2;
            AmountOfSpikes = 5;
            AmountOfSpeed = 35;
            heart_bar = BitmapFactory.decodeResource(getResources(), R.drawable.two_hearts);
            background = BitmapFactory.decodeResource(getResources(), R.drawable.master_mode_background);
        }

        ground = BitmapFactory.decodeResource(getResources(), R.drawable.empty_ground);
        What_skin(); // This helper func is checking what skin the player choose for playing the game


        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point(); // A var that hold x,y cords
        display.getSize(size); // Get the current x,y cords
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0, 0, dWidth, dHeight); // Building the size of the map
        rectGround = new Rect(0, dHeight - ground.getHeight(), dWidth, dHeight); // Building the size of the ground
        handler = new Handler();
        runnable = this::invalidate;
        textPaint.setColor(getResources().getColor(android.R.color.darker_gray)); // Printing the score text in the screen
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context, R.font.press_start_2p));
        random = new Random();
        animalX = dWidth / 2 - animal.getWidth() / 2; // The x size of the player
        animalY = dHeight - ground.getHeight() - animal.getHeight(); // The y size of the player


        for (int i = 0; i < AmountOfSpikes; i++) { // Loop that add spikes to the spike array (how many spikes will fall every reset)
            Spikes spike = new Spikes(context);
            spikes.add(spike);
        }

    }


    private void What_skin() {
        if (skins.getInt("bunny", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.bunny_puppy);
        else if (skins.getInt("dog", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.dog_puppy);
        else if (skins.getInt("cat", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.cat_puppy);
        else if (skins.getInt("fox", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.fox_puppy);
        else if (skins.getInt("tiger", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.tiger_puppy);
        else if (skins.getInt("pig", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.pig_puppy);
        else if (skins.getInt("chicken", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.chicken_puppy);
        else if (skins.getInt("bear", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.bear_puppy);
        else if (skins.getInt("monkey", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.monkey_puppy);
        else if (skins.getInt("sheep", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.sheep_puppy);
        else if (skins.getInt("cow", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.cow_puppy);
        else if (skins.getInt("panda", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.panda_puppy);
        else if (skins.getInt("lion", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.lion_puppy);
        else if (skins.getInt("owl", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.owl_puppy);
        else if (skins.getInt("dragon", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.dragon_puppy);
        else if (skins.getInt("giraffe", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.giraffe_puppy);
        else if (skins.getInt("elephant", 0) == 2)
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.elephant_puppy);
        else
            animal = BitmapFactory.decodeResource(getResources(), R.drawable.default_puppy);
    }

    @Override
    protected void onDraw(Canvas canvas) { // The method that print everything in the game
        super.onDraw(canvas);

        // The achievements data (checking evey tick if the player reached this score for the first time)
        // Every mode have a different achievements
        // For every achievement the player get we will display a custom toast that tells the user about it

        int Mode = what_mode.getInt("mode", 0);

        if (Mode == 0) {
            if (!achievements.getBoolean("medium_mode", false) && points == 4000) {
                toast_text.setText("Congratulations you opened medium mode!");
                toast_icon.setVisibility(GONE);
                toast.show();
                editor_achievements.putBoolean("medium_mode", true);
                editor_achievements.apply();
            }
            if (skins.getInt("dog", 0) == 0 && points == 3000) {
                toast_text.setText("Congratulations you unlocked dog skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.dog_puppy);
                toast.show();
                editor_skins.putInt("dog", 1);
                editor_skins.apply();
            }
            if (skins.getInt("fox", 0) == 0 && points == 5000) {
                toast_text.setText("Congratulations you unlocked fox skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.fox_puppy);
                toast.show();
                editor_skins.putInt("fox", 1);
                editor_skins.apply();
            }
        } else if (Mode == 1) {
            if (!achievements.getBoolean("hard_mode", false) && points == 4000) {
                toast_text.setText("Congratulations you opened hard mode!");
                toast_icon.setVisibility(GONE);
                toast.show();
                editor_achievements.putBoolean("hard_mode", true);
                editor_achievements.apply();
            }
            if (skins.getInt("pig", 0) == 0 && points == 3000) {
                toast_text.setText("Congratulations you unlocked pig skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.pig_puppy);
                toast.show();
                editor_skins.putInt("pig", 1);
                editor_skins.apply();
            }
            if (skins.getInt("chicken", 0) == 0 && points == 5000) {
                toast_text.setText("Congratulations you unlocked chicken skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.chicken_puppy);
                toast.show();
                editor_skins.putInt("chicken", 1);
                editor_skins.apply();
            }
        } else if (Mode == 2) {
            if (!achievements.getBoolean("master_mode", false) && points == 6000) {
                toast_text.setText("Congratulations you opened master mode!");
                toast_icon.setVisibility(GONE);
                toast.show();
                editor_achievements.putBoolean("master_mode", true);
                editor_achievements.apply();
            }
            if (skins.getInt("sheep", 0) == 0 && points == 3000) {
                toast_text.setText("Congratulations you unlocked sheep skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.sheep_puppy);
                toast.show();
                editor_skins.putInt("sheep", 1);
                editor_skins.apply();
            }
            if (skins.getInt("panda", 0) == 0 && points == 5000) {
                toast_text.setText("Congratulations you unlocked panda skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.panda_puppy);
                toast.show();
                editor_skins.putInt("panda", 1);
                editor_skins.apply();
            }
            if (what_spike.getInt("sword", 0) == 0 && points == 4000) {
                toast_text.setText("Congratulations you unlocked sword skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.sword_icon);
                toast.show();
                editor_spikes.putInt("sword", 1);
                editor_spikes.apply();
            }
        } else if (Mode == 3) {
            if (skins.getInt("owl", 0) == 0 && points == 7000) {
                toast_text.setText("Congratulations you unlocked owl skin!");
                toast_icon.setVisibility(VISIBLE);
                toast_icon.setImageResource(R.drawable.owl_puppy);
                toast.show();
                editor_skins.putInt("owl", 1);
                editor_skins.apply();
            }
        }

        // Draw the map,ground,player and heart bar (for every tick)

        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(ground, null, rectGround, null);
        canvas.drawBitmap(animal, animalX, animalY, null);
        canvas.drawBitmap(heart_bar, dWidth - heart_bar.getWidth(), 0, null);


        // The system that drop coins

        if (random.nextInt(drop_coin) == 0) { // Making new coins every time we rolling 0 from the int "drop_coin"
            Coin Coin1 = new Coin(context);
            Coins.add(Coin1);
        }

        // Looping the coins array for making the anim and response when the player get the coin or when the coin hit the ground

        for (int i = 0; i < Coins.size(); i++) {

            canvas.drawBitmap(Coins.get(i).getCoin(Coins.get(i).coinFrame), Coins.get(i).coinX, Coins.get(i).coinY, null);
            Coins.get(i).coinFrame++;
            if (Coins.get(i).coinFrame > 5) { // my change
                Coins.get(i).coinFrame = 0;
            }
            Coins.get(i).coinY += Coins.get(i).coinVelocity + AmountOfSpeed;

            if (Coins.get(i).coinX + Coins.get(i).getCoinWidth() >= animalX && Coins.get(i).coinX <= animalX + animal.getWidth()
                    && Coins.get(i).coinY + Coins.get(i).getCoinWidth() >= animalY && Coins.get(i).coinY + Coins.get(i).getCoinWidth() <= animalY + animal.getHeight()) {
                editor_coins.putInt("coins", coins.getInt("coins", 0) + 1);
                editor_coins.apply();
                coinsToRemove.add(Coins.get(i));
            } else if (Coins.get(i).coinY + Coins.get(i).getCoinHeight() >= dHeight - ground.getHeight()) {
                coinsToRemove.add(Coins.get(i));
            }
        }
        Coins.removeAll(coinsToRemove); // Saving data (more useful against lags)


        // Dropping new heart every time the random var rolling 0 (every tick)

        if (random.nextInt(drop_heart) == 0) {
            NewHeart();
        }

        // Dropping new key every time the random var rolling 0 (every tick)

        if (random.nextInt(drop_key) == 0) {
            NewKey();
        }

        // The same as the coins system

        for (int i = 0; i < Hearts.size(); i++) {
            Hearts.get(i).update();
            Hearts.get(i).draw(canvas);
            if (Hearts.get(i).getX() + Hearts.get(i).GetW() >= animalX && Hearts.get(i).getX() <= animalX + animal.getWidth()
                    && Hearts.get(i).getY() + Hearts.get(i).GetW() >= animalY && Hearts.get(i).getY() + Hearts.get(i).GetW() <= animalY + animal.getHeight()) {
                if (life < max_life) {
                    life++;
                }
                heartsToRemove.add(Hearts.get(i));
            } else if (Hearts.get(i).getY() + Hearts.get(i).GetH() >= dHeight - ground.getHeight()) {
                heartsToRemove.add(Hearts.get(i));
            }
        }
        Hearts.removeAll(heartsToRemove);

        for (int i = 0; i < Keys.size(); i++) {
            Keys.get(i).update();
            Keys.get(i).draw(canvas);
            if (Keys.get(i).getX() + Keys.get(i).GetW() >= animalX && Keys.get(i).getX() <= animalX + animal.getWidth()
                    && Keys.get(i).getY() + Keys.get(i).GetW() >= animalY && Keys.get(i).getY() + Keys.get(i).GetW() <= animalY + animal.getHeight()) {
                editor_keys.putInt("keys", keys.getInt("keys", 0) + 1);
                editor_keys.apply();
                keysToRemove.add(Keys.get(i));
            } else if (Keys.get(i).getY() + Keys.get(i).GetH() >= dHeight - ground.getHeight()) {
                keysToRemove.add(Keys.get(i));
            }
        }
        Keys.removeAll(keysToRemove);


        // The spike system

        for (int i = 0; i < spikes.size(); i++) {

            canvas.drawBitmap(spikes.get(i).getSpike(spikes.get(i).spikeFrame), spikes.get(i).spikeX, spikes.get(i).spikeY, null);
            spikes.get(i).spikeFrame++;
            if (spikes.get(i).spikeFrame >= 8) {
                spikes.get(i).spikeFrame = 0;
            }
            spikes.get(i).spikeY += spikes.get(i).spikeVelocity + AmountOfSpeed;
            if (spikes.get(i).spikeY + spikes.get(i).getSpikeHeight() >= dHeight - ground.getHeight()) {
                NewExplosions(i);
                points += 10; // Every time a spike hit the ground
                spikes.get(i).resetPosition(); // Reset his position and dropping him again from the sky
            }

            if (spikes.get(i).spikeX + spikes.get(i).getSpikeWidth() >= animalX && spikes.get(i).spikeX <= animalX + animal.getWidth()
                    && spikes.get(i).spikeY + spikes.get(i).getSpikeWidth() >= animalY && spikes.get(i).spikeY + spikes.get(i).getSpikeWidth() <= animalY + animal.getHeight()) {
                life--;
                spikes.get(i).resetPosition();
                if (life == 0) {
                    FinishGame(); // If the player have 0 life he lose
                }

            }
        }

        // The explosions system (happening only when the spike hits the ground)
        // And checking what spike the player choose to display different explosions animation

        for (int i = 0; i < explosions.size(); i++) {
            canvas.drawBitmap(explosions.get(i).getExplosion(explosions.get(i).explosionFrame), explosions.get(i).explosionX,
                    explosions.get(i).explosionY, null);
            explosions.get(i).explosionFrame++;

            if (what_spike.getInt("ninja_sword", 0) == 2) {
                if (explosions.get(i).explosionFrame >= 9) {
                    expToRemove.add(explosions.get(i));
                }
            } else if (what_spike.getInt("sword", 0) == 2) {
                if (explosions.get(i).explosionFrame >= 6) {
                    expToRemove.add(explosions.get(i));
                }
            } else if (what_spike.getInt("steak", 0) == 2) {
                if (explosions.get(i).explosionFrame >= 7) {
                    expToRemove.add(explosions.get(i));
                }
            } else if (what_spike.getInt("bomb", 0) == 2) {
                if (explosions.get(i).explosionFrame >= 6) {
                    expToRemove.add(explosions.get(i));
                }
            } else {
                if (explosions.get(i).explosionFrame >= 9) {
                    expToRemove.add(explosions.get(i));
                }
            }
        }
        explosions.removeAll(expToRemove);

        // Change the life bar to the current life the player have

        if (life == 5) {
            SetHeart_bar(R.drawable.five_hearts);
        }
        if (life == 4) {
            SetHeart_bar(R.drawable.four_hearts);
        }
        if (life == 3) {
            SetHeart_bar(R.drawable.three_hearts);
        }
        if (life == 2) {
            SetHeart_bar(R.drawable.two_hearts);
        } else if (life == 1) {
            SetHeart_bar(R.drawable.one_heart);
        }

        // Change the color of the score text bar

        if (points == 500)
            textPaint.setColor(getResources().getColor(android.R.color.holo_orange_dark));

        if (points == 1000)
            textPaint.setColor(getResources().getColor(R.color.teal_200));

        canvas.drawText("" + points, 20, TEXT_SIZE, textPaint);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    private void NewKey() { // Making new key
        Bitmap key = BitmapFactory.decodeResource(getResources(), R.drawable.key);
        int x = random.nextInt(dWidth - key.getWidth());
        int y = -200 + random.nextInt(600) * -1;
        Keys.add(new Key(key, x, y));
    }

    public void SetHeart_bar(int resourceId) { // Helping method for changing the heart_bar image
        heart_bar = BitmapFactory.decodeResource(getContext().getResources(), resourceId);
    }

    public void NewHeart() { // Making new heart
        Bitmap heart = BitmapFactory.decodeResource(getResources(), R.drawable.heart_drop);
        int x = random.nextInt(dWidth - heart.getWidth());
        int y = -200 + random.nextInt(600) * -1;
        Hearts.add(new Heart(heart, x, y));
    }


    public void FinishGame() { // Finishing the game
        Intent intent = new Intent(context, GameOver.class);
        intent.putExtra("points", points);
        context.startActivity(intent);
        ((Activity) context).finish();
    }

    public void NewExplosions(int i) { // Making new explosion
        Explosions explosion = new Explosions(context);
        explosion.explosionX = spikes.get(i).spikeX;
        explosion.explosionY = spikes.get(i).spikeY;
        explosions.add(explosion);
    }

    // The player movement system

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (touchY >= animalY) {
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN) {
                oldX = event.getX();
                oldAnimalX = animalX;
            }
            if (action == MotionEvent.ACTION_MOVE) {
                float shift = oldX - touchX;
                float newAnimalX = oldAnimalX - shift;
                if (newAnimalX <= 0)
                    animalX = 0;
                else if (newAnimalX >= dWidth - animal.getWidth()) {
                    animalX = dWidth - animal.getWidth();
                } else
                    animalX = newAnimalX;
            }
        }
        return true;
    }
}
