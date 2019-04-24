/* This is free and unencumbered software released into the public domain. */

package com.github.drydart.flutter_wasm;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/** FlutterMethodCallHandler */
@SuppressWarnings("unchecked")
abstract class FlutterMethodCallHandler implements MethodCallHandler {
  final Registrar registrar;

  FlutterMethodCallHandler(final Registrar registrar) {
    this.registrar = registrar;
  }

  AssetManager
  getAssets() {
    return registrar.context().getAssets();
  }

  AssetFileDescriptor
  openAsset(final String assetName) throws IOException {
    final String assetKey = registrar.lookupKeyForAsset(assetName);
    return getAssets().openFd(assetKey);
  }

  String
  readAssetText(final String assetName) throws IOException {
    final String assetKey = registrar.lookupKeyForAsset(assetName);
    final InputStream assetStream = getAssets().open(assetKey, AssetManager.ACCESS_STREAMING);
    final ByteArrayOutputStream result = new ByteArrayOutputStream();
    {
      final byte[] buffer = new byte[8192];
      int length;
      while ((length = assetStream.read(buffer)) != -1) {
        result.write(buffer, 0, length);
      }
    }
    return result.toString("UTF-8");
  }

  Bitmap
  loadBitmap(final String assetName) throws IOException {
    return loadBitmap(assetName, null);
  }

  Bitmap
  loadBitmap(final String assetName,
             final BitmapFactory.Options options) throws IOException {
    final InputStream stream = openAsset(assetName).createInputStream();
    return BitmapFactory.decodeStream(stream, null, options);
  }

  static <T> T
  getRequiredArgument(final MethodCall call,
                      final String name) {
    assert(call != null);
    assert(name != null);

    if (!call.hasArgument(name)) {
      throw new AssertionError();
    }
    final T arg = call.argument(name);
    if (arg == null) {
      throw new AssertionError();
    }
    return arg;
  }

  static <T> T
  getOptionalArgument(final MethodCall call,
                      final String name) {
    return getOptionalArgument(call, name, (T)null);
  }

  static <T> T
  getOptionalArgument(final MethodCall call,
                      final String name,
                      final T defaultValue) {
    assert(call != null);
    assert(name != null);

    return call.hasArgument(name) && call.argument(name) != null ?
        (T)call.argument(name) : defaultValue;
  }
}
