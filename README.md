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

### Credits

Author [Vlad Kalyuzhnyu](https://github.com/androidovshchik)
