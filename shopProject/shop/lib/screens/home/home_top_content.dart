import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../../providers/advertisements.dart';
import 'advertisement_item.dart';

import '../../size_config.dart';
import '../../constants.dart';
import 'dot_indicators.dart';

class HomeTopContent extends StatefulWidget {
  final dynamic ads;

  HomeTopContent({this.ads});

  @override
  _HomeTopContentState createState() => _HomeTopContentState();
}

class _HomeTopContentState extends State<HomeTopContent> {
  int initialIndex = 0;
  @override
  Widget build(BuildContext context) {
    final advertisementData = Provider.of<Advertisements>(context);
    final advertisement = advertisementData.ads;
    return AspectRatio(
      aspectRatio: 1.81,
      child: Stack(
        children: [
          PageView.builder(
            onPageChanged: (value){
              ///this is to check if it is null value.
              if(mounted){
                setState(() {
                  initialIndex = value;
                });
              }
            },
            scrollDirection: Axis.horizontal,
            // padding: EdgeInsets.all(10.0),
            itemCount: advertisement.length,
            itemBuilder: (context, i) => ChangeNotifierProvider.value(
              // create: (c) => products[i]
              value: advertisement[i],
              child: AdvertisementItem(
                image: advertisement[i].imageUrl,
              ),
            ),
          ),
          Positioned(
            bottom: getProportionateScreenWidth(15),
            right: getProportionateScreenWidth(15),
            child: Row(
              children: List.generate(
                widget.ads.length,
                (index) => DotIndicator(
                  isActive: initialIndex == index,
                  activeColor: Colors.white,
                  inActiveColor: Colors.white,
                ),
              ),
            ),
          )
        ],
      ),
    );
  }
}

// return GridView.builder(
// padding: EdgeInsets.all(10.0),
// itemCount: advertisement.length,
// itemBuilder: (context, i) => ChangeNotifierProvider.value(
// // create: (c) => advertisement[i],
// value: advertisement[i],
// child: AdvertisementItem(),
// ),
// gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
// crossAxisCount: 1,
// childAspectRatio: 3 / 2,
// crossAxisSpacing: 10,
// mainAxisSpacing: 10,
// ),
// );
