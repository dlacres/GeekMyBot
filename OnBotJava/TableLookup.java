package org.firstinspires.ftc.teamcode;
//
// Lookup the output value of an input.
//
// The first column of the table, [0][], is the input variable we want to lookup in the table.
// The second column of the table,[1][], is the output variable we lookup.
// If the input is part way between two points, use the input ratio between the points to determin the output.
//
public class TableLookup {
  private int i;
  private double num,den;
//  private double table_1[][];
  double table_1[][]={
      {0.0, 0.0},
      {1.0, 2.0},
      {3.0, 3.0},
      {6.0, 6.0},
      {10.0, 10.0}};
  
  public TableLookup(double table[][]){
    this.table_1 = table;
  }
  public TableLookup(){
    
  }
  public double calc(double in){
      double out = 0.0;
      final int x=0;
      final int y=1;

      // Find the x region we are in
      for (i=0; i<(table_1[0].length-1); ++i){
          if (table_1[i][0]<=in && table_1[i+1][0]>in) {
            break;
          }
      } 
      num=table_1[i+1][y]-table_1[i][y];
      den=table_1[i+1][x]-table_1[i][x];
      out=(num*in)/den + table_1[i][y] - (num*table_1[i][x])/den;

      return(out);
  }
}
