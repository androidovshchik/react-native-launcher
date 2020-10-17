## React Native Launcher
> App launcher via alarm

<a href="https://www.npmjs.com/package/react-native-launcher">
  <img src="https://badge.fury.io/js/react-native-launcher.png" height="18" alt="npm version">
</a>

### Install

```bash
npm install react-native-launcher --save
```

### Setup

* In `android/settings.gradle`

```gradle
//...
include ':react-native-launcher'
```

* In `android/app/build.gradle`

```gradle
dependencies {
    //...
    implementation project(':react-native-launcher')
    //...
}
```

### Usage

Import

```typescript
import LauncherPlugin from 'react-native-launcher';
```

An example can be found [here](https://github.com/androidovshchik/react-native-launcher/blob/master/example/App.tsx)

### Notice

`delay` param works as a delayed time before launch and as a unique identifier

`canDrawOverlays` may not work on Android Oreo. See [this question](https://stackoverflow.com/questions/46173460/why-in-android-8-method-settings-candrawoverlays-returns-false-when-user-has)

`getLaunchArgs` only works for new intents and not for the first intent

### Credits

Author [Vlad Kalyuzhnyu](https://github.com/androidovshchik)
