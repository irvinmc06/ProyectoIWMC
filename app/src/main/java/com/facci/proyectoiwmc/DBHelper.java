package com.facci.proyectoiwmc;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NOMBRE= "CNE_IWMC";
    public static final String TABLA_NOMBRE ="VOTANTES_IWMC";

    public static final String COl_1="ID_IWMC";
    public static final String COL_2="Nombre_IWMC";
    public static final String COL_3="Apellido_IWMC";
    public static final String COL_4="RecintoElectoral_IWMC";
    public static final String COL_5="AñoNacimiento_IWMC";

    public DBHelper(Context context) {
        super(context, DB_NOMBRE,null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(String.format("create table %s (ID INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT, %s TEXT,%s INTEGER)", TABLA_NOMBRE, COL_2, COL_3, COL_4, COL_5));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(String.format("DROP TABLE IF EXISTS %s",TABLA_NOMBRE));
        onCreate(db);

    }
    public  boolean Insertar (String Nombre, String Apellido,String RecintoElectoral,int AñoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Nombre);
        contentValues.put(COL_3,Apellido);
        contentValues.put(COL_4,RecintoElectoral);
        contentValues.put(COL_5, AñoNacimiento);
        long Resultado = db.insert(TABLA_NOMBRE, null, contentValues);

        if (Resultado == -1){
            return false;
        }
        else {
            return true;
        }

    }
    public Cursor VerTodos () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(String.format("select * from %s",TABLA_NOMBRE),null);
        return res;
    }
    public Integer Eliminar (String Id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLA_NOMBRE,"Id = ?",new String[]{Id});
    }

    public boolean Modificar (String Id,String Nombre,String Apellido,String RecintoElectoral,int AñoNacimiento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,Nombre);
        contentValues.put(COL_3,Apellido);
        contentValues.put(COL_4,RecintoElectoral);
        contentValues.put(COL_5,AñoNacimiento);
        db.update(TABLA_NOMBRE,contentValues,"Id = ?",new String[]{Id});
        return true;
    }
}
