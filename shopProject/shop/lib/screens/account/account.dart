import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import '../../size_config.dart';
import 'button_qr.dart';
import 'account_body.dart';

class AccountScreen extends StatelessWidget {
  static const routeName = '/account-page';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Align(
          alignment: Alignment.topLeft,
          child: Text('My Account'),
        ),
        actions: [
          AccountSectionButton(
            image: 'assets/icons/qr_icon.svg',
            action: () {
              print('tapped qr');
            },
          ),
          SizedBox(
            width: getProportionateScreenWidth(20),
          ),
          AccountSectionButton(
            image: 'assets/icons/settings_icon.svg',
            action: () {
              print('tapped setting');
            },
          ),
          SizedBox(
            width: getProportionateScreenWidth(10),
          ),
        ],
      ),
      body: AccountBody(),
    );
  }
}


