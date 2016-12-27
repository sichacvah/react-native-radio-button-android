'use strict';

import React from 'react';

import {
  View,
  StyleSheet,
  requireNativeComponent
} from 'react-native';
//import NativeMethodsMixin from 'NativeMethodsMixin';

class RadioButton extends React.Component {

  _onChange = (event) => {
    //this.refs['RCTRadioButton'].setNativeProps({on: this.props.value});

    this.props.onChange && this.props.onChange(event);
    this.props.onValueChange && this.props.onValueChange(event.nativeEvent.event);
  }

  render() {

    return (
      <RCTRadioButton
        ref={'RCTRadioButton'}
        style={[this.props.style, styles.radioButton]}
        on={this.props.value}
        disabled={this.props.disabled}
        onChange={this._onChange}
        />
    );
  }
}


RadioButton.defaultProps = {
  value: false,
  disabled: false,
};

const styles = StyleSheet.create({
  radioButton: {
    height: 27,
    width: 27,
  },
});

RadioButton.propTypes = {
  value: React.PropTypes.bool,
  text: React.PropTypes.string,
  onValueChange: React.PropTypes.func,
  disabled: React.PropTypes.bool,
  ...View.propTypes
};

const RCTRadioButton = requireNativeComponent('RCTRadioButtonAndroid', RadioButton, {
  nativeOnly: { onChange: true, on: true }
});
export default RadioButton;
