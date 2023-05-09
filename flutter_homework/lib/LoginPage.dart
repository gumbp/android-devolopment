// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'package:flutter/painting.dart';
import 'package:flutter/services.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'mydevice.dart';

///@Description     xxxx
///@author          Mr.GAN
///@create          2022-03-16 15:35

class LoginPage extends StatefulWidget {
  //登录页
  const LoginPage({Key? key, required this.title}) : super(key: key);
  final String title;

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  // ignore: non_constant_identifier_names
  final TextEditingController _NameController = TextEditingController();
  // ignore: non_constant_identifier_names
  final TextEditingController _PasswordController = TextEditingController();
  static bool _checkboxSelected = false; //用于选择是否记住密码
  bool psd = true; //用于选择是否显示密码
  final String account = "admin"; //设置初始账号
  final String code = "12345"; //设置初始密码

  void IfRemember() {
    setState(() {
      _checkboxSelected = !_checkboxSelected; //点击时将选择状态置反
    });
  }

  //用于保存账号密码
  save() async {
    SharedPreferences sp = await SharedPreferences.getInstance();
    sp.setString(account, _NameController.value.text.toString());
    sp.setString(code, _PasswordController.value.text.toString());
    Fluttertoast.showToast(
        msg: "账户保存成功", backgroundColor: Colors.white, textColor: Colors.black);
  }

  //当点击登录按钮时
  Login() async {
    //验证账号密码正确与否
    if (_NameController.value.text.toString() == account &&
        _PasswordController.value.text.toString() == code) {
      Fluttertoast.showToast(
          msg: "登陆成功",
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.CENTER,
          timeInSecForIosWeb: 1,
          backgroundColor: Colors.white,
          textColor: Colors.black,
          fontSize: 14.0);
      //跳转至我的设备页面
      Navigator.push(context, MaterialPageRoute(builder: (context) {
        return MyDevice();
      }));
    } else {
      Fluttertoast.showToast(
          msg: "账号密码有误，请重新输入",
          toastLength: Toast.LENGTH_SHORT,
          gravity: ToastGravity.CENTER,
          timeInSecForIosWeb: 1,
          backgroundColor: Colors.white,
          textColor: Colors.black,
          fontSize: 14.0);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      width: MediaQuery.of(context).size.width, // 屏幕宽度
      height: MediaQuery.of(context).size.height, // 屏幕高度
      decoration: BoxDecoration(
        image: DecorationImage(
          //image: AssetImage("images/background.jpg"),
          image: AssetImage("images/模糊图.jpg"),
          fit: BoxFit.cover,
        ),
      ),
      child: (Scaffold(
        backgroundColor: Colors.transparent, //把scaffold的背景色改成透明
        appBar: AppBar(
          elevation: 0,
          backgroundColor: Colors.transparent, //把scaffold的背景色改成透明
          title: Text(widget.title),
        ),
        body: Container(
          alignment: Alignment.center,
          //padding: EdgeInsets.only(left: 10.0, right: 10.0, top: 2.0, bottom: 2.0),
          width: MediaQuery.of(context).size.width, // 获取屏幕宽度
          height: MediaQuery.of(context).size.height, // 获取屏幕高度

          child: Column(mainAxisAlignment: MainAxisAlignment.start, children: <Widget>[
            Padding(padding: EdgeInsets.symmetric(vertical: 60)),
            Column(children: <Widget>[
              AutofillGroup(
                child: ConstrainedBox(
                  constraints: BoxConstraints(maxHeight: 400, maxWidth: 300),
                  child: TextField(
                    controller: _NameController,
                    textInputAction: TextInputAction.next,
                    //autofillHints: const [AutofillHints.username],
                    autofocus: false,
                    maxLength: 10,
                    decoration: InputDecoration(
                      labelText: "用户名",
                      prefixIcon: Icon(Icons.person),
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(250),
                          borderSide:
                              BorderSide(color: Colors.black, width: 0)),
                    ),
                  ),
                ),
              ),
              AutofillGroup(
                  child: ConstrainedBox(
                constraints: BoxConstraints(maxHeight: 400, maxWidth: 300),
                child: TextField(
                  controller: _PasswordController,
                  autofocus: false,
                  autofillHints: const [AutofillHints.password],
                  decoration: InputDecoration(
                    labelText: "密码",
                    prefixIcon: Icon(Icons.lock),
                    border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(250),
                        borderSide: BorderSide(color: Colors.black, width: 0)),
                    suffixIcon: IconButton(
                      icon: Icon(
                        //根据passwordVisible状态显示不同的图标
                        psd ? Icons.visibility : Icons.visibility_off,
                        color: Theme.of(context).primaryColorDark,
                      ),
                      onPressed: () {
                        //更新状态控制密码显示或隐藏
                        setState(() {
                          psd = !psd;
                        });
                      },
                    ),
                  ),
                  onEditingComplete: () => TextInput.finishAutofillContext(),
                  obscureText: psd,
                  maxLength: 15,
                ),
              )),
              // ignore: deprecated_member_use

             // ignore: deprecated_member_use
             OutlineButton.icon(
                icon: Icon(Icons.login),
                borderSide: BorderSide(
                  width: 1.0,
                  color: Color(0xFF000000),
                  style: BorderStyle.solid,
                ),
                onPressed: () {
                  setState(() {
                    Login();
                  });
                }, //调用登陆按钮回调函数
                label: Text("登录"),
              ),



              /*ElevatedButton(onPressed: () {
                setState(() {
                  Login();
                });
              },
                child: Text('登录'),//调用登陆按钮回调函数
              )*/
            ]),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                Text("记住密码"),
                Checkbox(
                    value: _checkboxSelected,
                    activeColor: Colors.blueAccent,
                    onChanged: (value) {
                      IfRemember();
                      if (_checkboxSelected) {
                        //复选框选中  则调用sp.save()保存账户信息
                        save();
                      }
                    }),
              ],
            ),
          ]),
        ),
      )),
    );
  }
}
