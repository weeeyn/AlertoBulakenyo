package com.activity.alertobulakenyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class Prep_EQ extends AppCompatActivity {

    ImageButton btnBackPrep;
    TextView tvIntro, tvCont, tvBull01, tvBull02, tvBull03, tvBull04, tvBull05, tvBull06;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_prep_eq);

        btnBackPrep = (ImageButton) findViewById (R.id.btnBackPrep);

        tvIntro = (TextView) findViewById (R.id.tvIntro);
        tvCont = (TextView) findViewById (R.id.tvCont);
        tvBull01 = (TextView) findViewById (R.id.tvBull01);
        tvBull02 = (TextView) findViewById (R.id.tvBull02);
        tvBull03 = (TextView) findViewById (R.id.tvBull03);
        tvBull04 = (TextView) findViewById (R.id.tvBull04);
        tvBull05 = (TextView) findViewById (R.id.tvBull05);
        tvBull06 = (TextView) findViewById (R.id.tvBull06);

        btnBackPrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Prep_EQ.this, Prepare.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,
                        R.anim.slide_out_right);
            }
        });

        tvIntro.setText("       Earthquake is any sudden shaking of the ground caused by the passage of seismic waves through Earth’s rocks. Seismic waves are produced when some form of energy stored in Earth’s crust is suddenly released, usually when masses of rock straining against one another suddenly fracture and “slip.” Earthquakes occur most often along geologic faults, narrow zones where rock masses move in relation to one another. The major fault lines of the world are located at the fringes of the huge tectonic plates that make up Earth’s crust.");

        tvCont.setText("       Earthquakes happen without warning. We don’t know when the next major earthquake will happen, but we can take action now to prepare to survive it and minimize impact. Preparedness starts with a plan. For cities and agencies, this means working with engineers to retrofit older structures and design new buildings, bridges, and highways to withstand an earthquake as best as possible. For individuals and families, this means putting together an earthquake preparedness plan and kit and knowing what to do before, during, and after an earthquake.");

        tvBull01.setText("\u25CF  Fasten shelves securely to walls." +
                "\n\u25CF  Place large or heavy objects on lower shelves." +
                "\n\u25CF  Store breakable items such as bottled foods, glass, and china in low, closed cabinets with latches." +
                "\n\u25CF  Hang heavy items such as pictures and mirrors away from beds, couches, and anywhere people sit." +
                "\n\u25CF  Brace overhead light fixtures." +
                "\n\u25CF  Repair defective electrical wiring and leaky gas connections. These are potential fire risks." +
                "\n\u25CF  Secure a water heater by strapping it to the wall studs and bolting it to the floor." +
                "\n\u25CF  Repair any deep cracks in ceilings or foundations. Get expert advice if there are signs of structural defects." +
                "\n\u25CF  Store weed killers, pesticides, and flammable products securely in closed cabinets with latches and on bottom shelves.");

        tvBull02.setText("\u25CF  Under sturdy furniture such as a heavy desk or table." +
                "\n\u25CF  Against an inside wall." +
                "\n\u25CF  Away from where glass could shatter around windows, mirrors, pictures, or where heavy bookcases or other heavy furniture could fall over." +
                "\n\u25CF  In the open, away from buildings, trees, telephone and electrical lines, overpasses, or elevated expressways.");

        tvBull03.setText("\u25CF  Contact your local emergency management office for more information on earthquakes." +
                "\n\u25CF  Teach children how and when to call 9-1-1, police, or fire department and which radio station to tune to for emergency information." +
                "\n\u25CF  Teach all family members how and when to turn off gas, electricity, and water");

        tvBull04.setText("\u25CF  Flashlight and extra batteries" +
                "\n\u25CF  Portable, battery-operated radio and extra batteries" +
                "\n\u25CF  First aid kit and manual" +
                "\n\u25CF  Emergency food and water" +
                "\n\u25CF  Non Electric can opener" +
                "\n\u25CF  Essential medicines" +
                "\n\u25CF  Cash and credit cards" +
                "\n\u25CF  Sturdy shoes");

        tvBull05.setText("\u25CF  Make a plan for reuniting after the disaster in case family members are separated from one another during an earthquake (a real possibility during the day when adults are at work and children are at school)." +
                "\n\u25CF  Ask an out-of-state relative or friend to serve as the \"family contact.\" After a disaster, it's often easier to call long distance. Make sure everyone in the family knows the name, address, and phone number of the contact person.");

        tvBull06.setText("\u25CF  Publish a special section in your local newspaper with emergency information on earthquakes. Localize the information by printing the phone numbers of local emergency services offices, the Red Cross, and hospitals." +
                "\n\u25CF  Conduct a week-long series on locating hazards in the home." +
                "\n\u25CF  Work with local emergency services and Red Cross officials to prepare special reports for people with mobility impairments on what to do during an earthquake." +
                "\n\u25CF  Provide tips on conducting earthquake drills in the home." +
                "\n\u25CF  Interview representatives of the gas, electric, and water companies about shutting off utilities." +
                "\n\u25CF  Work together in your community to apply your knowledge to building codes, retrofitting programs, hazard hunts, and neighborhood and family emergency plans.");
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(Prep_EQ.this, Prepare.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}