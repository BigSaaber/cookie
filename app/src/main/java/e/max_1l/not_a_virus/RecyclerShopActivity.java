package e.max_1l.not_a_virus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import javax.crypto.NullCipher;

public class RecyclerShopActivity extends AppCompatActivity implements View.OnClickListener {

    public static ArrayList<String> lootNames = new ArrayList<>();
    public static ArrayList<Integer> lootPrices = new ArrayList<>();
    public static ArrayList<Integer> lootDeeds = new ArrayList<>();
    Button bback;
    static TextView amount, farmspeed;
    static TextView log;
    public static int num, fcost , fspeed , ccost , count ;

    static Integer p1=100,p2=500, p3=1000, p4=5000, p5=10000, p6=50000, p7=100001, p8=500002, p9=1000003;
    static Integer d1=1, d2=5,  d3=10,  d4=50,  d5=100,  d6=500,  d7=1000,  d8=5000,  d9=10000;
    int flag = 0;



    /////////////////////////////////////////////////////////////////////////////////////////////////////////// сохранение настроек///////////////////////

    public static final String recyclerShopFileName = "recyclerShopSettings";          //название файла настроек




    public static final String lootPricesSetting1 = "lootPricesSetting1";
    public static final String lootPricesSetting2 = "lootPricesSetting2";
    public static final String lootPricesSetting3 = "lootPricesSetting3";
    public static final String lootPricesSetting4 = "lootPricesSetting4";
    public static final String lootPricesSetting5 = "lootPricesSetting5";
    public static final String lootPricesSetting6 = "lootPricesSetting6";
    public static final String lootPricesSetting7 = "lootPricesSetting7";
    public static final String lootPricesSetting8 = "lootPricesSetting8";
    public static final String lootPricesSetting9 = "lootPricesSetting9";




    public static SharedPreferences mrSettings;

    /////////////////////////////////////////////////////////////////////////////////////////////////////////// сохранение настроек///////////////////////



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_shop);


        bback = findViewById(R.id.back);
        amount = findViewById(R.id.cookies_amount);
        farmspeed = findViewById(R.id.cookies_farmspeed);
        log = findViewById(R.id.log);

        bback.setOnClickListener(this);


        //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
        num = getIntent().getIntExtra("num", -1 ) ;
        count = getIntent().getIntExtra("count", -1 ) ;
        ccost = getIntent().getIntExtra("ccost", -1 ) ;
        fspeed = getIntent().getIntExtra("fspeed", -1 ) ;
        fcost = getIntent().getIntExtra("fcost", -1 ) ;


        mrSettings = getSharedPreferences(recyclerShopFileName,Context.MODE_PRIVATE);




        initLoot();
        setUI();

    }

    public static void setUI(){
        if (num!=1) {
            amount.setText(num + " cookies");
        }
        else{
            amount.setText(num + " cookie");
        }
        farmspeed.setText(fspeed+"cokies/sec");
        //log.setText(lootPrices.get(7));
    }


    protected void onStart() {
        super.onStart();
        getMrSettings();
        initRecyclerView();

    }


    protected void onPause() {
        super.onPause();
        putMrSettings();

    }


    protected void onStop() {
        super.onStop();
        putMrSettings();
    }


    protected void onResume() {
        super.onResume();

            getMrSettings();

    }


    public static void putMrSettings(){
        SharedPreferences.Editor editor = mrSettings.edit();
        editor.putInt(lootPricesSetting1,lootPrices.get(0));
        editor.putInt(lootPricesSetting2,lootPrices.get(1));
        editor.putInt(lootPricesSetting3,lootPrices.get(2));
        editor.putInt(lootPricesSetting4,lootPrices.get(3));
        editor.putInt(lootPricesSetting5,lootPrices.get(4));
        editor.putInt(lootPricesSetting6,lootPrices.get(5));
        editor.putInt(lootPricesSetting7,lootPrices.get(6));
        editor.putInt(lootPricesSetting8,lootPrices.get(7));
        editor.putInt(lootPricesSetting9,lootPrices.get(8));
        editor.apply();
        //log.setText("mrSettings.getInt(lootPricesSetting1,-47): "+mrSettings.getInt(lootPricesSetting1,-47));
    }



    public static void getMrSettings(){

            lootPrices.set(0, mrSettings.getInt(lootPricesSetting1, p1));
            lootPrices.set(1, mrSettings.getInt(lootPricesSetting2, p2));
            lootPrices.set(2, mrSettings.getInt(lootPricesSetting3, p3));
            lootPrices.set(3, mrSettings.getInt(lootPricesSetting4, p4));
            lootPrices.set(4, mrSettings.getInt(lootPricesSetting5, p5));
            lootPrices.set(5, mrSettings.getInt(lootPricesSetting6, p6));
            lootPrices.set(6, mrSettings.getInt(lootPricesSetting7, p7));
            lootPrices.set(7, mrSettings.getInt(lootPricesSetting7, p8));
            lootPrices.set(8, mrSettings.getInt(lootPricesSetting7, p9));


    }




    private void initLoot(){
        lootNames.add("Grandma: 1");
        lootNames.add("Tractor: 2");
        lootNames.add("Farm: 3");
        lootNames.add("Fields: 4");
        lootNames.add("Grandma: 5");
        lootNames.add("Grandma: 6");
        lootNames.add("Grandma: 7");
        lootNames.add("Grandma: 8");
        lootNames.add("Grandma: 9");


        if (lootPrices.isEmpty()) {
            lootPrices.add(p1);
            lootPrices.add(p2);
            lootPrices.add(p3);
            lootPrices.add(p4);
            lootPrices.add(p5);
            lootPrices.add(p6);
            lootPrices.add(p7);
            lootPrices.add(p8);
            lootPrices.add(p9);


            lootDeeds.add(d1);
            lootDeeds.add(d2);
            lootDeeds.add(d3);
            lootDeeds.add(d4);
            lootDeeds.add(d5);
            lootDeeds.add(d6);
            lootDeeds.add(d7);
            lootDeeds.add(d8);
            lootDeeds.add(d9);
            //putMrSettings();
        }
        else {
            getMrSettings();
        }



    }

    private void initRecyclerView(){
        getMrSettings();
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        RecyclerShopAdapter adapter = new RecyclerShopAdapter(lootNames,lootPrices,lootDeeds,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                Intent f = new Intent();
                //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
                f.putExtra("num", num) ;
                f.putExtra("count", count) ;
                f.putExtra("ccost", ccost) ;
                f.putExtra("fspeed", fspeed) ;
                f.putExtra("fcost", fcost) ;

                setResult(RESULT_OK,f);
                finish();
                break;
        }
    }
}
