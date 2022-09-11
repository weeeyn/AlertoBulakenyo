package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prep_Flood extends AppCompatActivity {

    TextView tvIntro, tvCont, tvBull01, tvBull02, tvBull03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prep_flood);

        tvIntro = (TextView) findViewById (R.id.tvIntro);
        tvCont = (TextView) findViewById (R.id.tvCont);
        tvBull01 = (TextView) findViewById (R.id.tvBull01);
        tvBull02 = (TextView) findViewById (R.id.tvBull02);
        tvBull03 = (TextView) findViewById (R.id.tvBull03);

        tvIntro.setText("       A flood is an overflow of water (or rarely other fluids) that submerges land that is usually dry. In the sense of \"flowing water\", the word may also be applied to the inflow of the tide. Floods are an area of study of the discipline hydrology and are of significant concern in agriculture, civil engineering and public health. Human changes to the environment often increase the intensity and frequency of flooding, for example land use changes such as deforestation and removal of wetlands, changes in waterway course or flood controls such as with levees, and larger environmental issues such as climate change and sea level rise. In particular climate change's increased rainfall and extreme weather events increases the severity of other causes for flooding, resulting in more intense floods and increased flood risk.");

        tvCont.setText("       Floods cause power outages, damage infrastructure, trigger landslides, and can be deadly. Heavy rainfall in a short period of time causes water to rise rapidly, elevating the risk of flooding. Flash floods occur with little warning, but flooding can also develop slowly after rain ceases. Though most people associate hurricanes with wind damage, flooding poses one of the biggest threats from the storms. Here are some safety tips to help you prepare for rising water—and what to do once a flood has begun.");

        tvBull01.setText("\u25CF  Avoid building in a floodplain — an area especially prone to flooding during heavy rains." +
                "\n\u25CF  If you do live in a floodplain, consider buying flood insurance to help with losses if, and when, a flood occurs." +
                "\n\u25CF  Construct barriers (levees, beams, floodwalls) to stop flood water from entering your home. Sandbags can provide a temporary levee in an emergency." +
                "\n\u25CF  Seal walls in basements with waterproofing compounds to avoid seepage." +
                "\n\u25CF  Pay attention to weather forecasts. When heavy rain or storms are forecasted, listen to the radio or television for information on flooding risk." +
                "\n\u25CF  Be prepared! Assemble an emergency kit in case you need to evacuate. Don't forget to include necessary prescription medications and a small first aid kit." +
                "\n\u25CF  When a flood is imminent, have an emergency plan and practice survival skills, like first aid and how to disinfect water." +
                "\n\u25CF  Be prepared! Assemble an emergency kit in case you need to evacuate. Don't forget to include necessary prescription medications and a small first aid kit." +
                "\n\u25CF  Charge cell phone batteries and any reusable batteries for flashlights. Buy extra batteries in case power isn’t restored immediately." +
                "\n\u25CF  Heed evacuation warnings. If there is any possibility of a flash flood, move immediately to higher ground. Follow appropriate evacuation signs." +
                "\n\u25CF  If possible, bring in outdoor furniture and move important items to an upper floor, above possible flood levels." +
                "\n\u25CF  Turn off utilities at the main switches or valves if instructed. Disconnect electrical appliances.");

        tvBull02.setText("\u25CF  Avoid low spots, like ditches, basements, or underpasses. These become extremely dangerous during a flash flood." +
                "\n\u25CF  Do not walk through flooded areas. It can be difficult to tell how deep the water is and what lies underneath the water that could hurt you. Even shallow, moving water can make you fall." +
                "\n\u25CF  If you have to walk in water, wherever possible, walk where the water is not moving. Use a stick to check the firmness of the ground in front of you." +
                "\n\u25CF  Do not drive into flooded areas. Remember: “Turn around, don’t drown.” If floodwaters rise around your car, abandon the car and move to higher ground — only if you can do so safely." +
                "\n\u25CF  Do not touch electrical equipment if you are wet or standing in water.");

        tvBull03.setText("\u25CF  Return home only when authorities say it is safe." +
                "\n\u25CF  Listen for news reports to learn whether the water supply is safe to drink and where emergency shelters are located." +
                "\n\u25CF  Avoid floodwaters; water may be contaminated by oil, gas, or raw sewage. Water may also be electrically charged from underground or downed power lines." +
                "\n\u25CF  Still avoid moving water — the danger decreases only when water levels drop." +
                "\n\u25CF  Be aware of areas where flood waters have receded. Roads may have weakened and could collapse under the weight of a car." +
                "\n\u25CF  Stay away from downed power lines and report them to the power company." +
                "\n\u25CF  Stay out of any building if it is surrounded by floodwaters." +
                "\n\u25CF  Service damaged septic tanks, cesspools, pits, and leaching systems as soon as possible. Damaged sewage systems are serious health hazards." +
                "\n\u25CF  Only pump water out of a flooded building when water has receded outside." +
                "\n\u25CF  Clean and disinfect everything that was stuck in flooded waters. Mud left from flood water can contain sewage and chemicals." +
                "\n\u25CF  Be wary of lingering water inside buildings after a flood. A dehumidifier will help remove excess water and minimize mold damage.");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Prep_Flood.this, Prepare.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}