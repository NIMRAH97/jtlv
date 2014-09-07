package edu.wis.jtlv.new_tb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestCaseUtil {

	public static boolean diff(String log_file, String run_file) {
		String comm = "diff " + log_file + " " + run_file;
		try {
			Process p = Runtime.getRuntime().exec(comm);
			return (p.getInputStream().read() != -1);
		} catch (IOException e) {
			System.err.println("Failed comparing refs and logs ...");
		}
		return true;
	}

	/**
	 * Returns <code>true</code> if both input streams byte contents is
	 * identical.
	 * 
	 * @param input1
	 *            first input to contents compare
	 * @param input2
	 *            second input to contents compare
	 * @return <code>true</code> if content is equal
	 */
	public static boolean diff2(String file_a, String file_b) {
		boolean ignoreWhitespace = true;
		InputStream is_a = null;
		InputStream is_b = null;

		try {
			is_a = new FileInputStream(file_a);
			is_b = new FileInputStream(file_b);
		} catch (FileNotFoundException e) {
			System.err.println("Failed comparing refs and logs ...");
			return true;
		}

		try {
			if (is_a == is_b)
				return false;

			if (is_a == null && is_b == null) // no byte contents
				return false;

			if (is_a == null || is_b == null) // only one has
				return true;

			while (true) {
				int c1 = is_a.read();
				while (ignoreWhitespace && isWhitespace(c1))
					c1 = is_b.read();
				int c2 = is_b.read();
				while (ignoreWhitespace && isWhitespace(c2))
					c2 = is_b.read();
				if (c1 == -1 && c2 == -1)
					return false;
				if (c1 != c2)
					break;
			}
		} catch (IOException ex) {
			System.err.println("Failed comparing refs and logs ...");
			return true;
		} finally {
			try {
				try {
					if (is_a != null) {
						is_a.close();
					}
				} finally {
					if (is_b != null) {
						is_b.close();
					}
				}
			} catch (IOException e) {
				// Ignore
			}
		}
		return true;
	}

	private static boolean isWhitespace(int c) {
		if (c == -1)
			return false;
		return Character.isWhitespace((char) c);
	}

	public static boolean diff3(String file_a, String file_b) {
		boolean res = true;
		FileInputStream fis_a = null;
		FileInputStream fis_b = null;
		try {
			fis_a = new FileInputStream(file_a);
			fis_b = new FileInputStream(file_b);
			int crl, crr;
			do {
				crl = fis_a.read();
				crr = fis_b.read();
			} while ((crl == crr) & (crl != -1) & (crr != -1));
			if (crl == crr) {
				res = false;
			}
		} catch (FileNotFoundException e1) {
			System.err.println("Couldn't find files.");
			res = true;
		} catch (IOException e) {
			System.err.println("Failed comparing refs and logs ...");
			res = true;
		}

		finally {
			if (fis_a != null)
				try {
					fis_a.close();
				} catch (IOException e) {
					;
				}
			if (fis_b != null)
				try {
					fis_b.close();
				} catch (IOException e) {
					;
				}
		}

		return res;
	}

	public static void copy(String from_name, String to_name)
			throws IOException {
		File from_file = new File(from_name);
		File to_file = new File(to_name);

		if ((!from_file.exists()) | (!from_file.isFile())
				| (!from_file.canRead())) {
			throw new IOException("Cannot open file " + from_file + " to read.");
		}

		if (to_file.exists())
			if (!to_file.canWrite())
				throw new IOException("Cannot open file " + to_file
						+ " to write.");

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(from_file);
			to = new FileOutputStream(to_file);
			byte[] buffer = new byte[4096];
			int bytes_read;
			while ((bytes_read = from.read(buffer)) != -1)
				to.write(buffer, 0, bytes_read);
		}

		finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}
			if (to != null)
				try {
					to.close();
				} catch (IOException e) {
					;
				}
		}
	}
}
