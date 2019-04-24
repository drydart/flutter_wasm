/* This is free and unencumbered software released into the public domain. */

import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_wasm/flutter_wasm.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_wasm');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('version', () async {
    expect(await Wasm.version, '42');
  });
}
