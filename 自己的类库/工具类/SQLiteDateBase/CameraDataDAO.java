package com.chinaautoid.minehandsetmanagersystem.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * ���ݷ��ʶ��� �����˶����еķ��� ����SQLite���ݿ�
 */
public class CameraDataDAO {

	private NotepadDBHelper dbHelper;

	public CameraDataDAO(Context context) {
		dbHelper = new NotepadDBHelper(context, "mimesys.db", null, 1);
	}

	private static final String TABLE = "mimetab";

	//����
	public long insert(ContentValues values) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		long id = sdb.insert(TABLE, null, values);
		sdb.close();
		return id;
	}

	//��ѯ
	public Cursor query() {
		String sql = "select * from mimetab " + "order by _created desc";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return db.rawQuery(sql, null);
	}

	//ͨ��ID��ѯ
	public Cursor queryById(int id) {
		String sql = "select * from mimetab " + "where _id=?";
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		return db.rawQuery(sql, new String[] { String.valueOf(id) });
	}

	// ����idɾ��ָ����¼
	public void delete(String id) {
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		sdb.delete(TABLE, "_id=?", new String[] { id });
		sdb.close();
	}

	// ����id���¼�¼
	public void update(ContentValues values, String whereClause,
			String[] whereArgs) {
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
			String sql = "create table if not exists mimetab( _id integer primary key autoincrement, _content text not null, _created text not null,"
					+ "_photoId text not null)";
			db.execSQL(sql);
			Log.i("TAG", "table create ok");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			String sql = "drop table if exists mimetab";
			db.execSQL(sql);
			onCreate(db);
		}
	}

}
