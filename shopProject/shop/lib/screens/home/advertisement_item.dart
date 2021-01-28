import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../../providers/advertisement.dart';

import 'package:flutter_svg/flutter_svg.dart';
import '../../size_config.dart';

class AdvertisementItem extends StatelessWidget {
  final dynamic image;

  AdvertisementItem({this.image});

  @override
  Widget build(BuildContext context) {
    final advertisement = Provider.of<Advertisement>(context);
    return Container(
      decoration: BoxDecoration(
        borderRadius: const BorderRadius.all(Radius.circular(12)),
        image: DecorationImage(
          image: AssetImage(image),
          fit: BoxFit.cover,
        ),
      ),
    );
  }
}

// return ClipRRect(
// borderRadius: BorderRadius.circular(10),
// child: GridTile(
// child: GestureDetector(
// onTap: () {
// // Navigator.of(context).pushNamed(
// //   ProductDetailScreen.routeName,
// //   arguments: product.id,
// // );
// },
// child: Column(
// children: [
// Image.asset(
// product.imageUrl,
// fit: BoxFit.cover,
// width: 300,
// height: 160,
// ),
// SizedBox(
// width: 50,
// )
// ],
// ),
// ),
// ),
// );
