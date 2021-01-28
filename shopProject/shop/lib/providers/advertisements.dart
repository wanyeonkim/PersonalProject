import 'package:flutter/cupertino.dart';
import 'product.dart';

import 'package:provider/provider.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import '../models/http_exception.dart';
import 'advertisement.dart';

class Advertisements with ChangeNotifier {
  List<Advertisement> _ads = [];

  final String authToken;
  final String userId;

  Advertisements(
    this.authToken,
    this.userId,
    this._ads,
  );

  List<Advertisement> get ads {
    return [..._ads];
  }

  Advertisement findById(String id) {
    return _ads.firstWhere((prod) => prod.id == id);
  }

  ///fetch and set advertisement
  Future<void> fetchAndSetAdvertisement([bool filterByUser = false]) async {
    final filterString =
        filterByUser ? 'orderBy="creatorId"&equalTo="$userId"' : '';
    var url =
        'https://db-practice-14b7e-default-rtdb.firebaseio.com/advertisements.json?auth=$authToken&$filterString';
    try {
      final response = await http.get(url);
      final extractedData = jsonDecode(response.body) as Map<String, dynamic>;
      if (extractedData == null) {
        return;
      }

      url =
          'https://db-practice-14b7e-default-rtdb.firebaseio.com/userFavorites/$userId.json?auth=$authToken';
      final favoriteResponse = await http.get(url);
      final favoriteData = json.decode(favoriteResponse.body);

      final List<Advertisement> loadedProducts = [];
      extractedData.forEach((prodId, prodData) {
        loadedProducts.add(Advertisement(
          id: prodId,
          imageUrl: prodData['imageUrl'],
        ));
      });
      _ads = loadedProducts;
      notifyListeners();
    } catch (e) {
      throw e;
    }
  }

  /// add advertisement
  Future<void> addAdvertisement(Advertisement product) async {
    final url =
        'https://db-practice-14b7e-default-rtdb.firebaseio.com/advertisements.json?auth=$authToken';
    try {
      final response = await http.post(
        url,
        body: json.encode(
          {
            'imageUrl': product.imageUrl,
            'creatorId': userId,
          },
        ),
      );
      final newProduct = Advertisement(
        imageUrl: product.imageUrl,
        id: json.decode(response.body)['name'],
      );
      // _ads.add(newProduct);
      _ads.insert(0, newProduct); //adding it to front of the list
      notifyListeners();
    } catch (e) {
      print(e);
      throw e;
    }
  }

  /// update the current
  Future<void> updateAdvertisement(String id, Advertisement newProduct) async {
    //getting the index of the index of the product from the product list.
    final prodIndex = _ads.indexWhere((prod) => prod.id == id);
    if (prodIndex >= 0) {
      final url =
          'https://db-practice-14b7e-default-rtdb.firebaseio.com/advertisements/$id.json?auth=$authToken';

      await http.patch(url,
          body: json.encode({
            'imageUrl': newProduct.imageUrl,
          }));
      _ads[prodIndex] = newProduct;
      notifyListeners();
    } else {
      print('...');
    }
  }


  ///deleting advertisement
  Future<void> deleteAdvertisement(String id) async {
    final url =
        'https://db-practice-14b7e-default-rtdb.firebaseio.com/advertisements/$id.json?auth=$authToken';
    final existingProductIndex = _ads.indexWhere((prod) => prod.id == id);
    var existingProduct = _ads[existingProductIndex];
    _ads.removeAt(existingProductIndex);
    notifyListeners();
    final response = await http.delete(url).then(
      (response) {
        if (response.statusCode >= 400) {
          _ads.insert(existingProductIndex, existingProduct);
          notifyListeners();
          throw HttpException('could not delete product!');
        }
        existingProduct = null;
      },
    );
  }
}
