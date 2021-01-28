// import 'package:firebase_storage/firebase_storage.dart' as firebase_storage;
import 'package:flutter/material.dart';

import '../../../constants.dart';
import '../../../size_config.dart';
import '../../rating.dart';
import '../../small_dot.dart';
import 'package:provider/provider.dart';
// import 'package:cloud_firestore/cloud_firestore.dart';

class RestaurantInfoMediumCard extends StatelessWidget {
  const RestaurantInfoMediumCard({
    Key key,
    @required this.image,
    @required this.name,
    @required this.description,
    @required this.rating,
    @required this.press,
    this.taptap,
  }) : super(key: key);

  final String image, name, description;
  final double rating;
  final VoidCallback press;
  final Function taptap;

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: press,
      child: SizedBox(
        width: getProportionateScreenWidth(200),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            AspectRatio(
              aspectRatio: 1.25,
              child: ClipRRect(
                borderRadius: BorderRadius.all(Radius.circular(10)),
                child: GridTile(
                  child: GestureDetector(
                    onTap: taptap,
                      //send the id over

                    child: Image.network(image, fit: BoxFit.cover),
                  ),
                ),
              ),
            ),
            VerticalSpacing(of: 10),
            Text(
              name,
              maxLines: 1,
              overflow: TextOverflow.ellipsis,
              style: kSubHeadTextStyle,
            ),
            Text(
              description,
              maxLines: 1,
              style: kBodyTextStyle,
            ),
            VerticalSpacing(of: 10),
            Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Rating(rating: rating),
              ],
            )
          ],
        ),
      ),
    );
  }
}

// class FireStorageService extends ChangeNotifier {
//   FireStorageService();
//   static Future<dynamic> loadImage(BuildContext, String Image) async {
//     return await firebase_storage.FirebaseStorage.instance
//         .ref()
//         .child(Image)
//         .getDownloadURL();
//   }
// }
//
// Future<dynamic> _getImage(BuildContext context, String imageName) async {
//   Image image;
//   await FireStorageService.loadImage(context, imageName).then((value) {
//     image = Image.network(
//       value.toString(),
//       fit: BoxFit.scaleDown,
//     );
//   });
//   print(image);
//   return image;
// }

// child: FutureBuilder(
// future: _getImage(context, 'Food6.png'),
// builder: (context, snapshot) {
// if (snapshot.connectionState == ConnectionState.done) {
// print(snapshot.connectionState);
// // print(_getImage(context, 'Food1.png'));
// return Container(
// width: MediaQuery.of(context).size.width / 1.2,
// height: MediaQuery.of(context).size.width / 1.2,
// // color: Colors.red,
// child: snapshot.data,
// );
// }
// if (snapshot.connectionState == ConnectionState.waiting) {
// return Container(
// // color: Colors.red,
// width: MediaQuery.of(context).size.width / 1.2,
// height: MediaQuery.of(context).size.width / 1.2,
// child: CircularProgressIndicator(),
// );
// }
// return Container();
// },
// ),

// Container(
// width: MediaQuery.of(context).size.height / 3.5,
// height: MediaQuery.of(context).size.height / 3.5,
// child: FutureBuilder(
// future: _getImage(context, 'Food1.png'),
// builder: (context, snapshot) {
// if (snapshot.connectionState == ConnectionState.done) {
// return Container(
// width: MediaQuery.of(context).size.width / 1.2,
// height: MediaQuery.of(context).size.height / 1.2,
// child: snapshot.data,
// );
// }
// if (snapshot.connectionState == ConnectionState.waiting){
// return Container(
// width: MediaQuery.of(context).size.height / 1.2,
// height: MediaQuery.of(context).size.height / 1.2,
// child: CircularProgressIndicator(),
// );
// }
// return Container();
// }),
// ),

/////////////////

// @override
// Widget build(BuildContext context) {
//   return InkWell(
//     onTap: press,
//     child: SizedBox(
//       width: getProportionateScreenWidth(200),
//       child: Column(
//         crossAxisAlignment: CrossAxisAlignment.start,
//         children: [
//           AspectRatio(
//             aspectRatio: 1.25,
//             child: ClipRRect(
//               borderRadius: BorderRadius.all(Radius.circular(10)),
//               child: Image.asset(image, fit: BoxFit.cover),
//             ),
//           ),
//           VerticalSpacing(of: 10),
//           Text(
//             name,
//             maxLines: 1,
//             overflow: TextOverflow.ellipsis,
//             style: kSubHeadTextStyle,
//           ),
//           Text(
//             description,
//             maxLines: 1,
//             style: kBodyTextStyle,
//           ),
//           VerticalSpacing(of: 10),
//
//           Row(
//             crossAxisAlignment: CrossAxisAlignment.center,
//             mainAxisAlignment: MainAxisAlignment.spaceBetween,
//             children: [
//               Rating(rating: rating),
//             ],
//           )
//         ],
//       ),
//     ),
//   );
// }
// }
