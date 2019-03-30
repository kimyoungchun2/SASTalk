package sastalk3.generic.inheritance;

public class StorageImpl<T> implements Storage<T> {

	private T[] array;
	
	@SuppressWarnings("unchecked")
	public StorageImpl(int capacity) {
		this.array = (T[]) (new Object[capacity]);
	}
	
	@Override
	public void add(T item, int index) {
		this.array[index] = item;
	}

	@Override
	public T get(int index) {
		return this.array[index];
	}

}
