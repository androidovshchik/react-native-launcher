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
import {BackHandler, Button, StyleSheet, View,} from 'react-native';
import LauncherPlugin from 'react-native-launcher';

class App extends Component {

    render() {
        return (
            <View style={styles.container}>
                <View style={[styles.button, {marginTop: 16}]}>
                    <Button
                        title="openExact"
                        onPress={() => {
                            LauncherPlugin.openExact(0, {
                                delay: 0
                            })
                            BackHandler.exitApp()
                        }}/>
                </View>
                <View style={styles.button}>
                    <Button
                        title="openAndAllowWhileIdle"
                        onPress={() => {
                            LauncherPlugin.openAndAllowWhileIdle(1, {
                                method: "openAndAllowWhileIdle"
                            })
                            BackHandler.exitApp()
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
