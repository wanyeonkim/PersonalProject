import 'package:flutter/material.dart';
// import '../../../components/bottom_nav_bar.dart';
// import '../../../screens/findRestaurants/find_restaurants_screen.dart';

import '../../../constants.dart';
import '../../../size_config.dart';
import '../../../components/buttons/primary_button.dart';
// import '../../../screens/forgotPassword/forgot_password_screen.dart';

import 'package:provider/provider.dart';
import '../../../providers/auth.dart';
import '../../../models/http_exception.dart';

// import '../../home/home_screen.dart';

class SignInForm extends StatefulWidget {
  const SignInForm({
    Key key,
  }) : super(key: key);

  @override
  _SignInFormState createState() => _SignInFormState();
}

class _SignInFormState extends State<SignInForm> {
  // Note: This is a `GlobalKey<FormState>`,
  // not a GlobalKey<MyCustomFormState>.
  final _formKey = GlobalKey<FormState>();

  bool _autoValidate = false;
  bool _obscureText = true;

  Map<String, String> _authData = {
    'email': '',
    'password': '',
  };
  var _isLoading = false;

  FocusNode _passwordNode;

  @override
  void initState() {
    super.initState();
    _passwordNode = FocusNode();
  }

  @override
  void dispose() {
    super.dispose();
    _passwordNode.dispose();
  }

  @override
  Widget build(BuildContext context) {
    // String _email, _password;
    return Form(
      key: _formKey,
      child: Center(
        child: Column(
          children: [
            // Email Field
            SizedBox(
              width: getProportionateScreenWidth(300),
              child: TextFormField(
                autovalidate: _autoValidate,
                validator: emailValidator,
                onSaved: (value) => _authData['email'] = value,
                textInputAction: TextInputAction.next,
                onEditingComplete: () {
                  // Once user click on Next then it go to password field
                  _passwordNode.requestFocus();
                },
                style: kSecondaryBodyTextStyle,
                cursorColor: kActiveColor,
                keyboardType: TextInputType.emailAddress,
                decoration: InputDecoration(
                  fillColor: kLoginEmailInput,
                  hintText: "Email Address",
                  contentPadding: kTextFieldPadding,
                ),
              ),
            ),
            VerticalSpacing(),

            // Password Field
            SizedBox(
              width: getProportionateScreenWidth(300),
              child: TextFormField(
                focusNode: _passwordNode,
                obscureText: _obscureText,
                autovalidate: _autoValidate,
                validator: passwordValidator,
                onSaved: (value) => _authData['password'] = value,
                style: kSecondaryBodyTextStyle,
                cursorColor: kActiveColor,
                decoration: InputDecoration(
                  fillColor: kLoginEmailInput,
                  hintText: "Password",
                  contentPadding: kTextFieldPadding,
                  suffixIcon: GestureDetector(
                    onTap: () {
                      setState(() {
                        _obscureText = !_obscureText;
                      });
                    },
                    child: _obscureText
                        ? const Icon(Icons.visibility_off,
                            color: kBodyTextColor)
                        : const Icon(Icons.visibility, color: kBodyTextColor),
                  ),
                ),
              ),
            ),
            VerticalSpacing(),

            // Forget Password
            GestureDetector(
              onTap: () => Navigator.push(
                context,
                MaterialPageRoute(
                  // builder: (context) => ForgotPasswordScreen(),
                ),
              ),
              child: Text(
                "Forget Password?",
                style: Theme.of(context)
                    .textTheme
                    .caption
                    .copyWith(fontWeight: FontWeight.w500),
              ),
            ),
            VerticalSpacing(),

            if (_isLoading)
              CircularProgressIndicator()
            else
              // Sign In Button
              PrimaryButton(
                text: "Sign In",
                press: _submit,
              )
          ],
        ),
      ),
    );
  }

  Future<void> _submit() async {
    if (!_formKey.currentState.validate()) {
      // Invalid!
      return;
    }
    _formKey.currentState.save();
    setState(() {
      _isLoading = true;
    });
    try {
      // Log user in
      await Provider.of<Auth>(context, listen: false).signIn(
        _authData['email'],
        _authData['password'],
      );
    } on HttpException catch (error) {
      var errorMessage = 'Authentication failed';
      if (error.toString().contains('EMAIL_EXISTS')) {
        errorMessage = 'This email address is already in use.';
      } else if (error.toString().contains('INVALID_EMAIL')) {
        errorMessage = 'This is not a valid email address';
      } else if (error.toString().contains('WEAK_PASSWORD')) {
        errorMessage = 'This password is too weak.';
      } else if (error.toString().contains('EMAIL_NOT_FOUND')) {
        errorMessage = 'Could not find a user with that email.';
      } else if (error.toString().contains('INVALID_PASSWORD')) {
        errorMessage = 'Invalid password.';
      }
      _showErrorDialog(errorMessage);
    } catch (error) {
      final errorMessage = error.toString();
      // 'Could not authenticate you. Please try again later.';
      _showErrorDialog(errorMessage);
    }
    setState(() {
      _isLoading = false;
      // Navigator.of(context).pop();
    });

  }

  ///dialog section this will
  void _showErrorDialog(String message) {
    showDialog(
      context: context,
      builder: (ctx) => AlertDialog(
        title: Text('An Error Occurred!'),
        content: Text(message),
        actions: <Widget>[
          FlatButton(
            child: Text('Okay'),
            onPressed: () {
              Navigator.of(ctx).pop();
            },
          )
        ],
      ),
    );
  }
}
// if (_formKey.currentState.validate()) {
//   /// If all data are correct then save data to out variables
//   _formKey.currentState.save();
//
//   // just for demo
//   Navigator.push(
//       context,
//       MaterialPageRoute(
//         builder: (context) => BottomNavBar(),
//       ));
// } else {
//   /// If all data are not valid then start auto validation.
//   setState(() {
//     _autoValidate = true;
//   });
// }
