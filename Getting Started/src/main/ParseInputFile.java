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

		String jobTempFileName = null;
		String rptLength = null;
		String ftpParmLibName = null;
		String ftpPath = null;
		String ftpRptFileName = null;

		boolean somethingToPrint = false;
		Line lineObj;
		OutLine myOutLine = new OutLine();

		try {
			FileReader reader = new FileReader(newFile);
			textReader = new BufferedReader(reader);
			BufferedReader br = null;
			br = new BufferedReader(reader);
			String aLine = null;

			while ((aLine = br.readLine()) != null) {
				lineObj = new Line(aLine);
				if (aLine.contains(" JOB ")) {
					if (somethingToPrint) {
						myOutLine.printOutLine();
						somethingToPrint = true;
					}
					myOutLine.clearOutLine();
					somethingToPrint = true;
					lineObj = new Line(aLine);
					myOutLine.setJobName(lineObj.getToken(1));
					myOutLine.setJobProgName(lineObj.getToken(4));
				} else if (aLine.contains(" CLASS=")) {
					myOutLine.setJobClass(lineObj.getToken(2));
				} else if (aLine.contains("MSGCLASS=")) {
					myOutLine.setJobMsgClass(lineObj.getToken(2));
				} else if (aLine.contains(" SET JOBMBR")) {
					myOutLine.setJobMemberName(lineObj.getToken(4));
				} else if (aLine.contains(" SET ENV")) {
					myOutLine.setJobEnvironment(lineObj.getToken(4));
				} else if (aLine.contains(" SET  RG")) {
					myOutLine.setJobRegion(lineObj.getToken(4));
				} else if (aLine.contains(" EXEC ")) {
					myOutLine.setJobProgram(lineObj.getToken(4));
					somethingToPrint = true;
				} else if (aLine.contains(" SYSOUT=(")) {
					myOutLine.setGenSysNumber(lineObj.getToken(1));
					myOutLine.setGenPrintClass(lineObj.getToken(3));
					myOutLine.setGenWriterName(lineObj.getToken(5));
				} else if (aLine.contains(" DUMMY")) {
					myOutLine.setGenSysNumber(lineObj.getToken(1));
					myOutLine.setGenPrintClass(lineObj.getToken(3));
					myOutLine.printOutLine();
					somethingToPrint = true;
				} else if (aLine.contains(" DEST=")) {
					myOutLine.setGenPrintDest(lineObj.getToken(2));
				} else if (aLine.contains(" OUTPUT=")) {
					myOutLine.setGenFormId(lineObj.getToken(2));
					myOutLine.printOutLine();
					myOutLine.clearPrintParms();
					somethingToPrint = false;
				}
			}
			if (somethingToPrint) {
				myOutLine.printOutLine();
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
}
