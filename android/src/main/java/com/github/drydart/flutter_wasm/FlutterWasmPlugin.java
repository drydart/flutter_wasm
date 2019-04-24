/* This is free and unencumbered software released into the public domain. */

package com.github.drydart.flutter_wasm;

import com.github.drydart.flutter_wasm_vm.Flutter_wasm_vm;
import com.github.drydart.flutter_wasm_vm.State;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import java.io.IOException;

/** FlutterWasmPlugin */
public class FlutterWasmPlugin extends FlutterMethodCallHandler {
  private static final String TAG = "FlutterWasmPlugin";
  public static final String CHANNEL = "flutter_wasm";

  FlutterWasmPlugin(final Registrar registrar) {
    super(registrar);
  }

  /** Plugin registration. */
  public static void registerWith(final Registrar registrar) {
    assert(registrar != null);

    (new MethodChannel(registrar.messenger(), CHANNEL))
      .setMethodCallHandler(new FlutterWasmPlugin(registrar));
  }

  @Override
  public void onMethodCall(final MethodCall call, final Result result) {
    assert(call != null);
    assert(result != null);

    assert(call.method != null);
    switch (call.method) {

      case "getVersion": {
        result.success(Flutter_wasm_vm.version());
        break;
      }

      default: {
        result.notImplemented();
      }
    }
  }
}
