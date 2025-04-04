/**
 * The game of life
 *
 * @author Marc Oliveras <admin@oligalma.com> 
 * @link http://www.oligalma.com
 * @copyright 2021 Oligalma
 * @license GPL License v3
 */

package the_game_of_life;

public class Vivarium implements Cloneable
{
	// Attributes.
	private boolean[][] area;
	private boolean ball;
	private int rowsNumber, colsNumber;

	// Constructor.
	public Vivarium(int rowsNumber, int colsNumber, boolean ball) {
		this.rowsNumber = rowsNumber;
		this.colsNumber = colsNumber;
		this.ball = ball;
		this.area = new boolean[rowsNumber][colsNumber];
	}

	// Method to calculate next generations
	public void nextGen() {
		int cell = 0, row, col;

		// Copy area matrix.
		boolean[][] areaCopy = this.copyArea();

		for (row = 0; row < rowsNumber; row++) {
			for (col = 0; col < colsNumber; col++) {
				if (ball) // Ball mode...
				{
					if (row == 0 && col == 0) {
						if (areaCopy[rowsNumber - 1][colsNumber - 1])
							cell++;
						if (areaCopy[rowsNumber - 1][col])
							cell++;
						if (areaCopy[rowsNumber - 1][col + 1])
							cell++;
						if (areaCopy[row][colsNumber - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][colsNumber - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					} else if (row == 0 && col == colsNumber - 1) {
						if (areaCopy[rowsNumber - 1][col - 1])
							cell++;
						if (areaCopy[rowsNumber - 1][col])
							cell++;
						if (areaCopy[rowsNumber - 1][0])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][0])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][0])
							cell++;
					} else if (row == rowsNumber - 1 && col == 0) {
						if (areaCopy[row - 1][colsNumber - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][colsNumber - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[0][colsNumber - 1])
							cell++;
						if (areaCopy[0][col])
							cell++;
						if (areaCopy[0][col + 1])
							cell++;
					} else if (row == rowsNumber - 1 && col == colsNumber - 1) {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][0])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][0])
							cell++;
						if (areaCopy[0][col - 1])
							cell++;
						if (areaCopy[0][col])
							cell++;
						if (areaCopy[0][0])
							cell++;
					} else if (row == 0 && col != colsNumber - 1 && col != 0) {
						if (areaCopy[rowsNumber - 1][col - 1])
							cell++;
						if (areaCopy[rowsNumber - 1][col])
							cell++;
						if (areaCopy[rowsNumber - 1][col + 1])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					} else if (row == rowsNumber - 1 && col != colsNumber - 1 && col != 0) {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[0][col - 1])
							cell++;
						if (areaCopy[0][col])
							cell++;
						if (areaCopy[0][col + 1])
							cell++;
					} else if (row != rowsNumber - 1 && row != 0 && col == 0) {
						if (areaCopy[row - 1][colsNumber - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][colsNumber - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][colsNumber - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					} else if (row != rowsNumber - 1 && row != 0 && col == colsNumber - 1) {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][0])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][0])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][0])
							cell++;
					} else {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					}
				} else // Not ball mode...
				{
					if (row == 0 && col == 0) {
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					} else if (row == 0 && col == colsNumber - 1) {
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
					} else if (row == rowsNumber - 1 && col == 0) {
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
					} else if (row == rowsNumber - 1 && col == colsNumber - 1) {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
					} else if (row == 0 && col != colsNumber - 1 && col != 0) {
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					} else if (row == rowsNumber - 1 && col != colsNumber - 1 && col != 0) {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
					} else if (row != rowsNumber - 1 && row != 0 && col == 0) {
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					} else if (row != rowsNumber - 1 && row != 0 && col == colsNumber - 1) {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
					} else {
						if (areaCopy[row - 1][col - 1])
							cell++;
						if (areaCopy[row - 1][col])
							cell++;
						if (areaCopy[row - 1][col + 1])
							cell++;
						if (areaCopy[row][col - 1])
							cell++;
						if (areaCopy[row][col + 1])
							cell++;
						if (areaCopy[row + 1][col - 1])
							cell++;
						if (areaCopy[row + 1][col])
							cell++;
						if (areaCopy[row + 1][col + 1])
							cell++;
					}
				}
				if (areaCopy[row][col]) {
					if (cell == 0 || cell == 1 || cell >= 4)
						area[row][col] = false;
				} else {
					if (cell == 3)
						area[row][col] = true;
				}
				cell = 0;
			}
		}
	}
	
	// Method to calculate the stationary generation.
	public int getStationary()
	{	
		Vivarium v = (Vivarium)this.clone();
		int stationary = 0;		
				
		for (int i = 0; i < 1000; i++)
		{	
			boolean[][] areaCopy2 = v.copyArea();
			
			v.nextGen();
			
			// Check if the current matrix and the last are equal.
			boolean equal = true;
			for (int row2 = 0; row2 < rowsNumber; row2++)
			{
				for (int col2 = 0; col2 < colsNumber; col2++)
				{
					if (areaCopy2[row2][col2] != v.area[row2][col2]) {
						equal = false;
						break;
					}
				}
			}
			
			if (!equal)
				stationary++;			
		}
						
		if(stationary < 1000)
			return stationary;
		else
			return -1;
	}
	
	public boolean[][] getArea()
	{
		return area;
	}
	
	public void setArea(boolean[][] area)
	{
		this.area = area;
	}

	public boolean getBall()
	{
		return this.ball;
	}
	
	public void setBall(boolean ball)
	{
		this.ball = ball;
	}

	public void clearArea()
	{
		for (int i = 0; i < area.length; i++)
			for (int j = 0; j < area[0].length; j++)
				area[i][j] = false;	
	}
	
	private boolean[][] copyArea()
	{
		boolean[][] areaCopy = new boolean[rowsNumber][colsNumber];
		
		for (int row = 0; row < rowsNumber; row++)
			for (int col = 0; col < colsNumber; col++)
				areaCopy[row][col] = area[row][col];
		
		return areaCopy;
	}
	
	protected Object clone()
	{
		Vivarium v = new Vivarium(rowsNumber, colsNumber, ball);
		
		v.area = this.copyArea();
		
		return v;
	}
}
