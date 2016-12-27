package io.sichacvah.react.radio_button;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import android.widget.CompoundButton;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;


public class RadioButtonManager extends SimpleViewManager<RadioButtonView> {
  private final static String REACT_CLASS = "RCTRadioButtonAndroid";


  private static final CompoundButton.OnCheckedChangeListener ON_CHECKED_CHANGE_LISTENER =
    new CompoundButton.OnCheckedChangeListener() {
      @Override 
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ReactContext reactContext = (ReactContext) buttonView.getContext();
        reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher().dispatchEvent(
            new RadioButtonEvent(
              buttonView.getId(),
              SystemClock.nanoTime(),
              isChecked));
      }
    };


  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected RadioButtonView createViewInstance(ThemedReactContext context) {
    RadioButtonView view = new RadioButtonView(context);
    view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
    return view;
  }

  @Override
  protected void addEventEmitters(final ThemedReactContext reactContext, final RadioButtonView view) {
    view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
  }

  @ReactProp(name = ViewProps.ON)
  public void setOn(RadioButtonView view, boolean on) {
    view.setOnCheckedChangeListener(null);
    view.setOn(on);
    view.setOnCheckedChangeListener(ON_CHECKED_CHANGE_LISTENER);
  }

  @ReactProp(name = "disabled", defaultBoolean = false)
  public void setEnabled(RadioButtonView checkbox, boolean disabled) {
    checkbox.setEnabled(!disabled);
  }

}
