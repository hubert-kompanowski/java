import java.util.ArrayList;

public class SparseDataFrame  extends DataFrame{

    protected ArrayList<ArrayList<COOValue>> spraseDF;

    private int cols_number;
    private int size;
    private Object hiding;

    public SparseDataFrame(String[] names, String[] types, Object hide ) {
        super(names, types);
        cols_number = all_names.length;
        hiding = hide;
    }

    public SparseDataFrame (DataFrame df, Object hide){

        super(df.all_names, df.all_types);
        hiding = hide;
        size = df.list.get(0).size();
        cols_number = all_names.length;
        spraseDF = new ArrayList<>();

        for(int col_it=0; col_it<all_names.length; col_it++) {
            spraseDF.add(new ArrayList<>());
            for (int row_it = 0; row_it < size; row_it++) {

                if(!(df.list.get(col_it).get(row_it)).equals(hiding)){
                    spraseDF.get(col_it).add(new COOValue(row_it, df.list.get(col_it).get(row_it)));
                }
            }
        }
    }


    DataFrame toDense(){
        DataFrame df = new DataFrame(all_names, all_types);

        for(int col_it=0; col_it<all_names.length; col_it++){
            for(int row_it=0; row_it<size; row_it++){
                df.get(all_names[col_it]).add(hiding);
            }
        }
        for(int i=0 ; i<spraseDF.size(); i++){
            for(int j=0; j<spraseDF.get(i).size(); j++){
                df.get(all_names[i]).set(spraseDF.get(i).get(j).index,spraseDF.get(i).get(j).content);
            }
        }
        return df;
    }
}
