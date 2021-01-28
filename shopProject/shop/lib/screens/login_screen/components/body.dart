import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';

import '../../../components/buttons/socal_button.dart';
import '../../../constants.dart';
import '../../../size_config.dart';
import '../../../components/welcome_text.dart';
import '../../../screens/signUp/sign_up_screen.dart';
import 'sign_in_form.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: kDefaultPadding),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            WelcomeText( 
              title: "The Holmes",
              loginIcon:
                  "assets/images/theholmes_face_only.svg",
            ),

            // SignInForm contains forget password
            SignInForm(),
            VerticalSpacing(),

            kOrText,// a spacer for letter 'or'

            VerticalSpacing(of: 20),

            // Create new account
            Center(
              child: Text.rich(
                TextSpan(
                  style: Theme.of(context)
                      .textTheme
                      .caption
                      .copyWith(fontWeight: FontWeight.w600),
                  text: "Don’t have account? ",
                  children: <TextSpan>[
                    TextSpan(
                      text: "Create new account.",
                      style: TextStyle(color: kActiveColor),
                      recognizer: TapGestureRecognizer()
                        ..onTap = () => Navigator.push(
                              context,
                              MaterialPageRoute(
                                builder: (context) => SignUpScreen(),
                              ),
                            ),
                    )
                  ],
                ),
              ),
            ),
            VerticalSpacing(),

            // Facebook
            // SocalButton(
            //   press: () {},
            //   text: "Connect with Facebook",
            //   color: Color(0xFF395998),
            //   icon: SvgPicture.asset(
            //     'assets/icons/facebook.svg',
            //     color: Color(0xFF395998),
            //   ),
            // ),
            // VerticalSpacing(of: 15),
            //
            // // Google
            // SocalButton(
            //   press: () {},
            //   text: "Connect with Google",
            //   color: Color(0xFF4285F4),
            //   icon: SvgPicture.asset(
            //     'assets/icons/google.svg',
            //   ),
            // ),
            VerticalSpacing(),
          ],
        ),
      ),
    );
  }
}
