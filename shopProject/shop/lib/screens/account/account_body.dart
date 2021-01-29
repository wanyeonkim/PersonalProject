import 'package:flutter/material.dart';
import 'package:shop/size_config.dart';
import '../../constants.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:provider/provider.dart';
import '../../providers/profile.dart';

class AccountBody extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final profileData = Provider.of<ProfileData>(context);
    return SingleChildScrollView(
      child: Column(
        children: <Widget>[
          SizedBox(
            height: getProportionateScreenHeight(10),
          ),
          ClipRRect(
            borderRadius: BorderRadius.circular(60),
            child: Container(
              margin: EdgeInsets.symmetric(
                horizontal: getProportionateScreenWidth(10),
                vertical: getProportionateScreenHeight(10),
              ),
              color: kAccColor,
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  SizedBox(
                    width: getProportionateScreenWidth(20),
                  ),
                  ClipRRect(
                    borderRadius: BorderRadius.circular(10),
                    child: Container(
                      color: kAccColor,
                      width: getProportionateScreenWidth(100),
                      child: Image.asset(
                          'assets/images/theholmes_logo_vector.png'),
                    ),
                  ),
                  Spacer(
                    flex: 1,
                  ),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      // profileData.fullname != null ? Text('hello') : Text('no username'),
                      Text("Name: "),
                      SizedBox(
                        height: getProportionateScreenHeight(20),
                      ),
                      Text('Email'),
                    ],
                  ),
                  Spacer(
                    flex: 4,
                  ),
                ],
              ),
            ),
          ),
          Divider(
            thickness: 2,
            endIndent: 20,
            indent: 20,
          ),

          /// Membership tier
          ExpansionTile(
            title: Text("Membership Tier"),
            leading: SvgPicture.asset('assets/icons/card.svg'),
            children: <Widget>[
              Column(children: <Widget>[
                Container(
                  padding: EdgeInsets.symmetric(
                      horizontal: getProportionateScreenWidth(20),
                      vertical: getProportionateScreenHeight(20)),
                  child: Text('Membership Tier Info'),
                ),
              ]),
            ],
          ),
          SizedBox(
            height: getProportionateScreenHeight(40),
          ),

          /// the first Row
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              ///box 1
              AccountPBox(
                image: 'assets/icons/payment_icon.svg',
                text: 'Payment\nmethod',
                width: 30,
                press: () {},
              ),

              ///box 2
              AccountPBox(
                image: 'assets/icons/stamp_icon.svg',
                text: 'Stamp',
                width: 30,
                press: () {},
              ),
            ],
          ),
          SizedBox(
            height: getProportionateScreenHeight(40),
          ),

          ///second row
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              ///box 3
              AccountPBox(
                image: 'assets/icons/voucher_icon.svg',
                text: 'Vouchers',
                width: 30,
                press: () {},
              ),

              ///box 4
              AccountPBox(
                image: 'assets/icons/orders_icon.svg',
                text: 'Order\nhistory',
                width: 25,
                press: () {},
              ),
            ],
          ),
        ],
      ),
    );
  }
}

/// refactored the boxes into this since they are duplicates

class AccountPBox extends StatelessWidget {
  const AccountPBox({
    Key key,
    this.image,
    this.text,
    this.press,
    this.width,
    this.height,
  }) : super(key: key);

  final String image;
  final String text;
  final Function press;
  final double width;
  final double height;
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: press,
      child: SafeArea(
        child: ClipRRect(
          borderRadius: BorderRadius.circular(10),
          child: Container(
            color: kBoxColor,
            width: getProportionateScreenWidth(110),
            height: getProportionateScreenHeight(150),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                Padding(
                  padding: EdgeInsets.all(8.0),
                  child: Align(
                    alignment: Alignment.topLeft,
                    child: SvgPicture.asset(
                      image,
                      width: width,
                      height: height,
                    ),
                  ),
                ),
                SizedBox(
                    // height: getProportionateScreenHeight(20),
                    ),
                Padding(
                  padding: EdgeInsets.symmetric(
                      horizontal: getProportionateScreenWidth(15),
                      vertical: getProportionateScreenHeight(8)),
                  child: Align(
                    alignment: Alignment.bottomLeft,
                    child: Text(
                      text,
                      style: TextStyle(color: Colors.white),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
