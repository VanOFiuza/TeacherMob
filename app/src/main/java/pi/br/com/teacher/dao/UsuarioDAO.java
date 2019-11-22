package pi.br.com.teacher.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import pi.br.com.teacher.model.UsuarioLogin;


public class UsuarioDAO extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public UsuarioDAO(Context context) {
        super(context, "teacher", null, 7);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Usuario (id INTEGER PRIMARY KEY , email TEXT NOT NULL, senha TEXT NOT NULL, tipo TEXT NOT NULL);";
        db.execSQL(sql);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Usuario;";
        db.execSQL(sql);
        onCreate(db);

    }


    public UsuarioLogin validarLogin() {
        String sql = "SELECT email, senha, tipo FROM Usuario where id = '" + 1 + "';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        if (c.moveToFirst()) {

            UsuarioLogin user = new UsuarioLogin();
            user.setUsuario(c.getString(0));
            user.setSenha(c.getString(1));
            user.setTipo(c.getString(2));
            return user;
        } else {
            return null;

        }

    }

    public void inserir(UsuarioLogin usuarioRest) {

        db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("id", 1);
        dados.put("email", usuarioRest.getUsuario());
        dados.put("senha", usuarioRest.getSenha());
        dados.put("tipo",  usuarioRest.getTipo());
        db.insert("Usuario", null, dados);

    }

    public void alterar(UsuarioLogin usuarioRest) {

        SQLiteDatabase db = getReadableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("email", usuarioRest.getUsuario());
        dados.put("senha", usuarioRest.getSenha());
        dados.put("tipo",usuarioRest.getTipo());
        db.update("Usuario", dados, "id=" + 1, null);


    }

    public void deletar() {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("Usuario", "id= 1", null);

    }

}
