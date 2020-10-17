declare module "react-native-launcher" {
    const canDrawOverlays: (promise: Promise<boolean>) => void;
    const requestDrawOverlays: (promise?: Promise<number>) => void;
    const openExact: (delay: number, data?: object) => void;
    const openAndAllowWhileIdle: (delay: number, data?: object) => void;
    const openExactAndAllowWhileIdle: (delay: number, data?: object) => void;
    const cancelOpen: (delay: number, data?: object) => void;
}