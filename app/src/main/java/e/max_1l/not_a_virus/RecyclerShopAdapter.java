package e.max_1l.not_a_virus;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerShopAdapter extends RecyclerView.Adapter<RecyclerShopAdapter.ViewHolder> {


    private static ArrayList<String> lootNames;
    private static ArrayList<Integer> lootPrices;
    private static ArrayList<Integer> lootDeeds;
    private Context context;
    private int number;


    public RecyclerShopAdapter(ArrayList<String> lootNames, ArrayList<Integer> lootPrices, ArrayList<Integer> lootDeeds, Context context) {
        this.lootNames = lootNames;
        this.lootPrices = lootPrices;
        this.lootDeeds = lootDeeds;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclershop_file,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

        viewHolder.lootName.setText(lootNames.get(i));
        viewHolder.tvLootPrice.setText(lootPrices.get(i)+" cookies");
        if (i==0) {
            viewHolder.tvLootDeed.setText(lootDeeds.get(i) + " cookie/sec");
        }else {
            viewHolder.tvLootDeed.setText(lootDeeds.get(i) + " cookies/sec");
        }
        viewHolder.bjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (RecyclerShopActivity.num>=lootPrices.get(i)) {

                    RecyclerShopActivity.num -=lootPrices.get(i);
                    RecyclerShopActivity.fspeed +=lootDeeds.get(i);

                    RecyclerShopAdapter.lootPrices.set(i, (RecyclerShopAdapter.lootPrices.get(i)) * 3 / 2);
                    RecyclerShopActivity.lootPrices.set(i, RecyclerShopAdapter.lootPrices.get(i));
                    viewHolder.tvLootPrice.setText(lootPrices.get(i) + " cookies");
                    RecyclerShopActivity.setUI();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return lootPrices.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder{
        Button bjoin;
        TextView lootName,tvLootPrice,tvLootDeed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bjoin = itemView.findViewById(R.id.join_button);
            lootName = itemView.findViewById(R.id.loot_name);
            tvLootPrice = itemView.findViewById(R.id.loot_price);
            tvLootDeed = itemView.findViewById(R.id.loot_deed);
        }
    }

}
