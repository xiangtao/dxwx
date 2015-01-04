package my.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {
    
    /**
     * 按eachSize大小拆分List
     * @param src 带拆分的List
     * @param eachSize 每份大小
     * @return 
     */
    public static List<List> splitList(List src,int eachSize){
	if(src == null || src.size() ==0 || eachSize==0) return Collections.EMPTY_LIST;
	int srcSize = src.size();
	int sumCount = srcSize/eachSize;
	sumCount = (srcSize%eachSize!=0)?sumCount+1:sumCount; 
	List<List> rtnList = new ArrayList<List>(sumCount);
	for (int i = 0; i < sumCount; i++) {
	    	int startIdx = i*eachSize;
		int endNum = (i+1)*eachSize;
		int endIdx = endNum>srcSize?srcSize:endNum;
		rtnList.add(src.subList(startIdx, endIdx));
	}
	return rtnList;
    }
    
    public static void main(String[] args) {
	List src = new ArrayList();
	/*for (int i = 0; i < 100; i++) {
	    src.add(i);
	}*/
	List<List> rtn = splitList(src, 1000);
	for (List one : rtn) {
	    System.out.println(one);
	}
	
	System.out.println(rtn.size() + "--" +  rtn);
    }

}
