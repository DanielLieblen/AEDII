package Structure;
/*
Autor Daniel Moura
*/
public class Dado_Hash<K, V>{
    K key;
    V value;
    Dado_Hash<K, V> next;

    public Dado_Hash(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Dado_Hash<K, V> getNext() {
        return next;
    }

    public void setNext(Dado_Hash<K, V> next) {
        this.next = next;
    }
}
