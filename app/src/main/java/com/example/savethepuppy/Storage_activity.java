package com.example.savethepuppy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Storage_activity extends AppCompatActivity {

    ImageView exit, bunny_skin, cat_skin, dog_skin, fox_skin, tiger_skin, pig_skin, chicken_skin, bear_skin, default_skin,
            monkey_skin, sheep_skin, cow_skin, panda_skin, lion_skin, owl_skin, dragon_skin, giraffe_skin, elephant_skin, inuse_puppy, inuse_spike,
            ninja_sword_spike, sword_spike, bomb_spike, default_spike, steak_spike;
    SharedPreferences skins, spikes;
    SharedPreferences.Editor editor_skins, editor_spikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        skins = getSharedPreferences("skins", MODE_PRIVATE);
        spikes = getSharedPreferences("spikes", MODE_PRIVATE);
        editor_skins = getSharedPreferences("skins", MODE_PRIVATE).edit();
        editor_spikes = getSharedPreferences("spikes", MODE_PRIVATE).edit();

        // Finding all the ids of all the needed

        exit = findViewById(R.id.exit_storage);
        default_skin = findViewById(R.id.default_storage);
        bunny_skin = findViewById(R.id.bunny_storage);
        cat_skin = findViewById(R.id.cat_storage);
        dog_skin = findViewById(R.id.dog_storage);
        fox_skin = findViewById(R.id.fox_storage);
        tiger_skin = findViewById(R.id.tiger_storage);
        pig_skin = findViewById(R.id.pig_storage);
        chicken_skin = findViewById(R.id.chicken_storage);
        bear_skin = findViewById(R.id.bear_storage);
        monkey_skin = findViewById(R.id.monkey_storage);
        sheep_skin = findViewById(R.id.sheep_storage);
        cow_skin = findViewById(R.id.cow_storage);
        panda_skin = findViewById(R.id.panda_storage);
        lion_skin = findViewById(R.id.lion_storage);
        owl_skin = findViewById(R.id.owl_storage);
        dragon_skin = findViewById(R.id.dragon_storage);
        giraffe_skin = findViewById(R.id.giraffe_storage);
        elephant_skin = findViewById(R.id.elephant_storage);
        default_spike = findViewById(R.id.default_spike_storage);
        ninja_sword_spike = findViewById(R.id.ninja_sword_storage);
        sword_spike = findViewById(R.id.sword_storage);
        steak_spike = findViewById(R.id.steak_storage);
        bomb_spike = findViewById(R.id.bomb_storage);
        inuse_puppy = findViewById(R.id.inuse_puppy);
        inuse_spike = findViewById(R.id.inuse_spike);

        what_in_use(); // Helper function that checking what skin the player choose
        what_you_have(); // Helper function that checking what skin the player have

        // The choosing skins system

        default_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disable_all_skins();
                inuse_puppy.setImageResource(R.drawable.default_puppy);
            }
        });
        bunny_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("bunny", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("bunny", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.bunny_puppy);
                }
            }
        });
        dog_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("dog", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("dog", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.dog_puppy);
                }
            }
        });
        cat_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("cat", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("cat", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.cat_puppy);
                }
            }
        });
        fox_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("fox", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("fox", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.fox_puppy);
                }
            }
        });
        tiger_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("tiger", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("tiger", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.tiger_puppy);
                }
            }
        });
        pig_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("pig", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("pig", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.pig_puppy);
                }
            }
        });
        chicken_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("chicken", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("chicken", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.chicken_puppy);
                }
            }
        });
        bear_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("bear", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("bear", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.bear_puppy);
                }
            }
        });
        monkey_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("monkey", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("monkey", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.monkey_puppy);
                }
            }
        });
        sheep_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("sheep", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("sheep", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.sheep_puppy);
                }
            }
        });
        cow_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("cow", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("cow", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.cow_puppy);
                }
            }
        });
        panda_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("panda", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("panda", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.panda_puppy);
                }
            }
        });
        lion_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("lion", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("lion", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.lion_puppy);
                }
            }
        });
        owl_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("owl", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("owl", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.owl_puppy);
                }
            }
        });
        dragon_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("dragon", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("dragon", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.dragon_puppy);
                }
            }
        });
        giraffe_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("giraffe", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("giraffe", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.giraffe_puppy);
                }
            }
        });
        elephant_skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skins.getInt("elephant", 0) == 1) {
                    disable_all_skins();
                    editor_skins.putInt("elephant", 2);
                    editor_skins.apply();
                    inuse_puppy.setImageResource(R.drawable.elephant_puppy);
                }
            }
        });

        default_spike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disable_all_spikes();
                inuse_spike.setImageResource(R.drawable.shuriken_ninja0);
            }
        });
        ninja_sword_spike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spikes.getInt("ninja_sword", 0) == 1) {
                    disable_all_spikes();
                    editor_spikes.putInt("ninja_sword", 2);
                    editor_spikes.apply();
                    inuse_spike.setImageResource(R.drawable.ninja_sword_icon);
                }
            }
        });
        sword_spike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spikes.getInt("sword", 0) == 1) {
                    disable_all_spikes();
                    editor_spikes.putInt("sword", 2);
                    editor_spikes.apply();
                    inuse_spike.setImageResource(R.drawable.sword_icon);
                }
            }
        });
        steak_spike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spikes.getInt("steak", 0) == 1) {
                    disable_all_spikes();
                    editor_spikes.putInt("steak", 2);
                    editor_spikes.apply();
                    inuse_spike.setImageResource(R.drawable.steak_icon);
                }
            }
        });
        bomb_spike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spikes.getInt("bomb", 0) == 1) {
                    disable_all_spikes();
                    editor_spikes.putInt("bomb", 2);
                    editor_spikes.apply();
                    inuse_spike.setImageResource(R.drawable.bomb_icon);
                }
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storage_activity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish(); // Finishing the current activity
            }
        });

    }

    // Helper function that unequip all the skins that in use (only one of tham can be in use)

    private void disable_all_skins() {
        if (skins.getInt("bunny", 0) == 2) {
            editor_skins.putInt("bunny", 1);
            editor_skins.apply();
        } else if (skins.getInt("dog", 0) == 2) {
            editor_skins.putInt("dog", 1);
            editor_skins.apply();
        } else if (skins.getInt("cat", 0) == 2) {
            editor_skins.putInt("cat", 1);
            editor_skins.apply();
        } else if (skins.getInt("fox", 0) == 2) {
            editor_skins.putInt("fox", 1);
            editor_skins.apply();
        } else if (skins.getInt("tiger", 0) == 2) {
            editor_skins.putInt("tiger", 1);
            editor_skins.apply();
        } else if (skins.getInt("pig", 0) == 2) {
            editor_skins.putInt("pig", 1);
            editor_skins.apply();
        } else if (skins.getInt("chicken", 0) == 2) {
            editor_skins.putInt("chicken", 1);
            editor_skins.apply();
        } else if (skins.getInt("bear", 0) == 2) {
            editor_skins.putInt("bear", 1);
            editor_skins.apply();
        } else if (skins.getInt("monkey", 0) == 2) {
            editor_skins.putInt("monkey", 1);
            editor_skins.apply();
        } else if (skins.getInt("sheep", 0) == 2) {
            editor_skins.putInt("sheep", 1);
            editor_skins.apply();
        } else if (skins.getInt("cow", 0) == 2) {
            editor_skins.putInt("cow", 1);
            editor_skins.apply();
        } else if (skins.getInt("panda", 0) == 2) {
            editor_skins.putInt("panda", 1);
            editor_skins.apply();
        } else if (skins.getInt("lion", 0) == 2) {
            editor_skins.putInt("lion", 1);
            editor_skins.apply();
        } else if (skins.getInt("owl", 0) == 2) {
            editor_skins.putInt("owl", 1);
            editor_skins.apply();
        } else if (skins.getInt("dragon", 0) == 2) {
            editor_skins.putInt("dragon", 1);
            editor_skins.apply();
        } else if (skins.getInt("giraffe", 0) == 2) {
            editor_skins.putInt("giraffe", 1);
            editor_skins.apply();
        } else if (skins.getInt("elephant", 0) == 2) {
            editor_skins.putInt("elephant", 1);
            editor_skins.apply();
        }
    }

    // Helper function that unequip all the spike skins that in use (only one of tham can be in use)

    private void disable_all_spikes() {
        if (spikes.getInt("ninja_sword", 0) == 2) {
            editor_spikes.putInt("ninja_sword", 1);
            editor_spikes.apply();
        } else if (spikes.getInt("sword", 0) == 2) {
            editor_spikes.putInt("sword", 1);
            editor_spikes.apply();
        } else if (spikes.getInt("ninja_sword", 0) == 2) {
            editor_spikes.putInt("ninja_sword", 1);
            editor_spikes.apply();
        } else if (spikes.getInt("steak", 0) == 2) {
            editor_spikes.putInt("steak", 1);
            editor_spikes.apply();
        } else if (spikes.getInt("bomb", 0) == 2) {
            editor_spikes.putInt("bomb", 1);
            editor_spikes.apply();
        }
    }

    private void what_you_have() { // Checking what skin do u have if u don't have the skin he will be in gray-black color
        if (skins.getInt("bunny", 0) == 0)
            bunny_skin.setImageResource(R.drawable.bunny_unuse);
        if (skins.getInt("dog", 0) == 0)
            dog_skin.setImageResource(R.drawable.dog_unuse);
        if (skins.getInt("cat", 0) == 0)
            cat_skin.setImageResource(R.drawable.cat_unuse);
        if (skins.getInt("fox", 0) == 0)
            fox_skin.setImageResource(R.drawable.fox_unuse);
        if (skins.getInt("tiger", 0) == 0)
            tiger_skin.setImageResource(R.drawable.tiger_unuse);
        if (skins.getInt("pig", 0) == 0)
            pig_skin.setImageResource(R.drawable.pig_unuse);
        if (skins.getInt("chicken", 0) == 0)
            chicken_skin.setImageResource(R.drawable.chicken_unuse);
        if (skins.getInt("bear", 0) == 0)
            bear_skin.setImageResource(R.drawable.bear_unuse);
        if (skins.getInt("monkey", 0) == 0)
            monkey_skin.setImageResource(R.drawable.monkey_unuse);
        if (skins.getInt("sheep", 0) == 0)
            sheep_skin.setImageResource(R.drawable.sheep_unuse);
        if (skins.getInt("cow", 0) == 0)
            cow_skin.setImageResource(R.drawable.cow_unuse);
        if (skins.getInt("panda", 0) == 0)
            panda_skin.setImageResource(R.drawable.panda_unuse);
        if (skins.getInt("lion", 0) == 0)
            lion_skin.setImageResource(R.drawable.lion_unuse);
        if (skins.getInt("owl", 0) == 0)
            owl_skin.setImageResource(R.drawable.owl_unuse);
        if (skins.getInt("dragon", 0) == 0)
            dragon_skin.setImageResource(R.drawable.dragon_unuse);
        if (skins.getInt("giraffe", 0) == 0)
            giraffe_skin.setImageResource(R.drawable.giraffe_unuse);
        if (skins.getInt("elephant", 0) == 0)
            elephant_skin.setImageResource(R.drawable.elephant_unuse);
        if (spikes.getInt("ninja_sword", 0) == 0)
            ninja_sword_spike.setImageResource(R.drawable.ninja_sword_unuse);
        if (spikes.getInt("sword", 0) == 0)
            sword_spike.setImageResource(R.drawable.sword_unuse);
        if (spikes.getInt("steak", 0) == 0)
            steak_spike.setImageResource(R.drawable.steak_unuse);
        if (spikes.getInt("bomb", 0) == 0)
            bomb_spike.setImageResource(R.drawable.bomb_unuse);

    }

    private void what_in_use() {  // Checking what skin do u have if u have the skin he will be in his color
        if (skins.getInt("bunny", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.bunny_puppy);
        } else if (skins.getInt("dog", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.dog_puppy);
        } else if (skins.getInt("cat", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.cat_puppy);
        } else if (skins.getInt("fox", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.fox_puppy);
        } else if (skins.getInt("tiger", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.tiger_puppy);
        } else if (skins.getInt("pig", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.pig_puppy);
        } else if (skins.getInt("chicken", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.chicken_puppy);
        } else if (skins.getInt("bear", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.bear_puppy);
        } else if (skins.getInt("monkey", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.monkey_puppy);
        } else if (skins.getInt("sheep", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.sheep_puppy);
        } else if (skins.getInt("cow", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.cow_puppy);
        } else if (skins.getInt("panda", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.panda_puppy);
        } else if (skins.getInt("lion", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.lion_puppy);
        } else if (skins.getInt("owl", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.owl_puppy);
        } else if (skins.getInt("dragon", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.dragon_puppy);
        } else if (skins.getInt("giraffe", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.giraffe_puppy);
        } else if (skins.getInt("elephant", 0) == 2) {
            inuse_puppy.setImageResource(R.drawable.elephant_puppy);
        }
        if (spikes.getInt("ninja_sword", 0) == 2) {
            inuse_spike.setImageResource(R.drawable.ninja_sword_icon);
        } else if (spikes.getInt("sword", 0) == 2) {
            inuse_spike.setImageResource(R.drawable.sword_icon);
        } else if (spikes.getInt("steak", 0) == 2) {
            inuse_spike.setImageResource(R.drawable.steak_icon);
        } else if (spikes.getInt("bomb", 0) == 2) {
            inuse_spike.setImageResource(R.drawable.bomb_icon);
        }
    }

}