//定义设备信息类
import 'package:flutter_homework/DatabaseHelper.dart';

///@Description     xxxx
///@author          Mr.GAN
class Deviceinfo {
  String devicename = "设备名称";
  String devicetype = "设备类型";
  String devicestate = "设备状态";

  //Deviceinfo构造函数
  Deviceinfo(
      {required this.devicename,
      required this.devicetype,
      required this.devicestate});

  Map<String, dynamic> toMap() {
    return {
      DatabaseHelper.columnDeviceName: devicename,
      DatabaseHelper.columnDeviceType: devicetype,
      DatabaseHelper.columnDeviceState: devicestate,
    };
  }

  Deviceinfo.fromMap(Map<String, dynamic> map) {
    devicename = map['devicename'];
    devicetype = map['devicetype'];
    devicestate = map['devicestate'];
  }

  //重写tostring方法
  String toString() {
    return 'Deviceinfo{devicename: $devicename, devicetype: $devicetype, devicestate: $devicestate}';
  }
}
