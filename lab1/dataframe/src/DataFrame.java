import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DataFrame {


    protected ArrayList<ArrayList> list = new ArrayList<ArrayList>();
    protected String[] all_names;
    protected String[] all_types;


    DataFrame(String[] new_names, String[] new_types){

        all_names = new_names.clone();
        all_types = new_types.clone();
        ArrayList al = new ArrayList();

        for (int i =0; i<new_names.length; ++i){
            list.add(new ArrayList());
        }
    }


    DataFrame(String fileName, String[] types, boolean isHeader){

        try(BufferedReader br = Files.newBufferedReader(Paths.get(fileName))){
            String line = br.readLine();
            if(isHeader && line != null){
                all_names = line.split(",");
                line = br.readLine();
            }
            for(int j=0; j<all_names.length; j++){
                list.add(new ArrayList());
            }
            while(line!=null){
                String[] data = line.split(",");
                for(int j=0; j<all_names.length; j++){
                    list.get(j).add(data[j]);
                }
                line = br.readLine();
            }
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }


    public int size(){
        return list.get(1).size();
    }


    public ArrayList get(String colname){
        for(int i = 0; i< all_names.length; i++){
            if(colname.equals(all_names[i])){
                return list.get(i);
            }
        }
        return new ArrayList();
    }


    public DataFrame get(String[] cols, boolean copy){
        String[] ntype = new String[cols.length];
        for(int i=0; i<cols.length; i++){
            for(int j = 0; j< all_names.length; j++){
                if(cols[i].equals(all_names[j])){
                    ntype[i] = all_types[j];
                }
            }
        }
        DataFrame dframe = new DataFrame(cols, ntype);

        for(int i=0; i<cols.length; i++){
            for(int j = 0; j< all_names.length; j++){
                if(cols[i].equals(all_names[j])){
                    if(copy) {
                        dframe.list.add((ArrayList)list.get(j).clone()); // deep copy
                    } else {
                        dframe.list.add(list.get(j));   // shallow copy
                    }
                }
            }
        }
        return dframe;
    }


    public DataFrame iloc(int i){
        DataFrame df = new DataFrame(new String[]{all_names[i]},new String[] {all_types[i]});
        df.list.add((ArrayList)list.get(i).clone());
        return df;
    }


    public DataFrame iloc(int from, int to){

        String[] n = new String[to-from+1];
        String[] t = new String[to-from+1];
        int i=0;
        for(int it=from; it<=to; it++,i++){
            n[i] = all_names[it];
            t[i] = all_types[it];
        }
        DataFrame df = new DataFrame(n,t);
        for(int it=from; it<=to; it++){
            df.list.add((ArrayList)list.get(it).clone());
        }
        return df;
    }
}
