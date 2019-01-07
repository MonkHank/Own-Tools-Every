package com.chainaautoid.kaohe.engine;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据访问对象 借助此对象中的方法 访问SQLite数据库
 */
public class RukuDataDAO {

	private NotepadDBHelper dbHelper;

	public RukuDataDAO(Context context) {
		dbHelper = new NotepadDBHelper(context, "notepad.db", null,1);
	}

	private static final String TABLE = "notetab";

	public long insert(ContentValues values) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		long id = sdb.insert(TABLE, null, values);
		sdb.close();
		return id;
	}

	public Cursor query() {
		String sql = "select * from notetab " + "order by _created desc";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return db.rawQuery(sql, null);
	}

	public Cursor queryById(int id) {
		String sql = "select * from notetab " + "where _id=?";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return db.rawQuery(sql, new String[] { String.valueOf(id) });
	}

	// 根据id删除指定记录
	public void delete(String id) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		sdb.delete(TABLE, "_id=?", new String[] { id });
		sdb.close();
	}

	// 根据id更新记录
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
			String sql = "create table if not exists notetab( _id integer primary key autoincrement, _content text not null, _created text not null)";
			db.execSQL(sql);
			Log.i("TAG", "table create ok");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "drop table if exists notetab";
			db.execSQL(sql);
			onCreate(db);
		}
		
	}

}
