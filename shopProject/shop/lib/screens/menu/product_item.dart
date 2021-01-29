import 'package:flutter/material.dart';
import '../../providers/products.dart';
import 'package:shop/size_config.dart';
import '../product_detail_screen.dart';
import 'package:provider/provider.dart';
import '../../providers/product.dart';
import '../../providers/cart.dart';
import '../../providers/auth.dart';
import '../../constants.dart';

class ProductItem extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final product = Provider.of<Product>(context);
    final products = Provider.of<Products>(context);
    final cart = Provider.of<Cart>(context, listen: false);
    final authData = Provider.of<Auth>(context, listen: false);
    return Center(
      child: Column(
        // mainAxisSize: MainAxisSize.min,
        children: <Widget>[
          ListTile(
            title: Column(
              children: [
                // Text('${products.items.length}'),
                Text(
                  product.title,
                  style: kSecondaryBodyTextStyle,
                ),
                Text(
                  "\$${product.price}",
                  style: kBodyTextStyle,
                ),
              ],
            ),
            // product.imageUrl,
            leading: Hero(
              tag: product.id,
              child: ClipRRect(
                borderRadius: BorderRadius.circular(10),
                child: Image.asset(product.imageUrl),
              ),
            ),
            onTap: () {
              Navigator.of(context).pushNamed(
                ProductDetailScreen.routeName,
                arguments: product.id,
              );
            },
            trailing: Wrap(
              // alignment: WrapAlignment.start,
              direction: Axis.vertical,
              spacing: 12,
              children: <Widget>[
                IconButton(
                  icon: Icon(product.isFavorite
                      ? Icons.favorite
                      : Icons.favorite_border),
                  color: Theme.of(context).accentColor,
                  onPressed: () {
                    product.toggleFavStatus(authData.token, authData.userId);
                  },
                ),
                IconButton(
                  icon: Icon(
                    Icons.shopping_cart,
                  ),
                  onPressed: () {
                    cart.addItem(
                      product.id,
                      product.price,
                      product.title,
                    );
                    Scaffold.of(context).hideCurrentSnackBar();
                    Scaffold.of(context).showSnackBar(SnackBar(
                      content: Text(
                        '${product.title} Added Item to cart!',
                        textAlign: TextAlign.center,
                      ),
                      duration: Duration(seconds: 2),
                      action: SnackBarAction(
                        label: 'UNDO',
                        onPressed: () {
                          cart.removeSingleItem(product.id);
                        },
                      ),
                    ));
                  },
                  color: Theme.of(context).accentColor,
                ),
              ],
            ),
          ),
          Divider(
            color: kLoginEmailInput,
            thickness: 2,
            indent: 20,
            endIndent: 20,
          ),
        ],
      ),
    );
  }
}

// footer: GridTileBar(
// leading: IconButton(
// icon: Icon(
// product.isFavorite ? Icons.favorite : Icons.favorite_border),
// color: Theme.of(context).accentColor,
// onPressed: () {
// product.toggleFavStatus(authData.token,authData.userId);
// },
// ),
// backgroundColor: Colors.black87,
// title: Text(
// product.title,
// textAlign: TextAlign.center,
// ),
// trailing: IconButton(
// icon: Icon(
// Icons.shopping_cart,
// ),
// onPressed: () {
// cart.addItem(
// product.id,
// product.price,
// product.title,
// );
// Scaffold.of(context).hideCurrentSnackBar();
// Scaffold.of(context).showSnackBar(SnackBar(
// content: Text(
// '${product.title} Added Item to cart!',
// textAlign: TextAlign.center,
// ),
// duration: Duration(seconds: 2),
// action: SnackBarAction(
// label: 'UNDO',
// onPressed: () {
// cart.removeSingleItem(product.id);
// },
// ),
// ));
// },
// color: Theme.of(context).accentColor,
// // onPressed: () {},
// ),
// ),

// child: Card(
// elevation: 3,
// color: kLoginEmailInput,
// child: Column(
// // mainAxisSize: MainAxisSize.min,
// children: <Widget>[
// ListTile(
// title: Column(
// children: [
// Text(product.title,style: kSecondaryBodyTextStyle,),
// Text("\$${product.price}",style: kBodyTextStyle,),
// // IconButton(
// //   icon: Icon(product.isFavorite
// //       ? Icons.favorite
// //       : Icons.favorite_border),
// //   color: Theme.of(context).accentColor,
// //   onPressed: () {
// //     product.toggleFavStatus(authData.token, authData.userId);
// //   },
// // ),
// ],
// ),
// leading: Hero(
// tag: product.id,
// child: CircleAvatar(
// radius: 40,
// backgroundImage: AssetImage(
// product.imageUrl,
// ),
// ),
// ),
// onTap: () {
// Navigator.of(context).pushNamed(
// ProductDetailScreen.routeName,
// arguments: product.id,
// );
// },
// trailing: Wrap(
// // alignment: WrapAlignment.start,
// direction:Axis.vertical ,
// spacing: 12,
// children: <Widget>[
// IconButton(
// icon: Icon(product.isFavorite
// ? Icons.favorite
//     : Icons.favorite_border),
// color: Theme.of(context).accentColor,
// onPressed: () {
// product.toggleFavStatus(authData.token, authData.userId);
// },
// ),
// IconButton(
// icon: Icon(
// Icons.shopping_cart,
// ),
// onPressed: () {
// cart.addItem(
// product.id,
// product.price,
// product.title,
// );
// Scaffold.of(context).hideCurrentSnackBar();
// Scaffold.of(context).showSnackBar(SnackBar(
// content: Text(
// '${product.title} Added Item to cart!',
// textAlign: TextAlign.center,
// ),
// duration: Duration(seconds: 2),
// action: SnackBarAction(
// label: 'UNDO',
// onPressed: () {
// cart.removeSingleItem(product.id);
// },
// ),
// ));
// },
// color: Theme.of(context).accentColor,
// ),
// ],
// ),
// ),
// ],
// ),
// ),
