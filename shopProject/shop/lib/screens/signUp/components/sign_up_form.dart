import 'package:flutter/material.dart';
// import '../../../screens/phoneLogin/phone_login_screen.dart';

import '../../../constants.dart';
import '../../../size_config.dart';
import '../../../components/buttons/primary_button.dart';

import 'package:provider/provider.dart';
import '../../../providers/auth.dart';
import '../../../models/http_exception.dart';
// import '../../../screens/menu/menu_screen.dart';

// import '../../home/home_screen.dart';

class SignUpForm extends StatefulWidget {
  @override
  _SignUpFormState createState() => _SignUpFormState();
}

class _SignUpFormState extends State<SignUpForm> {
  // Note: This is a `GlobalKey<FormState>`,
  // not a GlobalKey<MyCustomFormState>.
  final _formKey = GlobalKey<FormState>();
  bool _autoValidate = false;
  bool _obscureText = true;

  FocusNode _emailNode;
  FocusNode _passwordNode;
  FocusNode _conformPasswordNode;
  Map<String, String> _authData = {
    'email': '',
    'password': '',
    'fullName':'',
  };
  var _isLoading = false;

  String _fullName, _email, _password, _conformPassword;

  @override
  void initState() {
    super.initState();
    _passwordNode = FocusNode();
    _emailNode = FocusNode();
    _conformPasswordNode = FocusNode();
  }

  @override
  void dispose() {
    super.dispose();
    _passwordNode.dispose();
    _emailNode.dispose();
    _conformPasswordNode.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: Center(
        child: Column(
          children: [
            // Full Name Field
            SizedBox(
              width: getProportionateScreenWidth(300),
              child: TextFormField(
                autovalidate: _autoValidate,
                validator: requiredValidator,
                onSaved: (value) => _authData['fullName'] = value,
                textInputAction: TextInputAction.next,
                onEditingComplete: () {
                  // Once user click on Next then it go to email field
                  _emailNode.requestFocus();
                },
                style: kSecondaryBodyTextStyle,
                cursorColor: kActiveColor,
                decoration: InputDecoration(
                  fillColor: kLoginEmailInput,
                  hintText: "Full Name",
                  contentPadding: kTextFieldPadding,
                ),
              ),
            ),
            VerticalSpacing(),

            // Email Field
            SizedBox(
              width: getProportionateScreenWidth(300),
              child: TextFormField(
                focusNode: _emailNode,
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
                textInputAction: TextInputAction.next,
                onEditingComplete: () => _conformPasswordNode.requestFocus(),
                // We need to validate our password
                onChanged: (value) => _password = value,
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

            // Confirm Password Field
            SizedBox(
              width: getProportionateScreenWidth(300),
              child: Container(
                child: TextFormField(
                  focusNode: _conformPasswordNode,
                  obscureText: _obscureText,
                  autovalidate: _autoValidate,
                  validator: (value) =>
                      matchValidator.validateMatch(value, _password),
                  onSaved: (value) => _conformPassword = value,
                  style: kSecondaryBodyTextStyle,
                  cursorColor: kActiveColor,
                  decoration: InputDecoration(
                    fillColor: kLoginEmailInput,
                    hintText: "Confirm Password",
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
            ),
            VerticalSpacing(),
            // Sign Up Button
            if (_isLoading)
              CircularProgressIndicator()
            else
              PrimaryButton(
                text: "Sign Up",
                press: _submit,
              ),
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
      // sign up user
      await Provider.of<Auth>(context, listen: false).signUp(
        _authData['email'],
        _authData['password'],
        _authData['fullName'],
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
      const errorMessage =
          'Could not authenticate you. Please try again later.';
      _showErrorDialog(errorMessage);
    }

    setState(() {
      _isLoading = false;
      Navigator.of(context).pop();
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
