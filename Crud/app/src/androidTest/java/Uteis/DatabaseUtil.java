package Uteis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;

public class DatabaseUtil extends SQLiteOpenHelper {
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder stringBuilderAluno = new StringBuilder();
        stringBuilderAluno.append("CREATE TABLE ALUNO(");
        stringBuilderAluno.append("ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        stringBuilderAluno.append("NOME TEXT NOT NULL,");
        stringBuilderAluno.append("MATRICULA TEXT NOT NULL,");
        stringBuilderAluno.append("HORAS TEXT NOT NULL)");
        sqLiteDatabase.execSQL(stringBuilderAluno.toString());
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ALUNO");
        onCreate(sqLiteDatabase);
    }
    public SQLiteDatabase getConexaoDatabase(){
        return this.getWritableDatabase();
    }
    private static final String NOME_BASE_DE_DADOS = "TESTECRUD.db";
    private static final int VERSAO_BASE_DE_DADOS = 1;

    public DatabaseUtil(Context context){
        super(context,NOME_BASE_DE_DADOS,null,VERSAO_BASE_DE_DADOS);
    }
}
