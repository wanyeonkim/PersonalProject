import 'package:animated_text_kit/animated_text_kit.dart';
import 'package:flutter/material.dart';

import '../../../constants.dart';
import '../../../components/buttons/primary_button.dart';
import '../../../size_config.dart';
import '../../../size_config.dart';
import '../../login_screen/sign_in_screen.dart';
import '../../home/dot_indicators.dart';
import 'onboard_content.dart';

class Body extends StatefulWidget {
  @override
  _BodyState createState() => _BodyState();
}

class _BodyState extends State<Body> {
  int currentPage = 0;

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Column(
        children: [
          SizedBox(
            height: getProportionateScreenHeight(50),
          ),
          TypewriterAnimatedTextKit(
            speed: Duration(milliseconds: 300),
            text: ['The Holmes'],
            textStyle: TextStyle(
              color: kSignupInput,
              fontSize: 45.0,
              fontWeight: FontWeight.w900,
            ),
          ),
          // Spacer(flex: 2),
          Expanded(
            flex: 35,
            child: PageView.builder(
              itemCount: onBoardingData.length,
              onPageChanged: (value) {
                setState(() {
                  currentPage = value;
                });
              },
              itemBuilder: (context, index) => OnboardContent(
                title: onBoardingData[index]["title"],
                illustration: onBoardingData[index]["illustration"],
              ),
            ),
          ),
          Spacer(),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: List.generate(onBoardingData.length,
                (index) => DotIndicator(isActive: index == currentPage)),
          ),
          Spacer(flex: 3),
          Padding(
            padding: EdgeInsets.symmetric(
                horizontal: getProportionateScreenWidth(110)),
            child: PrimaryButton(
              press: () => Navigator.pushReplacement(
                context,
                MaterialPageRoute(
                  builder: (context) => SignInScreen(),
                ),
              ),
              text: "시작하기",
            ),
          ),
          Spacer(flex: 2),
        ],
      ),
    );
  }
}

// Demo data for our Onboarding screen
List<Map<String, dynamic>> onBoardingData = [
  {
    "title": "월 3만원에 누리는\n 하루 한잔의 커피",
    "illustration": "assets/images/page1cup.svg",
  },
  {
    "illustration": "assets/images/page2card.svg",
    "title": "오로지 당신에게만\n      허용된 공간",
  },
  {
    "illustration": "assets/images/page3logo.svg",
    "title": "당신의 취향을 추리합니다",
  },
];
