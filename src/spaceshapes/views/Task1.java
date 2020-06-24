package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class Task1 implements TreeModel {
	protected List<TreeModelListener> _listenerList = new ArrayList<TreeModelListener>();
	private ShapeModel _model;
	public Task1(ShapeModel model) {
		_model = model;
	}

	@Override
	public void addTreeModelListener(TreeModelListener listener) {
		_listenerList.add(listener);
	}	
	
	@Override
	public void removeTreeModelListener(TreeModelListener listener) {
		_listenerList.remove(listener);
	}

	@Override
	public Object getChild(Object parent, int index) {
		if (parent instanceof CarrierShape ) {
			CarrierShape container = (CarrierShape) parent;
			if (container.shapeCount() <= index) {
				return null;
			}
			return container.shapeAt(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object shape) {
		if (shape instanceof CarrierShape) {
			CarrierShape container = (CarrierShape) shape;
			return container.shapeCount();
		}
		return 0;
	}

	@Override
	public int getIndexOfChild(Object parent, Object simpleShape) {
		if (parent instanceof CarrierShape) {
			CarrierShape container = (CarrierShape) parent;
			for (int i = 0; i < container.shapeCount(); i++) {
				if (container.shapeAt(i) == simpleShape) {
					return 1;
				}
			}
		}
		return -1;
	}

	@Override
	public Object getRoot() {
		return _model.root();
	}

	@Override
	public boolean isLeaf(Object shape) {
		if (shape instanceof Shape) {
			if (shape instanceof CarrierShape) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void valueForPathChanged(TreePath arg0, Object arg1) {

	}

}
