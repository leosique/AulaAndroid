package Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;

import Model.AlunoModel;
import Uteis.DatabaseUtil;

public class AlunoRepo{
    DatabaseUtil databaseUtils;
    public AlunoRepo(Context context){
        databaseUtils = new DatabaseUtil(context);
    }
    public void salvar(AlunoModel alunoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", alunoModel.getNome());
        contentValues.put("matricula", alunoModel.getMatricula());
        contentValues.put("horas",alunoModel.getHoras());
        databaseUtils.getConexaoDatabase().insert( "ALUNO",null, contentValues);
    }
    public List<AlunoModel> alunoModelList(){
        ArrayList palavras = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM ALUNO ORDER BY NOME");
        Cursor cursor = databaseUtils.getConexaoDatabase().rawQuery(stringBuilder.toString(),null);
        palavras.add(stringBuilder.toString());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            AlunoModel alunoModel = new AlunoModel();
            alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
            alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            alunoModel.setHoras(cursor.getString(cursor.getColumnIndex("horas")));
            alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));
            palavras.add(alunoModel);
            cursor.moveToNext();
        }
        return palavras;
    }
    public Integer Excluir(int codigo){
        return databaseUtils.getConexaoDatabase().delete("ALUNO","id = ?",new String[]{
                Integer.toString(codigo)
        } );
    }
    public AlunoModel getAluno(int num){
        Cursor cursor = databaseUtils.getConexaoDatabase().rawQuery(
                "SELECT * FROM ALUNO WHERE id = "+num,null
        );
        cursor.moveToFirst();
        AlunoModel alunoModel = new AlunoModel();
        alunoModel.setId(cursor.getInt(cursor.getColumnIndex("id")));
        alunoModel.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        alunoModel.setMatricula(cursor.getString(cursor.getColumnIndex("matricula")));
        alunoModel.setHoras(cursor.getString(cursor.getColumnIndex("horas")));
        return alunoModel;
    }
    public void Atualizar(AlunoModel alunoModel){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",alunoModel.getNome());
        contentValues.put("horas",alunoModel.getHoras());
        contentValues.put("matricula",alunoModel.getMatricula());
        databaseUtils.getConexaoDatabase().update("ALUNO",contentValues,"id = ?",new String[]{
                Integer.toString(alunoModel.getId())
        });
    }
}
