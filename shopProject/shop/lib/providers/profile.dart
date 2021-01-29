import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'dart:async';
import 'dart:convert';
import '../models/http_exception.dart';

import 'package:shared_preferences/shared_preferences.dart';

class ProfileData with ChangeNotifier {
  List<String> _Infos = [];
  final String userId;
  String _fullName;
  String _emailId;
  final String authToken;

  ProfileData(
    this.authToken,
    this.userId,
    this._fullName,
    this._emailId,
  );

  dynamic get fullname{
    return _fullName;
  }

  dynamic get email{
    return _emailId;
  }
  List<String> get userD {
    return [..._Infos];
  }

  Future<void> fetchUserData() async {
    final url =
        'https://db-practice-14b7e-default-rtdb.firebaseio.com/userInfo/$userId.json?auth=$authToken';
    final response = await http.get(url);
    final extractedData = json.decode(response.body) as Map<String, dynamic>;
    if (extractedData == null) {
      return;
    }

    ///this is created as it will prevent null data causes by async
    // final List<String> loadedData = [];
    extractedData.forEach((dataId, dataContent) {
      _Infos.add(dataContent['email']);
      _Infos.add(dataContent['fullName']);
    });
  }
}
