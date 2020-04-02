package com.snowballs.gloryroad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SoldierAdapter extends RecyclerView.Adapter<SoldierAdapter.SingleViewHolder> {

    private List<SoldierDto> soldierList;
    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = 0;

    public SoldierAdapter(List<SoldierDto> soldierList) {
        this.soldierList = soldierList;
    }

    public void setSoldierList(List<SoldierDto> soldierList) {
        this.soldierList = new ArrayList<>();
        this.soldierList = soldierList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.soldier, viewGroup, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(soldierList.get(position));
    }

    @Override
    public int getItemCount() {
        return soldierList.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvPlace;
        private ImageView okImage;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPlace = itemView.findViewById(R.id.tvPlace);
            okImage = itemView.findViewById(R.id.imageView);
        }

        void bind(final SoldierDto soldier) {
            if (checkedPosition == -1) {
                okImage.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    okImage.setVisibility(View.VISIBLE);
                } else {
                    okImage.setVisibility(View.GONE);
                }
            }
            tvName.setText(soldier.getName());
            tvPlace.setText(soldier.getBirthPlace());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    okImage.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }

    public SoldierDto getSelected() {
        if (checkedPosition != -1) {
            return soldierList.get(checkedPosition);
        }
        return null;
    }
}