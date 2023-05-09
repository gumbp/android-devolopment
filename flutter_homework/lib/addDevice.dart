// ignore_for_file: prefer_const_constructors, prefer_const_literals_to_create_immutables

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'DatabaseHelper.dart';
import 'Deviceinfo.dart';
import 'mydevice.dart';
import 'package:fluttertoast/fluttertoast.dart';

///@Description     xxxx
///@author          Mr.GAN
///@create          2022-03-13 23:19
class AddDevice extends StatefulWidget {
  @override
  _AddDeviceState createState() => _AddDeviceState();
}

class _AddDeviceState extends State<AddDevice> {
  @override
  final TextEditingController _DeviceNameController = TextEditingController();
  String SelectDeviceType = '灯光';//默认的设备类型
  String SelectDeviceState = '开启';//默认的设备状态
  final dbHelper = DatabaseHelper.instance; //实例化数据帮助类 便于操作
  bool isopen = false; //用于控制expandsionpanellist 是否展开
  Widget build(BuildContext context) {
    return Expanded(
        child: Container(
            width: MediaQuery.of(context).size.width, // 屏幕宽度
            height: MediaQuery.of(context).size.height, // 屏幕高度
            decoration: BoxDecoration(
              image: DecorationImage(
                image: AssetImage("images/模糊图.jpg"),
                //image: AssetImage("images/background.jpg"),
                fit: BoxFit.cover,
              ),
            ),
            child: Scaffold(
                backgroundColor: Colors.transparent,
                appBar: AppBar(
                  elevation: 0,
                  backgroundColor: Colors.transparent,
                  title: const Text('添加设备'),
                ),
                body: SingleChildScrollView(
                  child: Container(
                    child: Column(
                      children: <Widget>[
                        Padding(padding: EdgeInsets.symmetric(vertical: 10)),
                        Column(children: <Widget>[
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: <Widget>[
                              Text(
                                '设备名称',
                                textAlign: TextAlign.left,
                                textScaleFactor: 1.1,
                              ),
                              Expanded(
                                  child: TextField(
                                controller: _DeviceNameController, //获取输入设备信息
                                autofocus: false,
                                maxLength: 10,
                                decoration: InputDecoration(
                                  labelText: "设备名称",
                                  prefixIcon: Icon(Icons.add_business_outlined),
                                  border: OutlineInputBorder(
                                      borderRadius: BorderRadius.circular(250),
                                      borderSide: BorderSide(
                                          color: Colors.black26, width: 0)),
                                ),
                              ))
                            ],
                          ),
                        ]),
                        Column(children: <Widget>[
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: <Widget>[
                              Text('设备类型', textScaleFactor: 1.1),

                              //列表框
                              Expanded(
                                  child: ExpansionTile(
                                title: Text(
                                  '设备选项',
                                ),
                                initiallyExpanded: false,
                                children: <Widget>[
                                  TextButton(
                                      onPressed: () {
                                        SelectDeviceType = "灯光";
                                        select();
                                      },
                                      child: Text('灯光')),
                                  TextButton(
                                      onPressed: () {
                                        SelectDeviceType = "调光";
                                        select();
                                      },
                                      child: Text('调光')),
                                ],
                              ))
                            ],
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: <Widget>[
                              Text('设备状态', textScaleFactor: 1.1),

                              //列表框
                              Expanded(
                                  child: ExpansionTile(
                                title: Text(
                                  '状态选项',
                                ),
                                initiallyExpanded: false,
                                children: <Widget>[
                                  TextButton(
                                      onPressed: () {
                                        SelectDeviceState = "打开";
                                        select();
                                      },
                                      child: Text('打开')),
                                  TextButton(
                                      onPressed: () {
                                        SelectDeviceState = "关闭";
                                        select();
                                      },
                                      child: Text('关闭')),
                                ],
                              ))
                            ],
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                            children: <Widget>[
                              TextButton.icon(
                                  onPressed: insert, //向数据库插入信息
                                  icon: Icon(Icons.add),
                                  label: Text("添加设备", textScaleFactor: 1.1)),
                              TextButton.icon(
                                  onPressed: delect, //向数据库插入信息
                                  icon: Icon(Icons.delete),
                                  label: Text("清空设备", textScaleFactor: 1.1)),
                              TextButton.icon(
                                  onPressed: () {
                                    Navigator.push(context,
                                        MaterialPageRoute(builder: (context) {
                                      return MyDevice();
                                    }));
                                  },
                                  icon: Icon(Icons.zoom_in_rounded),
                                  label: Text("查看设备", textScaleFactor: 1.1))
                            ],
                          ),
                        ])
                      ],
                    ),
                  ),
                ))));
  }

  //插入数据
  insert() async {
    //await dbHelper.database;//创建数据库
    var device_example = Deviceinfo(
        devicename: _DeviceNameController.value.text.toString(),
        devicetype: SelectDeviceType,
        devicestate: SelectDeviceState);
    //await dbHelper.database;//创建数据库
    await dbHelper.insert(device_example);
    Fluttertoast.showToast(
        msg: '添加成功',
        backgroundColor: Colors.white,
        textColor: Colors.black); //提示插入成功
  }

  delect() async {
    //await dbHelper.database; //创建数据库
    await dbHelper.clearTable();
    Fluttertoast.showToast(
        msg: '清空成功',
        backgroundColor: Colors.white,
        textColor: Colors.black); //提示删除成功
  }

  select() {
    Fluttertoast.showToast(
        msg: '已选中', backgroundColor: Colors.white, textColor: Colors.black);
  }
}
