import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class dwlHelloWorld {
	public static int countLines(String path) {
		int numberOfLines = 0;
		BufferedReader br = null;
		try {
			FileReader fr = new FileReader(path);
			br = new BufferedReader(fr);
			String aLine = null;
			while ((aLine = br.readLine()) != null) {
				numberOfLines++;
			}
		} catch (Exception e) {
			// TODO
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return numberOfLines;
	}

	public static void main(String[] args) {
		File newFile = new File("C:/Bimedit/work/JCL_Out.txt");
		BufferedReader textReader = null;

		try {
			FileReader reader = new FileReader(newFile);
			textReader = new BufferedReader(reader);
			int numberOfLines = countLines("C:/Bimedit/work/JCL_Out.txt");

			String[] textData = new String[numberOfLines];
			for (int i = 0; i < numberOfLines; i++) {
				textData[i] = textReader.readLine();
				System.out.println(textData[i]);
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
