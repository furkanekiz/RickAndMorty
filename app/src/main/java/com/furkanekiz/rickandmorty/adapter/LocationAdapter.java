package com.furkanekiz.rickandmorty.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.furkanekiz.rickandmorty.LocationItemClickListener;
import com.furkanekiz.rickandmorty.R;
import com.furkanekiz.rickandmorty.databinding.LocationCardBinding;

import com.furkanekiz.rickandmorty.model.location.LocationResult;
import com.furkanekiz.rickandmorty.view.HomeActivty;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    List<LocationResult> locationResultList;
    int itemLayoutId;
    LocationItemClickListener locationItemClickListener;
    private int selected_position = -1;

    public LocationAdapter(List<LocationResult> locationResultList, int itemLayoutId, LocationItemClickListener locationItemClickListener) {
        this.locationResultList = locationResultList;
        this.itemLayoutId = itemLayoutId;
        this.locationItemClickListener = locationItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LocationCardBinding locationCardBinding = LocationCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(locationCardBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LocationResult item = locationResultList.get(position);
        holder.locationCardBinding.txtLocationName.setText(item.getName());
        holder.locationCardBinding.txtLocationType.setText(item.getType());
        holder.bindData(item, position);

    }

    @Override
    public int getItemCount() {
        return locationResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LocationCardBinding locationCardBinding;

        public ViewHolder(@NonNull LocationCardBinding locationCardBinding) {
            super(locationCardBinding.getRoot());
            this.locationCardBinding=locationCardBinding;

        }


        public void bindData(LocationResult item, int pos) {

            itemView.setOnClickListener(view -> {
                locationItemClickListener.onClick(item);
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    selected_position = position;
                    notifyDataSetChanged();
                }
            });
            if (selected_position == pos) {
                itemView.setBackground(ContextCompat.getDrawable(HomeActivty.context, R.drawable.btn_selected));
            } else {
                itemView.setBackground(ContextCompat.getDrawable(HomeActivty.context, R.drawable.btn_unselected));
            }
        }
    }

}
