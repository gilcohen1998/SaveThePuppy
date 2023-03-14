package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShopActivity extends AppCompatActivity {

    ImageView next_button, bunny_skin, cat_skin, dog_skin, fox_skin, tiger_skin, pig_skin, chicken_skin, bear_skin, exit_to_main;
    RelativeLayout layout;
    SharedPreferences skins, coins; // Data
    SharedPreferences.Editor editor_skins, editor_coins;
    Dialog dialog;
    ImageView icon_dialog, exit_dialog;
    TextView price_dialog, shop_coins, text_dialog;
    Button yes_button;
    int amount_coins;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);


        editor_coins = getSharedPreferences("coins", MODE_PRIVATE).edit();
        editor_skins = getSharedPreferences("skins", MODE_PRIVATE).edit();

        skins = getSharedPreferences("skins", MODE_PRIVATE);
        coins = getSharedPreferences("coins", MODE_PRIVATE);

        // New dialog

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.shop_dialog);
        text_dialog = dialog.findViewById(R.id.text_dialog);
        icon_dialog = dialog.findViewById(R.id.icon_dialog);
        yes_button = dialog.findViewById(R.id.yes_button);
        exit_dialog = dialog.findViewById(R.id.exit_dialog);
        price_dialog = dialog.findViewById(R.id.price_dialog);

        // Find the ids of all the needed

        layout = findViewById(R.id.shop_layout);
        shop_coins = findViewById(R.id.shop_coins);
        next_button = findViewById(R.id.next_button);
        bunny_skin = findViewById(R.id.bunny_skin);
        cat_skin = findViewById(R.id.cat_skin);
        dog_skin = findViewById(R.id.dog_skin);
        fox_skin = findViewById(R.id.fox_skin);
        tiger_skin = findViewById(R.id.tigger_skin);
        pig_skin = findViewById(R.id.pig_skin);
        chicken_skin = findViewById(R.id.chicken_skin);
        bear_skin = findViewById(R.id.bear_skin);
        exit_to_main = findViewById(R.id.exitshop_button);

        amount_coins = coins.getInt("coins", 0);
        shop_coins.setText("" + amount_coins);

        layout.setVisibility(View.VISIBLE); // For reduce lags

        // New custom toast making

        Toast toast = new Toast(getApplicationContext());
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.new_toast));
        toast.setView(view);
        TextView toast_text = view.findViewById(R.id.text_toast);
        ImageView toast_icon = view.findViewById(R.id.icon_toast);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.START, 50, 50);

        // if 0 u don't have this skin
        // if 1 u have this skin but not equipped
        // if 2 u have this skin and equipped

        // Visibility of the skins

        if (skins.getInt("bunny", 0) != 0)
            bunny_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("dog", 0) != 0)
            dog_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("cat", 0) != 0)
            cat_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("fox", 0) != 0)
            fox_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("tiger", 0) != 0)
            tiger_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("pig", 0) != 0)
            pig_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("chicken", 0) != 0)
            chicken_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("bear", 0) != 0)
            bear_skin.setVisibility(View.INVISIBLE);


        exit_to_main.setOnClickListener(new View.OnClickListener() { // Exit the page
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShopActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() { // Clicking on the next button
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity.this, ShopActivity2.class); // Creating new pass to shop activity2
                startActivity(intent); // Moving to shop activity2
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

        bunny_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("bunny", 0) == 0) { // If u don't have the skin it will be visible
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.bunny_puppy));
                    text_dialog.setText("Are u sure u want to buy this skin?");
                    yes_button.setText("YES");
                    price_dialog.setText("50 coins");

                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() { // If u buy the skin
                        @Override
                        public void onClick(View v) {
                            if (coins.getInt("coins", 0) >= 50) { // If u have enough money to buy this skin
                                editor_skins.putInt("bunny", 1);
                                editor_skins.apply();
                                editor_coins.putInt("coins", amount_coins - 50); // Updating the user's money
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));

                                toast_text.setText("Congratulations you bought the skin"); // Custom toast
                                toast_icon.setImageResource(R.drawable.bunny_puppy);
                            } else {
                                dialog.dismiss();
                                toast_text.setText("You dont have enough money to buy this skin");
                            }
                            toast.show();
                        }
                    });

                    dialog.show();
                }
            }
        });

        // Same as the rest of the skins

        cat_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("cat", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.cat_puppy));
                    text_dialog.setText("Are u sure u want to buy this skin?");
                    yes_button.setText("YES");
                    price_dialog.setText("100 coins");

                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (coins.getInt("coins", 0) >= 100) { // need to change to a price
                                editor_skins.putInt("cat", 1);
                                editor_skins.apply();
                                editor_coins.putInt("coins", amount_coins - 100);
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));
                                toast_text.setText("Congratulations you bought the skin");
                                toast_icon.setImageResource(R.drawable.cat_puppy);
                            } else {
                                dialog.dismiss();
                                toast_text.setText("You dont have enough money to buy this skin");
                            }
                            toast.show();
                        }
                    });

                    dialog.show();
                }
            }
        });


        tiger_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("tiger", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.tiger_puppy));
                    text_dialog.setText("Are u sure u want to buy this skin?");
                    yes_button.setText("YES");
                    price_dialog.setText("500 coins");

                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (coins.getInt("coins", 0) >= 500) { // need to change to a price
                                editor_skins.putInt("tiger", 1);
                                editor_skins.apply();
                                editor_coins.putInt("coins", amount_coins - 500);
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));

                                toast_text.setText("Congratulations you bought the skin");
                                toast_icon.setImageResource(R.drawable.tiger_puppy);
                            } else {
                                dialog.dismiss();
                                toast_text.setText("You dont have enough money to buy this skin");
                            }
                            toast.show();
                        }
                    });

                    dialog.show();
                }
            }
        });

        bear_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("bear", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.bear_puppy));
                    text_dialog.setText("Are u sure u want to buy this skin?");
                    yes_button.setText("YES");
                    price_dialog.setText("1000 coins");

                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (coins.getInt("coins", 0) >= 1000) { // need to change to a price
                                editor_skins.putInt("bear", 1);
                                editor_skins.apply();
                                editor_coins.putInt("coins", amount_coins - 1000);
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));

                                toast_text.setText("Congratulations you bought the skin");
                                toast_icon.setImageResource(R.drawable.bear_puppy);
                            } else {
                                dialog.dismiss();
                                toast_text.setText("You dont have enough money to buy this skin");
                            }
                            toast.show();
                        }
                    });

                    dialog.show();
                }
            }
        });

        dog_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("dog", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.dog_puppy));
                    text_dialog.setText("Earning by doing 3000 points in esay mode");
                    price_dialog.setText("");
                    yes_button.setText("OK");
                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        fox_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("fox", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.fox_puppy));
                    text_dialog.setText("Earning by doing 5000 points in esay mode");
                    price_dialog.setText("");
                    yes_button.setText("OK");
                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        pig_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("pig", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.pig_puppy));
                    text_dialog.setText("Earning by doing 3000 points in medium mode");
                    price_dialog.setText("");
                    yes_button.setText("OK");
                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

        chicken_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("chicken", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.chicken_puppy));
                    text_dialog.setText("Earning by doing 5000 points in medium mode");
                    price_dialog.setText("");
                    yes_button.setText("OK");
                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }
            }
        });

    }
}