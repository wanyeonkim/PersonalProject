import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../../providers/cart.dart';
import 'cart_item.dart' as CI;
import '../../providers/orders.dart';

class CartScreen extends StatelessWidget {
  static const routeName = '/cart';

  @override
  Widget build(BuildContext context) {
    final cart = Provider.of<Cart>(context);

    // final orders = Provider.of<Orders>(context);

    return Scaffold(
      appBar: AppBar(
        title: Text('Your Cart'),
        actions: <Widget>[
          ClearCart(cart: cart),
        ],
      ),
      body: Column(
        children: <Widget>[
          Expanded(
            child: ListView.builder(
              itemCount: cart.itemCount,
              itemBuilder: (context, i) => CI.CartItem(
                id: cart.items.values.toList()[i].id,
                price: cart.items.values.toList()[i].price,
                quantity: cart.items.values.toList()[i].quantity,
                title: cart.items.values.toList()[i].title,
                productId: cart.items.keys.toList()[i],
              ),
            ),
          ),
          SizedBox(
            height: 10,
          ),
          Card(
            margin: EdgeInsets.all(15),
            child: Padding(
              padding: EdgeInsets.all(8),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  Text(
                    'Total',
                    style: TextStyle(fontSize: 20),
                  ),
                  Spacer(),
                  Chip(
                    label: Text(
                      '\$${cart.totalAmount.toStringAsFixed(2)}',
                      style: TextStyle(
                          color:
                              Theme.of(context).primaryTextTheme.title.color),
                    ),
                    backgroundColor: Theme.of(context).primaryColor,
                  ),
                  OrderButton(cart: cart)
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}

/// Order Button

class OrderButton extends StatefulWidget {
  const OrderButton({
    Key key,
    @required this.cart,
  }) : super(key: key);

  final Cart cart;

  @override
  _OrderButtonState createState() => _OrderButtonState();
}

class _OrderButtonState extends State<OrderButton> {
  var _isLoading = false;
  @override
  Widget build(BuildContext context) {
    return FlatButton(
      child: _isLoading ? CircularProgressIndicator() : Text('ORDER NOW'),
      onPressed: (widget.cart.totalAmount <= 0 || _isLoading)
          ? null
          : () async {
              setState(() {
                _isLoading = true;
              });
              await Provider.of<Orders>(context, listen: false).addOrder(
                widget.cart.items.values.toList(),
                widget.cart.totalAmount,
              );
              setState(() {
                _isLoading = false;
              });
              widget.cart.clearCart();
            },
      textColor: Theme.of(context).primaryColor,
    );
  }
}

/// Clear Cart Button

class ClearCart extends StatefulWidget {
  final Cart cart;

  @override
  _ClearCartState createState() => _ClearCartState();

  ClearCart({this.cart});
}

class _ClearCartState extends State<ClearCart> {
  var _isLoading = false;
  // var _isEmptyCart = false;
  @override
  Widget build(BuildContext context) {
    return FlatButton(
      // color: Colors.grey,
      child: _isLoading ? CircularProgressIndicator() : Text('Clear Cart'),
      onPressed: () {
        widget.cart.itemCount <= 0
            ? showDialog(
                context: context,
                builder: (ctx) => AlertDialog(
                  title: Text(
                    'Cart is empty',
                  ),
                  content: Text('press \'ok\' to go back'),
                  actions: <Widget>[
                    FlatButton(
                      child: Text('OK'),
                      onPressed: () {
                        Navigator.of(ctx).pop(false);
                      },
                    ),
                  ],
                ),
              )
            : showDialog(
                context: context,
                builder: (ctx) => AlertDialog(
                  title: Text(
                    'Are you sure?',
                  ),
                  content: Text(
                    'do you want to remove the item from the cart?',
                  ),
                  actions: <Widget>[
                    FlatButton(
                      child: Text('No'),
                      onPressed: () {
                        Navigator.of(ctx).pop(false);
                      },
                    ),
                    FlatButton(
                      child: Text('Yes'),
                      onPressed: () {
                        widget.cart.clearCart();
                        Navigator.of(ctx).pop(true);
                        Navigator.pop(context);
                      },
                    )
                  ],
                ),
              );
      },
    );
  }
}
