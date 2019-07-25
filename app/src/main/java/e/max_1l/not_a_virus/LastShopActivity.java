package e.max_1l.not_a_virus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LastShopActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv;
    Button back;
    static TextView amount;
    static TextView farmspeed;
    LastRecyclerAdapter adapter;
    public static int num, fcost , fspeed , ccost , count ;
    //
    public static SharedPreferences lsSettings;
    public static final String settingsName = "settingsName";
    public static final String agentsListName = "agentsListName";
    public ArrayList<Integer> agentsPriceList = new ArrayList<>();
    public ArrayList<Agent> agents;
    public static SharedPreferences.Editor editor;
    //
    static SoundPool mSoundPool;
    int  soundId;
    static int confirmSoundId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("dota 35","onCreate started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_shop);

        rv = findViewById(R.id.view_lasthop_rv);
        back = findViewById(R.id.back_lastshop_button);
        amount = findViewById(R.id.amount_lastshop_text);
        farmspeed = findViewById(R.id.farmspeed_lastshop_text);

        back.setOnClickListener(this);
        back.setSoundEffectsEnabled(false);

        lsSettings = getSharedPreferences(settingsName,Context.MODE_PRIVATE);
        if(lsSettings.contains(agentsListName+"1")) {
            agents = makeAgents();
            for (int i = 0; i <agents.size() ; i++) {
                int j = i+1;
                agents.get(i).price = lsSettings.getInt(agentsListName+j,-5);
            }
            setResyclerAdapter(agents);
            Log.d("dota 54","ls contains and the first price is"+agents.get(0).price);
        }
        else {
            setResyclerAdapter(makeAgents());
            initlsSettings();
            Log.d("dota 59","ls made by a makeAgents()"+"   the first price from SETTINGS is"+lsSettings.getInt(agentsListName+"1",-47));
        }
        getIntentSettings();
        setUI();
        mSoundPool = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
        soundId = mSoundPool.load(this,R.raw.cookiesound,1);
        confirmSoundId = mSoundPool.load(this,R.raw.confirm,1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("dota 70","onStop started");
        refreshAgents();
        savelsSettings();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("dota 78","onPause started");
        refreshAgents();
        savelsSettings();
    }

    public static boolean buyAnUpgrade(int cost, int upgrade){
        Log.d("UIl1","mad started   "+num);
        if (num>=cost) {
            num -= cost;
            fspeed += upgrade;
            setUI();
            Log.d("UIl1","mad worked    "+num);
            return true;
        }
        return false;
    }
    public static void playBuySound(){
        mSoundPool.play(confirmSoundId,1,1,1,0,2);
    }

    public void refreshAgents(){
        agents = adapter.getAgents();
    }

    public void savelsSettings(){
        editor = lsSettings.edit();
        for (int i = 0; i <agents.size() ; i++) {
            int j = i+1;
            editor.putInt(agentsListName+j,agents.get(i).price);
        }
        Log.d("dota 93","savelsSettings worked   the first price from SETTINGS is"+lsSettings.getInt(agentsListName+"1",-47));
        editor.apply();
    }

    public void initlsSettings(){
        for (Agent i:agents) {
            agentsPriceList.add(i.price);
        }
        
         editor = lsSettings.edit();
        for (int i = 0; i <agentsPriceList.size() ; i++) {
            int j = i+1;
            editor.putInt(agentsListName+j,agentsPriceList.get(i));
        }
        editor.apply();
    }

    public static void clearlsSettings(){
        editor = lsSettings.edit();
        //editor.clear();
        editor.apply();
    }

    private void setResyclerAdapter(ArrayList<Agent> a){
        agents = a;
        adapter = new LastRecyclerAdapter(this, agents);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    private void getIntentSettings(){
        num = getIntent().getIntExtra("num", -1 ) ;
        count = getIntent().getIntExtra("count", -1 ) ;
        ccost = getIntent().getIntExtra("ccost", -1 ) ;
        fspeed = getIntent().getIntExtra("fspeed", -1 ) ;
        fcost = getIntent().getIntExtra("fcost", -1 ) ;
    }
    private static void setUI(){
        if (num!=1) {
            amount.setText(num + " cookies");
        }
        else{
            amount.setText(num + " cookie");
        }
        farmspeed.setText(fspeed+"cokies/sec");
    }

    public ArrayList<Agent> makeAgents(){
        ArrayList<Agent> agents = new ArrayList<>();
        Agent agent;
        String[] names = {"one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve","thirteen","fourteen","fifteen"};
        int[] prices = {100,200,300,400,500,600,700,800,900,1000,1100,1200,1300,1400,1500};
        int[] upgrades = {2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
        for (int i = 0; i <names.length ; i++) {
            agent = new Agent(names[i],upgrades[i],prices[i]);
            agents.add(agent);
        }
        Log.d("Completed",agents.toString());
        return agents;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_lastshop_button:
                Intent f = new Intent();
                //int num = 0,count = 1, ccost = 10,fspeed = 0, fcost = 10 ;
                f.putExtra("num", num) ;
                f.putExtra("count", count) ;
                f.putExtra("ccost", ccost) ;
                f.putExtra("fspeed", fspeed) ;
                f.putExtra("fcost", fcost) ;
                mSoundPool.play(confirmSoundId,1,1,1,0,2);
                setResult(RESULT_OK,f);
                finish();
                break;

        }

    }
}
