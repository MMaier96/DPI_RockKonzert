package application.logger;

public class Logger {

	public static void printError(String msg) {
		String[] classNameSplit = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
		System.out.println("   [ERR] <" + classNameSplit[classNameSplit.length - 1] + ">:   " + msg);
	}

	public static void printInfo(String msg) {
		String[] classNameSplit = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
		System.out.println("   [INFO] <" + classNameSplit[classNameSplit.length - 1] + ">:   " + msg);
	}

	public static void printMessage(String msg) {
		System.out.println("\n\n ----- [ " + msg + " ] -----");
	}

	public static void printSeperator() {
		System.out.println("\n\n");
	}

	public static void printWarning(String msg) {
		String[] classNameSplit = Thread.currentThread().getStackTrace()[2].getClassName().split("\\.");
		System.out.println("   [WARN] <" + classNameSplit[classNameSplit.length - 1] + ">:   " + msg);
	}
}
