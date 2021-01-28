import 'package:flutter/material.dart';
import '../constants.dart';
import 'package:flutter_svg/flutter_svg.dart';
import '../size_config.dart';

class WelcomeText extends StatelessWidget {
  final String title, loginIcon;

  const WelcomeText({Key key, @required this.title, @required this.loginIcon})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          padding: EdgeInsets.only(left: 20.0),
          margin: EdgeInsets.symmetric(
              horizontal: getProportionateScreenWidth(90),
              vertical: getProportionateScreenHeight(10)),
          child: AspectRatio(
              aspectRatio: 1 / 1,
              child: SvgPicture.asset(
                loginIcon,
              )),
        ),
        VerticalSpacing(of: 10),
        Center(
          child: Text(
            title,
            style: kH1TextStyle,
          ),
        ),
        VerticalSpacing(of: 30),

        // VerticalSpacing(),
      ],
    );
  }
}

class WelcomeTextSignUp extends StatelessWidget {
  final String title, loginIcon;

  const WelcomeTextSignUp({Key key, @required this.title, @required this.loginIcon})
      : super(key: key);
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Center(
          child: Text(
            title,
            style: kH1TextStyleSignUp,
          ),
        ),
        HorizontalSpacing(of: 90,),
        Container(
          alignment: Alignment.bottomRight,
          child: SvgPicture.asset(
            loginIcon,width: getProportionateScreenWidth(100),
          ),
        ),
        // VerticalSpacing(of: 10),

        // VerticalSpacing(of: 30),

        // VerticalSpacing(),
      ],
    );
  }
}


