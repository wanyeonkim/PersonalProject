import 'package:flutter/material.dart';

import 'components/body.dart';
import '../../size_config.dart';
import 'package:provider/provider.dart';
import '../../providers/auth.dart';

class SignInScreen extends StatelessWidget {
  @override

  Widget build(BuildContext context) {
    // final auth = Provider.of<Auth>(context);
    SizeConfig().init(context);
    return Scaffold(
      appBar: AppBar(
        title: Text("Login/로그인"),
      ),
      body: Body(),
    );
  }
}
