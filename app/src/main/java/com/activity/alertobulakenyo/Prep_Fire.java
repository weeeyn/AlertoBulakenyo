package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prep_Fire extends AppCompatActivity {

    TextView tvIntro, tvCont, tvBullet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prep_fire);

        tvIntro = (TextView) findViewById (R.id.tvIntro);
        tvCont = (TextView) findViewById (R.id.tvCont);
        tvBullet = (TextView) findViewById (R.id.tvBullet);

        tvIntro.setText("       Fires can start in many different ways. A fire can start by accident, by acts of nature, or even by arson. You can help keep your family and co-workers safe during a fire by making sure smoke alarms work and by practicing a fire escape plan.");

        tvCont.setText("       If a fire starts in your home, there are a few things you should do to minimize the damage and keep yourself safe. First, make sure everyone in the home is aware of the fire and knows what to do. Then, if you can, try to put the fire out yourself using a fire extinguisher or a bucket of water. If that’s not possible, evacuate the house and call the fire department. Do not go back into the house until the firefighters have declared it safe. Once you’ve evacuated, try to stay calm and collected. Don’t run around in a panic, and make sure to keep track of your family and belongings. If you’re unsure where everyone is, meet at a predetermined spot away from the house. Once you’ve accounted for everyone, start thinking about what you need to do next.");

        tvBullet.setText("\u25CF  If smoke alarms are not already in place, install them outside each sleeping area and on each additional level of your home in accordance with local codes. Smoke alarms cut your chances of dying in a home fire nearly in half. Smoke alarms sense abnormal amounts of smoke or invisible combustion gasses in the air. They can detect both smoldering and flaming fires." +
                "\n\u25CF  Draw a floor plan of your home; mark two fire escape routes for each room. In thick, heavy, dark smoke it is easy to become disoriented. Creating a floor plan with two routes greatly helps everyone understand the safest routes during a frightening emergency." +
                "\n\u25CF  Consider escape ladders for sleeping areas on the second or third floor. Learn how to use them, and store them near the window. If main escape routes via stairs are blocked by smoke or fire, the windows may be your only alternative. Escape ladders permit quick exits, reducing time spent in smoke-filled, toxic environments while waiting for firefighters." +
                "\n\u25CF  Burglar bars and locks that block outside window entry must be easy to open from the inside. If a key is required to open bars or locks, keep a key near each window to use for fire escape. Quick-release devices are available for security bars. If smoke or fire is blocking the main exit, you must be able to use your alternate routes quickly. Fire deaths have occurred when people were trapped by security bars and were unable to get out and firefighters were unable to get in." +
                "\n\u25CF  Select a safe outside meeting place for everyone to meet after escaping from a fire. Make sure it will be a safe distance from heat, smoke, and flames. Family members may use different escape routes, exiting on different sides of the home. Gathering in a specific meeting place in front of the home will quickly let you know who is out, and allow you to advise firefighters of who may need help and their probable location inside." +
                "\n\u25CF  Conduct a home fire drill at least twice a year with all members of your household. Fires produce thick, dark smoke that irritates the eyes and breathing passages and can cause confusion. People who have become disoriented in fires have been found in closets, stairwells, and laundry rooms, thinking they were exits. Practicing your plan makes the actual response more of an appropriate reaction, requiring less thinking during an emergency situation." +
                "\n\u25CF  Practice alerting other household members. Yell \"Fire!\" several times during your escape. In a real fire this will alert family members to get out." +
                "\n\u25CF  Practice a crawl-low escape from your bedroom, as if you were crawling under a layer of smoke. Fires produce many toxic gasses. Some are heavy and will sink low to the floor; others will rise, carrying soot towards the ceiling. Crawling with your head at a level of one to two feet above the ground above the ground will temporarily provide the best air. Close doors behind you." +
                "\n\u25CF  Practice evacuating the building blindfolded. In a real fire situation, the amount of smoke generated by a fire will most likely make it impossible to see." +
                "\n\u25CF  Learn the emergency number for your local fire department. After leaving your home, you will need to call this number from an outside phone or from a neighbor's house." +
                "\n\u25CF  Teach family members to get out first, then call for help from a neighbor's house or outside phone. Get out of the house, away from toxic smoke and gasses. If a portable phone is handy during your escape, you may take it with you, but do not waste precious time looking for one. Use your neighbor's phone, a car phone, or nearby payphone to call for help." +
                "\n\u25CF  Practice getting out of your home during the day and night. Fire can happen at any time. Practicing your routes at night will help you move more quickly should a fire strike in the dark." +
                "\n\u25CF  Discuss fires with your family. Everyone should know what to do in case all family members are not together. Discussing disaster ahead of time helps reduce fear and lets everyone know how to respond during a fire.");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Prep_Fire.this, Prepare.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}