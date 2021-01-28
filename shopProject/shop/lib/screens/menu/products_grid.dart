import 'package:flutter/material.dart';
import 'file:///C:/programming/flutter/shopProject/shop/lib/providers/product.dart';
// import 'file:///C:/programming/flutter/shopProject/shop/lib/screens/menu/product_item.dart';

import 'package:provider/provider.dart';
import '../../providers/products.dart';
import 'product_item.dart';

class ProductsGrid extends StatelessWidget {
  final bool showFavs;

  ProductsGrid(this.showFavs);

  @override
  Widget build(BuildContext context) {
    final productsData = Provider.of<Products>(context);
    final products = showFavs? productsData.favoriteItems: productsData.items ;
      return ListView.builder(
        // scrollDirection: Axis.horizontal,
        // padding: EdgeInsets.all(10.0),
        itemCount: products.length,
        itemBuilder: (context, i) => ChangeNotifierProvider.value(
          // create: (c) => products[i]
          value: products[i],
          child: ProductItem(),
        ),
      );
    }
  }

// return GridView.builder(
// padding: EdgeInsets.all(10.0),
// itemCount: products.length,
// itemBuilder: (context, i) => ChangeNotifierProvider.value(
// // create: (c) => products[i]
// value: products[i],
// child: ProductItem(),
// ),
// gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
// crossAxisCount: 2,
// childAspectRatio: 3 / 2,
// crossAxisSpacing: 10,
// mainAxisSpacing: 10,
// ),
// );
