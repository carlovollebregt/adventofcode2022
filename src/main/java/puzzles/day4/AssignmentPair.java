package puzzles.day4;

public record AssignmentPair(int start1, int end1, int start2, int end2) {

	public static AssignmentPair fromString(String input) {
		String[] assignments = input.split(",");
		String[] assigment1 = assignments[0].split("-");
		String[] assigment2 = assignments[1].split("-");
		return new AssignmentPair(Integer.parseInt(assigment1[0]), Integer.parseInt(assigment1[1]), Integer.parseInt(assigment2[0]), Integer.parseInt(assigment2[1]));
	}

	public boolean overlapsCompletely() {
		return (start1 <= start2 && end1 >= end2) || (start2 <= start1 && end2 >= end1);
	}

	public boolean overlapsParty() {
		return start1 <= end2 && end1 >= start2;
	}
}
