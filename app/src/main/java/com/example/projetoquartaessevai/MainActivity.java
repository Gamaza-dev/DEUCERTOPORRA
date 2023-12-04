package com.example.projetoquartaessevai;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Context context;
    private BDh bdh;
    private ListView ContatoLista;
    private EditText procuraTexto;
    private Button procurar, AdicionarContato;

    private List<Contato> contatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        bdh = new BDh(context);

        ContatoLista = findViewById(R.id.contato_lista);
        procuraTexto = findViewById(R.id.procurar_texto);
        procurar = findViewById(R.id.procurar);
        AdicionarContato = findViewById(R.id.adicionar);

        Log.d(TAG, "onCreate");

        contatos = new ArrayList<>();
        contatos = bdh.getTodosContatos();

        String[] nomesArray = new String[contatos.size()];
        for (int x = 0; x < contatos.size(); x++) {
            nomesArray[x] = contatos.get(x).getNome();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, nomesArray);
        ContatoLista.setAdapter(adapter);
        ContatoLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contato contato = contatos.get(i);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(contato.getNome())
                        .setMessage(contato.getNumero() + "\n" + contato.getEmail() + "\n" + contato.getEmpresa() + "\n" + contato.getRelacionamento())
                        .show();
            }
        });

        procurar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Adicione lógica de busca aqui, se necessário
                Log.d(TAG, "Procurar clicado");
            }
        });

        AdicionarContato.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, AddContatoo.class);
                startActivity(intent);
                Log.d(TAG, "AdicionarContato clicado");
            }
        });
    }
}
