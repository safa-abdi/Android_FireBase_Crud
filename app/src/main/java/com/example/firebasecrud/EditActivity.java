package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @BindView(R.id.couleurEditText)
    EditText mAnimeEditText;

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.descriptionEditText)
    EditText mDescriptionEditText;

    @BindView(R.id.marqueEditText)
    EditText mmarqueEditText;

    @BindView(R.id.prixEditText)
    EditText mprixEditText;

    @BindView(R.id.characterButton)
    Button mCharacterButton;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        String mKey= Objects.requireNonNull(getIntent().getExtras()).getString("key");

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("voiture").child(mKey);

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Voiture voiture = dataSnapshot.getValue(Voiture.class);

                if (voiture.getName() != null) {
                    mNameEditText.setText(voiture.getName());
                }

                if (voiture.getCouleur()!= null) {
                    mAnimeEditText.setText(voiture.getCouleur());
                }

                if (voiture.getDescription() != null) {
                    mDescriptionEditText.setText(voiture.getDescription());
                }
                if (voiture.getMarque() != null) {
                    mmarqueEditText.setText(voiture.getMarque());
                }

                mprixEditText.setText(String.valueOf(voiture.getPrix()));
                mUrlEditText.setText(String.valueOf(voiture.getUrl()));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(EditActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mCharacterButton.setOnClickListener(v -> {
            mDatabaseReference.child("name").setValue(mNameEditText.getText().toString());
            mDatabaseReference.child("couleur").setValue(mAnimeEditText.getText().toString());
            mDatabaseReference.child("description").setValue(mDescriptionEditText.getText().toString());
            mDatabaseReference.child("marque").setValue(mmarqueEditText.getText().toString());
            mDatabaseReference.child("url").setValue(Integer.parseInt(mUrlEditText.getText().toString()));
            mDatabaseReference.child("prix").setValue(Integer.parseInt(mprixEditText.getText().toString()));
            finish();
        });
    }
}