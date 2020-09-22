package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class AvlTree <K extends Comparable, V> {
    private AvlLeaf<K, V> root;

    /*
    1. find most equal leaf
    2. check that is equal
     */
    public V find(K key) {
        if (root == null)
            return null;
        AvlLeaf found = root.find(key);
        return found.getKey().compareTo(key) == 0 ? (V) found.getValue() : null;
    }

    /*
    1. find most equal leaf
    2. add new leaf as child
     */
    public void add(AvlLeaf<K, V> leaf) throws TreeException {
        if (root == null) { // если корень равен нолю (его попросту нет) - то мы создаём корень
            root = leaf;
        } else {
           root.find(leaf.getKey()).add(leaf); // а начале ищем наиболее близкий ко входящему листу по сзначению лист, а затем добавляем его - либо в правые потомки, либо в левые
        }
        leaf.foundTurns();

        //this.renovateRoot();
    }


    public void delete(K key) throws TreeException {
        internaldDelete(key);
    }

    public AvlLeaf<K, V> internaldDelete(K key) throws TreeException {
        if (root == null) { // если корень нулевой - значит искать нечего. Выбрасываем ошибку.
            throw new TreeException("Key not exists");
        }
        AvlLeaf found = root.find(key);
        int cmp = found.getKey().compareTo(key);// проверяем, что найденное значение не просто максимально похожее на запрашиваемое, а является именно тем самым, которое мы запросили
        if (cmp != 0) { // если всё-таки значение не то - побрасываем ошибку, что такого значение в дереве нет
            throw new TreeException("Key not exists");
        }
        if (found.getParent() == null) { // если родитель у данного листа отсутствует - значит, это корень. С ним мы работает по особенному.
            if (found.getRight() != null) { // вопрос - имеется ли у это корня правый потомок?
                root = found.getRight(); // если да, то теперь именно он становится корнем. С собой он притянет всё своё поддерево
                root.cutRoot(); // уничтожаем ссылку на родителя
                if (found.getLeft() != null) { // если левый потомок старого не нулевой
                    add(found.getLeft()); // то он у нас остался в подвешенном состоянии. Соответственно, его надо просто добавить в дерево на общих основаниях.
                }
            } else if (found.getLeft() != null) { // в обратном случае - если правый потомок оказался нулевой - мы просто добавляем левый потомок
                root = found.getLeft();
            } else { // если потомков нет вообще - обнуляем корень
                root = null;
            }
        } else {
            found.delete(); // если мы имеем дело не с корнем - начинаем перебирать все листы по очереди по принципу "больше (направо) - меньше (налево)".
        }
        found.foundTurns();
//        if (root!= null) {
//            this.renovateBalance();
//            this.renovateRoot();
//        }
        return found;
    }

    /*
    1. find all leafs from a to z
    2. work with consumer with them.
     */
    public void process(Consumer<AvlLeaf<K, V>> consumer) { // перебор значений с входящим Consumer
        if (root != null) { // если корень не нулевой - то инициируем проход по всем значениям у данного листа (корня).
            root.process(consumer);
        }
    }
}
