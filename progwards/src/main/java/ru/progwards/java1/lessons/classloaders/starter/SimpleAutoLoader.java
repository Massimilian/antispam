package ru.progwards.java1.lessons.classloaders.starter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleAutoLoader extends ClassLoader {
    final static String PATH_OF_TASKS = "d:/progwards/testDir";
    final static String DOT_CLASS = ".class";
    private static SimpleAutoLoader loader = new SimpleAutoLoader(PATH_OF_TASKS);

    private final String basePath;

    public SimpleAutoLoader(String basePath) {
        this(basePath, ClassLoader.getSystemClassLoader());
    }

    public SimpleAutoLoader(String basePath, ClassLoader parent) {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            String classPath = className.replace(".", "/"); // в полученном и имени меняем все точки на слэши
            Path classPathName = Paths.get(basePath + classPath + DOT_CLASS); // добавляем в начале необходимую папку, а в конце - расширение .class
            if (Files.exists(classPathName)) { // если такой класс найден
                byte b[] = Files.readAllBytes(classPathName); // то превращаем его в массив байт и отправляем в metaspace
                return defineClass(className, b, 0, b.length);
            } else { // если нет - значит, это системный класс. Отправляем его в System-загрузчик
                return findSystemClass(className);
            }
        } catch (IOException ex) {
            throw new ClassNotFoundException(className);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findClass(name); // находим класс по имени
        if (resolve)
            resolveClass(c); // если необходимо - бросаем исключение (если класс не найден, а resolve=true)
        return c;
    }

    private static String makeClassName(Path path) throws IOException {
        path = path.toAbsolutePath().toRealPath(); // получение полного безотносительного маршрута
        Path relPath = Paths.get(PATH_OF_TASKS).relativize(path); // "релятивизирует" адрес, вытаскивая из обоих адресов дублированное начало и уничтожая его
        String className = relPath.toString().replaceAll("[\\/\\\\]", "."); // заменяем все слэши на точки
        if (className.toLowerCase().endsWith(DOT_CLASS)) { // если название заканчивается на .class
            className = className.substring(0, className.length() - DOT_CLASS.length()); // ... то обрубаем это окончание
        }
        return className; // возвращаем полученную строку с именем класса
    }

    private static void updateTaskList(Map<String, Task> tasks)
            throws IOException {
        Files.walkFileTree(Paths.get(PATH_OF_TASKS), new SimpleFileVisitor<Path>() { // используем проход по дереву файлов, которые находятся внутри адреса PATH_OF_TASKS
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(DOT_CLASS)) {  // для каждого файла проверяем, оканчивается ли он на .class
                    String className = makeClassName(path); // формируем имя класса (этот метод будет рассмотрен ниже)
                    Task task = tasks.get(className);  // проверяем наличие такого класса в списке классов. Если он уже есть - то делать ничего не нужно
                    if (task == null ) { // А если такого класса нет
                        try {
                            Class taskClass = loader.loadClass(className, true); // записываем этот класс через static экземпляр нашего ClassLoader в metaspace и возвращаем экземпляр в формате Class
                            Task newTask = (Task)taskClass.getDeclaredConstructor().newInstance(); // вызываем конструктор этого класса (имплементации Task) и создаём экземпляр этого класса
                            tasks.put(className, newTask); // помещаем этот класс внутрь словаря tasks
                            System.out.println("Добавлен класс " + className); // пишем об этом информацию
                        } catch (ClassNotFoundException |
                                IllegalAccessException |
                                InstantiationException |
                                NoSuchMethodException |
                                InvocationTargetException e) {
                            e.printStackTrace(); // обрабатываем аозможные ошибки
                        }
                    }
                }
                return FileVisitResult.CONTINUE; // метод завершён
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });

    }

    public static void main(String[] args) throws Exception {
        Map<String, Task> tasks = new LinkedHashMap<>();

        while(true) {
            System.out.println("Проверка классов и запуск задач: " +
                    String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date()));

            updateTaskList(tasks);

            SecureRandom random = new SecureRandom();
            byte[] data = new byte[1000];
            random.nextBytes(data);

            for (Map.Entry<String, Task> task : tasks.entrySet())
                System.out.println("     " + task.getValue().process(data));

            Thread.sleep(5_000);
        }
    }
}
