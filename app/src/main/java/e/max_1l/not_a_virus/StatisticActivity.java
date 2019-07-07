package e.max_1l.not_a_virus;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity implements View.OnClickListener {
    TextView allcookies, clickcookies, farmcookies,ach1name, ach1cond ;
    ImageView ach1;
    int allc, num, clickc, farmc,flag=0 ;
    Button back ;

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
        back = findViewById(R.id.back) ;

        back.setOnClickListener(this);
        ach1.setOnClickListener(this);

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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                Intent g = new Intent();
                g.putExtra("num", num) ;
                g.putExtra("allc", allc) ;
                g.putExtra("clickc", clickc) ;
                g.putExtra("farmc", farmc) ;
                setResult(RESULT_OK,g);
                finish();
                break;
        }
    }
}
