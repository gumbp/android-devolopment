// ignore_for_file: prefer_const_constructors

import 'package:flutter/material.dart';
import 'LoginPage.dart';

///@Description     xxxx
///@author          Mr.GAN
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false, //除去debug显示
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const LoginPage(title: '首页'),
    );
  }
}
