package com.example.projetoquartaessevai;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class BDh extends SQLiteOpenHelper{

    private static final int VERSION = 1;

    private static final String BD_NOME ="ContatosBD";

    private static final String CONTATOS_TABLE ="contatos";

    private static final String ID= "id";

    private static final String NOME="nome";

    private static final String NUMERO ="numero";

    private static final String EMAIL = "email";

    private static final String EMPRESA ="empresa";

    private static final String RELACIONAMENTO="relacionamento";

    public BDh(@Nullable Context context) {
        super(context, BD_NOME, null , VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTATOS_TABLE = "CREATE TABLE " + CONTATOS_TABLE
                + "(" + ID + " INTEGER PRIMARY KEY,"
                + NOME + " TEXT,"
                + NUMERO + " TEXT,"
                + EMAIL + " TEXT,"
                + EMPRESA + " TEXT,"
                + RELACIONAMENTO + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CONTATOS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + CONTATOS_TABLE;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }


    public Contato addContato(Contato contato) {
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME,contato.getNome());
        values.put(NUMERO, contato.getNumero());
        values.put(EMAIL, contato.getEmail());
        values.put(EMPRESA, contato.getEmpresa());
        values.put(RELACIONAMENTO, contato.getRelacionamento());

        bd.insert(CONTATOS_TABLE, null, values);
        bd.close();
        return contato;
    }

    public Contato getContato(int id) {
        SQLiteDatabase bd = getReadableDatabase();

        Cursor cursor = bd.query(
                CONTATOS_TABLE,new String[]{ID,NOME,NUMERO,EMAIL,EMPRESA,RELACIONAMENTO},ID +"=?",
                new String[]{String.valueOf(id)},null,null,null,null
        );

        Contato contato = null;

        if (cursor != null){
            cursor.moveToFirst();
            contato = new Contato(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );
            return contato;
        }else {
            return null;
        }


    }

    public List<Contato> getTodosContatos(){
        SQLiteDatabase bd = getReadableDatabase();
        List<Contato> contatos = new ArrayList<>();

        String query = " SELECT * FROM " + CONTATOS_TABLE;

        Cursor cursor = bd.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                Contato contato = new Contato();

                contato.setId(Integer.parseInt(cursor.getString(0)));
                contato.setNome(cursor.getString(1));
                contato.setNumero(cursor.getString(2));
                contato.setEmail(cursor.getString(3));
                contato.setEmpresa(cursor.getString(4));
                contato.setRelacionamento(cursor.getString(5));

                contatos.add(contato);
            }
            while(cursor.moveToNext());
        }
        return contatos;
    }
    public int updateContato(Contato contato){
        SQLiteDatabase bd = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NOME,contato.getNome());
        values.put(NUMERO,contato.getNumero());
        values.put(EMAIL,contato.getEmail());
        values.put(EMPRESA,contato.getEmpresa());
        values.put(RELACIONAMENTO,contato.getRelacionamento());

        return bd.update(
                CONTATOS_TABLE,
                values,
                ID + " = ? ",
                new String[]{String.valueOf(contato.getId())}
        );

    }
    public void deleteContato(Contato contato){
        SQLiteDatabase bd = getWritableDatabase();
        bd.delete(
                CONTATOS_TABLE,
                ID+"=?",
                new String[]{String.valueOf(contato.getId())}
        );
        bd.close();


    }
    public int getContatoCount(){
        SQLiteDatabase bd = getReadableDatabase();
        String query = " SELECT * FROM " + CONTATOS_TABLE ;
        Cursor cursor = bd.rawQuery(query,null);
        return cursor.getCount();
    }
}