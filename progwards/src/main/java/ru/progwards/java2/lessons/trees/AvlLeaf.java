package ru.progwards.java2.lessons.trees;

import java.util.function.Consumer;

public class AvlLeaf<K extends Comparable, V> {
    private K key;
    private V value;
    private AvlLeaf parent;
    private AvlLeaf left;
    private AvlLeaf right;
    private int balance = 0;
    private int depthRight = 0;
    private int depthLeft = 0;

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public int getBalance() {
        return balance;
    }

    public AvlLeaf getParent() {
        return parent;
    }

    public AvlLeaf getLeft() {
        return left;
    }

    public AvlLeaf getRight() {
        return right;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getDepthRight() {
        return depthRight;
    }

    public void setDepthRight(int depthRight) {
        this.depthRight = depthRight;
    }

    public int getDepthLeft() {
        return depthLeft;
    }

    public void setDepthLeft(int depthLeft) {
        this.depthLeft = depthLeft;
    }

    public void setParent(AvlLeaf parent) {
        this.parent = parent;
    }

    public void setLeft(AvlLeaf left) {
        this.left = left;
    }

    public void setRight(AvlLeaf right) {
        this.right = right;
    }

    public AvlLeaf(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /*
    find and return the most equal leaf
     */
    public AvlLeaf<K, V> find(K key) {
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

    /*
    add the leaf as the right (if bigger) or as the left (if smaller) child
     */
    void add(AvlLeaf<K, V> leaf) throws TreeException {
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
            checkBalancePlus(leaf);
    }

    /*
    prepare value and add it.
     */
    public void add(K key, V value) throws TreeException {
        add(new AvlLeaf<>(key, value));
    }

    /*
    check the balance from the added leaf to every parent leaf.
     */
    private void checkBalancePlus(AvlLeaf<K, V> leaf) {
        this.renovate(leaf);
        if (leaf.getParent() != null) {
            if (leaf.getParent().getRight() == leaf) {
                leaf.getParent().setDepthRight(Math.max(leaf.getDepthRight(), leaf.getDepthLeft()) + 1);
            }
            if (leaf.getParent().getLeft() == leaf)  {
                leaf.getParent().setDepthLeft(Math.max(leaf.getDepthLeft(), leaf.getDepthRight()) + 1);
            }
            checkBalancePlus(leaf.getParent());
        }
    }

    /*
    renovate balance by debth values
     */
    private void renovate(AvlLeaf<K, V> leaf) {
        leaf.setBalance(leaf.getDepthRight() - leaf.getDepthLeft());
    }

    /*
    return leafs from the most left to the most right
     */
    public void process(Consumer<AvlLeaf<K, V>> consumer) {
        if (left != null) {// работает рекурсия. Перебор надо сделать слева направо. Соответственно, обращаемся к самому левому корню
            left.process(consumer); // если левое значение есть - рекурсивно обращаемся к нему
        }
        consumer.accept(this); // если левое значение отсутствует - значит, проделываем работу с данным значением
        if (right != null) // если правое значение не равно нолю
            right.process(consumer); // тогда переходим туда. Но  - важно! - только после левого. Именно благодаря этому порядку значения будут выводиться по возрастающей.
    }

    /*
    delete this leaf (with renovating all links on parent, right and left children)
     */
    void delete() throws TreeException {
        if (parent.right == this) { // проверка на то, что значение является правым потомком родителя
            parent.setDepthRight(parent.getDepthRight() - 1);
            parent.right = this.right; // родителю присваиваем значение правого потомка
            if (right != null) {// а если он не нулевой (если нулевой - просто сохранится ссылка на null)
                right.parent = parent; // тогда указываем ему на нового родителя
            }
            if (left != null) {// также надо позаботиться о левом потомке. Если он не нулевой
                parent.find(left.key).add(left); // просто добавляем его в таблицу
            }
        } else { // если же значение не является правым потомком - оно является левым потомком
            parent.setDepthLeft(parent.getDepthLeft() - 1);
            parent.left = this.left; // родителю присваиваем значение левого потомка
            if (left != null) {// если значение не нулевое
                left.parent = parent; // то  указываем левому потомку на нового родителя
            }
            if (right != null) { // если правый потомок не нулевой
                parent.find(right.key).add(right); // то присоединяем его к дереву на общих основаниях
            }
        }
        checkBalancePlus(parent);
    }

    /*
    delete the parent link
     */
    public void cutRoot() {
        this.parent = null;
    }

    /*
    found the necessity of turns in this leaf or its parents
     */
    public void foundTurns() {
        if (this.getBalance() == 2 || this.getBalance() == -2) {
            turnIt(this.getBalance());
        } else if (this.getParent() != null) {
            this.getParent().foundTurns();
        }
    }

    private void turnIt(int balance) {
        System.out.println("Need turn.");
        if (balance == 2) {
            rightTurn();
        } else {
            leftTurn();
        }
    }

    private void rightTurn() {
    }

    private void leftTurn() {

    }

    private void smallTurn() {

    }
}
