package e.max_1l.not_a_virus;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LastRecyclerAdapter extends RecyclerView.Adapter<LastRecyclerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Agent> agents;


    public LastRecyclerAdapter(Context context, ArrayList<Agent> agents) {
        this.agents = agents;
        this.inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.agent_layout,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Agent agent = agents.get(i);
        viewHolder.upgrade.setText(String.valueOf(agent.upgrade)+ " cookies/sec");
        viewHolder.price.setText(String.valueOf(agent.price) + " cookies");
        viewHolder.name.setText(String.valueOf(agent.name));
        viewHolder.buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LastShopActivity.buyAnUpgrade(agent.price,agent.upgrade)) {
                    agent.price = agent.price * 13 / 10;
                    viewHolder.price.setText(String.valueOf(agent.price) + " cookies");
                    agents.get(i).price = agent.price;
                    LastShopActivity.playBuySound();
                }
            }
        });
    }

    public ArrayList<Agent> getAgents(){
        Log.d("dota","first price agent from adapter: "+ agents.get(0).price+"   size:"+ agents.size());
        return agents;

    }

    @Override
    public int getItemCount() {
        return agents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,price,upgrade;
        public Button buy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text);
            price = itemView.findViewById(R.id.price_text);
            upgrade = itemView.findViewById(R.id.upgrade_text);
            buy = itemView.findViewById(R.id.buy_button);
        }
    }
}
