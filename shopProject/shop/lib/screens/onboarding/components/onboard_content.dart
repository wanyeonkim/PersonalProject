import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import '../../../constants.dart';

import '../../../size_config.dart';

class OnboardContent extends StatelessWidget {
  const OnboardContent({
    Key key,
    @required this.illustration,
    @required this.title,
  }) : super(key: key);

  final String illustration, title;

  @override
  Widget build(BuildContext context) {
    switch (illustration) {
      case "assets/images/page1cup.svg":
        {
          return Column(
            children: [
              VerticalSpacing(of: 50),
              Text(
                title,
                style: kHeadlineTextStyle,
              ),
              VerticalSpacing(of: 30),
              Expanded(
                child: AspectRatio(
                  aspectRatio: 3 / 2,
                  child: Padding(
                    padding: EdgeInsets.fromLTRB(
                      15,
                      15,
                      50,
                      15,
                    ),
                    child: SvgPicture.asset(illustration),
                  ),
                ),
              ),
              VerticalSpacing(of: 30),
            ],
          );
        }
        break;

      case "assets/images/page2card.svg":
        {
          return Column(
            children: [
              VerticalSpacing(of: 50),
              Text(
                title,
                style: kHeadlineTextStyle,
              ),
              Expanded(
                child: AspectRatio(
                  aspectRatio: 4 / 2,
                  child: Padding(
                    padding: EdgeInsets.fromLTRB(
                      65,
                      45,
                      50,
                      45,
                    ),
                    child: SvgPicture.asset(illustration),
                  ),
                ),
              ),
              VerticalSpacing(of: 60),
            ],
          );
        }
        break;
      case "assets/images/page3logo.svg":
        {
          return Column(
            children: [
              VerticalSpacing(of: 50),
              Text(
                title,
                style: kHeadlineTextStyle,
              ),
              Expanded(
                child: AspectRatio(
                  aspectRatio: 5 / 2,
                  child: Padding(
                    padding: EdgeInsets.fromLTRB(
                      40,
                      40,
                      40,
                      40,
                    ),
                    child: SvgPicture.asset(illustration),
                  ),
                ),
              ),
              VerticalSpacing(of: 60),
            ],
          );
        }
        break;
    }
  }
}
