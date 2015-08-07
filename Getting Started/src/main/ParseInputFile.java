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
		Line lineObj;
		
		try {
			// int numberOfLines = 0;
			FileReader reader = new FileReader(newFile);
			textReader = new BufferedReader(reader);
			//int numberOfLines = countLines("C:/Bimedit/work/JCL_Out.txt");
			BufferedReader br = null;
			// String[] textData = new String[70];
			// FileReader fr = new FileReader(path);
			br = new BufferedReader(reader);
			String aLine = null;
			
			while ((aLine = br.readLine()) != null) {
				// System.out.println(aLine);
				lineObj = new Line(aLine);
				if (aLine.contains(" JOB ")) {
					if (somethingToPrint) {
						writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment,
								jobRegion, jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest,
								genFormId);
						somethingToPrint = true;
					}

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

					lineObj = new Line(aLine);
					jobName = lineObj.getToken(1);
					jobProgName = lineObj.getToken(4);
				}
				else if (aLine.contains(" CLASS=")) {
					jobClass = lineObj.getToken(2);
				}
				else if (aLine.contains("MSGCLASS=")) {
					jobMsgClass = lineObj.getToken(2);
				}
				else if (aLine.contains(" SET JOBMBR")) {
					jobMemberName = lineObj.getToken(4);
				}
				else if (aLine.contains(" SET ENV")) {
					jobEnvironment = lineObj.getToken(4);
				}
				else if (aLine.contains(" SET  RG")) {
					jobRegion = lineObj.getToken(4);
				}
				else if (aLine.contains(" EXEC ")) {
					jobProgram = lineObj.getToken(4);
					somethingToPrint = true;
				}
				else if (aLine.contains(" SYSOUT=(")) {
					genSysNumber = lineObj.getToken(1);
					genPrintClass = lineObj.getToken(4);
					genWriterName = lineObj.getToken(5);
				}
				else if (aLine.contains(" DUMMY")) {
					genSysNumber = lineObj.getToken(1);
					genPrintClass = lineObj.getToken(3);
					writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment, jobRegion,
							jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest, genFormId);
					somethingToPrint = true;
				}
				else if (aLine.contains(" DEST=")) {
					genPrintDest = lineObj.getToken(2);
				}
				else if (aLine.contains(" OUTPUT=")) {
					genFormId = lineObj.getToken(2);
					writeOutput(jobName, jobProgName, jobClass, jobMsgClass, jobMemberName, jobEnvironment, jobRegion,
							jobProgram, genSysNumber, genPrintClass, genWriterName, genPrintDest, genFormId);
					genSysNumber = "";
					genPrintClass = "";
					genWriterName = "";
					genPrintDest = "";
					genFormId = "";
					somethingToPrint = false;
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
