package main;

public class Line {
	private String delims = "[ /,=]+";
	private String[] tokens;

	public Line(String line) {
		this.tokens = line.split(delims);
	}

	public String getToken(int i) {
		String lineField = tokens[i];
		return lineField;
	}
}
