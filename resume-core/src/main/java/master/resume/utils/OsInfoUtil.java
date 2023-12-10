package master.resume.utils;


import master.resume.enums.OsType;

public class OsInfoUtil {
    private static String OS = System.getProperty("os.name").toLowerCase();

    private static OsInfoUtil _instance = new OsInfoUtil();

    private OsType platform;

    private OsInfoUtil() {
    }

    public static boolean isLinux() {
        return OS.indexOf("linux") >= 0;
    }

    public static boolean isMacOS() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") < 0;
    }

    public static boolean isMacOSX() {
        return OS.indexOf("mac") >= 0 && OS.indexOf("os") > 0 && OS.indexOf("x") > 0;
    }

    public static boolean isWindows() {
        return OS.indexOf("windows") >= 0;
    }

    /**
     * 获取操作系统名字
     *
     * @return 操作系统名
     */
    public static OsType getOSname() {
        if (isLinux()) {
            _instance.platform = OsType.Linux;
        } else if (isMacOS()) {
            _instance.platform = OsType.Mac_OS;
        } else if (isMacOSX()) {
            _instance.platform = OsType.Mac_OS_X;
        } else if (isWindows()) {
            _instance.platform = OsType.Windows;
        }
        return _instance.platform;
    }
}


