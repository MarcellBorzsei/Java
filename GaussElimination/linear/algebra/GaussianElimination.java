package linear.algebra;

public class GaussianElimination
{

    //1. feladat

    private double[][] matrix;
    private int rows;
    private int cols;


    public GaussianElimination(int rows, int cols, double[][] matrix)
    {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new double[rows][cols];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                this.matrix[i][j] = matrix[i][j];
            }
        }
    }

    

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public double[][] getMatrix() { return matrix; }

    private void checkMatrixDimensions(double[][] matrix)
    {
        if(matrix.length != rows || matrix[0].length != cols)
        {
            throw new IllegalArgumentException("The new matrix's number of rows or columns doesn't match");
        }
    }

    public void setMatrix(double[][] newMatrix)
    {
        checkMatrixDimensions(matrix);
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                this.matrix[i][j] = newMatrix[i][j];
            }
        }
        
    }

    //2. feladat

    private int argMax(int rowInd, int colInd)
    {
        int maxInd = rowInd;
        double maxErtek = Math.abs(matrix[(rowInd)][colInd]);
        for(int i = (rowInd+1); i < rows; i++)
        {
            if(Math.abs(matrix[i][colInd]) > maxErtek)
            {
                maxInd = i;
                maxErtek = Math.abs(matrix[i][colInd]);
            }
        }

        return maxInd;
    }

    private void swapRows(int rowOne, int rowTwo)
    {
        for(int j = 0; j < cols; j++)
        {
            double rowTemp = matrix[rowOne][j];
            matrix[rowOne][j] = matrix[rowTwo][j];
            matrix[rowTwo][j] = rowTemp;
        }
    }

    // h = mulRow
    // k = colIndex
    // i = addRow

    private void multiplyAndAddRow(int addRow, int mulRow, int colIndex)
    { 

        double f = matrix[addRow][colIndex] / matrix[mulRow][colIndex];
        matrix[addRow][colIndex] = 0;
        for(int j = (colIndex + 1); j < cols; j++)
        {
            matrix[addRow][j] = matrix[addRow][j] - matrix[mulRow][j] * f;
        }
    }

    private void multiplyRow(int rowInd, int colInd)
    {
        double divisor = matrix[rowInd][colInd];
        for(int j = 0; j < cols; j++)
        {
            matrix[rowInd][j] /= divisor;
        }
    }

    public void rowEchelonForm()
    {
        int h = 0;
        int k = 0;
        
        while(h < rows && k < cols)
        {
            int i_max = argMax(h, k);

            if(matrix[i_max][k] == 0)
            {
                k += 1;
            }
            
            else
            {
                swapRows(h, i_max);
                for(int i = (h + 1); i < rows; i++)
                {
                    multiplyAndAddRow(i, h, k);
                }

                multiplyRow(h, k);
                h++;
                k++;            
            }

        }
    }


    //3. feladat

    public void backSubstitution()
    {
        for(int i = (rows - 1); i >= 0; i--)
        {
            if(matrix[i][i] == 0)
            {
                throw new IllegalArgumentException("The diagonal element was zero, there is no solution for this problem.");
            }
            else
            {
                for(int j = i-1; j >= 0; j--)
                {
                    multiplyAndAddRow(j, i, i);   
                }
            }
        }
    }

    public void print()
    {
        char unknows[] = {'x', 'y', 'z', 'w', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v'};
        
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                if(j == cols - 1)
                {
                    System.out.print("=");
                    if(matrix[i][j] < 0)
                    {
                        System.out.print(matrix[i][j]);
                    }
                    else
                    {
                        System.out.print(matrix[i][j]);
                    }
                }
                else
                {
                    if(matrix[i][j] < 0)
                    {
                        System.out.print(matrix[i][j] + "" + unknows[j]);
                    }
                    else
                    {
                        System.out.print("+" + matrix[i][j] + unknows[j]);
                    }
                }
                
                
            }

            System.out.println();
        }
    }    


    
}