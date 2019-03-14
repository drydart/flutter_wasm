#import "FlutterWasmPlugin.h"
#import <flutter_wasm/flutter_wasm-Swift.h>

@implementation FlutterWasmPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterWasmPlugin registerWithRegistrar:registrar];
}
@end
