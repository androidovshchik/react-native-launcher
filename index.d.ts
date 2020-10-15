declare module "react-native-launcher" {
    const openExact: (delay: number, data?: object) => void;
    const openAndAllowWhileIdle: (delay: number, data?: object) => void;
    const openExactAndAllowWhileIdle: (delay: number, data?: object) => void;
}