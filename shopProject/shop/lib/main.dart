import 'package:flutter/material.dart';
import 'package:shop/providers/cart.dart';
import './screens/cart/cart_screen.dart';
import 'package:shop/screens/onboarding/onboarding_scrreen.dart';
import 'screens/splash/splash_screen.dart';
import './screens/menu/product_overview_screen.dart';
import './screens/product_detail_screen.dart';

import './providers/products.dart';
import 'package:provider/provider.dart';
import './providers/cart.dart';
import './screens/cart/cart_screen.dart';
import 'providers/orders.dart';
import 'screens/order/orders_screen.dart';
import './screens/user_products_screen.dart';
import './screens/edit_product_screen.dart';
import 'providers/auth.dart';

import './screens/login_screen/sign_in_screen.dart';
import 'theme.dart';

import 'providers/bottom_NavBar.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:flutter/services.dart';
import 'constants.dart';
import 'package:shared_preferences/shared_preferences.dart';
import './screens/home/home.dart';
import 'providers/advertisements.dart';
import './screens/edit_advertisement_screen.dart';
import 'screens/account/account.dart';
import 'providers/profile.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    ///setting orientation
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.portraitDown,
    ]);

    SystemChrome.setSystemUIOverlayStyle(SystemUiOverlayStyle(
      statusBarColor: kBgColor,
      statusBarIconBrightness: Brightness.dark,
      systemNavigationBarColor: kBgColor,
      systemNavigationBarIconBrightness: Brightness.dark,
    ));

    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (context) => Auth(),
        ),
        ChangeNotifierProxyProvider<Auth, Products>(
          update: (ctx, auth, previousProducts) => Products(
            auth.token,
            auth.userId,
            previousProducts == null ? [] : previousProducts.items,
          ),
        ),
        ChangeNotifierProvider(
          create: (context) => Cart(),
        ),
        ChangeNotifierProxyProvider<Auth, Orders>(
          update: (ctx, auth, previousOrder) => Orders(
            auth.token,
            auth.userId,
            previousOrder == null ? [] : previousOrder.orders,
          ),
        ),
        ChangeNotifierProxyProvider<Auth, Advertisements>(
          update: (ctx, auth, previousAdvertisements) => Advertisements(
            auth.token,
            auth.userId,
            previousAdvertisements == null ? [] : previousAdvertisements.ads,
          ),
        ),
        ChangeNotifierProxyProvider<Auth, ProfileData>(
          update: (ctx, auth, previousProfileData) => ProfileData(
            auth.token,
            auth.userId,
            '',
            ''
          ),
        )
      ],
      child: Consumer<Auth>(
        builder: (ctx, auth, _) => MaterialApp(
          debugShowCheckedModeBanner: false,
          title: 'The Holmes',
          theme: buildThemeData(),
          home: auth.isAuth
              ? ChangeNotifierProvider<BottomNavigationBarProvider>(
                  child: BottomNav(),
                  create: (BuildContext context) =>
                      BottomNavigationBarProvider(),
                )
              : FutureBuilder(
                  future: auth.tryAutoLogin(),
                  builder: (ctx, authResultSnapshot) =>
                      authResultSnapshot.connectionState ==
                              ConnectionState.waiting
                          ? SplashScreen()
                          : SignInScreen()),
          routes: {
            ProductDetailScreen.routeName: (ctx) => ProductDetailScreen(),
            CartScreen.routeName: (ctx) => CartScreen(),
            OrdersScreen.routeName: (ctx) => OrdersScreen(),
            UserProductsScreen.routeName: (ctx) => UserProductsScreen(),
            EditProductScreen.routeName: (ctx) => EditProductScreen(),
            EditAdvertisement.routeName: (ctx) => EditAdvertisement(),
            AccountScreen.routeName: (ctx) => AccountScreen(),
          },
        ),
      ),
    );
  }
}

class BottomNav extends StatefulWidget {
  @override
  _BottomNavState createState() => _BottomNavState();
}

class _BottomNavState extends State<BottomNav> {
  /// setting tabs for use,, it cn be as many as i want
  /// but it has to match when setting up at the builder below
  var currentTab = [
    Home(),
    // CartScreen(),
    ProductOverviewScreen(), // this is menu section
    OnboardingScreen(),
    AccountScreen(),
    // OrdersScreen(),
  ];

  @override
  Widget build(BuildContext context) {
    final provider = Provider.of<BottomNavigationBarProvider>(context);
    return Scaffold(
      body: currentTab[provider.currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        // backgroundColor: kLoginEmailInput,
        // iconSize: 20,
        currentIndex: provider.currentIndex,
        onTap: (index) {
          provider.currentIndex = index;
        },
        items: [
          ///home button
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'assets/main_icons/outlined/home_icon_outline.svg',
              width: 20,
            ),
            activeIcon: SvgPicture.asset(
              'assets/main_icons/colored/home_icon_coloured.svg',
              width: 20,
            ),
            label: 'Home',
            backgroundColor: kSignupInput,
          ),

          ///menu button
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'assets/main_icons/outlined/menu_icon_outline.svg',
              width: 25,
            ),
            activeIcon: SvgPicture.asset(
              'assets/main_icons/colored/menu_icon_coloured.svg',
              width: 25,
            ),
            label: 'Menu',
            backgroundColor: Color(0xFFa38d7c),
          ),

          ///branch button
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'assets/main_icons/outlined/branch_icon_outline.svg',
              width: 30,
            ),
            activeIcon: SvgPicture.asset(
              'assets/main_icons/colored/branch_icon_coloured.svg',
              width: 30,
            ),
            label: 'Branch',
            backgroundColor: kLoginEmailInput,
          ),

          ///account button
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'assets/main_icons/outlined/account_icon_outline.svg',
              width: 25,
            ),
            activeIcon: SvgPicture.asset(
              'assets/main_icons/colored/account_icon_coloured.svg',
              width: 25,
            ),
            label: 'Account',
            backgroundColor: Color(0xFFffeed9),
          ),
        ],
      ),
    );
  }
}

/// checking if user is first time user or old user by saving it in prefs
// Future<bool> check() async {
//   final prefs = await SharedPreferences.getInstance();
//   prefs.setBool('user', true);
//   return prefs.getBool('user');
// }

// class MyHomePage extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('MyShop'),
//       ),
//       body: Center(
//         child: Text('Let\'s build a shop!'),
//       ),
//     );
//   }
// }

//

// ProductOverviewScreen()
