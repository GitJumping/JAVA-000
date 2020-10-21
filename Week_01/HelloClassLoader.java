import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) {
        try {
            Class clazz = new HelloClassLoader().findClass("Hello");
            Constructor<?> constructor = clazz.getConstructor();
            try {
                Object obj = constructor.newInstance();
                Method method = clazz.getMethod("hello");
                method.invoke(obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = HelloClassLoader.class.getClassLoader().getResourceAsStream("/Users/john/my_files/documents/work_documents/intellij_idea/my_geek_time_learn/geek_univsersity/java_advanced/JAVA-000/Week_01/Hello.xlass");
        byte[] bytes = new byte[0];
        try {
            System.out.println(inputStream);
            bytes = new byte[inputStream.available()];
            inputStream.read();
            for(int i=0; i<bytes.length; i++){
                bytes[i] = (byte)(255 - bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
