package com.example.projetoquartaessevai;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AddContatoo extends AppCompatActivity {

    Context context;
    BDh bdHandler;

    EditText et_name, et_number, et_email, et_organization;
    Button relationship, add;

    String relationshipString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contatoo);

        context = this;
        bdHandler = new BDh(context);

        et_name = findViewById(R.id.nome);
        et_number = findViewById(R.id.numero);
        et_email = findViewById(R.id.email);
        et_organization = findViewById(R.id.empresa);

        relationship = findViewById(R.id.relacionamento);
        add = findViewById(R.id.adicionar);

        relationshipString = "Unspecified";

        // Adiciona o listener de clique ao botão 'relationship'
        relationship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence options[] = {"Business", "Friend", "Acquaintance", "Other"};
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose Relationship Type")
                        .setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    relationshipString = "Business";
                                } else if (i == 1) {
                                    relationshipString = "Friend";
                                } else if (i == 2) {
                                    relationshipString = "Acquaintance";
                                } else if (i == 3) {
                                    relationshipString = "Other";
                                }
                            }
                        })
                        .show();
            }
        });

        // Adiciona o listener de clique ao botão 'add'
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = et_name.getText().toString();
                String numero = et_number.getText().toString();
                String email = et_email.getText().toString();
                String empresa = et_organization.getText().toString();

                if (!TextUtils.isEmpty(nome) && !TextUtils.isEmpty(numero) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(empresa)) {
                    Contato contato = new Contato(nome, numero, email, empresa, relationshipString);
                    bdHandler.addContato(contato);
                    startActivity(new Intent(context, MainActivity.class));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Please fill all the fields")
                            .setNegativeButton("OK", null)
                            .show();
                }
            }
        });}}



