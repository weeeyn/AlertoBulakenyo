package com.activity.alertobulakenyo.Admins;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.activity.alertobulakenyo.R;
import com.activity.alertobulakenyo.ObjectClasses.WarningHolder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class Admin_DisasterTyInfo extends AppCompatActivity {

    TextView tvDate, tvTime, tvTyName, tvSignal, tvLoc, tvIns;
    Button btnDelete;
    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //enable full screen

        setContentView(R.layout.activity_admin_disaster_ty_info);

        WarningHolder warningHolder = (WarningHolder) getIntent().getSerializableExtra("tywarn");

        tvDate = (TextView) findViewById (R.id.tvDate);
        tvTime = (TextView) findViewById (R.id.tvTime);
        tvTyName = (TextView) findViewById (R.id.tvTyName);
        tvSignal = (TextView) findViewById (R.id.tvSignal);
        tvLoc = (TextView) findViewById (R.id.tvLoc);
        tvIns = (TextView) findViewById (R.id.tvIns);

        tvDate.setText(warningHolder.getDisasterDate());
        tvTime.setText(warningHolder.getDisasterTime());
        tvTyName.setText(warningHolder.getTyphoonName());
        tvSignal.setText(warningHolder.getTyphoonSignal());
        tvLoc.setText(warningHolder.getBody() + ", Bulacan");
        tvIns.setText(warningHolder.getDisasterInfo());
        btnDelete = (Button) findViewById (R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Admin_DisasterTyInfo.this);

                builder.setMessage("Are you sure you want to delete this warning?");

                builder.setCancelable(true);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    fStore.collection("Warning")
                            .document(warningHolder.getId())
                            .delete()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Warning Deleted", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Admin_DisasterTy.class);
                                        startActivity(intent);
                                        overridePendingTransition(R.anim.slide_in_left,
                                                R.anim.slide_out_right);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Warning Not Deleted!", Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "onFailure: FAILED TO DELETE WARNING");
                                    }
                                }
                            });
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    // If user click no then dialog box is canceled.
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), Admin_DisasterTy.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}