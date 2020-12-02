package com.ambinusian.adab.room;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ClassDatabase_Impl extends ClassDatabase {
  private volatile ClassDAO _classDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ClassEntity` (`sessionId` INTEGER NOT NULL, `courseId` TEXT, `courseName` TEXT, `sessionTh` INTEGER NOT NULL, `sessionMode` TEXT, `className` TEXT, `topicTitle` TEXT, `topicDescription` TEXT, `sessionCampus` TEXT, `sessionRoom` TEXT, `lecturerId` TEXT, `lecturerName` TEXT, `sessionStartDate` TEXT, `sessionEndDate` TEXT, PRIMARY KEY(`sessionId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '5f89aca40f1373bb57167c1effcf24c2')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `ClassEntity`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsClassEntity = new HashMap<String, TableInfo.Column>(14);
        _columnsClassEntity.put("sessionId", new TableInfo.Column("sessionId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("courseId", new TableInfo.Column("courseId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("courseName", new TableInfo.Column("courseName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("sessionTh", new TableInfo.Column("sessionTh", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("sessionMode", new TableInfo.Column("sessionMode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("className", new TableInfo.Column("className", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("topicTitle", new TableInfo.Column("topicTitle", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("topicDescription", new TableInfo.Column("topicDescription", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("sessionCampus", new TableInfo.Column("sessionCampus", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("sessionRoom", new TableInfo.Column("sessionRoom", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("lecturerId", new TableInfo.Column("lecturerId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("lecturerName", new TableInfo.Column("lecturerName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("sessionStartDate", new TableInfo.Column("sessionStartDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsClassEntity.put("sessionEndDate", new TableInfo.Column("sessionEndDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysClassEntity = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesClassEntity = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoClassEntity = new TableInfo("ClassEntity", _columnsClassEntity, _foreignKeysClassEntity, _indicesClassEntity);
        final TableInfo _existingClassEntity = TableInfo.read(_db, "ClassEntity");
        if (! _infoClassEntity.equals(_existingClassEntity)) {
          return new RoomOpenHelper.ValidationResult(false, "ClassEntity(com.ambinusian.adab.room.ClassEntity).\n"
                  + " Expected:\n" + _infoClassEntity + "\n"
                  + " Found:\n" + _existingClassEntity);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "5f89aca40f1373bb57167c1effcf24c2", "796646dc98267232ac42493647c7b4af");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "ClassEntity");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `ClassEntity`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public ClassDAO classDAO() {
    if (_classDAO != null) {
      return _classDAO;
    } else {
      synchronized(this) {
        if(_classDAO == null) {
          _classDAO = new ClassDAO_Impl(this);
        }
        return _classDAO;
      }
    }
  }
}
