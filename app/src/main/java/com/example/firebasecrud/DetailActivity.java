package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.characterImageView)
    ImageView mVoitureImageView;

    @BindView(R.id.backgroundImageView)
    ImageView mBackgroundImageView;

    @BindView(R.id.nameTextView)
    TextView mNameTextView;

    @BindView(R.id.descriptionTextView)
    TextView mDescriptionTextView;

    @BindView(R.id.couleur)
    TextView couleur;

    @BindView(R.id.prix)
    TextView prix;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("voiture").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Voiture voiture = dataSnapshot.getValue(Voiture.class);

                if (voiture.getName() != null) {
                    mNameTextView.setText(voiture.getName());
                }

                if (voiture.getDescription() != null) {
                    mDescriptionTextView.setText(voiture.getDescription());
                }
                if (voiture.getCouleur() != null) {
                    prix.setText(voiture.getCouleur());
                }
               prix.setText(String.valueOf(voiture.getPrix()));
                int[] marquesImages= {R.drawable.ford,R.drawable.peugeot,R.drawable.renault,R.drawable.bmw,R.drawable.mercedes};
                int[] backgroundsImages= {R.drawable.fordback,R.drawable.peugeotback,R.drawable.renaultback,R.drawable.bmwback,R.drawable.mercedesback};
                if (voiture.getUrl() < 10) {
                    mVoitureImageView.setImageResource(marquesImages[voiture.getUrl()]);
                }

                if (voiture.getUrl() < 10) {
                    mBackgroundImageView.setImageResource(backgroundsImages[voiture.getUrl()]);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(DetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}