
package com.example.firebasecrud;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VoitureAdapter extends RecyclerView.Adapter<ViewHolder>{


    private final List<Voiture> mVoitureList;

    public VoitureAdapter(List<Voiture> VoitureList) {
        mVoitureList = VoitureList;
    }

    @Override
    public void onBindViewHolder(com.example.firebasecrud.ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @NonNull
    @Override
    public com.example.firebasecrud.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.character_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mVoitureList != null & mVoitureList.size() > 0) {
            return mVoitureList.size();
        } else {
            return 0;
        }
    }

    public void addItems(List<Voiture> VoitureList) {
        mVoitureList.addAll(VoitureList);
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mVoitureList != null & mVoitureList.size() > 0) {
            mVoitureList.remove(position);
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends com.example.firebasecrud.ViewHolder {

        @BindView(R.id.characterImageView)
        ImageView mCharacterImageView;

        @BindView(R.id.nameTextView)
        TextView mNameTextView;

        @BindView(R.id.animeTextView)
        TextView mAnimeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            mCharacterImageView.setImageDrawable(null);
            mNameTextView.setText("");
            mAnimeTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            Voiture mVoiture= mVoitureList.get(position);

            int[] marquesImages= {R.drawable.ford,R.drawable.peugeot,R.drawable.renault,R.drawable.bmw,R.drawable.mercedes};

            if (mVoiture.getUrl() < 10) {
                mCharacterImageView.setImageResource(marquesImages[mVoiture.getUrl()]);
            }

            if (mVoiture.getName() != null) {
                mNameTextView.setText(mVoiture.getName());
            }

            if (mVoiture.getCouleur() != null) {
                mAnimeTextView.setText(mVoiture.getCouleur());
            }

            itemView.setOnClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("key",  mVoiture.getKey());
                itemView.getContext().startActivity(intent);
            });

            itemView.setOnLongClickListener(v -> {
                Intent intent=new Intent(itemView.getContext(), EditActivity.class);
                intent.putExtra("key",  mVoiture.getKey());
                itemView.getContext().startActivity(intent);
                return false;
            });

        }
    }

}