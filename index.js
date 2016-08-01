import {
  Platform
} from 'react-native';
import IOSRadio from './index.ios.js';
import AndroidRadio from './index.android.js';

let RadioButton = (Platform.OS === 'android' ? AndroidRadio : IOSRadio);
export default RadioButton;
