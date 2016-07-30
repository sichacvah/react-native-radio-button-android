package io.sichacvah.react.radio_button;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import android.app.Activity;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class ComponentsPackage implements ReactPackage {
  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
    return Collections.emptyList();
  }


  @Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
    return Collections.emptyList();
  }

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
    return Arrays.<ViewManager>asList(
      new RadioButtonManager()
    );
  }
}
