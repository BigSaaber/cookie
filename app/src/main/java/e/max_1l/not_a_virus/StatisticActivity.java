package e.max_1l.not_a_virus;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity implements View.OnClickListener {
    TextView allcookies, clickcookies, farmcookies,ach1name, ach1cond ;
    ImageView ach1;
    int allc, num, clickc, farmc,flag=0 ;
    Button back,reset ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ach1 = findViewById(R.id.ach1);
        ach1name = findViewById(R.id.ach1name);
        ach1cond = findViewById(R.id.ach1cond);
        allcookies = findViewById(R.id.allcookies) ;
        clickcookies = findViewById(R.id.clickcookies) ;
        farmcookies = findViewById(R.id.farmcookies) ;
        back = findViewById(R.id.back_lastshop_button) ;
        reset = findViewById(R.id.reset) ;

        back.setOnClickListener(this);
        reset.setOnClickListener(this);

        allc = getIntent().getIntExtra("allc", -1 ) ;
        num = getIntent().getIntExtra("num", -1 ) ;
        clickc = getIntent().getIntExtra("clickc", -1 ) ;
        farmc = getIntent().getIntExtra("farmc", -1 ) ;

        allcookies.setText(allc+" cookies produced");
        clickcookies.setText(clickc+" cookies by clicks");
        farmcookies.setText(farmc+" cookies by farm");

        if (allc>=1000){
            ach1.setAlpha((float)1);
            ach1name.setAlpha((float)1);
            ach1cond.setAlpha((float)1);
        }



    }
    public void resetfunc(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StatisticActivity.this);
        builder.setTitle("reset the progress");
        builder.setMessage("Do you sure?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }});
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                num=0;
                allc=0;
                clickc=0;
                farmc=0;
                allcookies.setText(allc+" cookies produced");
                clickcookies.setText(clickc+" cookies by clicks");
                farmcookies.setText(farmc+" cookies by farm");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_lastshop_button:
                Intent g = new Intent();
                g.putExtra("num", num) ;
                g.putExtra("allc", allc) ;
                g.putExtra("clickc", clickc) ;
                g.putExtra("farmc", farmc) ;
                setResult(RESULT_OK,g);
                finish();
                break;
            case R.id.reset:
                resetfunc();
                break;
        }
    }
}
