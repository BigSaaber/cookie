package e.max_1l.not_a_virus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class LastShopActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_shop);

        rv = findViewById(R.id.lrv);

        LastRecyclerAdapter adapter = new LastRecyclerAdapter(this,makeAgents());
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));


    }

    public ArrayList<Agent> makeAgents(){
        ArrayList<Agent> agents = new ArrayList<>();
        Agent agent;
        String[] names = {"First","Second","Third"};
        int[] prices = {100,200,300};
        int[] upgrades = {2,4,6};
        for (int i = 0; i <names.length ; i++) {
            agent = new Agent(names[i],upgrades[i],prices[i]);
            agents.add(agent);
        }
        Log.d("Completed",agents.toString());
        return agents;
    }


    @Override
    public void onClick(View v) {

    }
}
