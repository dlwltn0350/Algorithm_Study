package Programmers.def;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**

@author jisoo
@since 2022. 9. 24.
@see
@performance
@difficulty 
@category #
@note 테케 절반 통과
*/
public class 블라인드5번 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer tokens;

	public static void main(String[] args) throws IOException {
//		String[] commands = {"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};
		
		String[] commands = {"UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"};
		solution(commands);
	}
	
	public static List<String> solution(String[] commands) {
        String[] answer = {};
        String[][] map = new String[51][51];
        HashMap<String, ArrayList<String>> hashmap = new HashMap<>();
        List<String> result = new ArrayList<>();
        
        for(int i=1; i<51; i++) {
        	for(int j=1; j<51; j++) {
        		map[i][j] = "EMPTY";
        	}
        }
        
        for(int i=0; i<commands.length; i++) {
        	String[] temp = commands[i].split(" ");
        	switch (temp[0]) {
			case "UPDATE":
				if(temp.length>3) {
					if(hashmap.containsKey(temp[1]+" "+temp[2])) {
						for(String str : hashmap.get(temp[1]+" "+temp[2])) {
							String[] sp = str.split(" ");
							map[Integer.parseInt(sp[0])][Integer.parseInt(sp[1])] = temp[3];
							//합병된 값들까지 변경해줌
						}
					}
					
					map[Integer.parseInt(temp[1])][Integer.parseInt(temp[2])] = temp[3];
				}else {
					for(int r=1; r<51; r++) {
						for(int c=1; c<51; c++) {
							if(map[r][c].equals(temp[1])) {
								map[r][c] = temp[2];
							}
						}
					}
				}
				
				break;
			case "MERGE": //표 병합
				
				if(hashmap.containsKey(temp[1]+" "+temp[2])) {
					hashmap.get(temp[1]+" "+temp[2]).add(temp[3]+" "+temp[4]);
					for(String str : hashmap.get(temp[1]+" "+temp[2])) {
						String[] spl = str.split(" ");
						map[Integer.parseInt(spl[0])][Integer.parseInt(spl[1])]
								= map[Integer.parseInt(temp[1])][Integer.parseInt(temp[2])];
					}
					//병합 연결을 위한 hashMap
				}else{
					ArrayList<String> list = new ArrayList<String>();
					list.add(temp[3]+" "+temp[4]);
					hashmap.put(temp[1]+" "+temp[2],list);
					map[Integer.parseInt(temp[3])][Integer.parseInt(temp[4])]
							= map[Integer.parseInt(temp[1])][Integer.parseInt(temp[2])];
				}
				
				//////////////////////////////
				
				if(hashmap.containsKey(temp[3]+" "+temp[4])) {
					hashmap.get(temp[3]+" "+temp[4]).add(temp[1]+" "+temp[2]);
					
					for(String str : hashmap.get(temp[3]+" "+temp[4])) {
						String[] spl = str.split(" ");
						map[Integer.parseInt(spl[0])][Integer.parseInt(spl[1])]
								= map[Integer.parseInt(temp[1])][Integer.parseInt(temp[2])];
					}
					
				}else {
					ArrayList<String> list2 = new ArrayList<String>();
					list2.add(temp[1]+" "+temp[2]);
					hashmap.put(temp[3]+" "+temp[4],list2);
					map[Integer.parseInt(temp[3])][Integer.parseInt(temp[4])]
							= map[Integer.parseInt(temp[1])][Integer.parseInt(temp[2])];
				}
				
				break;
			case "UNMERGE":
				ArrayList<String> remove = new ArrayList<String>();
				out: for(String keys : hashmap.keySet()) {
					for(String str : hashmap.get(keys)) {//arraylist
						if(str.equals(temp[1]+" "+temp[2])) {
//							System.out.println(keys);
							hashmap.get(keys).remove(str);
							//원래의 값을 temp[1]+" "+temp[2]가 가짐..
							
							for(String str2 : hashmap.get(keys)) {
								String[] spl = str2.split(" ");
								map[Integer.parseInt(spl[0])][Integer.parseInt(spl[1])] = "EMPTY";
 							}
							remove.add(keys);
							continue out;
						}
					}
				}
				
				for(int k=0; k<remove.size(); k++) {
					hashmap.remove(remove.get(k));
					String[] spl = remove.get(k).split(" ");
					map[Integer.parseInt(spl[0])][Integer.parseInt(spl[1])] = "EMPTY";
				}
				hashmap.remove(temp[1]+" "+temp[2]);
				break;
			case "PRINT":
				result.add(map[Integer.parseInt(temp[1])][Integer.parseInt(temp[2])]);
				break;

			}
        }
        
        System.out.println(result);
        return result;
    }
}