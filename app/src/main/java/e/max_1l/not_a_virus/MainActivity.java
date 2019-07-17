package e.max_1l.not_a_virus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView cookie, smallcookie ;
    TextView number,  clickspeed,   farmspeed ;
    Button  shop, statistics,rshop ;
    Handler h ;
    int firstFlag = 0;



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////звуки//////////////////////////////////////
    SoundPool mSoundPool;
    int  soundId,confirmSoundId;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////звуки//////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////////////////////// сохранение настроек///////////////////////

    public static final String fileName = "mysettings";          //название файла настроек
    public static final String numSetting = "numSetting";        //настройка кол-ва печенек
    public static final String countSetting = "countSetting";
    public static final String ccostSetting = "ccostSetting";
    public static final String fspeedSetting = "fpeedSetting";
    public static final String fcostSetting = "fcostSetting";
    public static final String allcSetting = "allcSetting";
    public static final String clickcSetting = "clickSetting";
    public static final String farmcSetting = "farmSetting";
    public SharedPreferences mSettings;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////// сохранение настроек///////////////////////

    int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10, allc = 0, clickc = 0, farmc = 0 ;



    @SuppressLint({"HandlerLeak", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cookie = findViewById(R.id.cookie) ;
        number = findViewById(R.id.number) ;
        clickspeed = findViewById(R.id.clickspeed) ;
        farmspeed = findViewById(R.id.farmspeed) ;
        shop = findViewById(R.id.shop) ;
        statistics = findViewById(R.id.statistics) ;
        rshop = findViewById(R.id.r_shop) ;
        smallcookie = findViewById(R.id.smallcookie) ;

        cookie.setOnClickListener(this);
        cookie.setSoundEffectsEnabled(false);
        shop.setOnClickListener(this);
        shop.setSoundEffectsEnabled(false);
        statistics.setOnClickListener(this);
        statistics.setSoundEffectsEnabled(false);
        rshop.setOnClickListener(this);
        rshop.setSoundEffectsEnabled(false);

        mSettings = getSharedPreferences(fileName, Context.MODE_PRIVATE);

        mSoundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        soundId = mSoundPool.load(this,R.raw.cookiesound,1);
        confirmSoundId = mSoundPool.load(this,R.raw.confirm,1);


        Thread f = new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                        num += fspeed ;
                        allc +=fspeed ;
                        farmc += fspeed ;
                        h.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        f.start();
        h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (num!=1) {
                    number.setText(num + " cookies");
                }else {
                    number.setText(num + " cookie");
                }
            }
        };





        cookie.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        num+=count ;
                        if (num!=1) {
                            number.setText(num + " cookies");
                        }
                        else{
                            number.setText(num + " cookie");
                        }
                        allc+=count ;
                        clickc+=count ;
                        mSoundPool.play(soundId,1,1,1,0,2);
                        cookie.setVisibility(View.INVISIBLE);
                        smallcookie.setVisibility(View.VISIBLE);
                        break;
                    case MotionEvent.ACTION_UP:
                        cookie.setVisibility(View.VISIBLE);
                        smallcookie.setVisibility(View.INVISIBLE);

                }
                return false;
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shop:
                Intent i = new Intent(this,ShopActivity.class) ;
                //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
                i.putExtra("num", num) ;
                i.putExtra("count", count) ;
                i.putExtra("ccost", ccost) ;
                i.putExtra("fspeed", fspeed) ;
                i.putExtra("fcost", fcost) ;
                mSoundPool.play(confirmSoundId,1,1,1,0,2);
                startActivityForResult(i,1);
            break;
            case R.id.statistics:
                /*
                Intent g = new Intent(this, StatisticActivity.class) ;
                g.putExtra("allc", allc) ;
                g.putExtra("num", num) ;
                g.putExtra("clickc", clickc) ;
                g.putExtra("farmc", farmc) ;
                mSoundPool.play(confirmSoundId,1,1,1,0,2);
                startActivityForResult(g,2);
                */
                Intent g = new Intent(this, LastShopActivity.class) ;
                startActivity(g);
             break;
            case R.id.r_shop:
                Intent f = new Intent(this,RecyclerShopActivity.class) ;
                //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
                f.putExtra("num", num) ;
                f.putExtra("count", count) ;
                f.putExtra("ccost", ccost) ;
                f.putExtra("fspeed", fspeed) ;
                f.putExtra("fcost", fcost) ;
                mSoundPool.play(confirmSoundId,1,1,1,0,2);
                startActivityForResult(f,1);
                break;
        }
    }

    public void myVoid(){
        //mSettings.getInt()
    }




    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(numSetting,num);
        editor.putInt(countSetting,count);
        editor.putInt(ccostSetting,ccost);
        editor.putInt(fspeedSetting,fspeed);
        editor.putInt(fcostSetting,fcost);
        editor.putInt(allcSetting,allc);
        editor.putInt(clickcSetting,clickc);
        editor.putInt(farmcSetting,farmc);
        editor.apply();
    }

    protected void onResume() {
        super.onResume();
        if(firstFlag==0){
            firstFlag++;
            setmSettings();
        }


    }

    private void setmSettings(){
        num = mSettings.getInt(numSetting, 0);
        count = mSettings.getInt(countSetting, 1);
        ccost = mSettings.getInt(ccostSetting, 10);
        fspeed = mSettings.getInt(fspeedSetting, 0);
        fcost = mSettings.getInt(fcostSetting, 10);
        allc = mSettings.getInt(allcSetting, 0);
        clickc = mSettings.getInt(clickcSetting, 0);
        farmc = mSettings.getInt(farmcSetting, 0);
        setText();

    }



    protected void setText (){
        if (num!=1) {
            number.setText(num + " cookies");
        }
        else{
            number.setText(num + " cookie");
        }
        clickspeed.setText(count + "/click");
        farmspeed.setText(fspeed + "/sec");
    }





    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
            switch (resultCode) {
                case RESULT_OK:
                    num = data.getIntExtra("num", 0);
                    number.setText(num + " cookie");
                    count = data.getIntExtra("count", 0);
                    clickspeed.setText(count + "/click");
                    ccost = data.getIntExtra("ccost", 0);
                    fspeed = data.getIntExtra("fspeed", 0);
                    farmspeed.setText(fspeed + "/sec");
                    fcost = data.getIntExtra("fcost", 0);

                    break;
            }
            break;
            case 2:
                switch (resultCode) {
                    case RESULT_OK:
                        num = data.getIntExtra("num", 0);
                        allc = data.getIntExtra("allc", 0) ;
                        clickc = data.getIntExtra("clickc", 0);
                        farmc = data.getIntExtra("farmc", 0);
                        number.setText(num + " cookie");
                        break;
                }
            break;
        }

    }
}
