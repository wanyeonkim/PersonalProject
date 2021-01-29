import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../providers/product.dart';
import '../size_config.dart';
import '../providers/products.dart';
import '../providers/cart.dart';
import '../constants.dart';

class ProductDetailScreen extends StatelessWidget {
  static const routeName = '/product-detail';

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<Cart>(context);
    final productId = ModalRoute.of(context).settings.arguments as String;
    final loadedProduct =
        Provider.of<Products>(context, listen: false).findById(productId);

    return Scaffold(
      // key: _scaffoldKey,
      appBar: AppBar(
        title: Text(loadedProduct.title),
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            Container(
              height: 300,
              child: Hero(
                tag: loadedProduct.id,
                child: ClipRRect(
                  borderRadius: BorderRadius.circular(10),
                  child: Image.asset(loadedProduct.imageUrl),

                ),
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Text(
              "\$${loadedProduct.price}",
              style: TextStyle(color: Colors.grey, fontSize: 20),
            ),
            SizedBox(
              height: 10,
            ),
            Container(
              padding: EdgeInsets.symmetric(horizontal: 10),
              width: double.infinity,
              child: Text(
                loadedProduct.description,
                textAlign: TextAlign.center,
                softWrap: true,
              ),
            ),
            SizedBox(height: 50),
          ],
        ),
      ),
      bottomSheet: Container(
        color: kBgColor,
        height: getProportionateScreenHeight(200.0),
        // width: double.infinity,
        child: SafeArea(
          child: bottomButton(loadedProduct: loadedProduct, cart: cart),
        ),
      ),
    );
  }
}

class bottomButton extends StatefulWidget {
  const bottomButton({
    Key key,
    @required this.loadedProduct,
    @required this.cart,
  }) : super(key: key);

  final Product loadedProduct;
  final Cart cart;

  @override
  _bottomButtonState createState() => _bottomButtonState();
}

int amount = 0;

///button part of the page (the card area)

class _bottomButtonState extends State<bottomButton> {
  @override
  Widget build(BuildContext context) {
    double totalAmount = widget.loadedProduct.price * amount;
    final GlobalKey<ScaffoldState> _scaffoldKey = GlobalKey<ScaffoldState>();
    return Card(
      key: _scaffoldKey,
      elevation: 3,
      color: kLoginEmailInput,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          ListTile(
            title: Column(
              // mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                // Text(product.title,style: kSecondaryBodyTextStyle,),
                Text(
                  "\$${totalAmount.toStringAsFixed(2)}",
                  style: kBodyTextStyle,
                ),
              ],
            ),
            leading: ClipRRect(
              borderRadius: BorderRadius.circular(10),
              child: Image.asset(widget.loadedProduct.imageUrl),

            ),
            onTap: () {
              /// action when tap
              /// but for this case no action wil required
            },
            trailing: Wrap(
              // alignment: WrapAlignment.start,
              direction: Axis.vertical,
              spacing: 12,
              children: <Widget>[
                IconButton(
                  icon: Icon(
                    Icons.remove,
                  ),
                  onPressed: () {
                    setState(() {
                      amount >= 1 ? amount-- : amount = 0;
                    });
                  },
                  color: Theme.of(context).accentColor,
                ),
                SizedBox(
                  width: 10.0,
                ),
                Container(
                  child: Center(child: Text('${amount}')),
                ),
                SizedBox(
                  width: 10.0,
                ),
                IconButton(
                  icon: Icon(Icons.add),
                  color: Theme.of(context).accentColor,
                  onPressed: () {
                    setState(() {
                      amount++;
                    });
                  },
                ),
              ],
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              SizedBox(
                width: getProportionateScreenWidth(40),
              ),
              Text(widget.loadedProduct.title),
              Spacer(),
              AddToCartButton(
                widget: widget,
                totalAmount: totalAmount,
              ),
              Spacer(),
            ],
          )
        ],
      ),
    );
  }
}

/// button for the cart

class AddToCartButton extends StatelessWidget {
  const AddToCartButton({
    Key key,
    @required this.widget,
    @required this.totalAmount,
  }) : super(key: key);

  final bottomButton widget;
  final double totalAmount;

  @override
  Widget build(BuildContext context) {
    return FlatButton(
      color: kButtonColor,
      child: Text(
        'Add To Cart',
        style: kButtonTextStyle2,
      ),
      onPressed: () {
        amount >= 1
            ? showDialog(
                context: context,
                builder: (ctx) => AlertDialog(
                  title: Text(
                    'Adding To Cart',
                  ),
                  content: Text(
                      'you are adding ${amount}  ${widget.loadedProduct.title} to cart'),
                  actions: <Widget>[
                    FlatButton(
                      child: Text('OK'),
                      onPressed: () {
                        widget.cart.addItemQuantity(
                            amount,
                            widget.loadedProduct.id,
                            totalAmount,
                            widget.loadedProduct.title);
                        amount = 0;
                        Navigator.of(ctx).pop(false);
                      },
                    ),
                  ],
                ),
              )
            : null;
        // showDialog(
        //   context: context,
        //   builder: (ctx) => AlertDialog(
        //     title: Text(
        //       'Adding To Cart',
        //     ),
        //     content: Text(
        //         'you are adding ${amount} of ${widget.loadedProduct.title} to cart'),
        //     actions: <Widget>[
        //       FlatButton(
        //         child: Text('OK'),
        //         onPressed: () {
        //           amount = 0;
        //           Navigator.of(ctx).pop(false);
        //         },
        //       ),
        //     ],
        //   ),
        // );
      },
    );
  }
}
