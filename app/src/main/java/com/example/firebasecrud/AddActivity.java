package com.example.firebasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.nameEditText)
    EditText mNameEditText;

    @BindView(R.id.couleurEditText)
    EditText mcouleurEditText;

    @BindView(R.id.urlEditText)
    EditText mUrlEditText;

    @BindView(R.id.descriptionEditText)
    EditText mDescriptionEditText;

    @BindView(R.id.marqueEditText)
    EditText marqueEditText ;

    @BindView(R.id.prixEditText)
    EditText prixEditText ;

    @BindView(R.id.characterButton)
    Button mCharacterButton;


    private DatabaseReference mDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ButterKnife.bind(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("voiture");

        mCharacterButton.setOnClickListener(v -> {
            String name = mNameEditText.getText().toString();
            String image = mcouleurEditText.getText().toString();
            String description = mDescriptionEditText.getText().toString();
            String marque = marqueEditText.getText().toString();
            int url = Integer.parseInt(mUrlEditText.getText().toString());
            int prix = Integer.parseInt(prixEditText.getText().toString());


            Voiture myV = new Voiture(name, image, description,marque, url,prix);
            String id = mDatabaseReference.push().getKey();
            if (id != null) { mDatabaseReference.child(id).setValue(myV); }

            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }
}