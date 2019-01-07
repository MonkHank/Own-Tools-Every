package com.chinaautoid.pianzaihuang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

/**
 * 数据访问对象 借助此对象中的方法 访问SQLite数据库
 */
public class ChukuDAO {

	private static final String TABLE = "chukutab";
	private NotepadDBHelper dbHelper;
	
	public ChukuDAO(Context context) {
		String path = Environment.getExternalStorageDirectory().getPath() + "/pianzaihuang/";
		dbHelper = new NotepadDBHelper(context, path + "chuku.db", null, 1);
	}

	//插入 (增)
	public long insert(ContentValues values) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		long id = sdb.insert(TABLE, null, values);
		sdb.close();
		return id;
	}

	//查询 (查)
	public Cursor query() {
		String sql = "select * from "+TABLE;
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return db.rawQuery(sql, null);
	}

	//通过 billNumber 查询 (查)
	public Cursor queryByBillNumber(String billNumber) {
		String sql = "select * from " + TABLE  + " where _billnumber=?";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return db.rawQuery(sql, new String[] { billNumber });
	}

	// 根据 barcode 删除指定记录  (删)
	public void delete(String barcode) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		sdb.delete(TABLE, "_barcode=?", new String[] { barcode });
		sdb.close();
	}
	
	// 根据 billNumber 删除指定记录  (删)
	public void deleteByBillnumber(String billNumber) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		sdb.delete(TABLE, "_billnumber=?", new String[] { billNumber });
		sdb.close();
	}

	/**
	 * 删除整张表
	 */
	public void deleteTable(){
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		sdb.delete(TABLE, null, null);
		sdb.close();
	}

	// 根据id更新记录  (改)
	public void update(ContentValues values, String whereClause,String[] whereArgs) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		sdb.update(TABLE, values, whereClause, whereArgs);
		sdb.close();
	}

	class NotepadDBHelper extends SQLiteOpenHelper {
		public NotepadDBHelper(Context context, String name,CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			String sql = "create table if not exists " + TABLE +"( " +
						"_id integer primary key autoincrement, " +
						"_barcode text not null, " +
						"_billnumber text not null,"+ 
						"_actdate text not null," +
						"_corpID text not null," +
						"_actor text not null)";
			db.execSQL(sql);
			Log.i("TAG", "table create ok");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "drop table if exists TABLE";
			db.execSQL(sql);
			onCreate(db);
		}
	}
}
