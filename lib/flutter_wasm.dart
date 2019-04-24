/* This is free and unencumbered software released into the public domain. */

/// WebAssembly interpreter for Flutter.
library flutter_wasm;

import 'dart:async' show Future;
//import 'dart:io' show File;

import 'package:flutter/services.dart' show MethodChannel, PlatformException;

const MethodChannel _channel = const MethodChannel('flutter_wasm');

abstract class Wasm {
  /// The current WebAssembly runtime version.
  static Future<String> get version async {
    return await _channel.invokeMethod('getVersion');
  }
}
