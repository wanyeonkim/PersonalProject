import 'package:flutter/foundation.dart';
import 'package:http/http.dart' as http;

import 'dart:convert';

class Advertisement with ChangeNotifier {
  final String id;
  final String imageUrl;

  Advertisement({
    @required this.id,
    @required this.imageUrl,
  });
}
