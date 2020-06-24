package spaceshapes.views;

import javax.swing.event.TreeModelEvent;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelListener;

public class Task2 extends Task1 implements ShapeModelListener {
	
	private ShapeModel _adaptee;

	public Task2(ShapeModel model) {
		super(model);
		_adaptee = model;
	}

	@Override
	public void update(ShapeModelEvent event) {
		Shape operand = event.operand();
		int index = event.index();
		CarrierShape parent = event.parent();
		ShapeModelEvent.EventType eventType = event.eventType();

		if(eventType == ShapeModelEvent.EventType.ShapeAdded) {
			fireTreeInsert(parent, operand, index);
		}
		else if(eventType == ShapeModelEvent.EventType.ShapeRemoved) {
			fireTreeRemove(parent, operand, index);
		}
	}

	public void fireTreeInsert(CarrierShape parent, Shape child, int index) {
		Object[] children = {child};
		int[] indicies = {index};
		Object[] path = getPath(parent);
		TreeModelEvent e = new TreeModelEvent(_adaptee, path, indicies, children);
		for (int i = 0; i < _listenerList.size(); i++) {
			_listenerList.get(i).treeNodesInserted(e);
		}
	}

	public void fireTreeRemove(CarrierShape parent, Shape child, int index) {
		Object[] children = {child};
		int[] indicies = {index};
		Object[] path = getPath(parent);
		TreeModelEvent e = new TreeModelEvent(_adaptee, path, indicies, children);	
		for (int i = 0; i < _listenerList.size(); i++) {
			_listenerList.get(i).treeNodesRemoved(e);
		}
	}	

	public Object[] getPath(CarrierShape parent) {
		Object[] treePath = parent.path().toArray();
		return treePath;
	}
}
