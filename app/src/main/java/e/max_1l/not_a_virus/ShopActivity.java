package e.max_1l.not_a_virus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShopActivity extends AppCompatActivity implements View.OnClickListener {
Button farmshop, back, clickshop ;
int num, fcost = 10, fspeed = 0, ccost = 10, count = 1;
TextView cookie, clickspeed, cookiescost, farmcost, farmspeed ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        /*
                i.putExtra("num", num) ;
                i.putExtra("fspeed", fspeed) ;
                i.putExtra("count", count) ;
         */

        farmshop = findViewById(R.id.farmshop) ;
        back = findViewById(R.id.back_lastshop_button) ;
        clickshop = findViewById(R.id.clickshop) ;
        cookie = findViewById(R.id.cookie) ;
        clickspeed = findViewById(R.id.clickspeed) ;
        cookiescost = findViewById(R.id.cookiescost) ;
        farmcost = findViewById(R.id.farmcost) ;
        farmspeed = findViewById(R.id.farmspeed) ;

        farmshop.setOnClickListener(this);
        back.setOnClickListener(this);
        clickshop.setOnClickListener(this);

        //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
        num = getIntent().getIntExtra("num", -1 ) ;
        count = getIntent().getIntExtra("count", -1 ) ;
        ccost = getIntent().getIntExtra("ccost", -1 ) ;
        fspeed = getIntent().getIntExtra("fspeed", -1 ) ;
        fcost = getIntent().getIntExtra("fcost", -1 ) ;
        clickspeed.setText(count+" /click");
        cookiescost.setText(ccost+" cookies");
        farmspeed.setText(fspeed+" /sec");
        farmcost.setText(fcost+" cookies");

        //fspeed = getIntent().getIntExtra("fspeed", 0 ) ;

        if (num!=1) {
            cookie.setText(num + " cookies");
        }
        else{
            cookie.setText(num + " cookie");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickshop:
                if (num>=ccost){
                    num-=ccost ;
                    count +=1 ;
                    if (num!=1) {
                        cookie.setText(num + " cookies");
                    }
                    else{
                        cookie.setText(num + " cookie");
                    }
                    ccost=ccost*3/2 ;
                    clickspeed.setText(count+" /click");
                    cookiescost.setText(ccost+" cookies");
                }
            break;
            case R.id.farmshop:
                if (num>=fcost) {
                    num -= fcost;
                    fspeed += 1;
                    if (num != 1) {
                        cookie.setText(num + " cookies");
                    } else {
                        cookie.setText(num + " cookie");
                    }
                    fcost = fcost * 3 / 2;
                    farmspeed.setText(fspeed+" /sec");
                    farmcost.setText(fcost+" cookies");
                }
            break;
            case R.id.back_lastshop_button:
                Intent f = new Intent();
                //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
                f.putExtra("num", num) ;
                f.putExtra("count", count) ;
                f.putExtra("ccost", ccost) ;
                f.putExtra("fspeed", fspeed) ;
                f.putExtra("fcost", fcost) ;

                setResult(RESULT_OK,f);
                finish();

        }
    }
}
