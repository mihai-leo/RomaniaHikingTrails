package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import logic.Trail;
import com.example.myapplication.R;
import com.example.myapplication.ViewTrail;
public class TrailAdapter extends RecyclerView.Adapter<TrailAdapter.TrailViewHolder> {
    private List<Trail> trails;
    private Context context;

    public TrailAdapter(List<Trail> trails,Context context) {
        this.trails = trails;
        this.context=context;
    }

    @NonNull
    @Override
    public TrailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trail, parent, false);
        return new TrailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailViewHolder holder, int position) {
        Trail trail = trails.get(position);
        holder.textViewTrailName.setText(trail.getName());
        holder.textViewTrailInfo.setText("Difficulty: " + trail.getDifficulty() + " | Distance: " + trail.getDistance() + " km");
        // Set image if dynamic images are used
        holder.buttonViewDetails.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewTrail.class);
            intent.putExtra("trail", trail); // Pass the Trail object to the new activity
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return trails.size();
    }

    static class TrailViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTrailName, textViewTrailInfo;
        Button buttonViewDetails;
        public TrailViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailName = itemView.findViewById(R.id.textViewTrailName);
            textViewTrailInfo = itemView.findViewById(R.id.textViewTrailInfo);
            buttonViewDetails = itemView.findViewById(R.id.buttonViewDetails);
        }
    }
}
