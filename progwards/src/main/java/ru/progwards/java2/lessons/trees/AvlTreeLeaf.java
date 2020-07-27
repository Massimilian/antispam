package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class AvlTreeLeaf<K extends Comparable, V> {
    private K key;
    private V value;
    private AvlTreeLeaf parent;
    private AvlTreeLeaf left;
    private AvlTreeLeaf right;
    int balance = 0;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public AvlTreeLeaf getParent() {
        return parent;
    }


    public AvlTreeLeaf getLeft() {
        return left;
    }


    public AvlTreeLeaf getRight() {
        return right;
    }

    public void setParent(AvlTreeLeaf parent) {
        this.parent = parent;
    }

    public void setLeft(AvlTreeLeaf left) {
        this.left = left;
    }

    public void setRight(AvlTreeLeaf right) {
        this.right = right;
    }

    public AvlTreeLeaf(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public AvlTreeLeaf<K, V> find(K key) {
        int cmp = key.compareTo(this.key); // сравниваем входящий ключ с тем, который представлен в данном листе
        if (cmp > 0) { // если входящий ключ меньше
            if (right != null) {// то проверяем наличие правого потомка
                return right.find(key); // и, если он есть - рекурсивно обращаемся к правому потомку
            } else {
                return this; // иначе - возвращаем родителя (за неимением правого потомка), как наиболее близкое значение
            }
        }
        if (cmp < 0) {// если входящий ключ больше
            if (left != null) {//значит, мы имеем дело с левой стороной. Проверяем на ноль
                return left.find(key); // если там есть значение - рекурсивно обращаемся к потомку слева
            } else {
                return this; // иначе - возвращаем данное значение, как наиболее близкое
            }
        }
        return this; // если запрашиваемый ключ равен представленному в данном объекте, значит - мы нашли совпадающий ключ либо наиболее близкий
    }

    void add(AvlTreeLeaf<K, V> leaf) throws TreeException {
        int cmp = leaf.key.compareTo(key); // проверяем на равенство значением ключа и входящее значение ключа
        if (cmp == 0) { // если они равны - то пробрасываем ошибку
            throw new TreeException("Key not exists");
        }
        if (cmp > 0) {
            right = leaf; // если входящее значение больше - засовываем входящий лист в правый потомок
            leaf.parent = this; // и делаем значение данного листа родителем для входящего значения
        } else {
            left = leaf; // если входящее значение меньше - засовываем входящий лист в левый потомок
            leaf.parent = this; // делаем значение данного листа родителем для входящего значения
        }
        checkBalance(leaf);
    }

    void delete() throws TreeException {
        if (parent.right == this) { // проверка на то, что значение является правым потомком родителя
            parent.right = right; // родителя присваиваем значение правого потомка
            if (right != null) {// а если он не нулевой (если нулевой - просто сохранится ссылка на null)
                right.parent = parent; // тогда указываем ему на нового родителя
            }
            if (left != null) {// также надо позаботиться о левом потомке. Если он не нулевой
                parent.find(left.key).add(left); // просто добавляем его в таблицу
            }
        } else { // если же значение не является правым потомком - оно является левым потомком
            parent.left = left; // родителю присваиваем значение левого потомка
            if (left != null) {// если значение не нулевое
                left.parent = parent; // то  указываем левому потомку на нового родителя
            }
            if (right != null) { // если правый потомок не нулевой
                parent.find(right.key).add(right); // то присоединяем его к дереву на общих основаниях
            }
        }
    }

    public String toString() {
        return "(" + key + "," + value + ")";
    }

    public void process(Consumer<AvlTreeLeaf<K, V>> consumer) {
        if (left != null) {// работает рекурсия. Перебор надо сделать слева направо. Соответственно, обращаемся к самому левому корню
            left.process(consumer); // если левое значение есть - рекурсивно обращаемся к нему
        }
        consumer.accept(this); // если левое значение отсутствует - значит, проделываем работу с данным значением
        if (right != null) // если правое значение не равно нолю
            right.process(consumer); // тогда переходим туда. Но  - важно! - только после левого. Именно благодаря этому порядку значения будут выводиться по возрастающей.
    }

    private void checkBalance(AvlTreeLeaf temp) {
        boolean hasNotAnotherValue = true;
        if (temp.getParent() != null) {
            if (temp.getParent().getLeft() != null && temp.getParent().getLeft().equals(temp)) {
                temp = temp.getParent();
                temp.balance--;
                hasNotAnotherValue = temp.getRight() == null;
            } else {
                temp = temp.getParent();
                temp.balance++;
                hasNotAnotherValue = temp.getLeft() == null;
            }
        }
        if (hasNotAnotherValue) {
            while (temp.getParent() != null) {
                if (temp.getParent().getLeft() != null && temp.getParent().getLeft().equals(temp)) {
                    temp = temp.getParent();
                    temp.balance--;
                    if (temp.balance == -2) {
                        balance(temp, true);
                    }
                } else {
                    temp = temp.getParent();
                    temp.balance++;
                    if (temp.balance == 2) {
                        balance(temp, false);
                    }
                }
            }
        }
    }

    private void balance(AvlTreeLeaf temp, boolean type) {
        AvlTreeLeaf leaf = type ? temp.getLeft() : temp.getRight();
        if (findTreeHeight(leaf.getRight()) < findTreeHeight(leaf.getLeft())) {
            smallRotation(temp, leaf, type ? leaf.getRight() : leaf.getLeft());
        } else {
            AvlTreeLeaf selected = type ? leaf.getRight() : leaf.getLeft();
            bigRotation(temp, leaf, selected, type ? selected.getRight() : selected.getLeft(), type ? selected.getLeft() : selected.getRight());
        }
    }

    private void smallRotation(AvlTreeLeaf top, AvlTreeLeaf around, AvlTreeLeaf jumper) {
        System.out.println(top.getValue());
        System.out.println(around.getValue());
        System.out.println(jumper.getValue());
        AvlTreeLeaf temp = jumper;
        around.setRight(top);
        top.setParent(around);
        top.setLeft(jumper);
        jumper.setParent(top);
        System.out.println("Small rotation.");
    }

    private void bigRotation(AvlTreeLeaf secondTop, AvlTreeLeaf firstTop, AvlTreeLeaf around, AvlTreeLeaf secondJumper, AvlTreeLeaf firstJumper) {
        smallRotation(firstTop, around, firstJumper);
        smallRotation(secondTop, around, secondJumper);
        System.out.println("Big rotattion.");
    }

    private int findTreeHeight(AvlTreeLeaf avl) {
        int result = 0;
        if (avl != null) {
            int depth = 1;
            while (avl.getLeft() != null) {
                avl = avl.getLeft();
                depth++;
            }
            result = depth;
            while (depth != 1) {
                if (avl.getLeft() != null) {
                    avl = avl.getLeft();
                    depth++;
                } else if (avl.getRight() != null) {
                    avl = avl.getRight();
                    depth++;
                } else {
                    while (avl.getParent() != null && avl.getRight() == null) {
                        avl = avl.getParent();
                        depth--;
                    }
                }
                result = Math.max(depth, result);
            }
        }
        return result;
    }
}
