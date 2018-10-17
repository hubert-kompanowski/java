public class Main {

    public static void main(String[] args) {

//        String[] cols_name = new String[]{"kol1","kol2","kol3"};
//        String[] cols_type = new String[]{"int","double","MyCustomType"};
//        DataFrame df = new DataFrame(cols_name, cols_type);
//
//       // System.out.print(df.size());
//
//        System.out.print(df.iloc(0,1).get("kol543"));
//
//
//        SparseDataFrame sdf = new SparseDataFrame(df,"0");


        DataFrame df = new DataFrame("/home/hubert/Pulpit/3 semestr/JAVA/java/lab1/dataframe/src/plik.", new String[]{"int","int","int"}, true);
        df.all_types = new String[] {"i", "i", "i"};

        System.out.print(df.get("a")+"\n\n");

        SparseDataFrame sdf = new SparseDataFrame(df, "0.0");

        System.out.print(sdf.toDense().get("a"));


    }
}
