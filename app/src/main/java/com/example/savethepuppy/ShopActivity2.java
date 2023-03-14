package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class ShopActivity2 extends AppCompatActivity {

    // Same here like shop activity

    ImageView next_button, monkey_skin, sheep_skin, cow_skin, panda_skin, lion_skin, owl_skin, dragon_skin, giraffe_skin, elephant_skin;
    RelativeLayout layout;
    LottieAnimationView owl_effect, dragon_effect, elephant_effect, giraffe_effect;
    SharedPreferences skins, coins;
    SharedPreferences.Editor editor_skins, editor_coins;
    Dialog dialog;
    ImageView icon_dialog, exit_dialog;
    TextView price_dialog, shop_coins, text_dialog;
    Button yes_button;
    int amount_coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop2);
        layout = findViewById(R.id.shop_layout2);

        owl_effect = findViewById(R.id.owl_effect);
        dragon_effect = findViewById(R.id.dragon_effect);
        elephant_effect = findViewById(R.id.elephant_effect);
        giraffe_effect = findViewById(R.id.giraffe_effect);
        next_button = findViewById(R.id.next_button2);
        shop_coins = findViewById(R.id.shop_coins2);
        monkey_skin = findViewById(R.id.monkey_skin);
        sheep_skin = findViewById(R.id.sheep_skin);
        cow_skin = findViewById(R.id.cow_skin);
        panda_skin = findViewById(R.id.panda_skin);
        lion_skin = findViewById(R.id.lion_skin);
        owl_skin = findViewById(R.id.owl_skin);
        dragon_skin = findViewById(R.id.dragon_skin);
        giraffe_skin = findViewById(R.id.giraffe_skin);
        elephant_skin = findViewById(R.id.elephant_skin);

        layout.setVisibility(View.VISIBLE);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.shop_dialog);
        text_dialog = dialog.findViewById(R.id.text_dialog);
        icon_dialog = dialog.findViewById(R.id.icon_dialog);
        yes_button = dialog.findViewById(R.id.yes_button);
        exit_dialog = dialog.findViewById(R.id.exit_dialog);
        price_dialog = dialog.findViewById(R.id.price_dialog);


        editor_coins = getSharedPreferences("coins", MODE_PRIVATE).edit();
        editor_skins = getSharedPreferences("skins", MODE_PRIVATE).edit();

        skins = getSharedPreferences("skins", MODE_PRIVATE);
        coins = getSharedPreferences("coins", MODE_PRIVATE);

        Toast toast = new Toast(getApplicationContext());
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) View view = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.new_toast));
        toast.setView(view);
        TextView toast_text = view.findViewById(R.id.text_toast);
        ImageView toast_icon = view.findViewById(R.id.icon_toast);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.START, 50, 50);

        amount_coins = coins.getInt("coins", 0);
        shop_coins.setText("" + amount_coins);

        if (skins.getInt("monkey", 0) != 0)
            monkey_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("sheep", 0) != 0)
            sheep_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("cow", 0) != 0)
            cow_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("panda", 0) != 0)
            panda_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("lion", 0) != 0)
            lion_skin.setVisibility(View.INVISIBLE);
        if (skins.getInt("owl", 0) != 0) {
            owl_effect.setVisibility(View.INVISIBLE);
            owl_skin.setVisibility(View.INVISIBLE);
        }
        if (skins.getInt("dragon", 0) != 0) {
            dragon_effect.setVisibility(View.INVISIBLE);
            dragon_skin.setVisibility(View.INVISIBLE);
        }
        if (skins.getInt("giraffe", 0) != 0) {
            giraffe_effect.setVisibility(View.INVISIBLE);
            giraffe_skin.setVisibility(View.INVISIBLE);
        }
        if (skins.getInt("elephant", 0) != 0) {
            elephant_effect.setVisibility(View.INVISIBLE);
            elephant_skin.setVisibility(View.INVISIBLE);
        }

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopActivity2.this, ShopActivity.class); // Creating new pass to shop activity
                startActivity(intent); // Moving to shop activity
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });
        monkey_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("monkey", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.monkey_puppy));
                    text_dialog.setText("Are u sure u want to buy this skin?");
                    yes_button.setText("YES");
                    price_dialog.setText("5000 coins");

                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (coins.getInt("coins", 0) >= 5000) { // need to change to a price
                                editor_skins.putInt("monkey", 1);
                                editor_skins.apply();
                                editor_coins.putInt("coins", amount_coins - 5000);
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));

                                toast_text.setText("Congratulations you bought the skin");
                                toast_icon.setImageResource(R.drawable.monkey_puppy);
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

        cow_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("cow", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.cow_puppy));
                    text_dialog.setText("Are u sure u want to buy this skin?");
                    yes_button.setText("YES");
                    price_dialog.setText("5000 coins");

                    exit_dialog.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    yes_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (coins.getInt("coins", 0) >= 5000) { // need to change to a price
                                editor_skins.putInt("cow", 1);
                                editor_skins.apply();
                                editor_coins.putInt("coins", amount_coins - 5000);
                                editor_coins.apply();
                                dialog.dismiss();
                                shop_coins.setText("" + coins.getInt("coins", 0));

                                toast_text.setText("Congratulations you bought the skin");
                                toast_icon.setImageResource(R.drawable.cow_puppy);
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

        sheep_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("sheep", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.sheep_puppy));
                    text_dialog.setText("Earning by doing 3000 points in hard mode");
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

        panda_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("panda", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.panda_puppy));
                    text_dialog.setText("Earning by doing 5000 points in hard mode");
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

        owl_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("owl", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.owl_puppy));
                    text_dialog.setText("Earning by doing 7000 points in master mode");
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

        lion_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("lion", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.lion_puppy));
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

        dragon_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("dragon", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.dragon_puppy));
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

        giraffe_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("giraffe", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.giraffe_puppy));
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

        elephant_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // When u click on this skin
                if (skins.getInt("elephant", 0) == 0) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    icon_dialog.setImageDrawable(getDrawable(R.drawable.elephant_puppy));
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