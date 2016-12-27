package io.sichacvah.react.radio_button;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.RCTEventEmitter;


class RadioButtonEvent extends Event<RadioButtonEvent> {

  public static final String EVENT_NAME = "topChange";

  private final boolean mIsChecked;

  public RadioButtonEvent(int viewId, long timestampMs, boolean isChecked) {
    super();
    init(viewId);
    mIsChecked = isChecked;
  }

  public boolean getIsChecked() {
    return mIsChecked;
  }

  @Override
  public String getEventName() {
    return EVENT_NAME;
  }

  @Override
  public short getCoalescingKey() {
    return 0;
  }

  @Override
  public void dispatch(RCTEventEmitter rctEventEmitter) {
    rctEventEmitter.receiveEvent(getViewTag(), getEventName(), serializeEventData());
  }

  private WritableMap serializeEventData() {
    WritableMap eventData = Arguments.createMap();
    eventData.putInt("target", getViewTag());
    eventData.putBoolean("value", getIsChecked());
    return eventData;
  }
}
