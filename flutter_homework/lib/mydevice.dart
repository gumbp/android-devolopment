import 'package:flutter/material.dart';
import 'AddDevice.dart';
import 'package:flutter/widgets.dart';
import 'DatabaseHelper.dart';

///@Description     xxxx
///@author          Mr.GAN
class MyDevice extends StatefulWidget {
  State<MyDevice> createState() => _Mydevice();
}

//我的设备界面
class _Mydevice extends State<MyDevice> {
  bool flag = false; //用于查看设备时的ui更新控制
  List<Map<String, dynamic>>? DeviceInfoList = [];
  final dbHelper1 = DatabaseHelper.instance; //实例化数据帮助类 便于操作
  final allRows = [];
  Widget build(BuildContext context) {
    if (!flag) {
      return Container(
        width: MediaQuery.of(context).size.width, // 屏幕宽度
        height: MediaQuery.of(context).size.height, // 屏幕高度
        decoration: BoxDecoration(
          image: DecorationImage(
            image: AssetImage("images/模糊图.jpg"),
            //image: AssetImage("images/background.jpg"),
            fit: BoxFit.cover,
          ),
        ),
        child:
            //默认设备页面ui
            Scaffold(
          backgroundColor: Colors.transparent,
          appBar: AppBar(
            elevation: 0,
            backgroundColor: Colors.transparent,
            title: Text("设备页面"),
          ),
          body: Container(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                //Column()
                TextButton.icon(
                    onPressed: ExistDevice,
                    icon: Icon(Icons.zoom_in_rounded),
                    label: Text("查看设备")),
                TextButton.icon(
                    onPressed: () {
                      Navigator.push(context,
                          MaterialPageRoute(builder: (context) {
                        return AddDevice();
                      }));
                    },
                    icon: Icon(Icons.add),
                    label: Text("添加设备")),
              ],
            ),
          ),
        ),
      );
    }

    //若点击查看设备按钮  则加载已存设备  重汇页面
    else {
      return Container(
          width: MediaQuery.of(context).size.width, // 屏幕宽度
          height: MediaQuery.of(context).size.height, // 屏幕高度
          decoration: const BoxDecoration(
            image: DecorationImage(
              //image: AssetImage("images/background.jpg"),
              image: AssetImage("images/模糊图.jpg"),
              fit: BoxFit.cover,
            ),
          ),
          child: Scaffold(
            backgroundColor: Colors.transparent,
            appBar: AppBar(
              backgroundColor: Colors.transparent,
              elevation: 0,
              title: Text("设备页面"),
            ),
            body: Container(
              child: Column(
                children: <Widget>[
                  Row(
                      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                      children: <Widget>[
                        TextButton.icon(
                            onPressed: () {
                              _queryAll();
                            },
                            icon: Icon(Icons.looks),
                            label: Text("查看设备")),


                        TextButton.icon(
                            onPressed: () {
                              Navigator.push(context,
                                  MaterialPageRoute(builder: (context) {
                                return AddDevice();
                              }));
                            },
                            icon: Icon(Icons.add),
                            label: Text("添加设备"))
                      ]),


                  Column(
                    //mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: <Widget>[
                      const Padding(
                          padding: EdgeInsets.symmetric(vertical: 20)),
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: const <Widget>[
                          Text('设备名称'),
                          Text('设备类型'),
                          Text('设备状态'),
                        ],
                      )
                    ],
                  ),
                  Expanded(
                    child: Container(
                      child: ListView.builder(
                        padding: const EdgeInsets.all(8),
                        itemCount: DeviceInfoList?.length,
                        itemBuilder: (BuildContext context, int index) {
                          return Container(
                            height: 100,
                            child: Center(
                              child: Text(
                                '${DeviceInfoList?[index]['DeviceName']}                ${DeviceInfoList?[index]['DeviceType']}                  ${DeviceInfoList?[index]['DeviceState']}',
                                //style: TextStyle(fontSize: 16),
                              ),
                            ),
                          );
                        },
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ));
    }
  }

  //查询数据库中数据
  _queryAll() async {
    final allRows = await dbHelper1.Showdevices();
    setState(() {
      flag = !flag;
      DeviceInfoList =  allRows ;
    });

  }

  //显示目前设备  刷新ui
  ExistDevice() {
    setState(() {
      flag = !flag;
    });
  }
}
