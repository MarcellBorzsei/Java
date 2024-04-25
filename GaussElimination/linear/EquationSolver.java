package linear;

import linear.algebra.GaussianElimination;

class EquationSolver
{ 

    //2,1,-1,8 -3,-1,2,-11 -2,1,2,-3

    static double[][] stringsToDoubles(String args[])
    {
        double[][] numbers = null;
        for(int i = 0; i < args.length; i++)
        {
            String parts[] = args[i].split(",", 0);
            if(i == 0)
            {
                numbers = new double[args.length][(parts.length)];
            }
            for(int j = 0; j < parts.length; j++)
            {
                numbers[i][j] = Double.parseDouble(parts[j]);
            }
        }

        return numbers;

    }

    public static void main(String[] args)
    {
        double[][] numbers = stringsToDoubles(args);
        
        GaussianElimination matrix = new GaussianElimination(numbers.length, numbers[0].length, numbers);
        
        matrix.print();
        System.out.println();
        matrix.rowEchelonForm();
        matrix.print();
        System.out.println();
        matrix.backSubstitution();
        matrix.print();


    }
    
}