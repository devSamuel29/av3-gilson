package arvore;

public class AvlTree<T extends Comparable<T>> {
    private Node<T> root;

    public AvlTree() {
        root = null;
    }

    public void insert(T data) {
        root = insert(data, root);
    }

    private Node<T> insert(T data, Node<T> node) {
        if(node == null) {
            return new Node<>(data);
        }

        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeftChild() == null) {
                node.setLeftChild(new Node<>(data));
                node.factorBalance++;
            } else {
                node.setLeftChild(insert(data, node.getLeftChild()));
                if (node.getLeftChild().getFactorBalance() != 0) {
                    node.factorBalance++;
                }
            }
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRightChild() == null) {
                node.setRightChild(new Node<>(data));
                node.factorBalance--;
            } else {
                node.setRightChild(insert(data, node.getRightChild()));
                if (node.getRightChild().getFactorBalance() != 0) {
                    node.factorBalance--;
                }
            }
        }

        return balance(node);
    }

    private Node<T> balance(Node<T> node) {
        if (node.getFactorBalance() >= 2) {
            if (node.getLeftChild().getFactorBalance() >= 0) {
                node = simpleRotateRight(node);
            } else {
                node = doubleRotateRight(node);
            }
        } else if (node.getFactorBalance() <= -2) {
            if (node.getRightChild().getFactorBalance() <= 0) {
                node = simpleRotateLeft(node);
            } else {
                node = doubleRotateLeft(node);
            }
        }
        return node;
    }

    private Node<T> simpleRotateLeft(Node<T> node) {
        Node<T> aux = node.getRightChild();
        if (aux.getLeftChild() != null) {
            node.setRightChild(aux.getLeftChild());
            aux.setLeftChild(node);
        } else {
            aux.setLeftChild(node);
            node.setRightChild(null);
        }
        updateBalanceFactor(aux);
        updateBalanceFactor(node);
        return aux;
    }

    private Node<T> doubleRotateLeft(Node<T> node) {
        node = simpleRotateRight(node.getRightChild());
        return simpleRotateLeft(node);
    }

    private Node<T> simpleRotateRight(Node<T> node) {
        Node<T> aux = node.getLeftChild();
        if (aux.getRightChild() != null) {
            node.setLeftChild(aux.getRightChild());
            aux.setRightChild(node);
        } else {
            aux.setRightChild(node);
            node.setLeftChild(null);
        }
        updateBalanceFactor(aux);
        updateBalanceFactor(node);
        return aux;
    }

    private Node<T> doubleRotateRight(Node<T> node) {
        node = simpleRotateLeft(node.getLeftChild());
        return simpleRotateRight(node);
    }

    private void updateBalanceFactor(Node<T> node) {
        node.setFactorBalance(leftChildHeight(node) - rightChildHeight(node));
    }

    private int height(Node<T> node) {
        if (node != null) {
            int leftFactorBalance = 0;
            int rightFactorBalance = 0;
            if (node.getLeftChild() != null && node.getRightChild() != null) {
                leftFactorBalance = height(node.getLeftChild(), 1, 1);
                rightFactorBalance = height(node.getRightChild(), 1, 1);
            } else if (node.getLeftChild() != null) {
                leftFactorBalance = height(node.getLeftChild(), 1, 0);
            } else if (node.getRightChild() != null) {
                rightFactorBalance = height(node.getRightChild(), 0, 1);
            }
            return Math.max(leftFactorBalance, rightFactorBalance);
        }
        return -1;
    }

    private int height(Node<T> node, int leftChildFactorBalance, int rightChildFactorBalance) {
        if (node.getLeftChild() != null) {
            leftChildFactorBalance = height(node.getLeftChild(), ++leftChildFactorBalance, rightChildFactorBalance);
        }
        if (node.getRightChild() != null) {
            rightChildFactorBalance = height(node.getRightChild(), leftChildFactorBalance, ++rightChildFactorBalance);
        }
        return Math.max(leftChildFactorBalance, rightChildFactorBalance);
    }

    private int leftChildHeight(Node<T> node) {
        return height(node.getLeftChild()) + 1;
    }

    private int rightChildHeight(Node<T> node) {
        return height(node.getRightChild()) + 1;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node<T> node){
        if(node.getLeftChild() != null && node.getRightChild() != null){
            node.setHashedValue(node.encryptSha1(node.getHashedValue() + toString(node.getLeftChild()) + toString(node.getRightChild())));
        }
        else if(node.getLeftChild() != null && node.getRightChild() == null){
            node.setHashedValue(node.encryptSha1(node.getHashedValue() + toString(node.getLeftChild())));
        }
        else if(node.getLeftChild() == null && node.getRightChild() != null){
            node.setHashedValue(node.encryptSha1(node.getHashedValue() + toString(node.getRightChild())));
        }
        return node.getHashedValue();
    }
}
