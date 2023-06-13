package arvore;

public class AvlTree<T extends Comparable<T>> {
    public Node<T> root;

    public AvlTree() {
        root = null;
    }

    public void insert(T data) {
        root = insert(data, root);
    }

    private Node<T> insert(T data, Node<T> node) {
        if (node == null) {
            return new Node<>(data);
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insert(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(insert(data, node.getRight()));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public void delete(T data) {
        root = delete(data, root);
    }

    private Node<T> delete(T data, Node<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(delete(data, node.getLeft()));
        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(delete(data, node.getRight()));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setData(getMax(node.getLeft()));
            node.setLeft(delete(node.getData(), node.getLeft()));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    public T getMax() {
        if (root == null) {
            return null;
        }
        return getMax(root);
    }

    private T getMax(Node<T> node) {
        if (node.getRight() != null) {
            return getMax(node.getRight());
        }
        return node.getData();
    }

    public T getMin() {
        if (root == null) {
            return null;
        }
        return getMin(root);
    }

    private T getMin(Node<T> node) {
        if (node.getLeft() != null) {
            return getMin(node.getLeft());
        }
        return node.getData();
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T data) {
        return contains(root, data);
    }

    public boolean contains(Node<T> node, T data) {
        if (node == null) {
            return false;
        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult < 0) {
            return contains(node.getLeft(), data);
        } else if (compareResult > 0) {
            return contains(node.getRight(), data);
        } else {
            return true;
        }
    }

    private Node<T> applyRotation(Node<T> node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> leftNode = node.getLeft();
        Node<T> centerNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> rightNode = node.getRight();
        Node<T> centerNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private void updateHeight(Node<T> node) {
        int maxHeight = Math.max(
                height(node.getLeft()),
                height(node.getRight())
        );
        node.setHeight(maxHeight + 1);
    }

    private int balance(Node<T> node) {
        return node != null ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    private int height(Node<T> node) {
        return node != null ? node.getHeight() : 0;
    }

    public String getHashedValues() {
        return getHashedValues(root);
    }

    private String getHashedValues(Node<T> node) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(getHashedValues(node.getLeft()));
        sb.append(node.getHashedValue());
        sb.append(" ");
        sb.append(getHashedValues(node.getRight()));

        return sb.toString();
    }

    @Override
    public String toString() {
        return inOrder(root);
    }

    private String inOrder(Node<T> node) {
        StringBuilder sb = new StringBuilder();
        if (node != null) {
            sb.append(inOrder(node.getLeft()));
            sb.append(node.getData());
            sb.append(" ");
            sb.append(inOrder(node.getRight()));
        }
        return sb.toString();
    }
}
