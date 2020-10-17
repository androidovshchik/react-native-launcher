declare module "react-native-launcher" {
    const canDrawOverlays: () => Promise<boolean>;
    const requestDrawOverlays: () => Promise<number>;
    const openExact: (delay: number, args?: object) => void;
    const openAndAllowWhileIdle: (delay: number, args?: object) => void;
    const openExactAndAllowWhileIdle: (delay: number, args?: object) => void;
    const getLaunchArgs: (callback: (args?: object) => void) => void;
    const cancelOpen: (delay: number, args?: object) => void;
}