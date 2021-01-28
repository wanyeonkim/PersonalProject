import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import '../../size_config.dart';

import '../../constants.dart';

import 'package:provider/provider.dart';

// import '../../screens/menu/menu_screen.dart';
import '../../providers/auth.dart';

class PrimaryButton extends StatelessWidget {
  final String text;
  final GestureTapCallback press;

   PrimaryButton({Key key, @required this.text, @required this.press})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    final auth = Provider.of<Auth>(context);
    EdgeInsets verticalPadding =
        EdgeInsets.symmetric(vertical: getProportionateScreenWidth(5));
    return SizedBox(
      width: getProportionateScreenWidth(250),
      child: Platform.isIOS
          ? CupertinoButton(
              padding: verticalPadding,
              color: kButtonColor,
              onPressed: press,
              child: buildText(context),
            )
          : FlatButton(
              padding: verticalPadding,
              color: kButtonColor,
              onPressed: press,
              child: buildText(context),
            ),
    );
  }

  Text buildText(BuildContext context) {
    return Text(
      text.toUpperCase(),
      style: kButtonTextStyle,
    );
  }
}
