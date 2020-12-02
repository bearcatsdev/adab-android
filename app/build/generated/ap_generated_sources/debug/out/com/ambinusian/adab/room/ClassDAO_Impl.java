package com.ambinusian.adab.room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ClassDAO_Impl implements ClassDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ClassEntity> __insertionAdapterOfClassEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ClassDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfClassEntity = new EntityInsertionAdapter<ClassEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `ClassEntity` (`sessionId`,`courseId`,`courseName`,`sessionTh`,`sessionMode`,`className`,`topicTitle`,`topicDescription`,`sessionCampus`,`sessionRoom`,`lecturerId`,`lecturerName`,`sessionStartDate`,`sessionEndDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ClassEntity value) {
        stmt.bindLong(1, value.getSessionId());
        if (value.getCourseId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getCourseId());
        }
        if (value.getCourseName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCourseName());
        }
        stmt.bindLong(4, value.getSessionTh());
        if (value.getSessionMode() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getSessionMode());
        }
        if (value.getClassName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getClassName());
        }
        if (value.getTopicTitle() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getTopicTitle());
        }
        if (value.getTopicDescription() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getTopicDescription());
        }
        if (value.getSessionCampus() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getSessionCampus());
        }
        if (value.getSessionRoom() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getSessionRoom());
        }
        if (value.getLecturerId() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getLecturerId());
        }
        if (value.getLecturerName() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getLecturerName());
        }
        if (value.getSessionStartDate() == null) {
          stmt.bindNull(13);
        } else {
          stmt.bindString(13, value.getSessionStartDate());
        }
        if (value.getSessionEndDate() == null) {
          stmt.bindNull(14);
        } else {
          stmt.bindString(14, value.getSessionEndDate());
        }
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM classentity";
        return _query;
      }
    };
  }

  @Override
  public void insertClass(final ClassEntity entity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfClassEntity.insert(entity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<ClassEntity>> getAllClass() {
    final String _sql = "SELECT * FROM classentity";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"classentity"}, false, new Callable<List<ClassEntity>>() {
      @Override
      public List<ClassEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfSessionId = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionId");
          final int _cursorIndexOfCourseId = CursorUtil.getColumnIndexOrThrow(_cursor, "courseId");
          final int _cursorIndexOfCourseName = CursorUtil.getColumnIndexOrThrow(_cursor, "courseName");
          final int _cursorIndexOfSessionTh = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionTh");
          final int _cursorIndexOfSessionMode = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionMode");
          final int _cursorIndexOfClassName = CursorUtil.getColumnIndexOrThrow(_cursor, "className");
          final int _cursorIndexOfTopicTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "topicTitle");
          final int _cursorIndexOfTopicDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "topicDescription");
          final int _cursorIndexOfSessionCampus = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionCampus");
          final int _cursorIndexOfSessionRoom = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionRoom");
          final int _cursorIndexOfLecturerId = CursorUtil.getColumnIndexOrThrow(_cursor, "lecturerId");
          final int _cursorIndexOfLecturerName = CursorUtil.getColumnIndexOrThrow(_cursor, "lecturerName");
          final int _cursorIndexOfSessionStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionStartDate");
          final int _cursorIndexOfSessionEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "sessionEndDate");
          final List<ClassEntity> _result = new ArrayList<ClassEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ClassEntity _item;
            final int _tmpSessionId;
            _tmpSessionId = _cursor.getInt(_cursorIndexOfSessionId);
            final String _tmpCourseId;
            _tmpCourseId = _cursor.getString(_cursorIndexOfCourseId);
            final String _tmpCourseName;
            _tmpCourseName = _cursor.getString(_cursorIndexOfCourseName);
            final int _tmpSessionTh;
            _tmpSessionTh = _cursor.getInt(_cursorIndexOfSessionTh);
            final String _tmpSessionMode;
            _tmpSessionMode = _cursor.getString(_cursorIndexOfSessionMode);
            final String _tmpClassName;
            _tmpClassName = _cursor.getString(_cursorIndexOfClassName);
            final String _tmpTopicTitle;
            _tmpTopicTitle = _cursor.getString(_cursorIndexOfTopicTitle);
            final String _tmpTopicDescription;
            _tmpTopicDescription = _cursor.getString(_cursorIndexOfTopicDescription);
            final String _tmpSessionCampus;
            _tmpSessionCampus = _cursor.getString(_cursorIndexOfSessionCampus);
            final String _tmpSessionRoom;
            _tmpSessionRoom = _cursor.getString(_cursorIndexOfSessionRoom);
            final String _tmpLecturerId;
            _tmpLecturerId = _cursor.getString(_cursorIndexOfLecturerId);
            final String _tmpLecturerName;
            _tmpLecturerName = _cursor.getString(_cursorIndexOfLecturerName);
            final String _tmpSessionStartDate;
            _tmpSessionStartDate = _cursor.getString(_cursorIndexOfSessionStartDate);
            final String _tmpSessionEndDate;
            _tmpSessionEndDate = _cursor.getString(_cursorIndexOfSessionEndDate);
            _item = new ClassEntity(_tmpSessionId,_tmpCourseId,_tmpCourseName,_tmpSessionTh,_tmpSessionMode,_tmpClassName,_tmpTopicTitle,_tmpTopicDescription,_tmpSessionCampus,_tmpSessionRoom,_tmpLecturerId,_tmpLecturerName,_tmpSessionStartDate,_tmpSessionEndDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
