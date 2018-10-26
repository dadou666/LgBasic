package execution;

public class LgClassLoader extends ClassLoader {
	public LgClassLoader(ClassLoader classloader) {
		super(classloader);

	}

	public LgClassLoader() {

	}

	public Class define(String name, byte[] bytes) throws ClassNotFoundException {

		return defineClass(name, bytes, 0, bytes.length);

	}
}
