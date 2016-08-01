import {
  Platform
} from 'react-native';
import IOSRadio from './index.ios';
import AndroidRadio from './index.android';

let RadioButton = (Platform.OS === 'android' ? AndroidRadio : IOSRadio);
export default RadioButton;
