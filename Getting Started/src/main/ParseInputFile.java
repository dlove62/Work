package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ParseInputFile {
	public static void main(String[] args) {
		File newFile = new File("C:/Bimedit/work/JCL_Out.txt");
		BufferedReader textReader = null;
		String jobName = null;
		String jobProgName = null;
		String jobClass = null;
		String jobMsgClass = null;
		String jobMemberName = null;
		String jobEnvironment = null;
		String jobRegion = null;
		String jobProgram = null;
		String jobTempFileName = null;
		String rptLength = null;
		String ftpParmLibName = null;
		String ftpPath = null;
		String ftpRptFileName = null;
		String genSysNumber = null;
		String genPrintClass = null;
		String genWriterName = null;
		String genPrintDest = null;
		String genFormId = null;
		boolean somethingToPrint = false;
		try {
			// int numberOfLines = 0;
			FileReader reader = new FileReader(newFile);
			textReader = new BufferedReader(reader);
			// int numberOfLines = countLines("C:/Bimedit/work/JCL_Out.txt");
			BufferedReader br = null;
			// String[] textData = new String[70];
			// FileReader fr = new FileReader(path);
			br = new BufferedReader(reader);
			String aLine = null;
			while ((aLine = br.readLine()) != null) {
				// System.out.println(aLine);
				if (aLine.contains(" JOB ")) {
					if (somethingToPrint) {
						writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment,
								jobRegion, jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest,
								genFormId);
						somethingToPrint = true;
					}
					// } else {
					jobName = "";
					jobProgName = "";
					jobClass = "";
					jobMsgClass = "";
					jobMemberName = "";
					jobEnvironment = "";
					jobRegion = "";
					jobProgram = "";
					genSysNumber = "";
					genPrintClass = "";
					genWriterName = "";
					genPrintDest = "";
					genFormId = "";
					somethingToPrint = true;
					// }
					String delims = "[ /,()']+";
					String[] tokens = aLine.split(delims);
					jobName = tokens[1];
					jobProgName = tokens[4];

					// for (int i = 0; i < tokens.length; i++) {
					// System.out.println(tokens[i]);
					// }
				}
				if (aLine.contains(" CLASS=")) {
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					jobClass = tokens[2];
					// System.out.println(tokens[2]);
				}
				if (aLine.contains("MSGCLASS=")) {
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					jobMsgClass = tokens[2];
					// System.out.println(tokens[2]);
				}
				if (aLine.contains(" SET JOBMBR")) {
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					jobMemberName = tokens[4];
					// System.out.println(tokens[4]);
				}
				if (aLine.contains(" SET ENV")) {
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					jobEnvironment = tokens[4];
					// System.out.println(tokens[4]);
				}
				if (aLine.contains(" SET  RG")) {
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					jobRegion = tokens[4];
					// System.out.println(tokens[4]);
				}
				if (aLine.contains(" EXEC ")) {
					somethingToPrint = true;
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					jobProgram = tokens[4];
					// System.out.println(tokens[4]);
				}
				if (aLine.contains(" SYSOUT=(")) {
					String delims = "[ /,=()]+";
					String[] tokens = aLine.split(delims);
					genSysNumber = tokens[1];
					genPrintClass = tokens[4];
					genWriterName = tokens[5];
					// System.out.println(tokens[1] + ' ' +
					// tokens[4] + ' ' +
					// tokens[5]);
				}
				if (aLine.contains(" DUMMY")) {
					String delims = "[ /,=()]+";
					String[] tokens = aLine.split(delims);
					genSysNumber = tokens[1];
					genPrintClass = tokens[3];
					writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment, jobRegion,
							jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest, genFormId);
					somethingToPrint = true;
					// System.out.println(tokens[1] + ' ' +
					// tokens[4] + ' ' +
					// tokens[5]);
				}
				// genPrintDest = null;
				// String genFormId = null;
				if (aLine.contains(" DEST=")) {
					String delims = "[ /,=]+";
					String[] tokens = aLine.split(delims);
					genPrintDest = tokens[2];
					// System.out.println(tokens[2]);
				}
				if (aLine.contains(" OUTPUT=")) {
					String delims = "[ /*.,=]+";
					String[] tokens = aLine.split(delims);
					genFormId = tokens[2];
					writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment, jobRegion,
							jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest, genFormId);
					genSysNumber = "";
					genPrintClass = "";
					genWriterName = "";
					genPrintDest = "";
					genFormId = "";
					somethingToPrint = false;
					// System.out.println(tokens[2]);
				}
			}
			if (somethingToPrint) {
				writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment, jobRegion,
						jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest, genFormId);
			}
		} catch (Exception x) {
			// TODO
		} finally {
			if (textReader != null)
				try {
					textReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private static void writeOutput(String jobName, String jobProgName, String jobClass, String jobMsgClass,
			String jobMemberName, String jobEnvironment, String jobRegion, String jobProgram, String genSysNumber,
			String genPrintClass, String genWriterName, String genPrintDest, String genFormId) {
		System.out.println(jobName + ',' + jobProgName + ',' + jobClass + ',' + jobMsgClass + ',' + jobMemberName + ','
				+ jobEnvironment + ',' + jobRegion + ',' + jobProgram + ',' + genSysNumber + ',' + genPrintClass + ','
				+ genWriterName + ',' + genPrintDest + ',' + genFormId);
	}
}
