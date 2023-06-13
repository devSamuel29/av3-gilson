package arvore;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Node<T extends Comparable<T>> {
    private T data;

    private String hashedValue;

    private Node<T> left;

    private Node<T> right;

    private int height;

    public Node(T data) {
        this.data = data;
        this.hashedValue = encryptSha1((String) data);
        this.left = null;
        this.right = null;
    }

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.hashedValue = encryptSha1((String) data);
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T element) {
        this.data = element;
    }

    public String getHashedValue() {
        return hashedValue;
    }

    public void setHashedValue(String hashedValue) {
        this.hashedValue = hashedValue;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String encryptSha1(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] messageDigest = md.digest(data.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            StringBuilder hashtext = new StringBuilder(no.toString(16));

            while (hashtext.length() < 32) {
                hashtext.insert(0, "0");
            }

            return hashtext.toString();
        }

        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
