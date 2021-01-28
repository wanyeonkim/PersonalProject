import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:provider/provider.dart';
import '../../providers/advertisements.dart';
import '../../widgets/app_drawer.dart';
import 'home_top_content.dart';
import '../../constants.dart';
import '../../size_config.dart';

import 'section_title.dart';


class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  var _isInit = true;
  var _isLoading = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  void didChangeDependencies() {
    if (_isInit) {
      setState(() {
        _isLoading = true;
      });

      final advertisements = Provider.of<Advertisements>(context)
          .fetchAndSetAdvertisement()
          .then((_) {
        setState(() {
          _isLoading = false;
        });
      });
    }
    _isInit = false;
    super.didChangeDependencies();
  }

  @override
  Widget build(BuildContext context) {
    final advertisements =
        Provider.of<Advertisements>(context).fetchAndSetAdvertisement();
    SizeConfig().init(context);
    return Scaffold(
      appBar: AppBar(
        title: Column(
          children: [
            Text(
              "선택된 지점".toUpperCase(),
              style: Theme.of(context)
                  .textTheme
                  .caption
                  .copyWith(color: kActiveColor),
            ),

            ///change this next time

            Text("의정부"),
          ],
        ),
        actions: [
          FlatButton(
            child: Container(
              width: getProportionateScreenWidth(30),
              child: SvgPicture.asset('assets/icons/qr_icon.svg'),
            ),
            onPressed: () {
              /// action for qr button
            },
          )
        ],
      ),
      drawer: AppDrawer(),
      body: _isLoading
          ? Center(
              child: CircularProgressIndicator(),
            )
          : SafeArea(
              child: SingleChildScrollView(
              ///first main column for the content.. it starts with hte banner
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  VerticalSpacing(
                    of: 10,
                  ),
                  Padding(
                    padding: const EdgeInsets.symmetric(horizontal: 20),
                    child: HomeTopContent(
                        ads: Provider.of<Advertisements>(context).ads),
                  ),
                  VerticalSpacing(
                    of: 25,
                  ),

                  ///this section is for the membership tier
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
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(
                        margin: EdgeInsets.symmetric(
                            horizontal: getProportionateScreenWidth(20),
                            vertical: getProportionateScreenHeight(20)),
                        height: getProportionateScreenHeight(80),
                        width: getProportionateScreenWidth(160),
                        color: kLoginEmailInput,
                        child: Center(
                          child: Text(
                            'Voucher',
                            style:
                                TextStyle(fontSize: 20.0, color: Colors.white),
                          ),
                        ),
                      ),
                      Container(
                        margin: EdgeInsets.symmetric(
                            horizontal: getProportionateScreenWidth(20),
                            vertical: getProportionateScreenHeight(20)),
                        height: getProportionateScreenHeight(80),
                        width: getProportionateScreenWidth(160),
                        color: kLoginEmailInput,
                        child: Center(
                          child: Text(
                            'Mystery Box',
                            style:
                                TextStyle(fontSize: 20.0, color: Colors.white),
                          ),
                        ),
                      ),

                    ],
                  ),
                  /// this section is for section tile where it will show the some content for the food and so on.
                  SectionTitle(
                    title: "Chef Recommendation",
                    press: () => Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => Container(),
                      ),
                    ),
                  ),
                  VerticalSpacing(of: 15,),

                ],
              ),
            )),
    );
  }
}
