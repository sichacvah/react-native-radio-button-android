'use strict';

import React from 'react';

import {
  View,
  StyleSheet,
  Dimensios,
  requireNativeComponent
} from 'react-native';
import NativeMethodsMixin from 'NativeMethodsMixin';

class RadioButton extends React.Component {
  constructor(props, context) {
    super(props, context);
    this._rctRadioButton = {};
    this.name = 'RadioButtonAndroid';
  }

  _onChange(event) {
    this.refs['RCTRadioButton'].setNativeProps({on: this.props.value});

    this.props.onChange && this.props.onChange(event);
    this.props.onValueChange && this.props.onValueChange(event.nativeEvent.event);
  }

  render() {
    const props = {...this.props};
    props.onStartShouldSetResponder = () => false;
    props.onResponderTerminationRequest = () => false;
    props.on = this.props.value;
    props.style = this.props.style;

    return (
      <RCTRadioButton {...props} ref={'RCTRadioButton'} onChange={this._onChange.bind(this)} />
    );
  }
}

RadioButtonAndroid.mixins = [NativeMethodsMixin];

RadioButtonAndroid.defaultProps = {
  value: false
};

RadioButtonAndroid.propTypes = {
  value: React.PropTypes.bool,
  text: React.PropTypes.string,
  onValueChange: React.PropTypes.func,
  ...View.propTypes
};

export default RadioButton;
