package arvore;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Node<T extends Comparable<T>> {
    private T data;

    private String hashedValue;

    private Node<T> left;

    private Node<T> right;

    public int factorBalance;

    public Node(T data) {
        this.data = data;
        this.hashedValue = encryptSha1((String) data);
        this.left = null;
        this.right = null;
        this.factorBalance = 0;
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

    public int getFactorBalance() {
        return factorBalance;
    }

    public void setFactorBalance(int factorBalance) {
        this.factorBalance = factorBalance;
    }

    public Node<T> getLeftChild() {
        return left;
    }

    public void setLeftChild(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRightChild() {
        return right;
    }

    public void setRightChild(Node<T> right) {
        this.right = right;
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
