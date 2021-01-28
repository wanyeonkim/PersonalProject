import 'package:flutter/material.dart';
import 'size_config.dart';
import 'package:form_field_validator/form_field_validator.dart';

// clolors that we use in our app
const kMainColor = Color(0xFF010F07);
const kActiveColor = Color(0xFF29150b);
const kAccentColor = Color(0xFFEF9920);
const kBodyTextColor = Color(0xFF868686);
const kInputColor = Color(0xFFFBFBFB);
const kBgColor = Color(0xFFf7efe6);


const kLoginEmailInput = Color(0xFFe0d1bf);
const kSignupInput = Color(0xFF736357);
const kButtonColor = Color(0xFF29150b);


// Text Styles
final TextStyle kH1TextStyle = TextStyle(
  fontFamily: 'ATC',
  fontSize: getProportionateScreenWidth(30),
  fontWeight: FontWeight.w500,
  letterSpacing: 0.22,
);

final TextStyle kH1TextStyleSignUp = TextStyle(
  fontFamily: 'ATC',
  fontSize: getProportionateScreenWidth(45),
  fontWeight: FontWeight.w500,
  letterSpacing: 0.22,
);


final TextStyle kH2TextStyle = TextStyle(
  fontSize: getProportionateScreenWidth(28),
  fontWeight: FontWeight.w600,
  letterSpacing: 0.18,
);

final TextStyle kH3TextStyle = kH2TextStyle.copyWith(
  fontSize: getProportionateScreenWidth(20),
  letterSpacing: 0.14,
);

final TextStyle kHeadlineTextStyle = TextStyle(
  fontSize: getProportionateScreenWidth(30),
  fontWeight: FontWeight.bold,
);

final TextStyle kSubHeadTextStyle = TextStyle(
  fontSize: getProportionateScreenWidth(20),
  fontWeight: FontWeight.w500,
  color: kMainColor,
  letterSpacing: 0.44,
);

final TextStyle kBodyTextStyle = TextStyle(
  fontSize: getProportionateScreenWidth(16),
  color: kBodyTextColor,
  height: 1.5,
);

final TextStyle kSecondaryBodyTextStyle = TextStyle(
  fontSize: getProportionateScreenWidth(14),
  fontWeight: FontWeight.w500,
  color: kMainColor,
  // height: 1.5,
);

final TextStyle kButtonTextStyle = TextStyle(
  color: Color(0xFFf7efe6),
  fontSize: getProportionateScreenWidth(25),
  fontWeight: FontWeight.bold,
);

final TextStyle kButtonTextStyle2 = TextStyle(
  color: Color(0xFFf7efe6),
  fontSize: getProportionateScreenWidth(15),
  // fontWeight: FontWeight.bold,
);





final TextStyle kCaptionTextStyle = TextStyle(
  color: kMainColor.withOpacity(0.64),
  fontSize: getProportionateScreenWidth(12),
  fontWeight: FontWeight.w600,
);

// padding
const double kDefaultPadding = 20.0;
final EdgeInsets kTextFieldPadding = EdgeInsets.symmetric(
  horizontal: kDefaultPadding,
  vertical: getProportionateScreenHeight(kDefaultPadding),
);

// Default Animation Duration
const Duration kDefaultDuration = Duration(milliseconds: 250);

// Text Field Decoration
const OutlineInputBorder kDefaultOutlineInputBorder = OutlineInputBorder(
  borderRadius: const BorderRadius.all(Radius.circular(6)),
  borderSide: const BorderSide(
    color: Color(0xFFF3F2F2),
  ),
);

const InputDecoration otpInputDecoration = InputDecoration(
  contentPadding: EdgeInsets.zero,
  counterText: "",
  errorStyle: TextStyle(height: 0),
);

const kErrorBorderSide = BorderSide(color: Colors.red, width: 1);

// Validator
final passwordValidator = MultiValidator([
  RequiredValidator(errorText: 'Password is required'),
  MinLengthValidator(8, errorText: 'Password must be at least 8 digits long'),
  PatternValidator(r'(?=.*?[#?!@$%^&*-/])',
      errorText: 'Passwords must have at least one special character')
]);

final emailValidator = MultiValidator([
  RequiredValidator(errorText: 'Email is required'),
  EmailValidator(errorText: 'Enter a valid email address')
]);

final requiredValidator =
    RequiredValidator(errorText: 'This field is required');
final matchValidator = MatchValidator(errorText: 'passwords do not match');

final phoneNumberValidator = MinLengthValidator(10,
    errorText: 'Phone Number must be at least 10 digits long');

// Common Text
final Center kOrText = Center(
    child: Text("Or", style: TextStyle(color: kMainColor.withOpacity(0.7))));