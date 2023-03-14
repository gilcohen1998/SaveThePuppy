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

public class Spike_ShopActivity extends AppCompatActivity {

    // The same as shop activity

    ImageView ninja_sword_skin, sword_skin, steak_skin, bomb_skin, exit_to_main;
    RelativeLayout layout;
    SharedPreferences spikes, coins;
    SharedPreferences.Editor editor_spikes, editor_coins;
    Dialog dialog;
    ImageView icon_dialog, exit_dialog;
    TextView price_dialog, shop_coins, text_dialog;
    Button yes_button;
    int amount_coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spike_shop);

        editor_coins = getSharedPreferences("coins", MODE_PRIVATE).edit();
        editor_spikes = getSharedPreferences("spikes", MODE_PRIVATE).edit();

        spikes = getSharedPreferences("spikes", MODE_PRIVATE);
        coins = getSharedPreferences("coins", MODE_PRIVATE);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.shop_dialog);
        text_dialog = dialog.findViewById(R.id.text_dialog);
        icon_dialog = dialog.findViewById(R.id.icon_dialog);
        yes_button = dialog.findViewById(R.id.yes_button);
        exit_dialog = dialog.findViewById(R.id.exit_dialog);
        price_dialog = dialog.findViewById(R.id.price_dialog);


        layout = findViewById(R.id.shop_layout);
        shop_coins = findViewById(R.id.shop_coins);
        ninja_sword_skin = findViewById(R.id.ninja_sword_skin);
        sword_skin = findViewById(R.id.sword_skin);
        steak_skin = findViewById(R.id.steak_skin);
        bomb_skin = findViewById(R.id.bomb_skin);
        exit_to_main = findViewById(R.id.exitshop_button);

        amount_coins = coins.getInt("coins", 0);
        shop_coins.setText("" + amount_coins);

        layout.setVisibility(View.VISIBLE);

        Toast toast = new Toast(getApplicationContext());
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.new_toast));
        toast.setView(view);
        TextView toast_text = view.findViewById(R.id.text_toast);
        ImageView toast_icon = view.findViewById(R.id.icon_toast);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.START, 50, 50);

        // if 0 u dont have this skin
        // if 1 u have this skin but not equipped
        // if 2 u have this skin and equipped

        if (spikes.getInt("ninja_sword", 0) != 0)
            ninja_sword_skin.setVisibility(View.INVISIBLE);
        if (spikes.getInt("sword", 0) != 0)
            sword_skin.setVisibility(View.INVISIBLE);
        if (spikes.getInt("steak", 0) != 0)
            steak_skin.setVisibility(View.INVISIBLE);
        if (spikes.getInt("bomb", 0) != 0)
            bomb_skin.setVisibility(View.INVISIBLE);

        exit_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Spike_ShopActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        ninja_sword_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (spikes.getInt("ninja_sword", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.ninja_sword_icon));
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
                                editor_spikes.putInt("ninja_sword", 1);
                                editor_spikes.apply();
                                editor_coins.putInt("coins", amount_coins - 1000);
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));

                                toast_text.setText("Congratulations you bought the skin");
                                toast_icon.setImageResource(R.drawable.ninja_sword_icon);
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

        sword_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (spikes.getInt("sword", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.sword_icon));
                    text_dialog.setText("Earning by doing 4000 points in hard mode");
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

        steak_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (spikes.getInt("steak", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.steak_icon));
                    text_dialog.setText("Can only revived from chest");
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

        bomb_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (spikes.getInt("bomb", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.bomb_icon));
                    text_dialog.setText("Can only revived from chest");
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