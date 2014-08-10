package de.black.core.tools.dua.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNode<T> implements Iterable<TreeNode<T>> {

	private List<TreeNode<T>> children;
	private T containedObject;
	private TreeNode<T> parent;
	
	public TreeNode(T object) {
		this.containedObject = object;
		this.children = new ArrayList<TreeNode<T>>(5);
		this.parent = null;
	}
	
	public TreeNode<T> setParent(TreeNode<T> t) {
		this.parent = t;
		return this;
	}
	
	public TreeNode<T> getParent() {
		return this.parent;
	}
	
	public T getObject() {
		return containedObject;
	}
	
	public <C> C getObjectAs(Class<C> c) {
		return c.cast(containedObject);
	}
	
	public void setObject(T newObj) {
		this.containedObject = newObj;
	}
	
	public void addChild(TreeNode<T> t) {
		t.setParent(this);
		this.children.add(t);
	}
	
	public void removeChild(TreeNode<T> t) {
		this.children.remove(t);
	}
	
	public boolean isLeaf() {
		return this.children.isEmpty();
	}
	
	public boolean isRoot() {
		return this.parent == null;
	}
	
	@Override
	public Iterator<TreeNode<T>> iterator() {
		return children.iterator();
	}
	
	public TreeNode<T> getFirstChild() {
		return children.get(0);
	}
	
	public List<TreeNode<T>> getChildren() {
		return children;
	}
	
}
