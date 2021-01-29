import 'package:flutter/material.dart';
import 'package:shop/size_config.dart';
import 'package:flutter_svg/flutter_svg.dart';
class AccountSectionButton extends StatelessWidget {
  const AccountSectionButton({
    Key key,
    this.image,
    this.action,
  }) : super(key: key);

  final String image;
  final Function action;
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: action,
      child: Container(
        width: getProportionateScreenWidth(30),
        child: SvgPicture.asset(image),
      ),
    );
  }
}