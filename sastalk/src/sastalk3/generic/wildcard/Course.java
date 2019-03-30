package sastalk3.generic.wildcard;

public class Course<T> {

	private String name;
	private T[] students;
	
	@SuppressWarnings("unchecked")
	public Course(String name, int capacity) {
		this.name = name;
		this.students = (T[])(new Object[capacity]);
	}
	
	public String getName() { return this.name; }
	public T[] getStudents() { return this.students; }
	public void add(T t) {
		for (int i=0; i < students.length; i++) {
			if (students[i] == null) {
				students[i] = t;
				break;
			}
		}
	}
}
