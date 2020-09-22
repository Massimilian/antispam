package ru.progwards.java1.lessons.classloaders.updater;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleAutoLoader extends ClassLoader {
    final static String PATH_OF_TASKS = "d:/progwards/testDir"; // папка, куда будут складываться классы для добавления
    final static String DOT_CLASS = ".class"; // расширение файла, подтверждающее, что это действительно класс
    private static SimpleAutoLoader loader = new SimpleAutoLoader(PATH_OF_TASKS); // статический ClassLoader, с помощью которого будет осуществляться вся работа

    private final String basePath;

    public SimpleAutoLoader(String basePath) {
        this(basePath, ClassLoader.getSystemClassLoader());
    }

    public SimpleAutoLoader(String basePath, ClassLoader parent) {
        super(parent);
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String className) throws ClassNotFoundException { // ищет указанный класс (полное имя класса передаётся в параметре)
        try {
            String classPath = className.replace(".", "/"); // в адресе меняем слэши на точки
            Path classPathName = Paths.get(basePath + classPath + DOT_CLASS); // добавляем базовый каталог и расширение. Таким образом, полный путь сформирован.
            if (Files.exists(classPathName)) { // проверяем - существует ли такой файл?
                byte b[] = Files.readAllBytes(classPathName); // Если да - считываем этот класс в массив байт
                return defineClass(className, b, 0, b.length); // создаём этот класс в metaspace, а название даём className
            } else { // если такого класса не существует
                return findSystemClass(className); // делегируем задачу загрузчику System
            }
        } catch (IOException ex) {
            throw new ClassNotFoundException(className);
        }
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) // стартует процесс загрузки класса с именем name (туда передаётся полностью весь адрес класса для загрузки)
            throws ClassNotFoundException {
        Class<?> c = findClass(name); // убираем из loadClass проверку кэша и делегирование (оно перекочевало в findClass. Теперь мы просто вызываем findClass.
        if (resolve)
            resolveClass(c); // если необходима проверка на ошибку - проводим её.
        return c;
    }

    private static String makeClassName(Path path) throws IOException {
        path = path.toAbsolutePath().toRealPath();
        Path relPath = Paths.get(PATH_OF_TASKS).relativize(path);
        String className = relPath.toString().replaceAll("[\\/\\\\]", ".");
        if (className.toLowerCase().endsWith(DOT_CLASS))
            className = className.substring(0, className.length() - DOT_CLASS.length());

        return className;
    }

    private static void updateTaskList(Map<String, Task> tasks)
            throws IOException {
        Files.walkFileTree(Paths.get(PATH_OF_TASKS), new SimpleFileVisitor<Path>() { // используем проход по дереву файлов, которые находятся внутри адреса PATH_OF_TASKS
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                if (path.toString().endsWith(DOT_CLASS)) { // для каждого файла проверяем, оканчивается ли он на .class
                    String className = makeClassName(path); // формируем имя класса (этот метод уже был рассмотрен)
                    Task task = tasks.get(className); // проверяем наличие такого класса в списке классов.
                    if (task == null || task.getModifiedTime() != attrs.lastModifiedTime().toMillis()) { // добавлена проверка на равенство времени создания файла
                        try {
                            if (task != null) { // если такой Task существует -
                                loader = new SimpleAutoLoader(PATH_OF_TASKS);// значит, для добавления новой версии нам необходимо создать новый экземпляр ClassLoader
                            }
                            Class taskClass = loader.loadClass(className, true); // подгружаем класс при помощи ClassLoader; получаем тип этого класса.
                            Task newTask = (Task)taskClass.getDeclaredConstructor().newInstance(); // создаём новый экземпляр класса
                            newTask.setModifiedTime(attrs.lastModifiedTime().toMillis()); // обновляем время создания
                            tasks.put(className, newTask); // добавляем в хранилище информацию о новом хранимом классе
                            System.out.println((task == null ? "Добавлен" : "Обновлён") + " класс " + className); // для наглядности выводим информацию об обновлении/добавлении плагина
                        } catch (ClassNotFoundException |
                                IllegalAccessException |
                                InstantiationException |
                                NoSuchMethodException |
                                InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) {
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void main(String[] args) throws Exception {
        Map<String, Task> tasks = new LinkedHashMap<>(); // создаём хранилище для классов.

        while(true) {
            System.out.println("Проверка классов и запуск задач: " +
                    String.format("%1$tI:%1$tM:%1$tS.%1$tN", new Date())); // показываем время начала работы приложения

            updateTaskList(tasks); // выполняет две задачи - поиск новых классов и их последующая загрузка

            SecureRandom random = new SecureRandom();
            byte[] data = new byte[1000];
            random.nextBytes(data);

            for (Map.Entry<String, Task> task : tasks.entrySet())
                System.out.println("     " + task.getValue().process(data)); // запускаем все хранимые в словаре (Map) классы

            Thread.sleep(5_000);
        }
    }
}
