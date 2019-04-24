/* This is free and unencumbered software released into the public domain. */

import 'dart:async' show Future;

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_wasm/flutter_wasm.dart' show Wasm;

void main() async {
  final version = await Wasm.version;
  print("Wasm $version");

  // TODO
}
