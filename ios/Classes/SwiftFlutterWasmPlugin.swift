/* This is free and unencumbered software released into the public domain. */

import Flutter
import Flutter_wasm_vm
import UIKit

public class SwiftFlutterWasmPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_wasm", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterWasmPlugin(registrar: registrar)
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  let registrar: FlutterPluginRegistrar

  init(registrar: FlutterPluginRegistrar) {
    self.registrar = registrar
    super.init()
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    switch (call.method) {
      case "getVersion":
        result(Flutter_wasm_vmVersion())

      default:
        result(FlutterMethodNotImplemented)
    }
  }
}
