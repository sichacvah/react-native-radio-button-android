package io.sichacvah.react.radio_button;

import android.os.Build;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import android.app.Activity;

import android.support.v4.view.ViewPager;
import android.view.View;

import javax.annotation.Nullable;
import java.util.Map;

import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.facebook.csslayout.CSSMeasureMode;
import com.facebook.csslayout.CSSNode;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.SystemClock;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;




public class RadioButtonManager extends SimpleViewManager<RadioButtonView> {
  private final static String REACT_CLASS = "RCTRadioButtonAndroid";

  static class RadioButtonShadowNode extends LayoutShadowNode implements CSSNode.MeasureFunction {
     private int mWidth;
     private int mHeight;
     private boolean mMeasured;

     private RadioButtonShadowNode() {
       setMeasureFunction(this);
     }

     @Override
     public void measure(
         CSSNode node,
         float width,
         CSSMeasureMode widthMode,
         float height,
         CSSMeasureMode heightMode,
          MeasureOutput measureOutput
         ) {

       if (!mMeasured) {
         RadioButtonView radioButton = new RadioButtonView(getThemedContext());
         final int spec = View.MeasureSpec.makeMeasureSpec(ViewGroup.LayoutParams.WRAP_CONTENT, View.MeasureSpec.UNSPECIFIED);
         radioButton.measure(spec, spec);
         mWidth = radioButton.getMeasuredWidth();
         mHeight = radioButton.getMeasuredHeight();
         mMeasured = true;
       }
        measureOutput.width = mWidth;
        measureOutput.height = mHeight;

    }
  }   


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
  public LayoutShadowNode createShadowNodeInstance() {
    return new RadioButtonShadowNode();
  }

  @Override
  public Class getShadowNodeClass() {
    return RadioButtonShadowNode.class;
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @Override
  protected RadioButtonView createViewInstance(ThemedReactContext context) {
    RadioButtonView view = new RadioButtonView(context);
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

}
