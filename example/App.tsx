/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * Generated with the TypeScript template
 * https://github.com/react-native-community/react-native-template-typescript
 *
 * @format
 */

import React, {Component} from 'react';
import {BackHandler, Button, StyleSheet, ToastAndroid, View,} from 'react-native';
import LauncherPlugin from 'react-native-launcher';

class App extends Component {

    render() {
        console.log(this.props);
        ToastAndroid.show(JSON.stringify(this.props, null, 2), ToastAndroid.LONG);
        return (
            <View style={styles.container}>
                <View style={[styles.button, {marginTop: 16}]}>
                    <Button
                        title="canDrawOverlays"
                        onPress={() => {
                            (async () => {
                                try {
                                    // @ts-ignore
                                    const isGranted = await LauncherPlugin.canDrawOverlays();
                                    ToastAndroid.show(`isGranted: ${isGranted}`, ToastAndroid.LONG);
                                } catch (e) {
                                    console.error(e);
                                    ToastAndroid.show(e.toString(), ToastAndroid.LONG);
                                }
                            })();
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="requestDrawOverlays"
                        onPress={() => {
                            (async () => {
                                try {
                                    // @ts-ignore
                                    const result = await LauncherPlugin.requestDrawOverlays();
                                    ToastAndroid.show(`Result: ${result}`, ToastAndroid.LONG);
                                } catch (e) {
                                    console.error(e);
                                    ToastAndroid.show(e.toString(), ToastAndroid.LONG);
                                }
                            })();
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="openExact"
                        onPress={() => {
                            LauncherPlugin.openExact(0, {
                                argDelay: 0
                            })
                            BackHandler.exitApp()
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="openAndAllowWhileIdle"
                        onPress={() => {
                            LauncherPlugin.openAndAllowWhileIdle(2000, {
                                method: "openAndAllowWhileIdle"
                            })
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="openExactAndAllowWhileIdle"
                        onPress={() => {
                            LauncherPlugin.openExactAndAllowWhileIdle(2, undefined)
                            BackHandler.exitApp()
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="getLaunchArgs"
                        onPress={() => {
                            LauncherPlugin.getLaunchArgs((args?: object) => {
                                ToastAndroid.show(JSON.stringify(args, null, 2), ToastAndroid.LONG);
                            })
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="cancelOpen"
                        onPress={() => {
                            // For example cancelling openAndAllowWhileIdle action
                            LauncherPlugin.cancelOpen(2000, {
                                method: "openAndAllowWhileIdle"
                            })
                        }}/>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        display: "flex",
        flexDirection: "column"
    },
    button: {
        marginStart: 16,
        marginEnd: 16,
        marginBottom: 16
    },
});

export default App;
