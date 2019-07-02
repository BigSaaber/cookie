package e.max_1l.not_a_virus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatisticActivity extends AppCompatActivity implements View.OnClickListener {
    TextView allcookies, clickcookies, farmcookies ;
    int allc, num, clickc, farmc ;
    Button back ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        allcookies = findViewById(R.id.allcookies) ;
        clickcookies = findViewById(R.id.clickcookies) ;
        farmcookies = findViewById(R.id.farmcookies) ;
        back = findViewById(R.id.back) ;

        back.setOnClickListener(this);

        allc = getIntent().getIntExtra("allc", -1 ) ;
        num = getIntent().getIntExtra("num", -1 ) ;
        clickc = getIntent().getIntExtra("clickc", -1 ) ;
        farmc = getIntent().getIntExtra("farmc", -1 ) ;

        allcookies.setText(allc+" cookies produced");
        clickcookies.setText(clickc+" cookies by clicks");
        farmcookies.setText(farmc+" cookies by farm");
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
        }
    }
}
