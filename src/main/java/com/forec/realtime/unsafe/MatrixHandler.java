package com.forec.realtime.unsafe;

/**
 * Not thread safe
 * @author Ettore Majorana
 *
 */

@SuppressWarnings("restriction")
public class MatrixHandler {

	protected final static long UNSET = -1l;
	private final static long LONG_SIZE_IN_BYTES = 8;
	private static final IllegalArgumentException ILLEGAL_ARGUMENT_EXCEPTION = 
			new IllegalArgumentException("Row in input is greater than the maximum allowed");
	private final long[] indexes;
	private final int rows;
	private final int columns;
	private int currShift;
	
	protected MatrixHandler(int rows, int columns){
		this.indexes = new long[rows];
		this.rows = rows;
		this.columns = columns;
		this.currShift = 0;
		for (int i = 0; i < rows; i++) {
			long startIndex = UnsafeHolder.getUnsafe().allocateMemory(LONG_SIZE_IN_BYTES * (long)columns);
			indexes[i] = startIndex;
		}
	}

	protected void set(int row, int column, long value){
		if(row >= rows){
			throw ILLEGAL_ARGUMENT_EXCEPTION;
		}
		if(column >= columns){
			throw ILLEGAL_ARGUMENT_EXCEPTION;
		}
		row = shiftedRow(row);
		long offset = calcOffset(row,column);
		System.out.println(String.format("set [%d,%d] = %d",row,column,value));
		UnsafeHolder.getUnsafe().putLong(offset, value);
	}
	
	protected long get(int row, int column){
		row = shiftedRow(row);
		long offset = calcOffset(row, column);
		long value = UnsafeHolder.getUnsafe().getLong(offset);
		System.out.println(String.format("get [%d,%d] = %d",row,column,value));
		return value;
	}

	private int shiftedRow(int row) {
		return (row + currShift) % (rows);
	}
	

	public void destroy(){
		for(int i = 0; i < rows; i++){
			UnsafeHolder.getUnsafe().freeMemory(indexes[i]);
		}
	}
	
	private long calcOffset(int row, int column) {
		return indexes[row] + column*LONG_SIZE_IN_BYTES;
	}
	
	protected void shift() {
		currShift = (currShift + 1) % (indexes.length-1);  
	}
	
	protected void copy(int dest, int source){
		for(int i = 0; i < columns; i++){
			long value = get(source, i);
			set(dest, i, value);
		}
	}

}
