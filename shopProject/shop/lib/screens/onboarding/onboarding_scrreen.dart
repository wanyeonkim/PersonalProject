import 'package:flutter/material.dart';
import '../../size_config.dart';
import 'components/body.dart';

import 'package:shared_preferences/shared_preferences.dart';

class OnboardingScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    SizeConfig().init(context);

    // Onboarding is our starting screen
    check();
    return Scaffold(
      body: Body(),
    );
  }
}

Future<void> check() async {
  final prefs = await SharedPreferences.getInstance();
  prefs.setBool('user', true);
}


