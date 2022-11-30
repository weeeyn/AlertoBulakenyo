package com.activity.alertobulakenyo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class Admin_DisasterEQ extends AppCompatActivity {

    Button btnPick;
    EditText etDate;
    TextView tvWarnTitle;
    DatePickerDialog datePicker;
    CardView cardWarn;
    RecyclerView rvDisEQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_disaster_eq);

        btnPick = (Button) findViewById (R.id.btnPick);

        etDate = (EditText) findViewById (R.id.etDate);

        tvWarnTitle = (TextView) findViewById (R.id.tvWarnTitle);

        cardWarn = (CardView) findViewById (R.id.cardWarn);

        rvDisEQ = (RecyclerView) findViewById (R.id.rvDisEQ);

        cardWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_DisasterEQ.this, Admin_DisasterEQInfo.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }
        });

        btnPick.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar calendar = Calendar.getInstance();

                int Year = calendar.get(Calendar.YEAR);
                int Month = calendar.get(Calendar.MONTH);
                int Day = calendar.get(Calendar.DAY_OF_MONTH);

                datePicker = new DatePickerDialog(Admin_DisasterEQ.this, R.style.DatePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {

                        month = month + 1;

                        if (month == 1)
                        {
                            etDate.setText("JANUARY " + day + ", " + year);
                            tvWarnTitle.setText("JANUARY " + day + ", " + year);
                        }
                        else if (month == 2)
                        {
                            etDate.setText("FEBRUARY " + day + ", " + year);
                            tvWarnTitle.setText("FEBRUARY " + day + ", " + year);
                        }
                        else if (month == 3)
                        {
                            etDate.setText("MARCH " + day + ", " + year);
                            tvWarnTitle.setText("MARCH " + day + ", " + year);
                        }
                        else if (month == 4)
                        {
                            etDate.setText("APRIL " + day + ", " + year);
                            tvWarnTitle.setText("APRIL " + day + ", " + year);
                        }
                        else if (month == 5)
                        {
                            etDate.setText("MAY " + day + ", " + year);
                            tvWarnTitle.setText("MAY " + day + ", " + year);
                        }
                        else if (month == 6)
                        {
                            etDate.setText("JUNE " + day + ", " + year);
                            tvWarnTitle.setText("JUNE " + day + ", " + year);
                        }
                        else if (month == 7)
                        {
                            etDate.setText("JULY " + day + ", " + year);
                            tvWarnTitle.setText("JULY " + day + ", " + year);
                        }
                        else if (month == 8)
                        {
                            etDate.setText("AUGUST " + day + ", " + year);
                            tvWarnTitle.setText("AUGUST " + day + ", " + year);
                        }
                        else if (month == 9)
                        {
                            etDate.setText("SEPTEMBER " + day + ", " + year);
                            tvWarnTitle.setText("SEPTEMBER " + day + ", " + year);
                        }
                        else if (month == 10)
                        {
                            etDate.setText("OCTOBER " + day + ", " + year);
                            tvWarnTitle.setText("OCTOBER " + day + ", " + year);
                        }
                        else if (month == 11)
                        {
                            etDate.setText("NOVEMBER " + day + ", " + year);
                            tvWarnTitle.setText("NOVEMBER " + day + ", " + year);
                        }
                        else if (month == 12)
                        {
                            etDate.setText("DECEMBER " + day + ", " + year);
                            tvWarnTitle.setText("DECEMBER " + day + ", " + year);
                        }
                    }
                }, Year, Month, Day);

                datePicker.show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}