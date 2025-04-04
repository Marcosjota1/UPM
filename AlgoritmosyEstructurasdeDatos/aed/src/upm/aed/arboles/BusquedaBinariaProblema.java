package upm.aed.arboles;

import java.util.ArrayList;
import java.util.List;

public class BusquedaBinariaProblema<E> implements AbstractProblem<E> {
	public List<E> data;
	public int begin;
	public int end;
	public E elem;
	
	public BusquedaBinariaProblema(List<E> data,E elem) {
		super();
		this.data = data;
		this.begin = 1;
		this.end = data.size();
		this.elem = elem;
	}
	public BusquedaBinariaProblema(List<E> data, int begin, int end,E elem) {
		super();
		this.data = data;
		this.begin = begin;
		this.end = end;
		this.elem = elem;
	}
	@Override
	public boolean isSmall() {
		// TODO Auto-generated method stub
		return begin == end;
	}
	
	@Override
	public List<AbstractProblem<E>> divide() {
		// TODO Auto-generated method stub
		List<AbstractProblem<E>> lista = new ArrayList<AbstractProblem<E>>();
		if (begin==end+1)
		{
			lista.add(new BusquedaBinariaProblema<E>(data,begin,begin,this.elem));
			lista.add(new BusquedaBinariaProblema<E>(data,end,end,this.elem));
		}   
		else
		{
			int medio = (end-begin+1)/2;
			BusquedaBinariaProblema<E> left = new BusquedaBinariaProblema<E>(data,begin,medio,this.elem);
			BusquedaBinariaProblema<E> right = new BusquedaBinariaProblema<E>(data,medio+1, end,this.elem);
			lista.add(left);
			lista.add(right);
		}
		return lista;
	}
	public List<E> getData() {
		return data;
	}
	public void setData(List<E> data) {
		this.data = data;
	}
	public int getBegin() {
		return begin;
	}
	public E getBeginElem() {
		return data.get(getBegin());
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public E getElem() {
		return elem;
	}
	public void setElem(E elem) {
		this.elem = elem;
	}

}
