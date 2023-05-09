import 'package:path/path.dart';
import 'package:sqflite/sqflite.dart';
import 'Deviceinfo.dart';

///@Description     xxxx
///@author          Mr.GAN
///@create          2022-03-17 20:48

class DatabaseHelper {
  static const _databaseName = "deviceinfo.db";
  static const _databaseVersion = 1;
  static const table = 'DeviceInfo'; //表名
  static const columnDeviceName = 'DeviceName';
  static const columnDeviceType = 'DeviceType';
  static const columnDeviceState = 'DeviceState';

  DatabaseHelper._privateConstructor();
  static final DatabaseHelper instance = DatabaseHelper._privateConstructor();

  //创建数据库  若存在则直接返回
  static Database? _database;
  Future<Database?> get database async {
    if (_database != null) return _database;
    _database = await _initDatabase();
    return _database;
  }

  //初始化数据库
  _initDatabase() async {
    String path = join(await getDatabasesPath(), _databaseName);
    return await openDatabase(path,
        version: _databaseVersion, onCreate: _onCreate);
  }

  // 建表
  Future _onCreate(Database db, int version) async {
    await db.execute('''
          CREATE TABLE $table (
            $columnDeviceName TEXT  NOT NULL PRIMARY KEY ,
            $columnDeviceType TEXT ,
            $columnDeviceState TEXT )
          ''');
  }

//向数据库插入表项
  Future<void> insert(Deviceinfo deviceinfo) async {
    Database? db = await instance.database;
    await db?.insert(
      table,
      deviceinfo.toMap(),
      conflictAlgorithm: ConflictAlgorithm.replace,
    );
  }

  Future<List<Map<String, dynamic>>?> queryAllRows() async {
    Database? db = await instance.database;
    var res = await db?.query(table, orderBy: "$columnDeviceName DESC");
    return res;
  }
//查询设备表并将结果转换为list类型
  Future<List<Map<String, dynamic>>?> Showdevices() async {
    var res = await instance.database;
    var result = await res?.rawQuery("SELECT * FROM $table ");
    return result?.toList();
  }

//清空设备表信息
  Future<void> clearTable() async {
    Database? db = await instance.database;
    await db?.rawQuery("DELETE FROM $table");
  }

//查询设备表中表项数
  Future<int> queryRowCount() async {
    Database? db = await instance.database;
    return Sqflite.firstIntValue(await db?.rawQuery('SELECT COUNT(*) FROM $table'));
  }


}
