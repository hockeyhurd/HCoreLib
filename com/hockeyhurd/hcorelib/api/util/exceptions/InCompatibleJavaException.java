package com.hockeyhurd.hcorelib.api.util.exceptions;

import com.hockeyhurd.hcorelib.api.util.SystemInfo;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;

/**
 * Exception to be used when java version is incompatible/unsupported.
 *
 * @author hockeyhurd
 * @version 5/18/2016.
 */
public class InCompatibleJavaException extends RuntimeException {

	public InCompatibleJavaException(String message) {
		super(message);
	}

	/**
	 * Static class for running checks.
	 *
	 * @author hockeyhurd
	 * @version 5/18/16
	 */
	public static class JavaCompatibility {
		private JavaCompatibility() {
		}

		/**
		 * Runs Java version checker.
		 *
		 * @param javaVersion Java version to check for.
		 * @return boolean result.
		 */
		public static boolean runCheck(JavaVersion javaVersion) {
			if (!SystemUtils.isJavaVersionAtLeast(javaVersion))
				throw new InCompatibleJavaException(String.format("Version: %s of java is not supported! Please update your Java to at least version %s!",
						SystemInfo.instance().getJavaVersion(), javaVersion.toString()));

			return true;
		}

	}

}
